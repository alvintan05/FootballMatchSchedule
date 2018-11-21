package k.com.alvin.footballmatchschedule.view

import k.com.alvin.footballmatchschedule.model.TeamInfoModel

/**
 * Created by Alvin Tandiardi on 17/11/2018.
 */
interface TeamView {
    fun hideLoading()
    fun showTeamList(data: List<TeamInfoModel>)
}