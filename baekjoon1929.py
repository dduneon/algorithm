from sys import stdin
M, N = map(int, stdin.readline().split())
prime = [True for _ in range(N+1)]
prime[0], prime[1] = False, False
for i in range(2, int(N**0.5)+1):
    if prime[i]:
        j = 2
        while i*j<=N:
            prime[i*j] = False
            j += 1

for i in range(M, N+1):
    if prime[i]:
        print(i)
