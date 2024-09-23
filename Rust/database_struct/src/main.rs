use std::io;
use rusqlite::{params, Connection, Result};
use io::stdin;

fn main() -> Result<()> {
    let connect_db = Connection::open("person_database")?;

    connect_db.execute(
        "CREATE TABLE IF NOT EXISTS person (
        id INTEGER PRIMARY KEY,
        name TEXT NOT NULL,
        age INTEGER NOT NULL
    )",
        params![],
    )?;
    println!("Do you want to read or write? (r/w)");
    let mut input: String = String::new();
    stdin().read_line(&mut input).expect("Failed to read line");

    if input.trim() == "r" {
        let mut stmt = connect_db.prepare("SELECT id, name, age FROM person")?;
        let person_iter = stmt.query_map(params![], |row| {
            Ok(NewPerson {
                id: row.get(0)?,
                name: row.get(1)?,
                age: row.get(2)?,
            })
        })?;

        for person in person_iter {
            println!("{:?}", person?);
        }
    } else if input.trim() == "w" {
        let mut name: String = String::new();
        let mut id: String = String::new();
        let mut age: String = String::new();

        println!("Write the name to add to the database:");
        stdin().read_line(&mut name).expect("Failed to read line");
        println!("Name = {}", name);

        println!("Write the age of the person:");
        stdin().read_line(&mut age).expect("Failed to read line");
        println!("Age = {}", age);

        println!("Write the ID of the person:");
        stdin().read_line(&mut id).expect("Failed to read line");
        println!("ID = {}", id);
        let new_person = NewPerson {
            id: id.trim().parse().expect("Invalid ID"),
            name: name.trim().to_string(),
            age: age.trim().parse().expect("Invalid age"),
        };

        connect_db.execute(
            "INSERT INTO person (id, name, age) VALUES (?1, ?2, ?3)",
            params![new_person.id, new_person.name, new_person.age],
        )?;
    } else {
        println!("Invalid input");
    }

    Ok(())
}


#[derive(Debug)]
struct NewPerson {
    id: u32,
    name: String,
    age: u8
}