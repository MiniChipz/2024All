use std::fs::File;
use std::path::Path;
use std::io;
use std::fs::OpenOptions;
use std::process::Command;
use std::io::{BufRead, BufReader, Write};

fn main() {
    let path = Path::new("C:\\Users\\Malte\\Documents\\Programmering\\Rust\\toDoList\\src\\list.txt");
    let file_name = path.display();
    let file_path: &str = "C:\\Users\\Malte\\Documents\\Programmering\\Rust\\toDoList\\src\\list.txt";

    let file = match File::open(&path) {
        Err(reason) => panic!("Couldn't open up {}: {}", file_name, reason),
        Ok(file) => file
    };

    let mut file_content: Vec<String> = Vec::new();
    let reader = BufReader::new(file);

    for line in reader.lines() {
        match line {
            Ok(line) => file_content.push(line),
            Err(why) => panic!("Couldn't read line: {}", why)
        }
    }
    println!("What do you want to do?");
    println!("1. Add todo to list \n2. Remove todo from list \n3. Read list");

    let mut input: String = String::new();

    loop {
        input.clear();
        io::stdin().read_line(&mut input).expect("Failed to read line");
        if matches!(input.trim(), "1" | "2" | "3") {
            break;
        }
        println!("\n1. Add todo to list \n2. Remove todo from list \n3. Read list");
    }

    match input.trim() {
        "1" => {
            clear_screen();
            println!("Write the todo:");
            let mut input1: String = String::new();
            io::stdin().read_line(&mut input1).expect("Failed to read line");
            let _ = write_to_file(path.to_str().unwrap(), input1.trim().to_string());
            main();
        }
        "2" => {
            clear_screen();
            let mut input: String = String::new();
            let max_line: u16 = read_lines(file_content);
            println!("\nWrite which line you want removed (1-{})", max_line);
            loop {
                input.clear();
                io::stdin().read_line(&mut input).expect("Error");
                let input_unt: u16 = input
                    .trim()
                    .parse()
                    .expect("Type a number ranging from 1-{max_line}");
                if input_unt >= 1 && input_unt <= max_line {
                    let line_to_remove: usize = input_unt as usize;
                    let _ = remove_line_from_file(file_path, line_to_remove);
                    break;
                }
                else {
                    println!("Write a number in the range 1-{}", max_line)
                }
            }
            println!("The line was removed");
            main();

        }
        "3" => {
            clear_screen();
            read_lines(file_content);
            println!(" ");
            main();
        }
        _ => {
            println!("Default");
        }
    }
}

fn remove_line_from_file(path: &str, target_line: usize) -> io::Result<()> {
    let file = File::open(path)?;
    let reader = BufReader::new(file);
    let lines: Vec<String> = reader.lines().collect::<Result<_, _>>()?;

    let mut file = OpenOptions::new()
        .write(true)
        .truncate(true)
        .open(path)?;

    for (index, line) in lines.iter().enumerate() {
        if index + 1 != target_line {
            writeln!(file, "{}", line)?;
        }
    }
    Ok(())
}

fn read_lines(file_content: Vec<String>) -> u16 {
    clear_screen();
    let mut line_number: u16 = 0;
    for line in file_content {
        line_number += 1;
        println!("{}: {}", line_number, line);
    }
    line_number
}

fn write_to_file(path: &str, content: String) -> std::io::Result<()> {
    let mut file = OpenOptions::new()
        .write(true)
        .append(true)
        .open(path)?;

    writeln!(file)?;
    write!(file, "{}", content)?;
    clear_screen();
    println!("You added: {} to your todo list", content);
    Ok(())

}

fn clear_screen() {
    if cfg!(target_os = "windows") {
        Command::new("cmd")
            .args(["/c", "cls"])
            .status()
            .unwrap();
    } else {
        Command::new("clear")
            .status()
            .unwrap();
    }
}