
# https://pynative.com/python-basic-exercise-for-beginners/

# Exercise:
def product(int1, int2):
    product = int1 * int2
    if product >= 1000:
        return product
    else:
        return int1 + int2
    
# Excercise 2:
def print_Number():
    prev_num = 0
    summ = 0
    i = 0
    for i in range(10):
        summ += i
        prev_num = i
        i += 1
        print(f"Current Number {i} Previous Number {prev_num} Sum: {summ}")

# Excercise 3:
def index_string():
    ord = input("Skriv en string \n")
    length = len(ord)
    for inte in range(0, length - 1, 2):
        print(f"index[", inte, "]", ord[inte])

# Excercise 4:
def remove_chars(str: str, int1: int):
    listen = list(str)
    print(listen[int1:])

# Excercise 5:
def list_is_same(listen):
    first = listen[0]
    last = listen[-1]
    if first == last:
        return True
    else:
        return False
# number1 = [1,2,3,4,5,6,1,4]
# print("Result is:", list_is_same(number1))

# Excercise 6:
def count_string(String, Ord) -> None:
    svar = String.count(Ord)
    print(f"Det står der {svar} gange")

# Excercise 7:
def trekant(rækker):
    for i in range(rækker):
        for j in range(i):
            print(i, end=' ')
        print(" ")


