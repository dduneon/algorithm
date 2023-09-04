from sys import stdin

N = int(stdin.readline())
tree = list(map(int, stdin.readlines()))

def GCD(a, b):
    r = a % b
    if r == 0:
        return b
    return GCD(b, r)

tmp = tree[1] - tree[0]
for i in range(1, N-1):
    tmp = GCD(max(tmp, tree[i+1] - tree[i]), min(tmp, tree[i+1] - tree[i]))
    if tmp == 1:
        break

print(((tree[-1] - tree[0]) // tmp + 1) - len(tree))