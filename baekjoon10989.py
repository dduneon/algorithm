a = [None] * 10001
b = map(int, open(0))
next(b)

for i in b:
    if a[i] is None:
        a[i] = 1
    else:
        a[i] += 1

for i in range(10001):
    if a[i] is not None:
        for _ in range(a[i]):
            print(i)
