class ConnectingGraph:
    """
    @param: n: An integer
    """
    def __init__(self, n):
        # do intialization if necessary
        self.father = {}
        for i in range(1, n+1):
            self.father[i] = i

    """
    @param: a: An integer
    @param: b: An integer
    @return: nothing
    """
    def connect(self, a, b):
        # write your code here
        self.father[self.find(a)] = self.find(b)

    """
    @param: a: An integer
    @param: b: An integer
    @return: A boolean
    """
    def query(self, a, b):
        # write your code here
        # print(self.father)
        return self.father[self.find(a)] == self.father[self.find(b)]

    def find(self, n):
        if n == self.father[n]:
            return n

        self.father[n] = self.find(self.father[n])
        return self.father[n]


cg = ConnectingGraph(5)
print(cg.query(1, 2))
cg.connect(1, 2)
print(cg.query(1, 3))
cg.connect(2, 4)
print(cg.query(1, 4))