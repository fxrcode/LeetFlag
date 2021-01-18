class UnionFind:
    def __init__(self) -> None:
        self.father = {}
        self.size_of_set = {}
        self.num_of_set = 0

    def add(self, x):
        # if node exists, ops ignored
        if x in self.father:
            return
        pass

    def merge(self, x, y):
        pass

    def find(self, x):
        pass

    def is_connected(self, x, y):
        pass

    def get_num_set(self):
        pass

    def get_size_of_set(self, x):
        pass
    