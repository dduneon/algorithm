import sys


class Node:

    def __init__(self, data, next=None):
        self.data = data
        self.next = next


N, K = sys.stdin.readline().strip().split()
start = Node(0)
head = Node(1)
start.next = head
cur = head
for i in range(2, int(N)+1):
    newnode = Node(i)
    cur.next = newnode
    cur = newnode
cur.next = head
# 원형 LinkedList 구축

print('<', end='')
if int(K) != 1:
    while start != start.next:
        for _ in range(int(K)-1):
            start = start.next
        print(start.next.data, end=', ')
        start.next = start.next.next
    print(start.data, end='>')
else:
    for i in range(1, int(N)):
        print(i, end=', ')
    print(int(N), end='>')
