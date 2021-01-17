from heapq import heappush, heappop


class Heap:
    """heap + function to delete specific node with lazy-deletion
    Use case
    ---
    * find max/min value
    * find kth minimum, O(nlogK)
    * if required O(logN) operation
    """

    def __init__(self) -> None:
        self.minheap = []
        self.deleted_set = set()

    def push(self, index, val):
        heappush(self.minheap, (val, index))

    def _lazy_deletion(self):
        while self.minheap and self.minheap[0][1] in self.deleted_set:
            heappop(self.minheap)

    def top(self):
        self._lazy_deletion()
        return self.minheap[0]

    def pop(self):
        self._lazy_deletion()
        heappop(self.minheap)

    def delete(self, index):
        self.deleted_set.add(index)

    def is_empty(self):
        return not bool(self.minheap)
