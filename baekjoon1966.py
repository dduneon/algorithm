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
    brk = False
    while True:
        if brk:
            break
        item = D.popleft()
        for p in range(item+1, 10):
            if priority[p] > 0:
                D.append(item)
            else:
                if i == M:
                    print(count)
                    brk = True
                    break
                else:
                    priority[D.popleft()] -= 1



