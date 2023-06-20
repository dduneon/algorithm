from sys import stdin
N = int(stdin.readline())
score = []
for n in range(N):
    score.append(list(map(int, stdin.readline().split())))

minscore = 10000
def dfs(idx, startmember):
    if idx >= N:
        return

    if len(startmember) == N/2:
        sc = cal(startmember)
        global minscore
        if minscore > sc:
            minscore = sc
        return

    dfs(idx+1, startmember + [idx])
    dfs(idx+1, startmember)

def cal(startmember):
    linkmember = [i for i in range(N) if i not in startmember]
    startscore = 0
    linkscore = 0
    for mem in startmember:
        for other in startmember:
            if mem == other:
                continue
            startscore += score[mem][other]
    for mem in linkmember:
        for other in linkmember:
            if mem == other:
                continue
            linkscore += score[mem][other]
    return startscore-linkscore if startscore >= linkscore else linkscore-startscore

dfs(0, [])
print(minscore)