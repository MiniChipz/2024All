use std::io;

fn main() {
    println!("Welcome to the rusting calculater\n");
    println!("Choose an operation (+, -, *, /):");

    let mut string: String = String::new();
    
    io::stdin()
        .read_line(&mut string)
        .expect("Invalid operation!");

    match string.trim() {
        "+" => {
            let index: i8 = string.trim().find('+');

            let num1: f32 = input[0..index].trim().parse().expect("Invalid first number!");
            let num2: f32 = input[index+1..].trim().parse().expect("Invalid second number!");
            println!("The result is: {}", num1 + num2);
        }
        "-" => {
            let index: i8 = string.trim().find('-');

            let num1: f32 = input[0..index].trim().parse().expect("Invalid first number!");
            let num2: f32 = input[index+1..].trim().parse().expect("Invalid second number!");
            println!("The result is: {}", num1 - num2);
        }
    _ => println!("Invalid operation!");
    }
}