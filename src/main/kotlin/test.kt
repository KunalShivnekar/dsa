import java.util.Collections

class Test {
    /*
Problem:
--------
- Given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
- Find the fewest number of coins that you need to make up that amount.
- If that amount of money cannot be made up by any combination of the coins, return -1.
- You may assume that you have an infinite number of each kind of coin.
- ** Also, print all coins that made up the amount in console log.
-----------------------------------------------------------------------------
| Test      | Input             | Amount    | Output    | Total coins       |
-----------------------------------------------------------------------------
| Test 1    | [1, 2, 5]         | 11        | 3         | 5 + 5 + 1         |
| Test 2    | [2]               | 3         | -1        | -                 |
| Test 3    | [1]               | 0         | 0         | -                 |
| Test 4    | [1]               | 1         | 1         | 1                 |
| Test 5    | [1]               | 2         | 2         | 1 + 1             |
| Test 6    | [1, 3, 4, 5]      | 7         | 2         | 4 + 3             |
| Test 7    | [8, 2]            | 10        | 2         | 8 + 2             |
-----------------------------------------------------------------------------
*/

    fun solution(coins: MutableList<Int>, amount: Int): Int {
        coins.sort()
        if(amount == 0) return 0
        var output = mutableListOf<Int>()
        var r = amount

        //i in (0..array.lastIndex).reversed()
        for(j in (0 until coins.size).reversed()){
            println(" j:$j")
            var coin = coins[j]
            println(" coin:$coin")
            if(coin>r) continue
            var q = r/coin
            r = r%coin
            println("q:$q r:$r")
            for(i in 0 until q)
                output.add(coin)
            if(r == 0){
                break
            }
        }

        if(r > 0)
            return -1
        else {
            println("number of coins:"+output.size)
            for(element in output)
                print(""+element)
            return output.size
        }
    }

}