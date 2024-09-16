use std::time::Instant;

fn main() {
    let start = Instant::now();
    let target_number: i32 = 1_000_000_000;
    let mut number: i32 = 0;
    while number < target_number {
        number += 1;
    }
    let duration = start.elapsed();
    println!("It took {:?} to do {} loops", duration, target_number);
}