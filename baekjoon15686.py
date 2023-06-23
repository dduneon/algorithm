from sys import stdin
N, M = map(int, stdin.readline().split())

selected = []
house = []
chicken = []

for i in range(N):
    row = list(map(int, stdin.readline().split()))
    for j in range(N):
        if row[j] == 1:
            house.append((i, j))
        elif row[j] == 2:
            chicken.append((i, j))

ch_distance = float('inf')
def dfs(idx, depth):
    print(idx, depth)
    if idx >= len(chicken):
        return
    if depth == M:
        for r1, c1 in selected:
            temp_distance = 0
            for r2, c2 in house:
                temp_distance += distance(r1, c1, r2, c2)
        global ch_distance
        ch_distance = min(ch_distance, temp_distance)
        return
    else:
        selected.append(chicken[idx])
        dfs(idx+1, depth+1)
        selected.pop()
        dfs(idx+1, depth)

def distance(r1, c1, r2, c2):
    return abs(r1-r2) + abs(c1-c2)

dfs(0, 0)
print(ch_distance)




