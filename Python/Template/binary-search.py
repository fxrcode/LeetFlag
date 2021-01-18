
class Solution:
    """Binary Search template
    Use case
    ---
    * Sorted Array
    * find better solution than O(N)
    * OOXX pattern

    Complexity
    ---
    Time: O(logN)
    Space: O(1)
    """

    def bin_search(self, nums, target):
        if not nums:
            return -1

        start, end = 0, len(nums)-1

        # must be start+1 < end!
        while start + 1 < end:
            # The result of division is always a float
            # mid = (start+end)/2

            # Integer division rounds down for both positive and negative numbers.
            mid = (start+end) // 2

            # do <, =, > case first, then we can check if = can be merge into other case
            if nums[mid] < target:
                start = mid
            elif nums[mid] == target:
                end = mid
            else:
                end = mid

        # when out of loop, start is adjacent to end. (start+1 >= end)
        # if we want to get 1st postition of target, then we check start before end
        if nums[start] == target:
            return start
        if nums[end] == target:
            return end

        # if we got here, means we didn't find target
        return -1


test = Solution()
print(test.bin_search([1, 2, 3, 5, 9], 10))
print(test.bin_search([1, 2, 3, 5, 9], 3))
print(test.bin_search([1, 2, 3, 5, 9], 9))
print(test.bin_search([1, 2, 3, 5, 9], 1))
