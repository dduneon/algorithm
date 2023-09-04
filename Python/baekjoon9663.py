from sys import stdin

input = stdin.readline

N = int(input())
chess = []

count = 0
def solv(row):
    if row == N:
        global count
        count += 1
        return
    # 해당 row에 놓지 말아야할 수 구하기
    impossible = set()
    for i in range(0, row):
        if chess[i]-(row-i) >= 0:
            impossible.add(chess[i]-(row-i))
        if chess[i]+(row-i) < N:
            impossible.add(chess[i]+(row-i))
        impossible.add(chess[i])

    # 더이상 갈 칸이 없다면 리턴
    if len(impossible) == N:
        return

    # 해당 row에 column들 중 impossible에 들어있지 않은 위치 구해서 재귀
    for i in range(N):
        if i not in impossible:
            chess.append(i)
            solv(row+1)
            chess.pop()

solv(0)
print(count)

