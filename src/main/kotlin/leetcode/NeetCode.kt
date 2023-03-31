package leetcode

import trees.BinaryTreeNode
import java.util.*
import kotlin.math.max

//778. Swim in Rising Water
fun swimInWater(grid: Array<IntArray>): Int {
    var n = grid.size
    var memo = Array(n){IntArray(n){-1}}
    var heap = PriorityQueue<IntArray> { v1, v2 -> memo[v1[0]][v1[1]] - memo[v2[0]][v2[1]] }
    memo[0][0] = grid[0][0]
    heap.add(intArrayOf(0,0))
    var dirRow = intArrayOf(-1,1,0,0)
    var dirCol = intArrayOf(0,0,-1,1)

    while(heap.isEmpty().not()){
        var s = heap.remove()
        var sx = s[0]
        var sy = s[1]

        for(i in 0..3){
            var tx = sx+dirRow[i]
            var ty = sy+dirCol[i]
            if(tx<0 || ty<0 || tx>=n || ty >= n)
                continue
            var dist = if(grid[tx][ty]>grid[sx][sy])
                Math.max(grid[tx][ty], memo[sx][sy])
            else
                memo[sx][sy]

            if(memo[tx][ty]==-1){
                memo[tx][ty] = dist
                heap.add(intArrayOf(tx,ty))
            }
            if(memo[tx][ty]>dist){
                memo[tx][ty] = dist
            }
        }
    }
    return memo[n-1][n-1]
}


//97. Interleaving String
fun isInterleave(s1: String, s2: String, s3: String): Boolean {
    if((s1.length + s2.length)!= s3.length) return false

    var memo = Array(s1.length +1){BooleanArray(s2.length+1)}
    memo[s1.length][s2.length] = true

    for(i in s1.length downTo 0){
        for(j in s2.length downTo 0){
            if(i<s1.length && s1[i] == s3[i+j] && memo[i+1][j])
                memo[i][j] = true
            if(j<s2.length && s2[j] == s3[i+j] && memo[i][j+1])
                memo[i][j] = true
        }
    }
    return memo[0][0]
}

//494. Target Sum
fun findTargetSumWays(nums: IntArray, target: Int): Int {
    return checkTarget(nums, target, 0)
}

fun checkTarget(nums: IntArray, target: Int, index:Int):Int {
    if(index==nums.size && target == 0) return 1
    if(index==nums.size) return 0

    return checkTarget(nums, target+nums[index],index+1) + checkTarget(nums, target-nums[index],index+1)
}

//518. Coin Change II
fun change(amount: Int, coins: IntArray): Int {
    var memo = IntArray(amount+1)
    memo[0] = 1

    for(coin in coins){
        for(i in 1..amount){
            if(coin<=i)
                memo[i] += memo[i-coin]
        }
    }
    return memo[amount]
}

//152. Maximum Product Subarray
fun maxProduct(nums: IntArray): Int {
    if (nums.size == 0) return 0
    var max_so_far = nums[0]
    var min_so_far = nums[0]
    var result = max_so_far
    for (i in 1 until nums.size) {
        val curr = nums[i]
        val temp_max = Math.max(curr, Math.max(max_so_far * curr, min_so_far * curr))
        min_so_far = Math.min(curr, Math.min(max_so_far * curr, min_so_far * curr))
        max_so_far = temp_max
        result = Math.max(max_so_far, result)
    }
    return result
}

//139. Word Break
fun wordBreak(s: String, wordDict: List<String>): Boolean {
    val dp = BooleanArray(s.length + 1) { false }
    dp[s.length] = true

    for (i in s.length downTo 0) {
        for (w in wordDict) {
            if (i + w.length <= s.length && w == s.substring(i, i + w.length))
                dp[i] = dp[i + w.length]
            if (dp[i]) break
        }
    }

    return dp[0]
}


//416. Partition Equal Subset Sum
fun canPartition(nums: IntArray): Boolean {
    var sum = 0
    nums.forEach { sum += it }
    if(sum%2 != 0) return false
    sum = sum/2

    var combn = mutableSetOf<Int>()
    combn.add(0)

    for(i in nums.size-1 downTo 0){
        var nextComb = mutableSetOf<Int>()
        for(num in combn){
            nextComb.add(num+nums[i])
            nextComb.add(num)
        }
        combn = nextComb
    }
    return combn.contains(sum)
}

//45. Jump Game II
fun jump(nums: IntArray): Int {
    if(nums.size==1) return 0


    var index = 0
    var newindex = 0
    var jumps = 0
    while(index<nums.size-1){
        var maxJump = 0
        println("index $index")
        for(i in index+1..index+nums[index]){

            if(i>=nums.size-1) {
                newindex = i
                break
            }
            var jump = i +nums[i]
            if(jump>maxJump){
                maxJump = jump
                newindex = i
            }
        }
        jumps++
        index = newindex
    }
    return jumps
}

//424. Longest Repeating Character Replacement
fun characterReplacement(s: String, k: Int): Int {
    var count = IntArray(26)
    var res = 0
    var maxF = 0
    var l = 0

    for(r in 0 until s.length){
        count[s[r]-'A'] += 1
        maxF = Math.max(maxF, count[s[r]-'A'])
        println("window:${r-l+1} maxF:$maxF")
        while((r-l+1)-maxF > k){
            count[s[l]-'A'] -= 1
            l++
        }
        res = Math.max(res, (r-l+1))
    }
    return res
}

//253. Meeting Rooms II
fun minMeetingRooms(intervals: Array<IntArray>): Int {
    intervals.sortBy {it[0]}
    var max = 1
    var stack = PriorityQueue<IntArray>(Comparator { o1, o2 -> return@Comparator o1[1]-o2[1] })
    stack.add(intervals[0])

    for(i in 1 until intervals.size){
        while(stack.peek()[1] <= intervals[i][0]) {
            stack.remove()
            if(stack.isEmpty()) break
        }
        stack.add(intervals[i])
        max = Math.max(max,stack.size)
    }
    return max
}

//1851. Minimum Interval to Include Each Query
fun minInterval(intervals: Array<IntArray>, queries: IntArray): IntArray {
    intervals.sortWith(Comparator { o1, o2 -> return@Comparator (o1[1]-o1[0]) - (o2[1]-o2[0]) })


    var res = mutableListOf<Int>()
    for(query in queries){
        var flag = false
        for(interval in intervals){
            if(interval[0]<= query && query <= interval[1]){
                res.add(interval[1]-interval[0]+1)
                flag = true
                break
            }
        }
        if(flag.not())res.add(-1)
    }
    return res.toIntArray()
}


//212. Word Search II
class WordSearchII {
    var trie = Trie()
    var res = mutableSetOf<String>()
    var visited = mutableSetOf<String>()

    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        words.forEach{
            trie.insert(it)
        }
        var rows = board.size
        var cols = board[0].size

        for(r in 0 until rows){
            for(c in 0 until cols){
                search(r,c,board,trie.root,"")
            }
        }
        return res.toList()
    }

    fun search(r:Int,c:Int,board: Array<CharArray>, node:TrieNode, word:String){
        if(r<0 || r>=board.size || c<0 || c>=board[0].size) return
        if(visited.contains("$r,$c")) return

        val n = trie.startsWith(board[r][c].toString(),node) ?: return
        visited.add("$r,$c")

        var w = word + board[r][c].toString()
        if(n.isEnd()) res.add(w)

        search(r+1,c,board,n,w)
        search(r-1,c,board,n,w)
        search(r,c+1,board,n,w)
        search(r,c-1,board,n,w)

        visited.remove("$r,$c")
    }
}

class Trie() {

    val root: TrieNode = TrieNode()

    fun insert(word: String) {
        var node = root
        word.forEach {
            if (node.contains(it).not()) {
                node.put(it, TrieNode())
            }
            node = node.get(it)
        }
        node.setIsEnd()
    }

    fun startsWith(prefix: String, tn:TrieNode = root): TrieNode? {
        var node = tn
        prefix.forEach {
            if (node.contains(it).not()) {
                return null
            }
            node = node.get(it)
        }
        return node
    }

}

class TrieNode {
    private var isEnd = false
    private val links = Array<TrieNode?>(26) { null }

    fun put(character: Char, node: TrieNode) {
        links[character - 'a'] = node
    }

    fun contains(letter: Char): Boolean {
        return links[letter - 'a'] != null
    }

    fun isEnd(): Boolean = isEnd

    fun setIsEnd() {
        isEnd = true
    }

    fun get(character: Char): TrieNode {
        return links[character - 'a']!!
    }
}

// 79. Word Search
fun exist(board: Array<CharArray>, word: String): Boolean {
    var rows = board.size
    var cols = board[0].size

    for(r in 0 until rows){
        for(c in 0 until cols){
            if(board[r][c] == word[0]){
                var res = search(r,c,board,word,0)
                if(res) return res
            }
        }
    }
    return false
}

fun search(r:Int,c:Int,board: Array<CharArray>, word: String, index:Int):Boolean{
    if(index>= word.length) return true
    if(r<0 || r>=board.size || c<0 || c>=board[0].size) return false


    if(board[r][c] == word[index]){
        board[r][c] = '*'
        var res = search(r+1,c,board,word,index+1) || search(r-1,c,board,word,index+1) || search(r,c-1,board,word,index+1) || search(r,c+1,board,word,index+1)
        if(res) return res
        board[r][c] = word[index]
    }
    return false
}

// 91. Decode Ways
fun numDecodings(s: String): Int {
    if(s.length == 0) return 0
    var cache = IntArray(s.length){-1}
    return isValid(s.toCharArray(),0,cache)
}

fun isValid(s:CharArray, index:Int, cache:IntArray):Int{
    if(index>= s.size) return 1
    if(cache[index]!=-1) return cache[index]
    var num = s[index].toString().toInt()
    var res = 0
    if(num == 0)
        return 0
    else
        res = isValid(s,index+1,cache)

    if(index<s.size-1){
        num = "${s[index]}${s[index+1]}".toInt()
        if(num <= 26)
            res += isValid(s,index+2,cache)
    }
    cache[index] = res
    return cache[index]
}

//437. Path Sum III

fun pathSum(root: BinaryTreeNode?, targetSum: Int): Int {
    var count = 0
    var map = HashMap<Long,Int>()

    fun preorder(node:BinaryTreeNode?, sum:Long){
        if(node==null)
            return
        var currSum = sum + node.data

        if(currSum == targetSum.toLong())
            count += 1

        count += map.getOrPut(currSum-targetSum){0}

        map.put(currSum, map.getOrPut(currSum){0} + 1)

        preorder(node.left, currSum)
        preorder(node.right, currSum)

        map.put(currSum, map.getOrPut(currSum){0} - 1)
    }

    preorder(root,0L)
    return count
}

//435. Non-overlapping Intervals
fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
    intervals.sortBy { it[0] }
    var prevEnd = intervals[0][1]
    var res = 0
    for(i in 1..intervals.size-1){
        if(intervals[i][0]>=prevEnd){
            prevEnd = intervals[i][1]
        } else {
            res++
            prevEnd = Math.min(prevEnd, intervals[i][1])
        }
    }

    return res
}

//56. Merge Intervals
fun merge(intervals: Array<IntArray>): Array<IntArray> {
    intervals.sortWith(Comparator { o1, o2 -> return@Comparator o1[0]-o2[0] })
    val res = ArrayList<IntArray>()
    var temp = intervals[0]
    for(i in 1..intervals.size-1){

        if(temp[1] < intervals[i][0]){
            res.add(temp)
            temp = intervals[i]
        } else if(temp[1] >= intervals[i][1]){
            continue
        } else {
            temp[1] = Math.max(intervals[i][1],temp[1])
        }
    }
    res.add(temp)
    return res.toTypedArray()
}

//57. Insert Interval
fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
    var res = ArrayList<IntArray>()
    var i=0
    for(oldInterval in intervals){

        if(newInterval[1] < oldInterval[0]){
            res.add(newInterval)
            break
        }
        else if(oldInterval[1]<newInterval[0]){
            res.add(oldInterval)
        } else {
            newInterval[0] = Math.min(oldInterval[0],newInterval[0])
            newInterval[1] = Math.max(oldInterval[1],newInterval[1])
        }
        i++
    }
    if(i == intervals.size)
        res.add(newInterval)
    else {
        for(j in i..intervals.size-1){
            res.add(intervals[j])
        }
    }

    return res.toTypedArray()
}


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
//213. House Robber II
fun robII(nums: IntArray): Int {
    var first = nums[0]
    if(nums.size == 1) return first
    var second = maxOf(nums[0], nums[1])
    if(nums.size == 2) return second
    if(nums.size == 3) return Math.max(second,nums[2])
    var res1 = 0
    for(i in 2..nums.size - 2) {
        res1 = maxOf(nums[i] + first, second)
        first = second
        second = res1
    }

    first = nums[1]
    second = maxOf(nums[1], nums[2])
    var res2 = 0
    for(i in 3..nums.size - 1) {
        res2 = maxOf(nums[i] + first, second)
        first = second
        second = res2
    }

    return Math.max(res1,res2)
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