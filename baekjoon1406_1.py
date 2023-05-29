import sys

def sol():
    left = list(input())
    right = []
    com = sys.stdin.read().splitlines()
    for c in com:
        if c[0] == 'L':
            if len(left) > 0:
                right.append(left.pop())
        elif c[0] == 'D':
            if len(right) > 0:
                left.append(right.pop())
        elif c[0] == 'B':
            if len(left) > 0:
                left.pop()
        elif c[0] == 'P':
            left.append(c[-1])

    print(''.join(map(str, left)), end='')
    print(''.join(reversed(right)))

sol()