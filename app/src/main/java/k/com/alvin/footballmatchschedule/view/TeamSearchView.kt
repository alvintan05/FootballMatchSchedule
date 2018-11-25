package k.com.alvin.footballmatchschedule.view

import k.com.alvin.footballmatchschedule.model.TeamInfoModel

/**
 * Created by Alvin Tandiardi on 25/11/2018.
 */
interface TeamSearchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamSearch(data : List<TeamInfoModel>)
}