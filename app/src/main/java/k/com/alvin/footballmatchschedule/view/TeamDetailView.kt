package k.com.alvin.footballmatchschedule.view

import k.com.alvin.footballmatchschedule.model.TeamInfoModel

/**
 * Created by Alvin Tandiardi on 17/11/2018.
 */
interface TeamDetailView {
    fun showTeamDetail(data: List<TeamInfoModel>)
}