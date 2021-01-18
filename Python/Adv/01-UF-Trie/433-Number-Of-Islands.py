from collections import deque

DIRECTIONS = [[0,1], [0,-1], [1,0], [-1,0]]

class Solution:
    """2nd use UF solve this Connected Components problem
    @param grid: a boolean 2D matrix
    @return: an integer
    """
    def numIslands(self, grid):
        # write your code here
        # cornor case:
        if not grid or not grid[0]:
            return 0

        self.father = {}
        self.cc = 0
        r, c = len(grid), len(grid[0])

        for x in range(r):
            for y in range(c):
                if grid[x][y]:
                    self.father[(x,y)] = (x,y)
                    self.cc += 1

        for x in range(r):
            for y in range(c):
                if grid[x][y]:
                    for dx,dy in DIRECTIONS:
                        xx,yy = x+dx, y+dy
                        if self.is_valid(grid, xx,yy) and grid[xx][yy]:
                            self.union((x,y), (xx,yy))

        return self.cc

    def union(self, a, b):
        root_a, root_b = self.find(a), self.find(b)
        if root_a != root_b:
            self.father[root_a] = root_b
            self.cc-= 1
            
    def find(self, node):
        path = []
        while node != self.father[node]:
            path.append(node)
            node = self.father[node]

        # node is root
        for p in path:
            self.father[p] = node
        return node

    def is_valid(self, grid, x,y):
        r = len(grid)
        c = len(grid[0])
        if not (0 <= x < r and 0 <= y < c):
            return False
        return True

#####################################################################
#                                                                   #
#                             BANNER                                #
#                                                                   #
#####################################################################
class Solution_BFS:
    """1st use BFS to solve this Connected Components problem
    @param grid: a boolean 2D matrix
    @return: an integer
    """
    def numIslands(self, grid):
        # write your code here
        self.visited = set() # (x,y) -> visited
        cc = 0
        for r in range(len(grid)):
            for c in range(len(grid[0])):
                if grid[r][c] == 1 and (r,c) not in self.visited:
                    self.bfs(grid, r,c)
                    cc+= 1
        return cc


    def bfs(self, grid, r, c):
        q = deque([(r,c)])  # fxr: why [(r,c)]
        self.visited.add((r,c))

        while q:
            x,y = q.popleft()
            for dx, dy in DIRECTIONS:
                next_x = x+dx
                next_y = y+dy
                if not self.is_valid(grid, next_x, next_y):
                    continue
                q.append((next_x, next_y))
                self.visited.add((next_x, next_y))   # fxr: better update visited along with q append, check bfs_topo

    def is_valid(self, grid, r, c):
        n, m = len(grid), len(grid[0])
        if not (0<= r < n and 0<= c < m):
            return False
        if (r,c) in self.visited:
            return False
        return grid[r][c]   # fxr: What? you can return T/F & int in a func?

sb = Solution_BFS()
grid = [
  [1,1,0,0,0],
  [0,1,0,0,1],
  [0,0,0,1,1],
  [0,0,0,0,0],
  [0,0,0,0,1]
]
print(sb.numIslands(grid))

# grid = [
#   [1,1]
# ]
# print(sb.numIslands(grid))

s = Solution()
s.numIslands(grid)
print(s.numIslands(grid))

