from math import *
t = int(input())
def fun1(a):
    res = []
    for i in range(1, a+1):
        if a % i == 0:
            res.append(i)
    return res
def fun2(a):
    res = set()
    for i in range(1, int(sqrt(a) + 1)):
        if a % i == 0:
            res.add(i)
            res.add(a // i)
    return list(res)
while t:
    a = int(input())
    div1 = fun1(a)
    print(div1)
    div2 = fun2(a)
    print(div2)
    t -= 1

