from sys import stdin

def check(row, col):
    possible = set()
    for p in range(1, 10):
        if p not in Pane[row] and p not in rPane[col]:
            possible.add(p)

    for r in range(row - (row%3), row - (row%3) + 3):
        for c in range(col - (col%3), col - (col%3) + 3):
            if Pane[r][c] in possible:
                possible.remove(Pane[r][c])

    return possible

def dfs(idx):
    # 종료 조건 : 빈칸이 없는 경우
    if idx == len(blank):
        for y in range(9):
            print(' '.join(str(Pane[y][x]) for x in range(9)))
        exit(0)
    r, c = blank[idx]
    pos = check(r, c)

    if not pos:
        return
    for p in pos:
        Pane[r][c] = p
        rPane[c][r] = p
        dfs(idx+1)
        Pane[r][c] = 0
        rPane[c][r] = 0


Pane = [list(map(int, stdin.readline().split(' '))) for _ in range(9)]
rPane = list(map(list, zip(*Pane)))
# 행 열을 뒤집은 2차원 배열 rPane

blank = []
for i in range(9):
    for j in range(9):
        if Pane[i][j] == 0:
            blank.append((i, j))
# blank에 빈 공간 좌표 추가(y, x)

# 한가지 경우밖에 없는 빈칸들에 대하여 모두 채워줌, 변경점이 없는 경우 종료
while True:
    isModified = False
    for row, col in blank:
        pos = check(row, col)
        if len(pos) > 1:
            continue
        isModified = True
        num = pos.pop()
        Pane[row][col] = num
        rPane[col][row] = num
        blank.remove((row, col))
    if not isModified:
        break

dfs(0)