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
for i in range(2, int(N)+2):
    newnode = Node(i)
    cur.next = newnode
    cur = newnode

cur.next = head
#
while(head.next):
    print(head.data)
    head = head.next

