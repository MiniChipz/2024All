fn main() {
    let test = Test {
        nig1: "nig".parse().unwrap(),
        nig2: "ger".parse().unwrap()
    };
    println!("Ord: {}{}", test.nig1, test.nig2)
}

struct Test {
    nig1: String,
    nig2: String
}
