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

answer = []

def distance(r1, c1, r2, c2):
    return abs(r1-r2) + abs(c1-c2)

for chicken_combination in combinations(chicken, M):
    ck_distance = 0
    for r2, c2 in house:
        tmp_distance = float('inf')
        for i in range(M):
            r1, c1 = chicken_combination[i]
            tmp_distance = min(tmp_distance, distance(r1, c1, r2, c2))
        ck_distance += tmp_distance
    answer.append(ck_distance)
print(min(answer))