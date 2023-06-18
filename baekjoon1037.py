from sys import stdin

_ = stdin.readline()
A = list(map(int, stdin.readline().split()))
A.sort()
print(A[0] * A[len(A)-1])
