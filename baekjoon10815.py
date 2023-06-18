_ = input()
N_set = set(input().split())
_ = input()
print(' '.join(['1' if i in N_set else '0' for i in input().split()]))