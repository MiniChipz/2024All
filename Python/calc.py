def regne():
  stykke = input("Hva skal regnes? \n")
  if "+" in stykke:
    ligmed = stykke.split("+")
    print(int(ligmed[0]) + int(ligmed[1]))
  elif "*" in stykke:
    ligmed = stykke.split("*")
    print(int(ligmed[0]) * int(ligmed[1]))
  elif "-" in stykke:
    ligmed = stykke.split("-")
    print(int(ligmed[0]) - int(ligmed[1]))
  elif "/" in stykke:
    ligmed = stykke.split("/")
    print(int(ligmed[0]) // int(ligmed[1]))
  else:
    print("Dette er ikke et gyldigt regnestykke")
    regne()

regne()