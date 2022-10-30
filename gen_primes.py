from math import *
t = int(input())

def generate_primes(a):
    res = [True]*(a+1)
    #base case
    res[0] = False
    res[1] = False
    for i in range(2, int(sqrt(a)+1)):
        if res[i] == True:
            for j in range(i*i, a+1, i):
                res[j] = False
    for i in range(0, len(res)):
        if res[i] == True:
            print(i,end=" ")

while t:
    a = int(input())
    res = generate_primes(a)
    t -= 1