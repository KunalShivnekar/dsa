package leetcode

fun climbStairs(n: Int): Int {
    var a=1
    var b=1
    var step = 1

    for (i in 1 until n){
        step = a+b
        a = b
        b = step
    }
    return step
}

//198. House Robber
fun rob(nums: IntArray): Int {
    if (nums.size==1) return nums[0]
    if (nums.size==2) return Math.max(nums[0],nums[1])
    var a = nums[0]
    var b = nums[1]
    var max = 0
    var temp = 0
    for (i in 2 until nums.size){
        max = Math.max(Math.max((a+nums[i]),b),temp+nums[i])
        temp=a
        a=b
        b=max
    }
    return max
}