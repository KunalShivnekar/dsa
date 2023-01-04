package arraysandlists

data class ListNode(var value:Int){
    var prevNode: ListNode?=null
    var nextNode: ListNode?=null
}

class LinkedList(var start:ListNode){
    companion object{
        fun createSinglyLLFromArray(arr:IntArray):LinkedList{
            var root = ListNode(arr[0])
            var ptr = root
            for (i in 1 until  arr.size){
                ptr.nextNode = ListNode(arr[i])
                ptr = ptr.nextNode!!
            }
            return LinkedList(root)
        }

        fun printList(node: ListNode?){
            var start = node
            while (start!=null){
                print("${start.value} ")
                start = start.nextNode
            }
        }
    }
}

fun mergeTwoLists(list1:ListNode?, list2:ListNode?):ListNode?{
    var mergedList:ListNode?=ListNode(0)
    var root = mergedList
    var root1 = list1
    var root2 = list2
    while (root1!=null && root2!=null){
        if (root1.value<root2.value){
            root?.nextNode = root1
            root1 = root1.nextNode
        } else {
            root?.nextNode = root2
            root2 = root2.nextNode
        }
        root = root?.nextNode
    }
    if (root1==null)
        root?.nextNode = root2
    else
        root?.nextNode = root1

    return mergedList?.nextNode
}

fun reverseList(list:ListNode?):ListNode?{
    var start:ListNode? = null
    var start1:ListNode? = null
    var start2:ListNode? = list

    while (start2!=null) {

        start = start1
        start1 = start2
        start2 = start2.nextNode
        start1.nextNode = start
    }
    return start1
}