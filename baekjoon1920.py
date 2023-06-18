# 수 찾기
from bisect import bisect_left, bisect_right

N = int(input())
A = list(map(int, input().split()))

A.sort()

M = int(input())
B = list(map(int, input().split()))

answer = []

def nums_in_array(array, target):
    return bisect_right(array, target) - bisect_left(array, target)

for i in range(M):
    if nums_in_array(A, B[i]) > 0:
        answer.append(1)
    else:
        answer.append(0)

for num in answer:
    print(num)