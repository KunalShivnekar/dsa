package graphs

import java.util.ArrayList
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue
import java.util.Stack

data class Vertex(val label:String, var isVisited:Boolean=false)

class AdjMatrixGraph (private val maxVertices:Int){

    private val adjMatrix:Array<Array<Int>> = Array(maxVertices) {
        Array(maxVertices){ 0 }
    }

    private val vertexList = ArrayList<Vertex>()
    private var vertexCount = 0

    companion object {
        fun createUndirectedGraph():AdjMatrixGraph{
            val graph = AdjMatrixGraph(10).apply {
                val Aindex = addVertex("A")
                val Bindex = addVertex("B")
                val Cindex = addVertex("C")
                val Dindex = addVertex("D")
                val Eindex = addVertex("E")
                val Findex = addVertex("F")
                val Gindex = addVertex("G")
                val Hindex = addVertex("H")

                addUndirectedEdge(Aindex, Bindex)
                addUndirectedEdge(Bindex, Hindex)
                addUndirectedEdge(Bindex, Cindex)
                addUndirectedEdge(Cindex, Eindex)
                addUndirectedEdge(Cindex, Dindex)
                addUndirectedEdge(Eindex, Gindex)
                addUndirectedEdge(Eindex, Findex)
                addUndirectedEdge(Hindex, Eindex)
            }
            return graph
        }

        fun createDirectedWeightedGraph():AdjMatrixGraph{
            val graph = AdjMatrixGraph(10).apply {
                val Aindex = addVertex("A")
                val Bindex = addVertex("B")
                val Cindex = addVertex("C")
                val Dindex = addVertex("D")
                val Eindex = addVertex("E")
                //val Findex = addVertex("F")
                //val Gindex = addVertex("G")
                //val Hindex = addVertex("H")

                addDirectedEdge(Aindex, Bindex,4)
                addDirectedEdge(Aindex, Cindex,1)
                addDirectedEdge(Bindex, Eindex,4)
                addDirectedEdge(Cindex, Bindex,2)
                addDirectedEdge(Cindex, Dindex,4)
                addDirectedEdge(Dindex, Eindex,4)
                //addDirectedEdge(Eindex, Gindex)
                //addDirectedEdge(Eindex, Findex)
                //addDirectedEdge(Hindex, Eindex)
            }
            return graph
        }
    }
    fun addDirectedEdge(start:Int, end:Int, weight:Int=1){
        if(start in 0 until vertexCount && end in 0 until vertexCount)
            adjMatrix[start][end] = weight
    }

    fun addUndirectedEdge(start:Int, end:Int){
        addDirectedEdge(start, end)
        addDirectedEdge(end, start)
    }

    fun removeDirectedEdge(start:Int, end:Int){
        if(start in 0 until vertexCount && end in 0 until vertexCount)
            adjMatrix[start][end] = 0
    }

    fun removeUndirectedEdge(start:Int, end:Int){
        removeDirectedEdge(start, end)
        removeDirectedEdge(end, start)
    }

    fun isEdge(start:Int, end:Int) : Boolean{
        return if(start in 0 until vertexCount && end in 0 until vertexCount)
            adjMatrix[start][end] > 0
        else
            false
    }

    fun addVertex(value:String):Int{
        if (vertexCount<maxVertices)
            vertexList.add(vertexCount++,Vertex(value))
        return vertexCount-1
    }

    fun displayVertex(index:Int):Vertex{
        println("Vertex visited: ${vertexList[index].label}")
        return vertexList[index]
    }

    fun getAdjacentUnvisitedVertex(source:Int):Int{
        for (j in 0 until vertexCount)
            if (isEdge(source,j).and(vertexList[j].isVisited.not()))
                return j
        return -1
    }

    fun resetVisitedFlags(){
        for (j in 0 until vertexCount)
            vertexList[j].isVisited = false
    }

    fun depthFirstSearch(valueToSearch:String):Boolean{
        var isFound = false
        val stack = Stack<Int>()
        if (vertexList[0].label == valueToSearch)
            return true
        vertexList[0].isVisited = true
        displayVertex(0)
        stack.push(0)
        while (stack.isNotEmpty()){
            val v = getAdjacentUnvisitedVertex(stack.peek())
            if (v==-1)
                stack.pop()
            else {
                vertexList[v].isVisited = true
                displayVertex(v)
                stack.push(v)
                if (vertexList[v].label == valueToSearch) {
                    isFound = true
                    break
                }
            }
        }
        resetVisitedFlags()
        return isFound
    }

    fun breadthFirstSearch(valueToSearch:String):Boolean{
        var isFound = false
        val queue:Queue<Int> = LinkedList()
        if (vertexList[0].label == valueToSearch)
            return true
        vertexList[0].isVisited = true
        displayVertex(0)
        queue.add(0)
        while (queue.isNotEmpty()){
            val v1 = queue.remove()
            while (true) {
                val v = getAdjacentUnvisitedVertex(v1)
                if (v==-1) break
                vertexList[v].isVisited = true
                displayVertex(v)
                queue.add(v)
                if (vertexList[v].label == valueToSearch) {
                    isFound = true
                    break
                }
            }
            if (isFound) break
        }
        resetVisitedFlags()
        return isFound
    }

    fun shortestPathUnweighted(source:Int){
        val queue:Queue<Int> = LinkedList()
        val distance = Array<Int>(vertexCount){-1}
        val path = Array<Int>(vertexCount){0}
        distance[source] = 0
        vertexList[source].isVisited = true
        queue.add(source)
        while (queue.isNotEmpty()){
            val v1 = queue.remove()
            while (true) {
                val v = getAdjacentUnvisitedVertex(v1)
                if (v==-1) break
                if (distance[v] == -1) {
                    distance[v] = distance[v1] + 1
                    vertexList[v].isVisited = true
                    path[v] = v1
                    queue.add(v)
                }
            }
        }
        resetVisitedFlags()
        for (i in distance.indices)
            println("Vertex ${vertexList[i].label} is at distance ${distance[i]} and nearest to ${vertexList[path[i]].label}")
    }

    fun shortestPathWeightedDijkstra(source:Int){

        val distance = Array<Int>(vertexCount){-1}
        val queue:Queue<Int> = PriorityQueue(Comparator { v1, v2 ->
            if (distance[v1]<distance[v2])
                -1
            else if (distance[v1]==distance[v2])
                0
            else 1
        })
        val path = Array<Int>(vertexCount){0}
        distance[source] = 0
        vertexList[source].isVisited = true
        queue.add(source)
        while (queue.isNotEmpty()){
            val v1 = queue.remove()
            for (j in 0 until vertexCount) {
                if (isEdge(v1,j)) {
                    val d = distance[v1] + adjMatrix[v1][j]
                    if (distance[j] == -1) {
                        distance[j] = d
                        path[j] = v1
                        queue.add(j)
                    }
                    if (distance[j] > d) {
                        distance[j] = d
                        path[j] = v1
                    }
                }
            }
        }
        for (i in distance.indices)
            println("Vertex ${vertexList[i].label} is at distance ${distance[i]} and nearest to ${vertexList[path[i]].label}")
    }
}
