import sqlite3

# Connect to the database (or create it if it doesn't exist)
connection = sqlite3.connect('Users.db')
cursor = connection.cursor()

# Create the Users table if it doesn't exist
cursor.execute("""
    CREATE TABLE IF NOT EXISTS Users (
        username TEXT,
        password TEXT,
        name TEXT,
        surname TEXT,
        ID INTEGER
    )
""")

# Data to insert into the Users table
temp_list = [
    ("MC", "1234", "Mini", "Chips", 2937),
    ("JD", "a8E3f1Bg", "John", "Doe", 4829),
    ("AL", "s7H2j5Kl", "Alice", "Liddell", 1938),
    ("BW", "m9J8r6Zn", "Bruce", "Wayne", 5762),
    ("CK", "q2L9p7Df", "Clark", "Kent", 8154),
    ("DP", "t3K4m5Rx", "Diana", "Prince", 2495),
    ("PB", "u6W1y8Xl", "Peter", "Parker", 3697),
    ("TW", "v7Q2z3Dn", "Tony", "Stark", 7304),
    ("NL", "w4T8m9Xp", "Natasha", "Romanoff", 6521),
    ("SL", "y5L7x2Mj", "Stephen", "Strange", 1846),
    ("WW", "z9F3q6Kt", "Wanda", "Maximoff", 9073)
]

# Insert data into the Users table
cursor.executemany("INSERT INTO Users (username, password, name, surname, ID) VALUES (?, ?, ?, ?, ?)", temp_list)

# Commit the transaction and close the connection
connection.commit()
connection.close()
