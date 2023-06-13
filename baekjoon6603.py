import sys

def DFS(L, startWith):
    if L == 6:
        print(' '.join(str(i) for i in tmp))
    else:
        for i in range(startWith, len(S)):
            tmp[L] = S[i]
            DFS(L+1, i+1)


commands = sys.stdin.read().splitlines()

for command in commands:
    if command == '0':
        exit()
    S = list(map(int, command.strip().split(' ')[1:]))
    tmp = [[] for _ in range(6)]
    DFS(0, 0)
    print()