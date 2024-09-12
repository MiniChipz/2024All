from timeit import default_timer as timer

def hydra():
    numb = 0
    start = timer()
    for i in range(0,1000000):
        numb += 1
    end = timer()
    print(" ")
    print(f'Execution time: {(end - start) / 1000} micro seconds')
    print(f"Number: {numb}")
    print(" ")

hydra()