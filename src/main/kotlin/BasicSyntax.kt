import java.util.*

/********* Array *********/

//1-D Array
var arr = arrayOf(1,2,3)
var arr2 = Array(10){-1}
var intarray = intArrayOf(1,2,3)
var intarray2 = IntArray(2)  // element default value is 0 and initialiser for each element is optional
var chararray = CharArray(2)
var chararray2 = charArrayOf()
var booleanArray = BooleanArray(2)

//2-D Array of Arrays
var intarray2d = Array(5){IntArray(5)}
var strarray2d = Array(5){Array(5){""} }
var chararray2d = Array(5){CharArray(5)}
var boolarray2d = Array(5){BooleanArray(5){true} } //BooleanArray(5){BooleanArray(5){false}} -> This gives compile error

var len = "asdasd".length
var size = intarray2d.size

/********* List *********/

var immutableList = listOf("")
var mutableList = mutableListOf<String>()
var mutableList2:MutableList<String> = MutableList(5){""}
var listsize = immutableList.size

// list with array
val listofArray = ArrayList<IntArray>()
var arrayoflist = Array(5){ArrayList<Int>(2) }
var size2 = listofArray.size

//Sorting
//intarray2d.sortWith(Comparator { o1, o2 -> return@Comparator (o1[1]-o1[0]) - (o2[1]-o2[0]) })
// intarray2d.sortBy {it[0]}

val inp2 = readLine()!!.split(" ")
var nums = inp2.map{it.toInt()}.sorted().toMutableList()

/********* Stack *********/
var stack = Stack<String>()
var sSize = stack.size
var sOps =  stack.empty() //POP stack.pop() //PUSH stack.push() //PEEK stack.peek()

/********* Queue *********/
/** Summary of Queue methods
            Throws exception    Returns special value
Insert      add(e)              offer(e)
Remove      remove()            poll()
Examine     element()           peek()
 */
var queue : Queue<String> = LinkedList()
var deque : Deque<String> = LinkedList()

/********* Priority Queue/Heap *********/
var pq1 = PriorityQueue<String>()
var pq2 = PriorityQueue<IntArray>(Comparator { o1, o2 -> return@Comparator o1[1]-o2[1] })

var pqOps = pq1.clear()//REMOVE ELEMENT pq1.remove("") //REMOVE HEAD pq1.remove() //pq1.isEmpty() //SEARCH pq1.contains("") //ADD pq1.add() //SIZE pq1.size

/********* Map *********/
var mutableMap1 = mutableMapOf<Int,Int>() // MutableMap<Int,Int>() will give compile error
var mutableMap2 = hashMapOf<Int,Int>()
var immutableMap1 = mapOf<Int,Int>()

var mOps1 = mutableMap1.getOrDefault(2,-1)
var mOps2 = mutableMap1.getOrPut(2) { -1 }
var mOps3 = mutableMap1.contains(1) //same as mutableMap1.containsKey(1)
var mOps4 = mutableMap1.containsValue(1)

/********* Set *********/
var mutableSet1 = mutableSetOf<Int>()
var mutableSet2:MutableSet<Int> = HashSet()

var immutableSet1 = setOf<Int>()
var immutableSet2:Set<Int> = HashSet()

var setOps1 = mutableSet1.add(1)
var setOps2 = mutableSet1.remove(1)
var setOps3 = mutableSet1.contains(1)