use reqwest;
use serde::Deserialize;
use std::error::Error;

#[tokio::main]
async fn main() -> Result<(), Box<dyn Error>> {
    let url = "https://jsonplaceholder.typicode.com/posts";

    let response = reqwest::get(url).await?
        .json::<Vec<Post>>()
        .await?;

    for post in response {
        println!("- {} (by user {})", post.title, post.userId);
    }
    Ok(())
}

#[derive(Deserialize)]
struct Post {
    userId: u32,
    title: String,
}
