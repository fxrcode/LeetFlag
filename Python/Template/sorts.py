class Solution:
    def quick_sort(self, A, start, end):
        if start >= end:
            return

        left, right = start, end
        pivot = A[(start+end) // 2]
        # partition template
        while left <= right:
            while left <= right and A[left] < pivot:
                left += 1
            while left <= right and A[right] > pivot:
                right -= 1

            if left <= right:
                A[left], A[right] = A[right], A[left]
                left += 1
                right -= 1

        # after partition, [right, left] are adjacent
        self.quick_sort(A, start, right)
        self.quick_sort(A, left, end)

    def merge_sort(self, A, start, end, temp):
        if start >= end:
            return

        # do the left interval
        self.merge_sort(A, start, (start + end) // 2, temp)
        # do the right interval
        self.merge_sort(A, (start+end) // 2 + 1, end, temp)
        # merge sorted left/right intervals
        self.merge(A, start, end, temp)

    def merge(self, A, start, end, temp):
        middle = (start+end) // 2
        left_idx = start
        right_idx = middle + 1
        idx = start

        while left_idx <= middle and right_idx <= end:
            if A[left_idx] < A[right_idx]:
                temp[idx] = A[left_idx]
                left_idx += 1
                idx += 1
            else:
                temp[idx] = A[right_idx]
                right_idx += 1
                idx += 1

        while left_idx <= middle:
            temp[idx] = A[left_idx]
            left_idx += 1
            idx += 1

        while right_idx <= end:
            temp[idx] = A[right_idx]
            right_idx += 1
            idx += 1

        for i in range(start, end+1):
            A[i] = temp[i]

test = Solution()
A = [33, 42, 9, 37, 8, 47, 5, 29, 49, 31, 4, 48, 16, 22, 26]
test.quick_sort(A, 0, len(A)-1)
print(A)

B = [33, 42, 9, 37, 8, 47, 5, 29, 49, 31, 4, 48, 16, 22, 26]
temp = [0] * len(B)
test.merge_sort(B, 0, len(B)-1, temp)
print(B)
