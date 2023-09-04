from sys import stdin

min_num, max_num = list(map(int, stdin.readline().split()))

prime_num = [True for _ in range(1000001)]
prime_num[0], prime_num[1] = False, False

for i in range()