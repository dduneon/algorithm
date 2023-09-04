import sys

i = sys.stdin.readlines()[1:]
i.sort(key=lambda n: int(n.split()[0]))
i.sort(key=lambda n: int(n.split()[1]))
print(''.join(i))