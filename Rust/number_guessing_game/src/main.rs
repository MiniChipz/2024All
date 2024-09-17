use std::io;
use rand::Rng;

fn main() {

    let answer: u8 = rand::thread_rng().gen_range(1..=10);
    let mut guess_int: u8 = 0;
    let mut input = String::new();
    println!("Guess a number between 1 and 10");
    while guess_int != answer {
        io::stdin().read_line(&mut input).expect("Error!");
        guess_int = input.trim().parse().expect("Please type a number!");

        if guess_int == answer {
            println!("You win!");
            break;
        } else if guess_int < answer {
            println!("Too small!");
        } else {
            println!("Too big!");
        }
        input.clear();
    }
}
