import trees.BinaryTree

fun main(args: Array<String>) {
    println("Hello World!")
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val tree = BinaryTree.createTree()
    val res = tree.inorderTraversal()
    res.stream().forEach {
        println("Nodes: $it")
    }
}