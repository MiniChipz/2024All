use num_bigint::BigInt;
use std::mem::replace;
use std::time::Instant;

fn main() {
    let mut fib1: BigInt = BigInt::from(0);
    let mut fib2: BigInt = BigInt::from(1);
    let mut total_num: BigInt = BigInt::from(0);

    let start = Instant::now();

    while total_num < BigInt::from(10000) {
        let fib3 = &fib1 + &fib2;
        replace(&mut fib1, fib2);
        fib2 = fib3;
        total_num += 1;
        println!("Fibo: {}, Total: {}", fib2, total_num);
    }

    let duration = start.elapsed();
    println!("Time taken: {:?}", duration);
}
