"""
Definition for a point.
class Point:
    def __init__(self, a=0, b=0):
        self.x = a
        self.y = b
"""
DIRECTIONS = [[0, 1], [0, -1], [1, 0], [-1, 0]]


class Solution:
    """
    @param n: An integer
    @param m: An integer
    @param operators: an array of point
    @return: an integer array
    """
    def numIslands2(self, n, m, operators):
        # write your code here
        self.father = {}
        self.cc = 0
        ret = []

        for i in range(len(operators)):
            (x, y) = operators[i]
            # init itself to root, and incr cc by 1
            if (x, y) in self.father:
                continue
            self.father[(x, y)] = (x, y)
            self.cc += 1

            # union around valid nodes
            for dx, dy in DIRECTIONS:
                xx, yy = x+dx, y+dy
                if (xx,yy) in self.father:
                    self.union((xx, yy), (x, y))

            ret.append(self.cc)

        return ret

    def union(self, a,b):
        root_a = self.father[a]
        root_b = self.father[b]
        if root_a != root_b:
            self.father[root_a] = root_b
            self.cc -= 1

    def find(self, node):
        path = []
        while node != self.father[node]:
            path.append(node)
            node = self.father[node]

        # path compression
        for n in path:
            self.father[n] = node
        # node is the root
        return node


    def is_valid(self, n,m,x,y):
        if not (0 <= x < n and 0 <= y < m):
            return False
        return True


class Point:
    def __init__(self, a=0, b=0):
        self.x = a
        self.y = b

s = Solution()
n, m, A = 3,3, [(0,0), (0,1), (2,2), (2,1)]
print(s.numIslands2(n,m,A))
n,m,A = 4,5,[[1,1],[0,1],[3,3],[3,4]]
print(s.numIslands2(n,m,A))