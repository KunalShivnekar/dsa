import trees.BinarySearchTree
import trees.BinaryTree

fun main(args: Array<String>) {
    println("Hello World!")
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    println("Number is at index: ${binarySearch(intArrayOf(-1,0,3,5,9,12),2)}")

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