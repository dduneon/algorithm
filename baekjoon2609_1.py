from sys import stdin
N, M = map(int, stdin.readline().split())
mul = N * M
while N % M != 0:
    N, M = M, N % M

print(M)
print(mul//M)