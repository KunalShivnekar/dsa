package trees

import java.util.Queue
import java.util.Stack

data class BinaryTreeNode(val data:Int, val left: BinaryTreeNode? = null, val right: BinaryTreeNode? = null)

class BinaryTree (private val root:BinaryTreeNode) {

    companion object {
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

}