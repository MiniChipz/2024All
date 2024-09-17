'''
Hangman spil
'''


import random
import time
import os
 
def play_again():
    question = 'Do You want to play again? (y/n) \n'
    play_game = input(question)
    while play_game.lower() not in ['y', 'n']:
        play_game = input(question)
    if play_game.lower() == 'y':
        return True
    else:
        return False
 
def hangman(word):
    display = '_' * len(word)
    count = 0
    limit = 5
    letters = list(word)
    guessed = []
 
    while count < limit:
        guess = input(f'Hangman Word: {display} Enter your guess: \n').strip()
        while len(guess) == 0 or len(guess) > 1:
            print('Invalid input. Enter a single lettter\n')
            guess = input(f'Hangman Word: {display} Enter your word guess: \n').strip()
 
        if guess in guessed:
            print('Oops! You already tried that guess, try again! \n')
            continue
 
        if guess in word:
            letters = [i for i, letter in enumerate(word) if letter == guess]
            for index in letters:
                display = display[:index] + guess + display[index + 1:]
 
        else:
            guessed.append(guess)
            count += 1
            if count == 1:
                time.sleep(1)
                print('  _____ \n'
                    '  |     | \n'
                    '  |       \n'
                    '  |       \n'
                    '  |       \n'
                    '  |       \n'
                    '  |       \n'
                    '__|__     \n')
                print(f'Wrong guess: {limit - count} guesses remaining \n')
               
            elif count == 2:
                time.sleep(1)
                print('  _____ \n'
                    '  |     | \n'
                    '  |     | \n'
                    '  |       \n'
                    '  |       \n'
                    '  |       \n'
                    '  |       \n'
                    '__|__     \n')
                print(f'Wrong guess: {limit - count} guesses remaining \n')
 
            elif count == 3:
                time.sleep(1)
                print('  _____ \n'
                    '  |     | \n'
                    '  |     | \n'
                    '  |     | \n'
                    '  |       \n'
                    '  |       \n'
                    '  |       \n'
                    '__|__     \n')
                print(f'Wrong guess: {limit - count} guesses remaining \n')
 
            elif count == 4:
                time.sleep(1)
                print('   _____ \n'
                      '  |     | \n'
                      '  |     | \n'
                      '  |     | \n'
                      '  |     | \n'
                      '  |       \n'
                      '  |       \n'
                      '__|__\n')
                print(f'Wrong guess: {limit - count} guesses remaining \n')
 
            elif count == 5:
                time.sleep(1)
                print('   _____ \n'
                      '  |     | \n'
                      '  |     | \n'
                      '  |     | \n'
                      '  |     o \n'
                      '  |    /|\\ \n'
                      '  |    / \\ \n'
                      '__|__\n')
                print(f'Wrong guess: {limit - count} guesses remaining \n')
 
        if display == word:
            print(f'Congrats! You have guessed the word \'{word}\' correctly!')
            break
 
def play_hangman():
    print('\n Welcome to Hangman Alfa Test ver. 0.1 \n')
    name = input('Enter your name: ')
    print(f'Hello {name}! Best of Luck! \n')
    time.sleep(1)
    print('...Loading... The Game is about to start! \nLet\'s play Hangman!')
    time.sleep(1)
    os.system('cls' if os.name == 'nt' else 'clear')
 
    words_to_guess = ['aas']
    play = True
    while play:
        word = random.choice(words_to_guess)
        hangman(word)
        play = play_again()
   
    print('Thanks for Playing!')
 
if __name__ == '__main__':
    play_hangman()