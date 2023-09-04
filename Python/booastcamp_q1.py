from sys import stdin

def sol(arr):
    count = 1
    result = []
    arr.sort()
    for i in range(len(arr)-1):
        if arr[i] == arr[i+1]:
            count += 1
        else:
            if count > 1:
                result.append(count)
                count = 1
    if count > 1:
        result.append(count)
    if not result:
        result.append(-1)
    return result

print(sol([3,5,7,9,1]))
