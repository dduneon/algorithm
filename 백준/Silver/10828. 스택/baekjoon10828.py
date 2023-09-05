import sys

class solv:
    def __init__(self):
        self.stack = []

    def push(self, item):
        self.stack.append(item)

    def top(self):
        if self.empty() == 1: return -1
        return self.stack[-1]

    def size(self):
        return len(self.stack)

    def empty(self):
        if self.size() > 0: return 0
        else: return 1

    def pop(self):
        if self.empty() == 1:
            return -1
        return self.stack.pop()

_ = sys.stdin.readline()
input = sys.stdin.read().split('\n')
st = solv()

for str in input:
    if len(str.split()) > 1:
        st.push(str.split()[1])
    if str == 'pop': print(st.pop())
    elif str == 'size': print(st.size())
    elif str == 'empty': print(st.empty())
    elif str == 'top': print(st.top())
