def heapify(arr, k):
    if k < 0:
        return
    l = left(k)
    r = right(k)
    largest = arr[k]
    if l < len(arr) and (arr[l] > arr[k]):
        largest = l
    else:
        largest = k
        # swap(k, l, arr)
    if r < len(arr) and (arr[r] > arr[largest]):
        largest = r
    print("k = ", k, "largest = ", largest)
    if k != largest:
        swap(k, largest, arr)
        heapify(arr, largest)


def left(s):
    return 2 * s + 1


def right(s):
    return 2 * s + 2


def swap(a, b, arr):
    tmp = arr[a]
    arr[a] = arr[b]
    arr[b] = tmp
    return arr


# inp = [50, 30, 20, 15, 10, 8, 16] (heapified)


def get_heap(inp):
    # processing all parent nodes -> n // 2 -1
    limit = int((len(inp) // 2) - 1)
    for i in range(limit, -1, -1):      # 2 to 0 index. all the way to root (step -1)
        heapify(inp, i)
    return inp


# inp = [15, 50, 8, 30, 10, 20, 16]
# res = get_heap(inp)
# print("get: ", res)

# [50,30,20,15,10,8,16]
#  0  1  2  3  4  5  6
# i = 0 => 2i+1 = 1 and 2i+2 = 2
# i = 1 => 2i+1 = 3 and 2i+2 = 4
# i = 2 => 2i+1 = 5 and 2i+2 = 6
