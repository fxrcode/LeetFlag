class ConnectingGraph3:
    """
    @param a: An integer
    @param b: An integer
    @return: nothing
    """
    def __init__(self, n):
        # initialize your data structure here.
        self.father = {}
        self.cc = n
        for i in range(1, n+1):
            self.father[i] = i

    def connect(self, a, b):
        # write your code here
        root_a = self.find(a)
        root_b = self.find(b)
        if root_a != root_b:
            self.cc -= 1
            self.father[root_a] = self.father[root_b]

    """
    @return: An integer
    """
    def query(self):
        # write your code here
        return self.cc

    def find(self, n):
        if n == self.father[n]:
            return n

        self.father[n] = self.find(self.father[n])
        return self.father[n]

cg = ConnectingGraph3(5)
print(cg.query())
cg.connect(1, 2)
print(cg.query())
cg.connect(2, 4)
print(cg.query())
cg.connect(1, 4)
print(cg.query())