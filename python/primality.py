from math import *
t = int(input())
def approach1(a):   # Time complexity: O(n)
    for i in range(2, a-1):
        if a%i == 0:
            return False
    return True
def approach2(a):   # Time Complexity: O(sqrt(n))
    if a == 0 or a == 1:
        return True
    elif a == 2 or a == 3:
        return False
    elif a%2 == 0 or a%3 == 0:
        return False
    else:
        for i in range(5, int(sqrt(a) + 1)):
            if a%i == 0 or a%(i+2) == 0:
                return False
    return True

while t:
    a = int(input())
    v1 = approach1(a)
    v2 = approach2(a)
    print(v1, v2)
    t -= 1

