package leetcode

import java.util.LinkedList
import java.util.Queue

//5. Longest Palindromic Substring
fun longestPalindrome(s: String): String {
    val Q:Queue<String> = LinkedList<String>()
    val map = HashMap<String,Int>()
    Q.add(s)
    var word = ""
    while(Q.isNotEmpty()){
        word = Q.remove()
        val l = if (map.containsKey(word))
                    -1
                else
                    checkPalindrome(word)
        if (l==-1){
            map.put(word,0)
            Q.add(word.dropLast(1))
            Q.add(word.drop(1))
        } else break
    }
return word
}

fun checkPalindrome(s:String):Int{
    val l = s.length
    var c = s.toCharArray()
    var i = 0
    var j = l-1
    while (i<j){
        if (c[i]==c[j]){
            i++
            j--
        } else break
    }
    return if (i>=j)
        l
    else
        -1
}





//300. Longest Increasing Subsequence - Incorrect soln
fun lengthOfLIS(nums: IntArray): Int {
    var res = 1
    for (i in 1 until nums.size){
        if((nums[i] - nums[i-1])>0)
            res++
    }
    return res
}

//70. Climbing Stairs
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