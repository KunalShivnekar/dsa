package leetcode

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