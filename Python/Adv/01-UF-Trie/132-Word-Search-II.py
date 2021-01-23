DIRECTIONS = [(0, -1), (0, 1), (-1, 0), (1, 0)]

class Solution:
    """
    @param board: A list of lists of character
    @param words: A list of string
    @return: A list of string
    """

    def wordSearchII(self, board, words):
        """ DFS with Trie
        Your submission beats47.60 %Submissions
        """
        if board is None or words is None:
            return []

        self.board = board
        self.words = words

        # build up trie
        self.trie = Trie()
        for w in words:
            self.trie.insert(w)

        n, m = len(board), len(board[0])

        ret = set()
        visited = set()
        for x in range(n):
            for y in range(m):
                visited.add((x,y))
                self.dfs(x,
                         y,
                         self.trie.root.children.get(board[x][y]),
                         visited,
                         ret)
                visited.remove((x,y))

        return list(ret)

    def dfs(self, x, y, node, visited, ret):
        """DFS for Trie soluation
        Note: python set pop is random remove!
        """
        # base
        if node is None:
            return

        if node.is_word:
            # print(list(visited))
            ret.add(node.word)
            # return

        # recur
        for dx, dy in DIRECTIONS:
            xx = x + dx
            yy = y + dy
            if self.is_valid(xx, yy) and (xx, yy) not in visited:
                visited.add((xx, yy))
                self.dfs(xx,
                         yy,
                         node.children.get(self.board[xx][yy]),
                         visited,
                         ret)
                visited.remove((xx,yy))
                # visited.pop()

    def is_valid(self, x, y):
        R, C = len(self.board), len(self.board[0])
        if not (0 <= x < R and 0 <= y < C):
            return False
        return True


#####################################################################
#                                                                   #
#                          Uses Hash                                #
#                                                                   #
#####################################################################
    def wordSearchII_hash(self, board, words):
        """Use Hash rather Trie, needs prefix set
        """
        if board is None or len(board) == 0:
            return []

        self.word_set = set(words)
        self.prefix_set = set()
        for w in words:
            for i in range(len(w)):
                self.prefix_set.add(w[:i+1])

        # search from each point in the board
        result = set()
        for x in range(len(board)):
            for y in range(len(board[0])):
                self.search(x,y,
                            board[x][y],
                            set([(x,y)]),
                            result)
        return list(result)

    def search(self, x,y, path, visited, result):
        """ DFS search for hash solution
        """
        # base
        if path in self.word_set:
            result.add(path)

        # prune
        if path not in self.prefix_set:
            return

        for dx,dy in DIRECTIONS:
            xx = x + dx
            yy = y + dy
            if not self.is_valid(xx,yy):
                continue
            if (xx,yy) in visited:
                continue
            visited.add((xx,yy))
            self.search(xx,yy,
                        path + self.board[xx][yy],
                        visited,
                        result)
            visited.remove((xx,yy))


# helper data struct classes
class TrieNode:
    def __init__(self) -> None:
        self.children = {}
        self.is_word = False
        self.word = None


class Trie:
    def __init__(self) -> None:
        self.root = TrieNode()

    def insert(self, word) -> None:
        node = self.root
        for c in word:
            if c not in node.children:
                node.children[c] = TrieNode()
            node = node.children[c]

        node.word = word
        node.is_word = True

    def find(self, word) -> TrieNode:
        node = self.root
        for c in word:
            node = node.children.get(c)
            if node is None:
                return None
        return node




#####################################################################
#                                                                   #
#                             TESTER                                #
#                                                                   #
#####################################################################
s = Solution()
# board = ["doaf", "agai", "dcan"]
# words = ["dog", "dad", "dgdg", "can", "again"]
# print(s.wordSearchII(board, words))

# board = ["abce", "sfcs", "adee"]
# words = ["see", "se"]
# print(s.wordSearchII(board, words))

# board = ["b", "a", "b", "b", "a"]
# words = ["babbab", "b", "a", "ba"]
# print(s.wordSearchII(board, words))

board = ["abce", "sfes", "adee"]
words = ["abceseeefs","abceseedasfe"] # ["abceseeef", "abceseeefs", "abceseedasfe"] # ["aba", "abceseeefb"]
print(s.wordSearchII(board, words))
print(s.wordSearchII_hash(board, words))