import sys
def sol():
    n = int(input())

    words = [set() for _ in range(50)]
    for _ in range(n):
        l = input()
        words[len(l)-1].add(l)

    for w in words:
        if w:
            for w in sorted(list(w)):
                print(w)

sol()