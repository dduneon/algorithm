from sys import stdin
from itertools import combinations

N, M = map(int, stdin.readline().split())

house = []
chicken = []

for i in range(N):
    row = list(map(int, stdin.readline().split()))
    for j in range(N):
        if row[j] == 1:
            house.append((i, j))
        elif row[j] == 2:
            chicken.append((i, j))

selected = list(combinations(chicken, M))
for r1, c1 in selected:
    tmp_distance = 0
    for r2, c2 in house:
        tmp_distance += distance(r1, c1, r2, c2)
def distance(r1, c1, r2, c2):
    return abs(r1-r2) + abs(c1-c2)




