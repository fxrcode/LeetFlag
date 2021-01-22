class TrieNode:
    """dedicated class to define Node.
    """
    def __init__(self):
        self.children = {}
        self.is_word = False


class Trie:
    """Your submission beats47.40 %Submissions
    """
    def __init__(self):
        # do intialization if necessary
        self.root = TrieNode()

    """
    @param: word: a word
    @return: nothing
    """
    def insert(self, word):
        # write your code here
        node = self.root
        for c in word:
            if c not in node.children:
                node.children[c] = TrieNode()
            node = node.children[c]

        # after loop, node is last letter of word
        node.is_word = True

    def find(self, word):
        node = self.root
        for c in word:
            node = node.children.get(c)
            if node is None:
                return None
        return node

    """
    @param: word: A string
    @return: if the word is in the trie.
    """
    def search(self, word):
        # write your code here
        node = self.find(word)

        return node is not None and node.is_word

    """
    @param: prefix: A string
    @return: if there is any word in the trie that starts with the given prefix.
    """
    def startsWith(self, prefix):
        # write your code here
        node = self.find(prefix)
        return node is not None


#####################################################################
#                                                                   #
#                             BANNER                                #
#                                                                   #
#####################################################################
t = Trie()
t.insert("lintcode")
print(t.search("lint"))
print(t.startsWith("lint"))

print("=======================")

t = Trie()
t.insert("lintcode")
print(t.search("code"))
print(t.startsWith("lint"))
print(t.startsWith("linterror"))
t.insert("linterror")
print(t.search("lintcode"))
print(t.startsWith("linterror"))