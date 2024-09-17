use std::io;

fn main() {
    println!("Welcome to the rusting calculator\n");
    println!("Enter an expression (e.g., 5 + 3):");

    let mut input = String::new();
    
    io::stdin()
        .read_line(&mut input)
        .expect("Failed to read line");

    let parts: Vec<String> = input.trim().split_whitespace().map(String::from).collect();

    if parts.len() != 3 {
        println!("Invalid input. Please enter an expression in the format: number operator number");
        return;
    }

    let result = calculate(&parts);
    match result {
        Ok(value) => println!("The result is: {}", value),
        Err(error) => println!("Error: {}", error),
    }
}

fn calculate(parts: &[String]) -> Result<i32, String> {
    let num1: i32 = parts[0].parse().map_err(|_| "Invalid first number! Please enter a number between -128 and 127.")?;
    let operator = &parts[1];
    let num2: i32 = parts[2].parse().map_err(|_| "Invalid second number! Please enter a number between -128 and 127.")?;

    match operator.as_str() {
        "+" => num1.checked_add(num2).ok_or_else(|| "Addition overflow!".to_string()),
        "-" => num1.checked_sub(num2).ok_or_else(|| "Subtraction overflow!".to_string()),
        "*" => num1.checked_mul(num2).ok_or_else(|| "Multiplication overflow!".to_string()),
        "/" => {
            if num2 == 0 {
                Err("Division by zero!".to_string())
            } else {
                num1.checked_div(num2).ok_or_else(|| "Division overflow!".to_string())
            }
        },
        _ => Err("Invalid operator!".to_string()),
    }
}