package leetcode

import trees.BinaryTreeNode
import java.util.*
import kotlin.math.max

//332. Reconstruct Itinerary


fun findItinerary(tickets: List<List<String>>): List<String> {

    var map = HashMap<String, MutableList<String>>()
    var res = mutableListOf<String>()

    for(ticket in tickets){
        map.getOrPut(ticket[0]){mutableListOf<String>()}.apply{
            add(ticket[1])
            sort()
        }
    }

    fun dfs (destination:String, target:Int):Boolean {
        res.add(destination)
        if(res.size == target) return true

        var destList = map.get(destination)
        if(destList==null || destList.size==0) return false

        val temp = ArrayList(destList)
        for(t in temp){
            destList.remove(t)
            if(dfs(t,target))
                return true
            else {
                res.removeAt(res.lastIndex)
                destList.add(t)
            }
        }

        return false
    }


    dfs("JFK", tickets.size+1)
    return res
}



//39. Combination Sum
class Solution {
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        dfs(candidates, target, 0, mutableListOf<Int>(), res)
        return res
    }

    private fun dfs(candidates: IntArray, target: Int, index:Int, list: MutableList<Int>, result: MutableList<List<Int>>) {
        if(target == 0){
            result.add(ArrayList(list))
            return
        }

        if(target < 0 || index >= candidates.size)
            return

        for(i in index until candidates.size){
            list.add(candidates[i])
            dfs(candidates, target-candidates[i], i, list, result)
            list.removeAt(list.size-1)
        }
    }
}

// 33. Search in Rotated Sorted Array
fun search(nums: IntArray, target: Int): Int {
    var i = 0
    var j = nums.size-1
    var mid = 0

    while(i<=j){
        mid = i + (j-i)/2
        if(target == nums[mid]) return mid
        if(nums[mid]>=nums[i]){
            if(nums[i] <= target && target < nums[mid])
                j=mid-1
            else
                i = mid+1
        } else {
            if(nums[mid] < target && target <= nums[j])
                i=mid+1
            else
                j = mid-1
        }

    }
    return -1
}


// 153. Find Minimum in Rotated Sorted Array
fun findMin(nums: IntArray): Int {
    var i = 0
    var j = nums.size-1
    var min = Integer.MAX_VALUE
    if((nums[i] < nums[j])|| j==0)
        return nums[0]
    while(i<j){
        var mid = i + (j-i)/2
        if(nums[mid]>nums[i]){
            i = mid+1
        } else {
            j = mid
        }
        min = minOf(min, nums[mid])
    }
    return min
}

//11. Container With Most Water
fun maxArea(height: IntArray): Int {
    var res = 0
    var l = 0
    var r = height.size-1
    while (l<r){
        var area = (r-l) * minOf(height[l], height[r])
        res = maxOf(res, area)
        if(height[l] < height[r]){
            l++
        } else {
            r++
        }
    }
    return res
}


// 15. 3Sum
fun threeSum(nums: IntArray): List<List<Int>> {
    if(nums.size<3){
        return List(0){List<Int>(0){0}}
    }
    if(nums.size==3){
        return if(nums.sum()==0) listOf(nums.toList()) else List(0){List<Int>(0){0}}
    }
    nums.sort()
    val a = MutableList(0){List(3){0}}
    val s = nums.size
    var j = 0
    var k = 0

    for(i in 0 until s-2){
        if(i!=0 && nums[i]==nums[i-1]){
            continue
        }
        j = i+1
        k = s-1
        while(j<k){
            if(nums[i]+nums[j]+nums[k]>0){
                k -= 1
            }
            else if(nums[i]+nums[j]+nums[k]<0){
                j += 1
            }
            else{
                a.add(listOf(nums[i], nums[j], nums[k]))
                j += 1
                k -= 1
                while(j<k && nums[j]==nums[j-1]){
                    j+=1
                }
                while(k<j && nums[k]==nums[k+1]){
                    k-=1
                }
            }
        }
    }
    return a
}

//128. Longest Consecutive Sequence
fun longestConsecutive(nums: IntArray): Int {
    if (nums.isEmpty()) return 0
    if (nums.size == 1) return 1
    val hashSet = HashSet<Int>()
    nums.forEach { hashSet.add(it) }
    var longestSize = 0
    var isNumberStartOfSequence: Boolean
    for (num in nums) {
        isNumberStartOfSequence = !hashSet.contains(num - 1)
        if (isNumberStartOfSequence) {
            var nextConsecutiveNumber = num + 1
            var currentSize = 1
            while (hashSet.contains(nextConsecutiveNumber)) {
                nextConsecutiveNumber++
                currentSize++
            }
            longestSize = max(longestSize, currentSize)
        }
    }
    return longestSize
}

//347. Top K Frequent Elements
fun topKFrequent(nums: IntArray, k: Int): IntArray {
    val count = hashMapOf<Int, Int>()
    val freq = Array(nums.size+1){ mutableListOf<Int>() }
    val res = mutableListOf<Int>()

    for (num in nums) {
        count[num] = count.getOrDefault(num, 0) + 1
    }
    for ((n, c) in count) {
        freq[c].add(n)
    }
    for (i in freq.size - 1 downTo 0) {
        for (n in freq[i]) {
            res.add(n)
            if (res.size == k) {
                return res.toIntArray()
            }
        }
    }
    return intArrayOf()
}

// 242. Valid Anagram
fun isAnagram(s: String, t: String): Boolean {

    fun countHash(string: String):String{
        val count = IntArray(26)
        for (c in string){
            count[c-'a'] += 1
        }
        return count.joinToString()
    }

    return countHash(s) == countHash(t)
}

// 49. Group Anagrams
fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val group = HashMap<String,MutableList<String>>()

    for (s in strs){
        val count = IntArray(26)
        for (c in s){
            count[c-'a'] += 1
        }
        val key = count.joinToString()
        group[key] = group.getOrDefault(key, mutableListOf()).also { it.add(s) }
    }
    return group.values.toList()
}

// 323. number-of-connected-components-in-an-undirected-graph
class ConnectedComponents(){
    private val parent = ArrayList<Int>()
    private val rank = ArrayList<Int>()

    fun countComponents(n:Int, edges:Array<IntArray>):Int {
        var count = n
        for (i in 0 until n) {
            parent.add(i)
            rank.add(1)
        }

        for (i in edges.indices) {
            count -= union(edges[i][0], edges[i][1])
        }

        return count
    }

    private fun find(node:Int):Int{
        var result = node
        while (parent[result] != result){
            parent[result] = parent[parent[result]]
            result = parent[result]
        }
        return result
    }

    private fun union(node1:Int, node2:Int):Int{
        val root1 = find(node1)
        val root2 = find(node2)
        if (root1==root2)
            return 0
        if (rank[root1] > rank[root2]){
            parent[root2] = root1
            rank[root1] += rank[root2]
        } else {
            parent[root1] = root2
            rank[root2] += rank[root1]
        }
        return 1
    }
}

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

    var c = s.toCharArray().filter { it.isLetterOrDigit() }.map { Character.toLowerCase(it) }
    val l = c.size
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