import customtkinter as ctk

# Initialize the main window
root = ctk.CTk()
root.geometry("650x500")
ctk.set_appearance_mode("System")
ctk.set_default_color_theme("dark-blue")

listen = [
    "The first ",
    "The second ",
    "The third ",
    "The fourth ",
    "The fifth "
]

frame = ctk.CTkFrame(root, width=1000, height=1000)
frame.place(relx=0.5, rely=0.5, anchor="center")
# Create a label with horizontal text
rowen = 0
colu = 0
for i in listen:
    if colu < 4:
        label = ctk.CTkLabel(frame, text=i)
        label.grid(column=colu, row=rowen)
        colu = colu + 1
    else:
        rowen = rowen + 1
        label = ctk.CTkLabel(frame, text=i)
        label.grid(column=colu, row=rowen)

# Start the main event loop
root.mainloop()
