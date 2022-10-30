t = int(input())

def evenodd(a):
    # bitwise AND with 1 results in 1 for odd and 0 for even
    if a&1 == 1:
        return "odd"
    else:
        return "even"

def shifttrick(a):
    # leftshift is multiply by power of 2
    # rightshift is divide by power of 2
    print(a<<2, a>>2)   # a * (2**2), a // (2**2)
    print(a<<3, a>>3)   # a * (2**3), a // (2**3)

def powerof2(a):
    """It's a bitwise operation between a number and its previous number. 
    Only way this expression could ever be false is if n is a power of 2, 
    so essentially you're verifying if it isn't a power of 2."""
    if not (a & (a-1)):
        return True
    else:
        return False

def countbits(a):
    count = 0
    while a:
        a = a & (a-1)
        count += 1
    return count

while t:
    a = int(input())
    print(evenodd(a))
    shifttrick(a)
    print(powerof2(a))
    print(countbits(a))
    t -= 1