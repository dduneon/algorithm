import sys

def sol():
    _ = sys.stdin.readline()

    A = list(map(int, sys.stdin.readline().split()))
    B = list(map(int, sys.stdin.readline().split()))

    A.sort()
    B.sort(reverse=True)

    result = 0
    for i in range(len(A)):
        result += (A[i] * B[i])

    print(result)

sol()