# 투 포인터를 활용한 풀이
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

start, end = 0, 0
total = 0
result = 0
# 범위 start <= total < end
while start <= end:
    if total >= N:
        total -= primes[start]
        start += 1
    elif total < N:
        if end == len(primes):
            break
        total += primes[end]
        end += 1
    if total == N:
        result += 1


print(result)
