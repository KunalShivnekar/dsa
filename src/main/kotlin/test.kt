import java.util.Collections

class Test {

    fun calcSubset(raw:Array<Int>):Boolean{
        var result = false
        var input = raw.toMutableList()
        Collections.sort(input)
        var sum = 0
        for (i in input) {
            sum += i
        }
        if ((sum%2 == 0).not())
            return false

        sum /= 2

        var subset1 = mutableListOf<Int>()
        var subset2 = mutableListOf<Int>()
        var sum1 = input[input.size-1]
        var sum2=0
        subset1.add(input[input.size-1])
        input[input.size-1] = 0
        var j = 0
        for (i in 0..input.size-2){
            if (sum1 == sum)
                break
            var tofind = sum-sum1
            var index = input.indexOf(tofind)
            if(index == -1) {
                result = false
                break
            }
            subset1.add(i+1, input[index])
            sum1 += input[index]
            input[index] = 0
        }
        var k =0
        for (j in 0..input.size-1) {
            if (input[j] != 0) {
                subset2.add(input[j])
                sum2 += input[j]
                k++
            }
        }
        if (sum2 == sum)
            result = true


        return result
    }
}