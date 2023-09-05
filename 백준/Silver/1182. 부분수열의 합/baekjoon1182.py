import sys
input = sys.stdin.readline

N, S = map(int, input().strip().split())
numbers = list(map(int, input().strip().split()))
result = 0

def dfs(startidx, sums):
    sums += numbers[startidx]
    if sums == S:
        global result
        result += 1
    for idx in range(startidx+1, N):
        dfs(idx, sums)

for i in range(N):
    dfs(i, 0)

print(result)