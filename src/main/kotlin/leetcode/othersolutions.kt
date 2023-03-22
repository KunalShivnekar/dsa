package leetcode

//122. Best Time to Buy and Sell Stock II
fun maxProfitII(prices: IntArray): Int {
    var profit = 0
    for(i in 1 until prices.size){
        if(prices[i]-prices[i-1] > 0)
            profit += prices[i]-prices[i-1]
    }
    return profit

}

//121. Best Time to Buy and Sell Stock
fun maxProfitI(prices: IntArray): Int {
    var buy = prices[0]
    var maxProfit = 0
    for(i in 1 until prices.size){
        maxProfit = Math.max(maxProfit, prices[i]-buy)
        buy = Math.min(buy, prices[i])
    }
    return maxProfit
}

//309. Best Time to Buy and Sell Stock with Cooldown
fun maxProfit(prices: IntArray): Int {
    var mem = IntArray(prices.size){-1}
    return getProfit(prices,0, mem)
}

fun getProfit(prices: IntArray, index:Int, mem:IntArray):Int{
    if(index>=prices.size-1) return 0
    if(mem[index]!=-1) return mem[index]

    var profit = 0
    for(j in index+1 until prices.size){
        profit = Math.max(profit, prices[j]-prices[index] + getProfit(prices, j+2, mem))
    }
    profit = Math.max(profit,getProfit(prices, index+1, mem))
    mem[index] = profit
    return profit
}




fun commonChars(words: Array<String>): List<String> {
    var res = ArrayList<String>()
    words[0].forEach{
        res.add(it.toString())
    }
    if(words.size == 1) return res

    for(i in 1 until words.size){
        var temp = ArrayList<String>()
        temp.addAll(res)
        words[i].forEach {
            temp.remove(it.toString())
        }
        temp.forEach{
            res.remove(it)
        }
    }
    return res
}