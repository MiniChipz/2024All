use std::io;
use rand::Rng;

fn main() {

    let answer: u8 = rand::thread_rng().gen_range(1..=10);

    println!("Guess a number");

    let mut guess = String::new();

    io::stdin().read_line(&mut guess).expect("Error!");

    match guess.trim().parse::<u8>() { 
        
    }
}