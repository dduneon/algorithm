from sys import stdin

input = stdin.readline

N = int(input())
C = [[True for _ in range(N)] for _ in range(N)]
result = 0

# 체스판 배열 (놓을 수 있는경우 -> True, 없는경우 -> False)
def recur(startLine):
    if startLine == N-1:
        global result
        result += 1
        return
    for i in range(N):
        if C[startLine][i]:
            C[startLine][i] = False
            tmp = 1
            for j in range(startLine + 1, N):
                C[j][i] = False
                if i - tmp > 0:
                    C[j][i - tmp] = False
                if i + tmp < N:
                    C[j][i + tmp] = False
                tmp += 1
        else:
            return
    recur(startLine+1)

recur(0)
print(result)