import sys
from collections import deque

n = int(sys.stdin.readline().strip())
for _ in range(n):
    N, M = map(int, sys.stdin.readline().split())
    L = map(int, sys.stdin.readline().split())
    D = deque()
    priority = [0 for _ in range(10)]
    for i in L:
        D.append(i)
        priority[i] += 1

    # 프린터 큐에서의 연산
    count = 0
    while True:
        relocation = False
        item = D.popleft()
        M -= 1

        for p in range(item+1, 10):
            if priority[p] > 0:
                relocation = True
                break

        if relocation:
            D.append(item)
            if M == -1:
                M = len(D)-1
        else:
            if M == -1:
                count += 1
                print(count)
                break
            else:
                priority[item] -= 1
                count += 1



