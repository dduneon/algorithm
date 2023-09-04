import sys

N = int(sys.stdin.readline())


def sol():
    queue = []

    for i in range(N):
        com = sys.stdin.readline().split()

        if com[0] == 'pop':
            print(queue.pop(0) if queue else -1)
        elif com[0] == 'size':
            print(len(queue))
        elif com[0] == 'empty':
            print(0 if queue else 1)
        elif com[0] == 'front':
            print(queue[0] if queue else -1)
        elif com[0] == 'back':
            print(queue[-1] if queue else -1)
        elif com[0] == 'push':
            queue.append(com[1])


sol()
