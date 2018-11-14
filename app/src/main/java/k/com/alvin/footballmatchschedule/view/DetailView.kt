package k.com.alvin.footballmatchschedule.view

import k.com.alvin.footballmatchschedule.model.DetailMatchModel
import k.com.alvin.footballmatchschedule.model.TeamInfoModel

/**
 * Created by Alvin Tandiardi on 31/10/2018.
 */
interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailMatchList(data: List<DetailMatchModel>)
    fun showHomeInfo(data: List<TeamInfoModel>)
    fun showAwayInfo(data: List<TeamInfoModel>)
}