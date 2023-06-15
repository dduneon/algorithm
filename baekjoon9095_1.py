import sys
input = sys.stdin.read

dp = [0] * 12
dp[1] = 1
dp[2] = 2
dp[3] = 4

N = list(map(int, input().split()[1:]))
for i in range(4, 12):
    if dp[i] != 0:
        continue
    dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]
print('\n'.join(str(dp[n]) for n in N))