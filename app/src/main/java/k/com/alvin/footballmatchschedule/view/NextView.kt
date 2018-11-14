package k.com.alvin.footballmatchschedule.view

import k.com.alvin.footballmatchschedule.model.MatchModel

/**
 * Created by Alvin Tandiardi on 30/10/2018.
 */
interface NextView {
    fun showLoading()
    fun hideLoading()
    fun showNextMatchList(data: List<MatchModel>)
}