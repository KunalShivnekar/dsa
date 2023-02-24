package graphs

//207. Course Schedule
class CourseSchedule {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        var graph = HashMap<Int, MutableList<Int>>()
        var cycle = mutableSetOf<Int>()

        for(prereq in prerequisites){
            graph.getOrPut(prereq[0]){mutableListOf<Int>()}.apply{ add(prereq[1])}
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