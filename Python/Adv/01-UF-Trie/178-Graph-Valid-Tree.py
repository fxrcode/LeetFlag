from collections import defaultdict, deque

class Solution:
    """
    @param n: An integer
    @param edges: a list of undirected edges
    @return: true if it's a valid tree, or false
    """
    def validTree(self, n, edges):
        """Method 1: UF. Because valid tree has no cycle => E = V-1. And CC === 1, so no separated groups.
        Better understand some cases quickly, and no need to check cycle!
        Your submission beats30.40 %Submissions
        """

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

    def validTree2(self, n, edges):
        """Using BFS
        Your submission beats10.20 %Submissions
        """
        # build up the graph
        if n-1 != len(edges):
            return False

        # neigs = {} # src: set of dst's
        # https://www.educative.io/edpresso/learning-about-defaultdict-in-python
        # create a dict of lists: key with no value is assigned with an empty list
        ## you can also init with int, or set
        neigs = defaultdict(list)
        for s,d in edges:
            neigs[s].append(d)
            neigs[d].append(s)

        # BFS
        visited = set()
        q = deque([0])
        visited.add(0)

        while q:
            cur = q.popleft()
            for neig in neigs[cur]:
                if neig in visited:
                    # return False
                    continue
                q.append(neig)
                visited.add(neig)

        return len(visited) == n


#####################################################################
#                                                                   #
#                             BANNER                                #
#                                                                   #
#####################################################################

s = Solution()
n,edges = 5, [[0, 1], [0, 2], [0, 3], [1, 4]]
print(s.validTree(n,edges))
print(s.validTree2(n,edges))

n,edges = 5, [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
print(s.validTree(n,edges))
