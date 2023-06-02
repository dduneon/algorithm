import sys
T = sys.stdin.readlines()[1:]
for t in T:
    t = t.strip()
    while '()' in t:
        t = t.replace('()', '')
    print('NO') if t else print('YES')