package trees

import java.util.*

data class BinaryTreeNode(var data:Int, var left: BinaryTreeNode? = null, var right: BinaryTreeNode? = null)

class BinaryTree (val root:BinaryTreeNode) {

    companion object {
        /*
            *         1
            *      2     3
            *    4   5  6  7
            * */
        fun createTree():BinaryTree {
            val four = BinaryTreeNode(4)
            val five = BinaryTreeNode(5)
            val six = BinaryTreeNode(6)
            val seven = BinaryTreeNode(7)

            val three = BinaryTreeNode(3, six, seven)
            val two = BinaryTreeNode(2, four, five)
            val one = BinaryTreeNode(1,two, three)
            return BinaryTree(one)
        }
    }

    fun preorderTraversal(rootNode: BinaryTreeNode = root): List<Int> {
        var result = arrayListOf<Int>()
        var stack = Stack<BinaryTreeNode>()
        stack.push(rootNode)

        while (stack.isEmpty().not()){
            var tempNode = stack.pop()
            result.add(tempNode.data)

            if (tempNode.right != null)
                stack.push(tempNode.right)
            if (tempNode.left != null)
                stack.push(tempNode.left)
        }
        return result
    }

    fun inorderTraversal(rootNode: BinaryTreeNode = root): List<Int> {
        var result = arrayListOf<Int>()
        var stack = Stack<BinaryTreeNode>()
        var currentNode:BinaryTreeNode? = rootNode
        // traverse the tree
        while (currentNode != null || stack.size > 0)
        {
            /* Reach the left most Node of the
            curr Node */
            while (currentNode !=  null)
            {
                /* place pointer to a tree node on
                   the stack before traversing
                  the node's left subtree */
                stack.push(currentNode)
                currentNode = currentNode.left
            }

            /* Current must be NULL at this point */
            currentNode = stack.pop()

            result.add(currentNode.data)

            /* we have visited the node and its
               left subtree.  Now, it's right
               subtree's turn */
            currentNode = currentNode.right;
        }

        return result
    }

    fun levelOrderTraversal(rootNode: BinaryTreeNode = root):List<Int> {
        val queue: Queue<BinaryTreeNode> = LinkedList()
        val res = mutableListOf<Int>()
        queue.add(rootNode)
        while (!queue.isEmpty()) {
            val tempNode: BinaryTreeNode = queue.poll()
            res.add(tempNode.data)

            /*Enqueue left child */
            if (tempNode.left != null) {
                queue.add(tempNode.left)
            }

            /*Enqueue right child */
            if (tempNode.right != null) {
                queue.add(tempNode.right)
            }
        }
        return res
    }

    fun zigzagLevelOrder(rootNode: BinaryTreeNode? = root): List<List<Int>> {
        val queue: Deque<BinaryTreeNode> = LinkedList()
        var res = mutableListOf<List<Int>>()
        if(rootNode==null) return res
        var depth = 0
        queue.add(rootNode)
        while (!queue.isEmpty()) {
            var level = mutableListOf<Int>()
            for (i in 0 until queue.size){
                if(depth%2==0) {
                    val tempNode: BinaryTreeNode = queue.pollFirst()
                    level.add(tempNode.data)
                    /*Enqueue left child */
                    tempNode.left?.let { queue.addLast(it) }
                    /*Enqueue right child */
                    tempNode.right?.let { queue.addLast(it) }
                } else {
                    val tempNode: BinaryTreeNode = queue.pollLast()
                    level.add(tempNode.data)
                    /*Enqueue right child */
                    tempNode.right?.let { queue.addFirst(it) }
                    /*Enqueue left child */
                    tempNode.left?.let { queue.addFirst(it) }
                }
            }
            res.add(level)
            depth++
        }
        return res
    }

    fun depthOfTree(rootNode: BinaryTreeNode = root):Int {
        val queue: Queue<BinaryTreeNode> = LinkedList()
        var depth = 0
        queue.add(rootNode)
        while (!queue.isEmpty()) {
            val size = queue.size
            for (i in 0 until size){
                val tempNode: BinaryTreeNode = queue.poll()
                /*Enqueue left child */
                if (tempNode.left != null) {
                    queue.add(tempNode.left)
                }
                /*Enqueue right child */
                if (tempNode.right != null) {
                    queue.add(tempNode.right)
                }
            }
            depth++
        }
        return depth
    }

    fun invertTree(rootNode: BinaryTreeNode = root):List<Int> {
        val queue: Queue<BinaryTreeNode> = LinkedList()
        val res = mutableListOf<Int>()
        queue.add(rootNode)
        while (!queue.isEmpty()) {
            val node: BinaryTreeNode = queue.poll()
            res.add(node.data)
            val temp = node.left
            node.left = node.right
            node.right = temp

            /*Enqueue left child */
            if (node.left != null) {
                queue.add(node.left)
            }

            /*Enqueue right child */
            if (node.right != null) {
                queue.add(node.right)
            }
        }
        return res
    }

    fun isSameTree(p: BinaryTreeNode?, q: BinaryTreeNode?): Boolean {
        return if(p!=null && q!=null) {
            if(p.data == q.data){
                isSameTree(p.right,q.right) && isSameTree(p.left, q.left)
            } else false
        } else p==null && q==null
    }

/***********************  Serialize and deserialize a Tree ***********************/
    private var i: Int = 0
fun serialize(root: BinaryTreeNode?): String? {
        val list: MutableList<String> = ArrayList()
        serializeDFS(root, list)
        return java.lang.String.join(",", list)
    }

    private fun serializeDFS(root: BinaryTreeNode?, list: MutableList<String>) {
        if (root == null) {
            list.add("N")
            return
        }
        list.add(java.lang.String.valueOf(root.data))
        serializeDFS(root.left, list)
        serializeDFS(root.right, list)
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): BinaryTreeNode? {
        val tokens = data.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return deserializeDFS(tokens)
    }

    private fun deserializeDFS(tokens: Array<String>): BinaryTreeNode? {
        val token = tokens[i]
        if (token == "N") {
            this.i++
            return null
        }
        val node = BinaryTreeNode(token.toInt())
        this.i++
        node.left = deserializeDFS(tokens)
        node.right = deserializeDFS(tokens)
        return node
    }
    /**********************************************/

}