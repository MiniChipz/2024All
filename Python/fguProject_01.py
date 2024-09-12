from random import choice

rows = 3
columns = 3

symbols = {
    "A": 2,
    "B": 4,
    "C": 6,
    "D": 8
}

def deposit():
    print("\nSkriv hvor meget du ønsker at indsætte")

    try:
        amount = input()
        if "," in amount or "." in amount:
            amount = float(amount.replace(".", "").replace(",", "."))
        else:
            amount = int(amount)
    except ValueError:
        print(f"\nDu skal skrive et gyldigt beløb!")
        return deposit()  # Retry input on failure

    if amount > 0:
        print(f"\nDu har indsat {amount} kroner")
        return amount
    else:
        print(f"\nDu skal indsætte et positivt beløb!")
        return deposit()

def buy_lines():
    print("\nHvor mange linjer ønsker du købe? (1-10)")
    try:
        lines = int(input())
        if lines < 1 or lines > 10:
            print(f"\nDu skal skrive et tal mellem 1 og 10!")
            return buy_lines()  # Retry input on failure
    except ValueError:
        print(f"\nDu skal skrive et gyldigt antal linjer!")
        return buy_lines()
    return lines

def get_bet(max_bet):
    print(f"\nHvor mange kroner ønsker du at spille med? (1-{max_bet})")
    try:
        bet = input()
        if "," in bet or "." in bet:
            bet = float(bet.replace(".", "").replace(",", "."))
        else:
            bet = int(bet)
        if bet < 1 or bet > max_bet:
            print(f"\nDu skal skrive et tal mellem 1 og {max_bet}!")
            return get_bet(max_bet)  # Retry input on failure
    except ValueError:
        print(f"\nDu skal skrive et gyldigt beløb!")
        return get_bet(max_bet)
    return bet

def slot_machine(rows: int, columns: int, symbols: dict):
    all_symbols = []
    for symbol, count in symbols.items():
        all_symbols.extend([symbol] * count)

    columns_list = []
    for _ in range(columns):
        column = []
        current_symbols = all_symbols[:]  # Make a copy of all symbols
        for _ in range(rows):
            value = choice(current_symbols)
            current_symbols.remove(value)
            column.append(value)
        columns_list.append(column)

    return columns_list

def print_test(columns):
    for row in range(len(columns[0])):
        for i, column in enumerate(columns):
            if i != len(columns) - 1:
                print(column[row], end=" | ")
            else:
                print(column[row], end="")
        print()

def main():
    balance = deposit()
    lines = buy_lines()
    columns = lines
    max_bet = balance // lines
    bet = get_bet(max_bet)
    total_bet = bet * lines

    print(f"\nDu har {balance} kroner, køber {lines} linjer og væder {bet} kroner.")

    slots = slot_machine(rows, columns, symbols)
    print_test(slots)

main()
