from sys import stdin
N, M = map(int, stdin.readline().split())

def GCD(A, B):
    r = A % B
    if r == 0:
        return B
    return GCD(B, r)

gcd = GCD(N, M)
print(gcd)
lcm = N*M/gcd
print(int(lcm))