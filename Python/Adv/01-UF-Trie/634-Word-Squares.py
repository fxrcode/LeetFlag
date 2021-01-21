class Solution:
    """Your submission beats97.20 %Submissions
    @param words: a set of words without duplicates
    @return: all word squares
    """
    def wordSquares(self, words):
        # write your code here
        self.trie = Trie()
        for word in words:
            self.trie.insert(word)

        squares = []
        for word in words:
            self.perm([word], squares)
        return squares

    def perm(self, square, squares):
        # base
        n, pos = len(square[0]), len(square)
        if n == pos:
            squares.append(list(square))
            return

        # prune for all rest of lines before permutation DFS
        for col in range(pos, n):
            pref = ''.join(square[r][col] for r in range(pos))
            if self.trie.find(pref) is None:
                return

        # recursion
        prefix = ''.join(square[i][pos] for i in range(pos))
        for word in self.trie.words_prefix(prefix):
            square.append(word)
            self.perm(square, squares)
            square.pop()


class TrieNode:
    def __init__(self) -> None:
        self.children = {}
        self.is_word = False
        self.word_list = []     # all words have prefix as in this Node

class Trie:
    def __init__(self) -> None:
        self.root = TrieNode()

    def insert(self, word):
        node = self.root
        for c in word:
            if c not in node.children:
                node.children[c] = TrieNode()
            node = node.children[c]
            node.word_list.append(word)

        node.is_word = True

    def find(self, word):
        node = self.root
        for c in word:
            node = node.children.get(c)
            if node is None:
                return None
        return node

    def words_prefix(self, prefix):
        node = self.find(prefix)
        return [] if node is None else node.word_list


s = Solution()
words = ["area","lead","wall","lady","ball"]
print(s.wordSquares(words))

words = ["abat","baba","atan","atal"]
print(s.wordSquares(words))
