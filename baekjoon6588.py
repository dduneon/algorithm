from sys import stdin

case = list(map(int, stdin.readlines()[:-1]))
maxnum = max(case)

# 에라토스테네스의 체 활용 소수 구하기
prime = [True for _ in range(maxnum + 1)]
prime[0], prime[1] = False, False

for i in range(2, int(maxnum**0.5)+1):
    if prime[i]:
        j = 2
        while i*j <= maxnum:
            prime[i*j] = False
            j += 1

primes = [i for i in range(len(prime)) if prime[i]]

def solve(target):
    for s in range(1, target//2):
        if prime[target-primes[s]]:
            print(f'{target} = {primes[s]} + {target-primes[s]}')
            return
    print('Goldbach\'s conjecture is wrong.')



for c in case:
    solve(c)
