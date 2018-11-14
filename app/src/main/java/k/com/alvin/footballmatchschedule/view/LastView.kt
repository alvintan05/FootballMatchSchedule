package k.com.alvin.footballmatchschedule.view

import k.com.alvin.footballmatchschedule.model.MatchModel

/**
 * Created by Alvin Tandiardi on 29/10/2018.
 */
interface LastView {
    fun showLoading()
    fun hideLoading()
    fun showLastMatchList(data: List<MatchModel>)
}