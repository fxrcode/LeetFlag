from collections import deque

DIRECTIONS = [[0,1], [0,-1], [1,0], [-1,0]]

class Solution:
    """
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

s = Solution()
grid = [
  [1,1,0,0,0],
  [0,1,0,0,1],
  [0,0,0,1,1],
  [0,0,0,0,0],
  [0,0,0,0,1]
]
print(s.numIslands(grid))

grid = [
  [1,1]
]
print(s.numIslands(grid))

