import sys


def sol():
    _ = sys.stdin.readline()
    i = sys.stdin.read().split('\n')
    l = [[] for _ in range(200001)]

    for c in i:
        try:
            x, y = c.split()
        except ValueError:
            break
        l[int(x) + 100000].append(int(y))

    for idx in range(200001):
        if l[idx]:
            for pr in sorted(l[idx]):
                print(str(idx - 100000) + ' ' + str(pr))


sol()
