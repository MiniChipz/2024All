use std::{io, thread};
use regex::Regex;
use std::time::Duration;

fn main() {
    println!("What should I calculate?");
    let mut start_string: String = String::new();
    // io::stdin() is for reading an input, it can only be a string input and has to be parsed later on if anything else is needed.
    io::stdin().read_line(&mut start_string).expect("Failed to read line");

    // char_regex remove every big and small letter and number.
    let char_regex: Regex = Regex::new(r"[A-Za-z0-9]").unwrap();
    // number_regex removes everything but numbers.
    let number_regex: Regex = Regex::new(r"[^\d\s]+").unwrap();

    // here I use my regex variables to remove what needs to be removed from the string. into owned makes me able to return it to a string instead of cow thingy
    let only_char: String = char_regex.replace_all(&start_string.trim(), "").into_owned();
    let only_num: String = number_regex.replace_all(&start_string.trim(), "").into_owned();

    // Here I trim and split a string by spaces into a vector (list)
    let number_list: Vec<&str> = only_num.trim().split_whitespace().collect();

    // Sees if there are multiple numbers
    if number_list.len() < 2 {
        println!("Error: Not enough numbers found in the input.");
        return;
    }

    // Here i parse from a string slice to an integer
    let num1: i32 = number_list[0].parse().expect("Failed to parse num1.");
    let num2: i32 = number_list[1].parse().expect("Failed to parse num2.");

    // sets the cooldown time
    let duration = Duration::new(2, 0);

    // match = switch, it matches the char to see what calculations are needed and then uses the function again
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
