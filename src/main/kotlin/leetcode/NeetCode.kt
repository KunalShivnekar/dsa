package leetcode

import trees.BinaryTreeNode
import java.util.*

//5. Longest Palindromic Substring
fun longestPalindromeBottomUp(s: String): String {
    var c = s.toCharArray()
    var isPalindrome =  Array(c.size){ BooleanArray(c.size) }
    var palindromes = 0
    var start = 0
    var end = 0
    var longestLength = 0

    for (i in c.indices.reversed()){
        for (j in i until c.size){
            if (i==j){
                isPalindrome[i][j] = true
            } else if (c[i] == c[j]) {
                if (j-i<=2){
                    isPalindrome[i][j] = true
                } else {
                        isPalindrome[i][j] = isPalindrome[i+1][j-1]
                }
            }

            if (isPalindrome[i][j]){
                palindromes++
                if((j-i+1)>longestLength){
                    longestLength = j-i+1
                    start = i
                    end = j+1
                }
            }
        }
    }
    println(palindromes)
    println("$start - $end")
    return s.substring(start,end)
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

fun longestPalindromeTopDown(s: String): String {
    var c = s.toCharArray()
    val Q:Queue<Pair<Int,Int>> = LinkedList()
    val map = HashMap<Pair<Int,Int>,Boolean>()
    Q.add(Pair(0,c.size-1))
    var word = Pair(0,0)
    while(Q.isNotEmpty()){
        word = Q.remove()
        val l = if (map.containsKey(word))
                    false
                else {
                    var i = word.first
                    var j = word.second
                    while (i<j){
                        if (c[i]==c[j]){
                            i++
                            j--
                        } else break
                    }
                    i>=j
                }
        if (l.not()){
            map[word] = false
            Q.add(Pair(word.first, word.second-1))
            Q.add(Pair(word.first+1, word.second))
        } else break
    }

    return s.substring(word.first,word.second+1)
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

// Frequent subtree sum
var maxFrequency = 0
fun findFrequentTreeSum(root: BinaryTreeNode?): IntArray {
    val map = HashMap<Int,Int>()
    subTreeSum(root, map)
    var res = mutableListOf<Int>()
    for (entry in map){
        if(entry.value==maxFrequency)
            res.add(entry.key)
    }
    return res.toIntArray()

}

fun subTreeSum(root: BinaryTreeNode?, map:HashMap<Int,Int>): Int {
    if(root == null) return 0
    val sum = root.data + subTreeSum(root.left,map) + subTreeSum(root.right,map)
    map[sum] = map.getOrDefault(sum,0) + 1
    if (map.getValue(sum) > maxFrequency)
        maxFrequency = map.getValue(sum)
    return sum
}