import sys

_ = sys.stdin.readline()
i = sorted(list(map(int, set(sys.stdin.readline().rstrip().split()))))
for p in i:
    print(p, end=' ')

print(*i)