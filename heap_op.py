from heap import get_heap
arr = [30, 15, 6, 8, 94]
# res = get_heap(arr)
# print(res)


class heap:
    def __init__(self, arr):
        self.data = get_heap(arr)
        self.total_parent = int((len(arr)//2)-1)
        self.total_nodes = len(arr)

    def print_heap(self):
        print("Heapified Data Structure => ", self.data)

    def insert(self, num):
        arr = self.data
        arr.append(num)
        index = len(arr)-1
        heap_now(arr, index)
        self.data = arr


def left(i):
    return (2*i)+1


def right(i):
    return (2*i)+2


def parent(i):
    return (i-1)//2


def swap(arr, a, b):
    tmp = arr[a]
    arr[a] = arr[b]
    arr[b] = tmp
    return arr


def heap_now(arr, k):
    if k == 0:
        return arr
    # print(arr, " and ", k)
    p = parent(k)
    if arr[p] < arr[k]:
        swap(arr, p, k)
        heap_now(arr, p)

# def verify_heap(arr):
#     l = len(arr)
#     total_parent = int(l//2)
#     print(total_parent, l, 2**total_parent)
#     if (l-1 != (2**total_parent)):
#         print("Not a heap")


obj = heap(arr)
obj.print_heap()
obj.insert(95)
obj.print_heap()
