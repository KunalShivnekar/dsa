package trees

data class BinarySearchTreeNode(val data:Int, val left: BinarySearchTreeNode?, val right: BinarySearchTreeNode?)

class BinarySearchTree (private val root:BinarySearchTreeNode) {

    fun findNode(rootNode:BinarySearchTreeNode?, data:Int):BinarySearchTreeNode?{
        var root = rootNode
        while (root!=null) {
            if (data==root.data)
                return root
            else if (data>root.data)
                root = root.right
            else
                root = root.left
        }
        return null
    }

    fun findMinimumNode(rootNode:BinarySearchTreeNode?):BinarySearchTreeNode?{
        if(rootNode == null) return null
        var root = rootNode
        while (root?.left!=null) {
            root = root.left
        }
        return root
    }

    fun findMaximumNode(rootNode:BinarySearchTreeNode?):BinarySearchTreeNode?{
        if(rootNode == null) return null
        var root = rootNode
        while (root?.right!=null) {
            root = root.right
        }
        return root
    }

}