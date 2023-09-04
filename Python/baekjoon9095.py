import sys
input = sys.stdin.read

def recursive(remain):
    if remain == 0:
        global result
        result += 1
    elif remain < 0:
        return
    else:
        recursive(remain-1)
        recursive(remain-2)
        recursive(remain-3)


N = list(map(int, input().split()[1:]))
for n in N:
    result = 0
    recursive(n)
    print(result)