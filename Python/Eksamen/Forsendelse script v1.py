#  __                                      __                       ______                                                                                                              
# |  \                                    |  \                     /      \                                                                                                             
# | $$       ______  __     __   ______  _| $$_           ______  |  $$$$$$\                                                                                                            
# | $$      |      \|  \   /  \ /      \|   $$ \         |      \ | $$_  \$$                                                                                                            
# | $$       \$$$$$$\\$$\ /  $$|  $$$$$$\\$$$$$$          \$$$$$$\| $$ \                                                                                                                
# | $$      /      $$ \$$\  $$ | $$    $$ | $$ __        /      $$| $$$$                                                                                                                
# | $$_____|  $$$$$$$  \$$ $$  | $$$$$$$$ | $$|  \      |  $$$$$$$| $$                                                                                                                  
# | $$     \\$$    $$   \$$$    \$$     \  \$$  $$       \$$    $$| $$                                                                                                                  
#  \$$$$$$$$ \$$$$$$$    \$      \$$$$$$$   \$$$$         \$$$$$$$ \$$                                                                                                                  
                                                                                                                                                                                      
                                                                                                                                                                                      
                                                                                                                                                                                      
#     _____                                __                  __       __                  __                                            __       __            __    __               
#    |     \                              |  \                |  \     /  \                |  \                                          |  \     /  \          |  \  |  \              
#     \$$$$$  ______    _______   ______  | $$____            | $$\   /  $$  ______    ____| $$  _______         ______    ______        | $$\   /  $$  ______  | $$ _| $$_     ______  
#       | $$ |      \  /       \ /      \ | $$    \           | $$$\ /  $$$ |      \  /      $$ /       \       /      \  /      \       | $$$\ /  $$$ |      \ | $$|   $$ \   /      \ 
#  __   | $$  \$$$$$$\|  $$$$$$$|  $$$$$$\| $$$$$$$\          | $$$$\  $$$$  \$$$$$$\|  $$$$$$$|  $$$$$$$      |  $$$$$$\|  $$$$$$\      | $$$$\  $$$$  \$$$$$$\| $$ \$$$$$$  |  $$$$$$\
# |  \  | $$ /      $$| $$      | $$  | $$| $$  | $$          | $$\$$ $$ $$ /      $$| $$  | $$ \$$    \       | $$  | $$| $$  | $$      | $$\$$ $$ $$ /      $$| $$  | $$ __ | $$    $$
# | $$__| $$|  $$$$$$$| $$_____ | $$__/ $$| $$__/ $$ __       | $$ \$$$| $$|  $$$$$$$| $$__| $$ _\$$$$$$\      | $$__/ $$| $$__| $$      | $$ \$$$| $$|  $$$$$$$| $$  | $$|  \| $$$$$$$$
#  \$$    $$ \$$    $$ \$$     \ \$$    $$| $$    $$|  \      | $$  \$ | $$ \$$    $$ \$$    $$|       $$       \$$    $$ \$$    $$      | $$  \$ | $$ \$$    $$| $$   \$$  $$ \$$     \
#   \$$$$$$   \$$$$$$$  \$$$$$$$  \$$$$$$  \$$$$$$$ | $$       \$$      \$$  \$$$$$$$  \$$$$$$$ \$$$$$$$         \$$$$$$  _\$$$$$$$       \$$      \$$  \$$$$$$$ \$$    \$$$$   \$$$$$$$
#                                                    \$                                                                  |  \__| $$                                                     
#                                                                                                                         \$$    $$                                                     
#                                                                                                                          \$$$$$$                                                      

# Ingen copyright tak




# Her importere vi vores modules
import PySimpleGUI as sg  # type: ignore
import csv
import os
from datetime import datetime

# Her importere vi vores csv fil og vores folder vi kommer til at lagre vores filer i.
csv_fil = "C:/Users/Malte/Documents/Programmering/Python/Eksamen/Ansat csv.csv"
folder_path = "C:/Users/Malte/Desktop/Testeren"

# Her laver vi vores login function hvor vi bruger vores csv fil til at se om brugernavnet og koden er rigtig
def login_event(values, window):
    # Her sætter vi username og password variablerne til inputet fra login vinduet
    username = values["-USERNAME-"]
    password = values["-PASSWORD-"]
    # Her åbner vi csv filen i r = 'read' mode. 
    with open(csv_fil, 'r', newline='') as file:
        csv_reader = csv.reader(file)
        header = next(csv_reader)
        login_successful = False
        for row in csv_reader:
            # Her tjekker vi om et af brugernavnene og koderne i vores csv passer til det som blev skrevet ind.
            if row[2] == username and row[3] == password:
                # Lukker vinduet for at åbne et nyt igen.
                window.close()
                # Laver en popup som skriver man successfuldt blev logget ind
                sg.popup("Du er blevet logget ind.")
                login_successful = True
                return sg.Window("Hovedemenu", layout_startmenu, size=(400, 200), finalize=True)
        if not login_successful:
            sg.popup("Forkert brugernavn eller adgangskode.")
            window['-PASSWORD-'].update('')
    return window

# Funktionen er til at skaffe filerne fra vores folder.
def get_files(path_folder):
    try:
        files = os.listdir(path_folder)
    except FileNotFoundError:
        files = []
    return files

# Den her funktion laver selve vinduet hvor den viser file exploren
def forsendelse_event(window, path_folder):
    window.close()
    files = get_files(path_folder)
    window = sg.Window("Find forsendelser", layout_find_forsendelse, size=(400, 400), finalize=True)
    window["-FIL_LISTE-"].update(files)
    return window

# Funktion til oprette vinduet.
def opret_event(values, window):
    window.close()
    # Sætter vores dato variable i åååå-mm-dd
    dato = datetime.now().strftime("%Y-%m-%d")
    by = values["-BY-"]
    adresse = values["-ADRESSE-"]
    # Sætter filnavnet til By_Adresse_Dato
    fil_navn = f"{by}_{adresse}_{dato}.txt"
    fil_path = os.path.join(folder_path, fil_navn)
    with open(fil_path, "w") as file:
        # Skriver ind i filen i csv format
        file.write(f"by,adresse,dato\n{by},{adresse},{dato}")
        # Laver popup vinduet som skriver man har lavet filen
    sg.popup(f"Du har oprettet en forsendelse: \n{fil_navn}")
    return window

# Alle layouts er vinduernes layouts
layout_login = [
    [sg.Text("Username:")],
    [sg.Input(key="-USERNAME-")],
    [sg.Text("Password:")],
    [sg.Input(key="-PASSWORD-", password_char='*')],
    [sg.Button('Login', bind_return_key=True)]
]

layout_startmenu = [
    [sg.Column([[sg.Button("Opret forsendelse")]], justification='center')],
    [sg.Column([[sg.Button("Find forsendelse")]], justification='center')]
]

layout_opret_forsendelse = [
    [sg.Text("By")],
    [sg.Combo(["Viborg", "Vejle", "Kolding", "Esbjerg", "Ribe", "Tønder", "Sønderborg", "Aabenraa", "Haderslev", "Herning", "Silkeborg", "Horsens", "Skanderborg", "Aarhus", "Randers"], key="-BY-")],
    [sg.Text("Adresse")],
    [sg.Input(key="-ADRESSE-")],
    [sg.Button("Opret", bind_return_key=True)],
]

layout_find_forsendelse = [
    [sg.Text("Forsendelser:")],
    [sg.Listbox(values=[], size=(60, 20), key="-FIL_LISTE-", enable_events=True)],
]

# Create the login window
window = sg.Window("Login", layout_login)

while True:
    # Her gemmer den alle værdier og events som den opfanger i window.read() i event og values variablerne. event og values er naming convention
    event, values = window.read()
    # Stopper programmet når brugeren lukker vinduet
    if event == sg.WINDOW_CLOSED:
        break
    # Køre funktionen til at tjekke om login er rigtigt når de trykker på login knappen
    if event == "Login":
        window = login_event(values, window)
    # Køre funktionen til at lave vinduet til at oprette en forsendelse
    elif event == "Opret forsendelse":
        window.close()
        window = sg.Window("Opret forsendelse", layout_opret_forsendelse, size=(400, 200), finalize=True)
    # Opretter forsendelsen
    elif event == "Opret":
        window = opret_event(values, window)
    # Åbner vinduet til at se forsendelser
    elif event == "Find forsendelse":
        window = forsendelse_event(window, folder_path)
    # Funktionen til at åbne filerne i vores folder
    elif event == "-FIL_LISTE-":
        selected_file = values["-FIL_LISTE-"]
        if selected_file:
            selected_path = os.path.join(folder_path, selected_file[0])
            if os.path.isfile(selected_path):
                os.startfile(selected_path)

window.close()