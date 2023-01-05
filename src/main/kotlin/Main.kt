import arraysandlists.LinkedList
import arraysandlists.mergeTwoLists
import arraysandlists.reverseList
import graphs.AdjMatrixGraph
import leetcode.rob
import trees.BinarySearchTree
import trees.BinaryTree

fun main(args: Array<String>) {
    println("Hello World!")
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
    rob(intArrayOf(2,7,9,3,1))

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
    val res = tree.inorderTraversal()
    res.stream().forEach {
        println("Nodes: $it")
    }
}

fun binarySearchTreeOps(){
    val tree = BinarySearchTree.createTree()
    println("Max Node = ${tree.findMaximumNode()}")
    println("Min Node = ${tree.findMinimumNode()}")
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
}