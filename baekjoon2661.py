from sys import stdin

def dfs(N):
    global result
    if len(result) == N:
        print(''.join(str(i) for i in result))
        exit(0)
    for i in range(1, 4):
        result.append(i)
        if check(result):
            dfs(N)
        result.pop()

def check(result):
    for i in range(1, len(result)//2 + 1):
        if result[-i:] == result[-i * 2:-i]:
            return False
    return True

N = int(stdin.readline())
result = []
dfs(N)