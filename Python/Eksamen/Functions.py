import PySimpleGUI as sg  # type: ignore
import csv
import os
from datetime import datetime
from Forsendelse_script_v2 import layout_startmenu, layout_opret_forsendelse, layout_find_forsendelse, layout_login

csv_fil = "C:/Users/Malte/Documents/Programmering/Python/Eksamen/Ansat csv.csv"
folder_path = "C:/Users/Malte/Desktop/Testeren"

def login_event(values, window):
    username = values["-USERNAME-"]
    password = values["-PASSWORD-"]
    with open(csv_fil, 'r') as file:
        csv_reader = csv.reader(file)
        header = next(csv_reader)
        for row in csv_reader:
            if row[2] == username and row[3] == password:
                ansat = [row[0], row[1], row[2], row[3], row[4], row[5]]
                window.close()
                sg.popup(f"Velkommen {ansat[0]} {ansat[1]}.")
                return sg.Window("Hovedemenu", layout_startmenu, size=(400, 200), finalize=True), ansat
        sg.popup("Forkert brugernavn eller adgangskode.")
        window['-PASSWORD-'].update('')
    return window, None

def column_reader(file_path, column: int):
    with open(file_path, 'r') as file:
        csv_reader = csv.reader(file)
        data = list(csv_reader)
        transposed_data = list(map(list, zip(*data)))
        return transposed_data[column]

def get_files(path_folder):
    try:
        files = os.listdir(path_folder)
    except FileNotFoundError:
        files = []
    return files

def forsendelse_event(window, path_folder):
    window.close()
    files = get_files(path_folder)
    window = sg.Window("Find forsendelser", layout_find_forsendelse, size=(400, 400), finalize=True)
    window["-FIL_LISTE-"].update(files)
    return window

def opret_event(values, window, ansat):
    window.close()
    dato = datetime.now().strftime("%Y-%m-%d")
    by = values["-BY-"]
    weight = values["-WEIGHT-"]
    højde = values["-HEIGHT-"]
    brede = values["-BREDE-"]
    dybde = values["-DYBDE-"]
    information = values["-INFORMATION-"]
    modtager_adresse = values["-ADRESSE-"]
    afsender_adresse = ansat[5]
    fil_navn = f"{by}_{modtager_adresse}_{dato}.txt"
    fil_path = os.path.join(folder_path, fil_navn)
    with open(fil_path, "w") as file:
        file.write(f"by,modtager_adresse,afsender_adresse,højde,brede,dybde,yderligere_information,ansat_id,dato\n{by},{modtager_adresse},{afsender_adresse},{weight}{højde},{brede},{dybde},{information},{ansat[4]},{dato}")
    sg.popup(f"Du har oprettet en forsendelse med disse informationer:\nBy: {by}\nModtager adresse: {modtager_adresse}\nAfsender adresse: {afsender_adresse}\nVægt: {weight}\nHøjde: {højde}, Brede: {brede}, Dybde: {dybde}\nYderligere informationer: {information}\nAnsat id: {ansat[4]}\nDato: {dato}")
    return window