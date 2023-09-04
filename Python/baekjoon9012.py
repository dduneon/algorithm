import sys
T = sys.stdin.readlines()[1:]
for t in T:
    count = 0
    for i in t:
        if count == -1:
            break
        if i == '(':
            count += 1
        elif i == ')':
            count -= 1
    print("YES" if count == 0 else "NO")