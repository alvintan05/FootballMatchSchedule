package k.com.alvin.footballmatchschedule.view

import k.com.alvin.footballmatchschedule.model.MatchModel

/**
 * Created by Alvin Tandiardi on 25/11/2018.
 */
interface MatchSearchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchSearch(data: List<MatchModel>)
}