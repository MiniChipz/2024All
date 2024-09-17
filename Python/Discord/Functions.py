from random import randint
from random import choice
def get_response(user_input: str) -> str:
    lowered: str = user_input.lower()
    if lowered == '':
        return "Skriv noget"
    elif "roll a dice" in lowered:
        return f"Du rullede {randint(1, 6)}"
    elif "har du et pas" in lowered:
        return f"Nej, må jeg få dit?"
    else:
        return choice(["Jeg ikke forstå?", "Hva' for noget?", "Taler du til mig?", "Hva' siger du?", "Er du racist?", "Undskyld jeg kommer forsent", "Hvaaad?"])
