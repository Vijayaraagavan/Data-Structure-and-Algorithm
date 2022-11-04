from heap import get_heap
# arr = [30, 15, 6, 8, 94]
arr = [5, 46, 92, 48, 10, 3, 11, 1052, 14, 88, 21]
# res = get_heap(arr)
# print(res)


class heap:
    def __init__(self, arr):
        self.data = get_heap(arr)
        self.total_parent = int((len(arr)//2)-1)
        self.total_nodes = len(arr)
        self.limit = len(arr)-1

    def print_heap(self):
        print("Heapified Data Structure => ", self.data)

    def insert(self, num):
        arr = self.data
        arr.append(num)
        index = len(arr)-1
        heap_now(arr, index)
        self.data = arr
        self.limit = self.limit+1

    def sort(self):
        arr = self.data
        for i in range(len(arr)):
            self.pop_max()

    def pop_max(self):
        arr = self.data
        print(arr[0], end=" ")
        l = self.limit
        last_node = l
        swap(arr, 0, l)
        pop_heap(arr, 0, l)
        self.limit = l-1
        self.data = arr


def pop_heap(arr, k, l):
    max = k
    lef = left(max)
    rig = right(max)
    # print(arr, k, lef, rig)
    if lef < l and arr[k] < arr[lef]:
        max = lef
    if rig < l and arr[max] < arr[rig]:
        max = rig
    if max != k:
        swap(arr, max, k)
        pop_heap(arr, max, l)


def left(i):
    return (2*i)+1


def right(i):
    return (2*i)+2


def parent(i):
    return (i-1)//2


def swap(arr, a, b):
    # print("swapping: ", a, " and ", b)
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
obj.sort()
# obj.pop_max()
# obj.print_heap()
# obj.pop_max()
obj.print_heap()
