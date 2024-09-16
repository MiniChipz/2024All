use num_bigint::BigInt;
use std::mem::replace;
use std::time::Instant;

fn main() {
    fibo();
}


fn fibo() {
    let mut fib1: BigInt = BigInt::from(0);
    let mut fib2: BigInt = BigInt::from(1);
    let mut total_num: BigInt = BigInt::from(0);

    let start = Instant::now();

    while total_num < BigInt::from(1_000_000) {
        let fib3 = &fib1 + &fib2;
        replace(&mut fib1, fib2);
        fib2 = fib3;
        total_num += 1;
        println!("Fibo: {}, Total: {}", fib2, total_num);
    }

    let duration = start.elapsed();
    println!("Time taken: {:?}", duration);
}

// fn string_reverse(input: String) { 
//     if !input.len() < 2 {
//         println!("You have to write a bigger word!");
//     }
//     let mut word_list: Vec<&str> = input.chars().collect();
//     let mut number: usize = 0;
//     let mut final_word: String = String::new();
//     for letter in word_list {
//         number += 1;
//         final_word += word_list[-number];
//     }
//     println!("Ordet:  {}", input);
//     println!("Omvendt:  {}", final_word);
    
// }