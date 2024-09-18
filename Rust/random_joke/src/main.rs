use std::fs::File;
use std::path::Path;
use std::io;
use std::io::{BufRead, BufReader};
use rand::seq::SliceRandom;


fn main() {
    println!("Press Enter for new joke (q for exit)");
    let mut input: String = String::new();
    io::stdin().read_line(&mut input).expect("Failed to read line");
    if input.trim() == "" {
        println!("{}", find_joke());
        main()
    } else if input.trim() == "q" {
        println!("You quit the jongler app");
        return;
    } else {
        println!("Invalid input");
        println!("{}", input);
        main()
    }
}


fn find_joke() -> String {
    let path = Path::new("C:\\Users\\Malte\\Documents\\Programmering\\Rust\\random_joke\\src\\joke_list.txt");
    let file_name = path.display();
    let file = match File::open(&path) {
        Err(reason) => panic!("Couldn't open up {}: {}", file_name, reason),
        Ok(file) => file
    };
    let mut file_content: Vec<String> = Vec::new();
    let reader: BufReader<File> = BufReader::new(file);

    for line in reader.lines() {
        let line = line.unwrap();
        file_content.push(line);
    }
    let random_joke = file_content.choose(&mut rand::thread_rng()).unwrap().to_string();

    random_joke
}