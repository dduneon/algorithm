import sys

com = sys.stdin.readlines()[1:]
sg = dict()
for c in com[0].strip().split():
    if c not in sg:
        sg[c] = 1
    else:
        sg[c] += 1

print(' '.join(str(sg.get(n, 0)) for n in com[2].strip().split()))