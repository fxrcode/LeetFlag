class ConnectingGraph2:
    """
    @param: n: An integer
    """
    def __init__(self, n):
        # do intialization if necessary
        self.father = {}
        self.count = {}
        for i in range(1, n+1):
            self.father[i] = i
            self.count[i] = 1

    """
    @param: a: An integer
    @param: b: An integer
    @return: nothing
    """
    def connect(self, a, b):
        # write your code here
        root_a = self.find(a)
        root_b = self.find(b)
        if root_a != root_b:
            self.father[root_a] = root_b
            self.count[root_b] += self.count[root_a]

    """
    @param: a: An integer
    @return: An integer
    """
    def query(self, a):
        # write your code here
        root_a = self.find(a)
        return self.count[root_a]

    def find(self, n):
        if n == self.father[n]:
            return n
        self.father[n] = self.find(self.father[n])
        return self.father[n]

cg = ConnectingGraph2(5)
print(cg.query(1))
cg.connect(1, 2)
print(cg.query(1))
cg.connect(2, 4)
print(cg.query(1))
cg.connect(1, 4)
print(cg.query(1))