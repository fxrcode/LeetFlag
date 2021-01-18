from collections import deque

class Solution:
    def bfs(self, start_node):
        """BFS used queue + dist map
        Use case
        ---
        * topological sorting
        * if puzzle has keyword: connection components
        * level order traversal
        * shortest path in Simple Graph
        * Given transform rule, find minimum steps to transform from init to target state

        Complexity
        ---
        Time: O(E+V)
        Space: O(V)
        """
        # needs queue for BFS
        # and dist map to mark visited and SSSP
        q = deque([start_node])
        dist = {start_node: 0}

        while q:
            node = q.popleft()
            if self.is_target(node):
                return "Found target"
            for neig in node.neighbors:
                if neig in dist:
                    continue
                q.append(neig)
                dist[neig] = dist[neig] + 1

        # if we need to find shortest dist from start_nodde to ALL other nodes
        return dist

        # if we need all Connected nodes
        return dist.keys

        # if we need SSSP from start to end node
        return dist[end_node]

    def topo_sort(self, nodes):
        """Use BFS + in-degrees to topological sort
        it also do cycle check
        """
        # calcuate in-degrees
        indegrees = self.calc_indegress(nodes)

        # init queue with all nodes with 0 in-degree
        q = deque([n for n in nodes if indegrees[n] == 0])

        # BFS to process all nodes: decrease indegrees
        topo_order = []
        while q:
            node = q.popleft()
            topo_order.append(node)
            for neig in node.neighbors:
                indegrees[neig] -= 1
            if indegrees[neig] == 0:
                q.append(neig)

        # check if cycle
        if len(topo_order) != len(nodes):
            return False, "no topo order due to cycle!"
        return topo_order


    def calc_indegrees(self, nodes):
        counter = {node: 0 for node in nodes}
        for node in nodes:
            for neig in node.neighbors:
                counter[neig] += 1
        return counter

    def is_target(self, node):
        """ dummy endpoint check
        """
        if node.val == 42:
            return True
        return False


# Definition for a Node.
class Node:
    def __init__(self, val=0, neighbors=None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []
