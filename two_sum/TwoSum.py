# -*- coding:utf-8 -*-

# Two Sum
# Given an array of integers, 
# return indices of the two numbers 
# such that they add up to a specific target.
def twoSum(nums, target):
    dict = {}
    for idx, num in enumerate(nums):
        complement = target - num
        if complement in dict:
            return [dict[complement], idx]
        dict[num] = idx
    return "No two sum solution."

if __name__ == "__main__":
    nums = [2, 7, 5, 11]
    target = 18
    print(twoSum(nums, target))
