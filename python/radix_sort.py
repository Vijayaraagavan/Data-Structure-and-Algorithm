from utils import read_arr
a = [10, 80, 90, 606, 30, 20]


def radix_sort(arr):
    # print(arr)
    bin_arr = [[], [], [], [], [], [], [], [], [], [], []]
    temp = arr
    big_num = get_biggest(arr)
    digits = len(str(big_num))
    for k in range(digits):
        for i in temp:
            r = get_digit(i, k)
            # print(i, r)
            bin_arr[r].append(i)
            # print(bin_arr)
        temp = []
        for i in bin_arr:
            for j in i:
                if j is not None:
                    temp.append(j)
        bin_arr = [[], [], [], [], [], [], [], [], [], [], []]
        # print(bin_arr)
    return temp


def get_biggest(arr):
    b = 0
    for i in arr:
        if i > b:
            b = i
    return b


def get_digit(k, i):
    a = k // 10
    if i == 0:
        return (k % 10)
    elif i == 1:
        a = k // 10
        return a % 10
    else:
        c = a // 10
        return c % 10


# a = read_arr()
print(radix_sort(a))
