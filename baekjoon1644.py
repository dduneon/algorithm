import sys
N = int(sys.stdin.readline())

prime = [True for _ in range(4000001)]

for i in range(2, int(N**0.5) + 1):
    if prime[i]:
        j=2
        while i*j<=N:
            prime[i*j] = False
            j+=1

print(prime[1], prime[2], prime[3], prime[4], prime[5], prime[6])