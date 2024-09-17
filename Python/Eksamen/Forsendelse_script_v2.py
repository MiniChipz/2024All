# Her importere vi vores modules.
import PySimpleGUI as sg
import csv
import os
from datetime import datetime
from random import randrange
# Her sætter vi vores fil variabler.
csv_fil = "C:/Users/Malte/Documents/Programmering/Python/Eksamen/Ansat csv.csv"
base_folder_path = "C:/Users/Malte/Desktop/Testeren/"
# Den her funktion laver popup vinduet som viser hvilke filer er blevet lavet.
def rute_done(files_created):
    if files_created:
        file_names = "\n".join(files_created)
        sg.popup(f"Følgende forsendelser blev oprettet:\n{file_names}")

def Route_Name_Create():
    return randrange(1000, 9999)

route_name = Route_Name_Create()

def login_event(values):
    username = values["-USERNAME-"]
    password = values["-PASSWORD-"]
    with open(csv_fil, 'r') as file:
        csv_reader = csv.reader(file)
        header = next(csv_reader)
        for row in csv_reader:
            if row[2] == username and row[3] == password:
                ansat = [row[0], row[1], row[2], row[3], row[4], row[5]]
                sg.popup(f"Velkommen {ansat[0]} {ansat[1]}.")
                return ansat
        sg.popup("Forkert brugernavn eller adgangskode.")
    return None

def get_files(path_folder):
    try:
        return os.listdir(path_folder)
    except FileNotFoundError:
        return []

def update_file_list(window, folder):
    files = get_files(folder)
    window["-FIL_LISTE-"].update(files)

def opret_event(values, ansat, route_folder, files_created):
    dato = datetime.now().strftime("%Y-%m-%d")
    by = values["-BY-"]
    weight = values["-WEIGHT-"]
    højde = values["-HEIGHT-"]
    brede = values["-BREDE-"]
    dybde = values["-DYBDE-"]
    information = values["-INFORMATION-"] or "Ingen yderligere informationer."
    modtager_adresse = values["-ADRESSE-"]
    afsender_adresse = ansat[5]
    fil_navn = f"{by}_{modtager_adresse}_{dato}.txt"
    fil_path = os.path.join(route_folder, fil_navn)
    with open(fil_path, "w") as file:
        file.write(f"by,modtager_adresse,afsender_adresse,højde,brede,dybde,yderligere_information,ansat_id,dato\n{by},{modtager_adresse},{afsender_adresse},{weight},{højde},{brede},{dybde},{information},{ansat[4]},{dato}")
    files_created.append(fil_navn)
    window['-ADRESSE-'].update('')
    window['-WEIGHT-'].update('')
    window['-HEIGHT-'].update('')
    window['-BREDE-'].update('')
    window['-DYBDE-'].update('')
    window['-INFORMATION-'].update('')

layout_login = [
    [sg.Text("Username:")],
    [sg.Input(key="-USERNAME-")],
    [sg.Text("Password:")],
    [sg.Input(key="-PASSWORD-", password_char='*')],
    [sg.Button('Login', bind_return_key=True)]
]

layout_rute_create = [
    [sg.Text("By", size=(10, 1)), sg.Combo(["Viborg", "Vejle", "Kolding", "Esbjerg", "Ribe", "Tønder", "Sønderborg", "Aabenraa", "Haderslev", "Herning", "Silkeborg", "Horsens", "Skanderborg", "Aarhus", "Randers"], key="-BY-", size=(40, 1))],
    [sg.Text("Adresse:", size=(10, 1)), sg.Input(key="-ADRESSE-", size=(40, 1))],
    [sg.Text("Vægt:", size=(10, 1)), sg.Input(key="-WEIGHT-", size=(40, 1))],
    [sg.Text("Højde:", size=(5, 1)), sg.Input(key="-HEIGHT-", size=(8, 1)),
     sg.Text("Brede:", size=(5, 1)), sg.Input(key="-BREDE-", size=(8, 1)),
     sg.Text("Dybde:", size=(5, 1)), sg.Input(key="-DYBDE-", size=(8, 1))],
    [sg.Text("Yderligere informationer:")],
    [sg.Multiline(key="-INFORMATION-", size=(40, 5))],
    [sg.Button("Tilføj forsendelse", size=(12, 1)), sg.Push(), sg.Button("Lav rute", size=(12, 1))]
]

layout_find_forsendelse = [
    [sg.Text("Ruter:")],
    [sg.Listbox(values=[], size=(60, 20), key="-FIL_LISTE-", enable_events=True)],
    [sg.Button("Ny rute"), sg.Button("Back")]
]

# Function to create the initial window (login)
window = sg.Window("Login", layout_login)

ansat = None
current_folder = base_folder_path
route_folder = None
files_created = []

while True:
    event, values = window.read()
    if event == sg.WINDOW_CLOSED:
        break
    if event == "Login":
        ansat = login_event(values)
        if ansat:
            window.close()
            window = sg.Window("Find rute", layout_find_forsendelse, finalize=True)
            update_file_list(window, current_folder)
    elif event == "Ny rute":
        route_name = f"Rute_{Route_Name_Create()}"
        route_folder = os.path.join(base_folder_path, route_name)
        os.makedirs(route_folder, exist_ok=True)
        window.close()
        window = sg.Window(f"Rute {route_name}", layout_rute_create, size=(450, 300), finalize=True)
    elif event == "Tilføj forsendelse":
        if route_folder:
            opret_event(values, ansat, route_folder, files_created)
        else:
            sg.popup("Opret venligst en rute først.")
    elif event == "Opret" and ansat:
        if route_folder:
            opret_event(values, ansat, route_folder, files_created)
        else:
            sg.popup("Opret venligst en rute først.")
    elif event == "-FIL_LISTE-":
        selected_file = values["-FIL_LISTE-"]
        if selected_file:
            selected_path = os.path.join(current_folder, selected_file[0])
            if os.path.isfile(selected_path):
                os.startfile(selected_path)
            elif os.path.isdir(selected_path):
                current_folder = selected_path
                update_file_list(window, current_folder)
    elif event == "Back":
        if os.path.normpath(current_folder) != os.path.normpath(base_folder_path):
            current_folder = os.path.dirname(current_folder)
            update_file_list(window, current_folder)
    elif event == "Lav rute":
        if route_folder:
            window.close()
            rute_done(files_created)
            files_created = []  # Nulstil listen over oprettede filer
        else:
            sg.popup("Opret venligst en rute først.")

window.close()
