def gcd(args):
    ch = min(args)
    res = 1
    while ch:
        print(ch)
        for i in args:
            if (i % ch) != 0:
                ch -= 1
                break
        else:
            res = ch
            break
    return res
t = int(input())
while t:
    takefrom = input('enter')
    format_in = takefrom.split()
    new_in = [int(i) for i in format_in]
    print("formatted: {}".format(new_in))
    print("gcd is: {}".format(gcd(new_in)))
    t -= 1

