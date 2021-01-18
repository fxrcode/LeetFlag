class UF:
    def __init__(self, n):
        self.father = {}
        for i in range(1, n+1):
            self.father[i] = i

    def union(self, a, b):
        self.father[self.find(a)] = self.find(b)

    def find(self, node):
        path = []
        while node != self.father[node]:
            path.append(node)
            node = self.father[node]

        for n in path:
            self.father[n] = node
        return node