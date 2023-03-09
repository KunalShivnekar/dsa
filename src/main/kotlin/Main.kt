import arraysandlists.LinkedList
import arraysandlists.mergeTwoLists
import arraysandlists.reverseList
import graphs.AdjMatrixGraph
import leetcode.ConnectedComponents
import leetcode.WordSearchII
import trees.BinarySearchTree
import trees.BinaryTree

fun main(args: Array<String>):Unit {
    println("Hello World!")
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val inp = listOf(1,2,3,3,5,6,5,"Kunal", "Shivnekar",10,30,3.4,5.0)
    inp.filterIsInstance<Int>()

    //a = [20,4,5,8,10,40,50,90]
    //b = [1,20,10,2,5,8,0,25,90]
    var res = WordSearchII().findWords( board = arrayOf(charArrayOf('o','a','a','n'), charArrayOf('e','t','a','e'), charArrayOf('i','h','k','r'), charArrayOf('i','f','l','v')),words = arrayOf("oath","pea","eat","rain"))

}
fun linkedListOps(){
    var list1 = LinkedList.createSinglyLLFromArray(intArrayOf(1,3,5,7)).start
    var list2 = LinkedList.createSinglyLLFromArray(intArrayOf(2,4,6,8)).start
    var merged = mergeTwoLists(list1,list2)
    //LinkedList.printList(merged)
    LinkedList.printList(reverseList(merged))
}

fun binaryTreeOps(){
    val tree = BinaryTree.createTree()
    var res = tree.levelOrderTraversal()
    res.stream().forEach {
        println("Nodes: $it")
    }
    res = tree.invertTree()
    res.stream().forEach {
        println("Nodes: $it")
    }
    println("Depth: ${tree.depthOfTree(tree.root)}")
}

fun binarySearchTreeOps(){
    val tree = BinarySearchTree.createTree()
    println("Max Node = ${tree.findMaximumNode()}")
    println("Min Node = ${tree.findMinimumNode()}")
    println(tree.isValidBST())
}

fun searchOps(){
    println("Number is at index: ${binarySearch(intArrayOf(-1,0,3,5,9,12),2)}")
}

fun graphOps(){
    val graph = AdjMatrixGraph.createUndirectedGraph()
    val result1 = graph.depthFirstSearch("E")
    println("Value is found:$result1")

    val result2 = graph.breadthFirstSearch("H")
    println("Value is found:$result2")

    graph.shortestPathUnweighted(0)

    val graph2 = AdjMatrixGraph.createDirectedWeightedGraph()
    graph2.shortestPathWeightedDijkstra(0)

    val r = ConnectedComponents().countComponents(5, arrayOf(intArrayOf(0,1), intArrayOf(1,2), intArrayOf(3,4)))
    println("ConnectedComponents: $r")
}