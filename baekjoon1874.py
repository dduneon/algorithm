import sys

N = sys.stdin.readlines()[1:]
stack = []
i = 1
result = []
state = True

for n in N:
    n = int(n.strip())

    while i <= n:
        stack.append(i)
        i += 1
        result.append('+')

    if stack.pop() != n:
        state = False
        break
    else:
        result.append('-')

print('\n'.join(result)) if state else print('NO')