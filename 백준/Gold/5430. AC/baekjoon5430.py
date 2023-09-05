import sys
T = int(sys.stdin.readline().strip())
for _ in range(T):
    p = sys.stdin.readline().strip()
    n = int(sys.stdin.readline())
    c = sys.stdin.readline()[1:-2]
    x = c.split(',') if c else ''
    lcur = 0
    rcur = len(x)-1
    isReverse = False
    isError = False
    cur = 0
    for com in p:
        if com == 'R':
            isReverse = not isReverse
        elif com == 'D':
            if lcur > rcur:
                isError = True
                break
            else:
                if isReverse:
                    rcur -= 1
                else:
                    lcur += 1
    if isError:
        print('error')
    else:
        if not isReverse:
            print('[', end='')
            print(','.join(x[i] for i in range(lcur, rcur+1, 1)), end='')
            print(']')
        else:
            print('[', end='')
            print(','.join(x[i] for i in range(rcur, lcur-1, -1)), end='')
            print(']')