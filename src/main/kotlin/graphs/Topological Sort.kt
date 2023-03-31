package graphs


//210. Course Schedule II // Topological Sort
class Solution {
    var order = mutableListOf<Int>()
    var visited = mutableSetOf<Int>()
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        var graph = HashMap<Int, MutableList<Int>>()
        var cycle = mutableSetOf<Int>()

        prerequisites.forEach{
            val (a,b) = it
            graph.getOrPut(a) { mutableListOf<Int>() } += b
        }

        for(i in 0 until numCourses){
            if(visited.contains(i)) continue
            if(dfs(i, graph, cycle).not())
                return IntArray(0)
        }
        return order.toIntArray()
    }

    fun dfs(course:Int, graph:HashMap<Int,MutableList<Int>>, cycle:MutableSet<Int>):Boolean{
        var prereqList = graph.getOrPut(course){mutableListOf<Int>()}

        if(cycle.contains(course))
            return false
        if(visited.contains(course))
            return true

        cycle.add(course)

        for(i in 0 until prereqList.size){
            if(dfs(prereqList[i], graph, cycle).not())
                return false
        }

        cycle.remove(course)
        order.add(course)
        visited.add(course)
        return true
    }
}

//207. Course Schedule
class CourseSchedule {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        var graph = HashMap<Int, MutableList<Int>>()
        var cycle = mutableSetOf<Int>()

        prerequisites.forEach {
            val (v, w) = it
            graph.getOrPut(v) { mutableListOf<Int>() } += w
        }

        for(i in 0 until numCourses){
            if(dfs(i, graph, cycle).not())
                return false
        }
        return true
    }

    fun dfs(course:Int, graph:HashMap<Int,MutableList<Int>>, cycle:MutableSet<Int>):Boolean{
        var prereqList = graph.getOrPut(course){mutableListOf<Int>()}

        if(cycle.contains(course))
            return false

        if(prereqList.isEmpty())
            return true

        cycle.add(course)

        prereqList.forEach{
            if(dfs(it, graph, cycle).not())
                return false
        }

        graph[course] = mutableListOf<Int>()
        cycle.remove(course)
        return true
    }
}