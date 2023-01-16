fun suggestedMeetingTime(workingHours: Pair<Int, Int>, meetings: List<Pair<Int, Int>>, currentTime: Int, duration: Int): String {
val start = Pair(0, workingHours.first)
    val end = Pair(workingHours.second, 3600)
    var meetingsTemp = ArrayList<Pair<Int, Int>>(meetings)
    meetingsTemp.add(0,start)
    meetingsTemp.add(end)
    var index = 0
    var res = "No time available"
    for (i in 0 until meetingsTemp.size){
        if (currentTime<meetingsTemp[i].first && currentTime<meetingsTemp[i].second){
            if ((meetingsTemp[i].first - currentTime)>duration){
                res = "$currentTime-${currentTime+duration}"
                break
            } else
                index = i
        } else if (currentTime>meetingsTemp[i].first && currentTime<meetingsTemp[i].second){
            index = i
            break
        } else if(currentTime==meetingsTemp[i].first || currentTime==meetingsTemp[i].second){
            index = i
            break
        }
    }
    if (index!=0){
        for (i in index until meetingsTemp.size){
            if((meetingsTemp[i+1].first - meetingsTemp[i].second)>duration){
                res = "${meetingsTemp[i].second}-${meetingsTemp[i].second+duration}"
                break
            }
        }
    }
    return res

}
