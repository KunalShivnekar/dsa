
fun groupnum(arr:IntArray, k:Int):Int{
    var temp = IntArray(arr.size)
    var window = 0
    for(i in 0 until  arr.size){
        var diff = k-arr[i]
        temp[i] = diff
        if(diff>=0) window++
    }
    var j =0
    var minswap = Int.MAX_VALUE
    var swap = 0
    for (i in 0 until temp.size){
        if (temp[i]<0)
            swap++
        if (i>=window-1){
            if(swap<minswap)
                minswap =swap
            if (temp[j]<0)
                swap--
            j++
        }
    }
    return minswap
}




fun triplet(arr:IntArray){
    arr.sort()
    //if ((arr[0]>=0).or(arr[arr.size-1]<=0)
        //return -1
    var i =0
    var j =arr.size-1
    var res = Array(0) { IntArray(0) }
    var index = 0
    while (arr[i]<arr[j]){
        var sum = arr[i]+arr[j]
        var mid = 0
        if(sum>0){
            sum = sum+arr[i+1]
            mid = i+1
        } else if(sum<0){
            sum = sum + arr[j-1]
            mid = j-1
        }
        if(sum==0){
            res[index] = intArrayOf(arr[i], arr[mid], arr[j])
        }
        i++
        j--
    }
}