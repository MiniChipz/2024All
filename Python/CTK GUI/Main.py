import customtkinter as ctk
from PIL import Image
import sqlite3

root = ctk.CTk()
root.geometry("650x500")
root.title("Login - Grande Chips Aps")
ctk.set_appearance_mode("System")
ctk.set_default_color_theme("dark-blue")


def login():
    username = username_entry.get().upper()
    password = password_entry.get()

    # Open connection
    connection = sqlite3.connect('Users.db')
    cursor = connection.cursor()

    try:
        # Execute query
        cursor.execute("SELECT * FROM Users WHERE username=? AND password=?", (username, password))  # username=? == <tabel navn>=<Pladsholder>, (variable)
        user = cursor.fetchone()

        if user:
            frame.destroy()
            db_username, db_password = user[0], user[1]
            main_menu(user)
        else:
            login_error.configure(text="Username or password incorrect")
            password_entry.delete(0, "end")

    except sqlite3.Error as e:
        print(f"An error occurred: {e}")

    finally:
        # Close connection
        connection.close()

def main_menu(user: list) -> None:
    frame = ctk.CTkFrame(root, width=250, height=200)
    frame.place(relx=0.5, rely=0.5, anchor="center")

    label = ctk.CTkLabel(frame, text=f"Welcome, {user[2]} {user[3]}")
    label.pack()

    button = ctk.CTkButton(frame, text="Calculator", command=calculator())
    button.pack()

def calculator():
    frame = ctk.CTkFrame(root, width=250, height=200)


if __name__ == '__main__':
    frame = ctk.CTkFrame(root, width=250, height=300)
    frame.place(relx=0.5, rely=0.5, anchor="center")

    username_entry = ctk.CTkEntry(frame, width=120, border_width=1, placeholder_text="Username")
    username_entry.pack(padx=30, pady=100)

    password_entry = ctk.CTkEntry(frame, width=120, border_width=1, show="*", placeholder_text="Password")
    # password_entry.pack(pady=40, padx=30)
    password_entry.place(relx=0.5, rely=0.6, anchor="center")

    login_error = ctk.CTkLabel(frame, text="", height=5, text_color="#ff0000")
    login_error.pack()

    img = Image.open("Padlock-symbol.webp")
    button = ctk.CTkButton(frame, text="Login", image=ctk.CTkImage(img), corner_radius=30, command=login)
    button.place(relx=0.5, rely=0.9, anchor="center")


root.mainloop()
