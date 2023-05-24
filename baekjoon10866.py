from collections import deque
import sys

def sol():
    d = deque()
    n = int(sys.stdin.readline())
    for _ in range(n):
        com = sys.stdin.readline().split()
        if com[0] == 'push_front':
            d.appendleft(com[1])
        elif com[0] == 'push_back':
            d.append(com[1])
        elif com[0] == 'pop_front':
            print(d.popleft() if d else -1)
        elif com[0] == 'pop_back':
            print(d.pop() if d else -1)
        elif com[0] == 'size':
            print(len(d))
        elif com[0] == 'empty':
            print(0 if d else 1)
        elif com[0] == 'front':
            print(d[0] if d else -1)
        elif com[0] == 'back':
            print(d[-1] if d else -1)

sol()
