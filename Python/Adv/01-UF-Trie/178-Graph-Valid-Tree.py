class Solution:
    """
    @param n: An integer
    @param edges: a list of undirected edges
    @return: true if it's a valid tree, or false
    """
    def validTree(self, n, edges):
        """[summary]
        Method 1: UF. Because valid tree has no cycle => E = V-1. And CC === 1, so no separated groups.
        Better understand some cases quickly, and no need to check cycle!
        """
        if not edges:
            return False

        self.cc = 0
        self.father = {}

        # E == V-1
        if len(edges) != n-1:
            return False

        for i in range(n):
            self.father[i] = i
            self.cc += 1

        for a,b in edges:
            self.union(a,b)

        return self.cc == 1

    def union(self, a,b):
        root_a, root_b = self.find(a), self.find(b)
        if root_a != root_b:
            self.father[root_a] = root_b
            self.cc -= 1

    def find(self, node):
        path = []
        while node != self.father[node]:
            path.append(node)
            node = self.father[node]
        # node is root
        for n in path:
            self.father[n] = node
        return node

s = Solution()
n,edges = 5, [[0, 1], [0, 2], [0, 3], [1, 4]]
print(s.validTree(n,edges))
n,edges = 5, [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
print(s.validTree(n,edges))
