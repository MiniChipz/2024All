use std::{io, thread};
use std::process::Command;
use regex::Regex;
use std::time::Duration;

fn main() {
    println!("What should I calculate?");
    let mut start_string: String = String::new();
    io::stdin().read_line(&mut start_string).expect("Failed to read line");

    let re = Regex::new(r"[A-Za-z0-9]").unwrap();
    let reg = Regex::new(r"[^\d\s]+").unwrap();

    let only_char: String = re.replace_all(&start_string.trim(), "").into_owned();
    let only_num: String = reg.replace_all(&start_string.trim(), "").into_owned();

    let numbers: Vec<&str> = only_num.trim().split_whitespace().collect();

    if numbers.len() < 2 {
        println!("Error: Not enough numbers found in the input.");
        return;
    }

    let num1: i32 = numbers[0].parse().expect("Failed to parse num1.");
    let num2: i32 = numbers[1].parse().expect("Failed to parse num2.");

    let duration = Duration::new(2, 0);


    match only_char.as_str().trim() {
        "+" => {
            let result: i32 = num1 + num2;
            println!("{} + {} = {}", num1, num2, result);
            thread::sleep(duration);
            main()
        }
        "-" => {
            let result: i32 = num1 - num2;
            println!("{} - {} = {}", num1, num2, result);
            thread::sleep(duration);
            main()
        }
        "*" => {
            let result: i32 = num1 * num2;
            println!("{} * {} = {}", num1, num2, result);
            thread::sleep(duration);
            main()
        }
        "/" => {
            let result: i32 = num1 / num2;
            println!("{} / {} = {}", num1, num2, result);
            thread::sleep(duration);
            main()
        }
        _ => {
            println!("Invalid calculation!");
            thread::sleep(duration);
            main()
        }

    }
}