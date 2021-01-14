class Solution:
    """4 types two-pointers template
    Use case
    ---
    * sliding window
    * if Time complexity O(N) required
    * if in-place required, no extra space
    * if puzzle related to substring/subarray
    * palindrome

    Complexity
    ---
    Time: O(N)
        * it's only decided by inner most loop
        * not related to depth of loops
    Space: O(1)
        * only need 2 pointer (2 int)
    """

    def partition(self, A, start, end):
        """ [i -> ... <- j]
        pointers merge from both side to meeting point
        example: 9chap partition template for quicksort/select
        """
        if start >= end:
            return

        left, right = start, end
        # key point 1: pivot is the value, not the index
        pivot = A[(start+end) // 2]

        # key point 2: every time you compare left&right, it should
        # left <= right, rather left < right
        while left <= right:
            while left <= right and A[left] < pivot:
                left += 1
            while left <= right and A[right] > pivot:
                right -= 1
            if left <= right:
                A[left], A[right] = A[right], A[left]
                left += 1
                right -= 1


    def separate(self, A, meet):
        """ [...  <- i,j ->  ...]
        leaving 2 pointers from meeting point
        ex: palindrome
        """
        left = meet
        right = meet+1
        while left >= 0 and right < len(A):
            #
            if A[left] == 42 and A[right] == -42:
                break
            left -= 1
            right += 1


    def sliding_window(self, A):
        """
        i->
        j-->
        slow fast pointers chasing (aka sliding window)
        ex: Longest Substring Without Repeating Characters
        """
        n = len(A)
        fast = 0
        for slow in range(n):
            # do sth in loop till we find requirement met
            while fast < n and self.sfc_foo(A, slow, fast):
                fast += 1

            # now we can manipulate the range in [slow, fast]
            if self.sfc_foo(A, slow, fast):
                pass


    def merge(self, list1, list2):
        """
        [i-> ...]
        [j-> ...]
        Merge 2 pointers pattern
        """
        new_list = []
        i, j = 0, 0

        # during merge, we only move i,j, no need for sth like list.pop()
        # since pop(0) is O(N) time complexity
        while i < len(list1) and j < len(list2):
            if list1[i] < list2[j]:
                new_list.append(list1[i])
                i += 1
            else:
                new_list.append(list2[j])
                j += 1

        # merge the rest nums into new_list
        # don't do new_list.extend(list1[i:]) or sth like that
        # since list1[i:] will create extra space
        while i < len(list1):
            new_list.append(list1[i])
            i += 1
        while j < len(list2):
            new_list.append(list2[j])
            j += 1
        return new_list


    def sfc_foo(self, A, slow, fast):
        """dummy method for slow/fast chasing pattern
        """
        return True
