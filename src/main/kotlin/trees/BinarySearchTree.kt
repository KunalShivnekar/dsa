package trees

data class BinarySearchTreeNode(val data:Int, val left: BinarySearchTreeNode? = null, val right: BinarySearchTreeNode? = null)

class BinarySearchTree (private val root:BinarySearchTreeNode) {

    companion object {
        fun createTree():BinarySearchTree {
            /*
            *         4
            *      2     6
            *    1   3  5  7
            * */

            val one = BinarySearchTreeNode(1)
            val three = BinarySearchTreeNode(3)
            val five = BinarySearchTreeNode(5)
            val seven = BinarySearchTreeNode(7)


            val two = BinarySearchTreeNode(2, one, three)
            val six = BinarySearchTreeNode(6, five, seven)
            val four = BinarySearchTreeNode(4, two, six)

            return BinarySearchTree(four)
        }
    }

    fun findNode(rootNode:BinarySearchTreeNode? = root, data:Int):BinarySearchTreeNode?{
        var root = rootNode
        while (root!=null) {
            root = if (data==root.data)
                return root
            else if (data>root.data)
                root.right
            else
                root.left
        }
        return null
    }

    fun findMinimumNode(rootNode:BinarySearchTreeNode? = root):BinarySearchTreeNode?{
        if(rootNode == null) return null
        var root = rootNode
        while (root?.left!=null) {
            root = root.left
        }
        return root
    }

    fun findMaximumNode(rootNode:BinarySearchTreeNode? = root):BinarySearchTreeNode?{
        if(rootNode == null) return null
        var root = rootNode
        while (root?.right!=null) {
            root = root.right
        }
        return root
    }

    fun lca(rootNode: BinarySearchTreeNode? = root, n1: Int, n2: Int): BinarySearchTreeNode? {
        var root: BinarySearchTreeNode? = rootNode
        while (root != null) {
            // If both n1 and n2 are smaller
            // than root, then LCA lies in left
            // If both n1 and n2 are greater
            // than root, then LCA lies in right
            root =
                if (root.data > n1 && root.data > n2)
                    root.left
                else if (root.data < n1 && root.data < n2)
                    root.right
                else
                    break
        }
        return root
    }

    //fun insertNode(rootNode: BinarySearchTreeNode? = root, n1: Int): BinarySearchTreeNode?{

    //}

}