import sys
def sol():
    _ = sys.stdin.readline()
    i = sys.stdin.read().split()

    for w in sorted(sorted(list(set(i))), key=len):
        print(w)

sol()