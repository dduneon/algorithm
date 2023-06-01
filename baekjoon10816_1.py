import sys
from collections import Counter

_ = sys.stdin.readline()
N = sys.stdin.readline().strip().split()
_ = sys.stdin.readline()
M = sys.stdin.readline().strip().split()

C = Counter(N)
print(' '.join(f'{C[m]}' if m in C else '0' for m in M))