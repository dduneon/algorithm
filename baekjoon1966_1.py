import sys

n = int(sys.stdin.readline().strip())
for _ in range(n):
    N, M = map(int, sys.stdin.readline().split())
    L = list(map(int, sys.stdin.readline().split()))

    count = 1
    relocation = False
    for i in range(len(L)-1, M, -1):
        if L[M] < L[i]:
            relocation = True
        elif L[M] == L[i]:
            count += 1

    if not relocation:
        count = 1
        for i in range(M):
            if L[M] <= L[i]:
                count += 1
    else:
        for i in range(M):
            if L[M] <= L[i]:
                count += 1
    print(count)


