from sys import stdin
import math
_ = stdin.readline()
N = list(map(int, stdin.readline().split()))

array = [True for _ in range(1001)]
array[0], array[1] = False, False
for i in range(2, int(math.sqrt(1000)) + 1):
    if array[i]:
        j = 2
        while i * j <= 1000:
            array[i*j] = False
            j += 1

count = 0
for n in N:
    if array[n]:
        count += 1
print(count)