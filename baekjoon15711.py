from sys import stdin
inp = stdin.readlines()[1:]
num = [sum(list(map(int, i.split()))) for i in inp]
maxsum = max(num)

prime = [True for _ in range(maxsum+1)]
prime[0], prime[1] = False, False

for i in range(2, int(maxsum**0.5) + 1):
    if prime[i]:
        j=2
        while i*j<=maxsum:
            prime[i*j] = False
            j += 1

for n in num:
    if n < 4:
        print('NO')
        continue
    if n % 2 == 0:
        # 골드바흐의 추측에 의해 두 홀수 소수의 합으로 나타낼 수 있음
        print('YES')
        continue
    if prime[n-2]:

        print('YES')
        continue
    else:
        print('NO')
        continue
