from sys import stdin

def check_rowcol(row, col):
    possible = set()
    for p in range(9):
        if p not in Pane[row] and p not in rPane[col] and p != 0:
            possible.add(p)
    return possible

def check_square(row, col, possible):
    # 0 1 2 3 4 5 6 7 8
    # 7인 경우 6 7 8 -> 7 - 7%3 = 6부터
    for r in range(row - (row%3), row - (row%3) + 3):
        for c in range(col - (col%3), col - (col%3) + 3):
            if Pane[r][c] in possible:
                possible.remove(Pane[r][c])
    return possible

Pane = [list(map(int, stdin.readline().split(' '))) for _ in range(9)]
rPane = list(map(list, zip(*Pane)))
# 행 열을 뒤집은 2차원 배열 rPane

blank = []
for i in range(9):
    for j in range(9):
        if Pane[i][j] == 0:
            blank.append((i, j))
# blank에 빈 공간 좌표 추가(y, x)

while True:
    isModified = False
    for row, col in blank:
        pos = check_square(row, col, check_rowcol(row, col))
        if len(pos) > 1:
            continue
        print(row, col, pos)
        isModified = True
        Pane[row][col] = map(int, pos)
        rPane[col][row] = map(int, pos)
        blank.remove((row, col))
    if not isModified:
        break

print(Pane)