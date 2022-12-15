
/*Given an array of integers nums which is sorted in ascending order, and an integer target,
 write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.*/
fun binarySearch(nums: IntArray, target: Int): Int {
    var startIndex = 0
    var endIndex = nums.size-1
    while (startIndex<=endIndex){
        var midIndex = (startIndex+endIndex)/2

        println("startIndex: $startIndex  endIndex: $endIndex midIndex: $midIndex")
        if (target==nums[midIndex])
            return midIndex
        else if(target>nums[midIndex])
            startIndex = midIndex+1
        else
            endIndex = midIndex-1
    }
    return -1
}