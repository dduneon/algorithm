import sys

class node:
    def __init__(self, data, before=None, next=None):
        self.data = data
        self.before = before
        self.next = next

    def printdata(self):
        self = self.next
        while self.next:
            print(self.data, end='')
            self = self.next
        print(self.data, end='')

def sol():
    s = sys.stdin.readline().strip()
    _ = sys.stdin.readline()
    head = node(' ')
    cur = head

    # data add
    for i in range(0, len(s)):
        nextnode = node(s[i])
        cur.next = nextnode
        nextnode.before = cur
        cur = nextnode

    # command
    command = sys.stdin.read().split('\n')
    for com in command:
        if 'L' in com:
            if cur.before:
                cur = cur.before
        elif 'D' in com:
            if cur.next:
                cur = cur.next
        elif 'B' in com:
            if cur.data != ' ':
                cur.before.next = cur.next
                if cur.next:
                    cur.next.before = cur.before
                cur = cur.before
        elif 'P' in com:
            newnode = node(com[2])
            newnode.before = cur
            newnode.next = cur.next
            if cur.next:
                newnode.next.before = newnode
            cur.next = newnode
            cur = newnode
    head.printdata()

sol()