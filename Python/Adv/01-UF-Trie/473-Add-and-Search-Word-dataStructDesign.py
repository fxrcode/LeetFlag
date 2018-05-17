class TrieNode:
    def __init__(self) -> None:
        self.children = {}
        self.is_word = False


class WordDictionary:
    """Your submission beats59.40 %Submissions
    """
    def __init__(self) -> None:
        self.root = TrieNode()

    """
    @param: word: Adds a word into the data structure.
    @return: nothing
    """
    def addWord(self, word):
        # write your code here
        node = self.root
        for c in word:
            if c not in node.children:
                node.children[c] = TrieNode()
            node = node.children[c]

        node.is_word = True

    """
    @param: word: A word could contain the dot character '.' to represent any one letter.
    @return: if the word is in the data structure.
    """
    def search(self, word):
        # write your code here
        if word is None:
            return False
        return self.search_helper(word, self.root, 0)

    def search_helper(self, word, node: TrieNode, i):
        """DFS to search word[i:] in the Trie from node
        return Boolean
        """
        # base
        if node is None:
            return False
        if i >= len(word):
            return node.is_word

        # recursion
        c = word[i]
        if c != '.':
            return self.search_helper(word, node.children.get(c), i+1)

        # case of .
        for child in node.children:
            if self.search_helper(word, node.children[child], i+1):
                return True
        # if not found
        return False


wd = WordDictionary()
wd.addWord("a")
print(wd.search("."))

wd = WordDictionary()
print(wd.search("lintcode"))
print(wd.search("lint"))
wd.addWord("lint")
print(wd.search("lint"))