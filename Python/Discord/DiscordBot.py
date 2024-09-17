from typing import Final
from discord import Intents, Client, Message
import os
from dotenv import load_dotenv
from Functions import *
import datetime
from colorama import Fore, Back, Style

load_dotenv()
TOKEN: Final[str] = os.getenv("DISCORD_BOT_TOKEN")

intents = Intents.default()
intents.message_content = True
client = Client(intents=intents)


async def send_message(message: Message, user_message: str) -> None:
    if not user_message:
        print("(Message empty)")
        return
    is_command = user_message[0] == "!"
    if is_command:
        user_message = user_message[1:]
        try:
            response: str = get_response(user_message)
            username: str = str(message.author)
            user_message: str = str(message.content)
            channel: str = str(message.channel)
            time: str = datetime.datetime.now().strftime("%H:%M:%S")
            date: str = datetime.datetime.now().strftime("%Y-%m-%d")
            res_list: list[str] = ["Jeg ikke forstå?", "Hva' for noget?", "Taler du til mig?", "Hva' siger du?"]
            if response in res_list:
                res: str = "Invalid command"
            else:
                res: str = response
            with open("C:\\Users\\Malte\\Documents\\Programmering\\Python\\Discord\\CommandLogs.txt", "a") as file:
                file.write(f"\n[{date}] [{time}] Channel: [{channel}] User: [{username}]: {user_message} Response: {res}\n")

            print(Fore.YELLOW + f"[{date}] [{time}]" + Fore.RESET + " Channel:" + Fore.YELLOW + f" [{channel}]" + Fore.RESET + " User:" + Fore.YELLOW + f" [{username}]:" + Fore.YELLOW + f" {user_message}" + Fore.RESET + f" Response:" + Fore.YELLOW + f" {res}\n")
            await message.reply(response)
        except Exception as e:
            print(e)


@client.event
async def on_ready() -> None:
    print(f"Logged in as {client.user}")


@client.event
async def on_message(message: Message) -> None:
    if message.author == client.user:
        return
    username: str = str(message.author)
    user_message: str = str(message.content)
    channel: str = str(message.channel)
    time: str = datetime.datetime.now().strftime("%H:%M:%S")
    date: str = datetime.datetime.now().strftime("%Y-%m-%d")

    is_command = user_message[0] == "!"
    if not is_command:
        with open("C:\\Users\\Malte\\Documents\\Programmering\\Python\\Discord\\ChatLogs.txt", "a") as file:
            file.write(f"\n[{date}] [{time}] Channel: [{channel}] User: [{username}]: {user_message}\n")
        print(Fore.YELLOW + f"[{date}] [{time}]" + Fore.RESET + " Channel:" + Fore.YELLOW + f" [{channel}]" + Fore.RESET + " User:" + Fore.YELLOW + f" [{username}]:" + Fore.YELLOW + f" {user_message}")

    await send_message(message, user_message)


def main() -> None:
    print("Starting bot...")
    client.run(TOKEN)


if __name__ == "__main__":
    main()
