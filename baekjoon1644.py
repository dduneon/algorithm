import sys
N = int(sys.stdin.readline())

prime = [True for _ in range(N+1)]
prime[0], prime[1] = False, False

for i in range(2, int(N**0.5) + 1):
    if prime[i]:
        j=2
        while i*j<=N:
            prime[i*j] = False
            j+=1

primes = [i for i in range(2, N+1) if prime[i]]
result = 0

for p in range(len(primes)):
    tmp = 0
    for a in range(p, len(primes)):
        tmp += primes[a]
        if tmp == N:
            result += 1
            break
        elif tmp > N:
            break

print(result)