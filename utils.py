def read_arr():
    a = []
    with open('input.txt') as f:
        for line in f:
            inner_list = [int(elt.strip()) for elt in line.split(',')]
            a = (inner_list)
    return a
