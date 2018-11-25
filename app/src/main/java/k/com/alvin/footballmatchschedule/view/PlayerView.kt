package k.com.alvin.footballmatchschedule.view

import k.com.alvin.footballmatchschedule.model.PlayerModel

/**
 * Created by Alvin Tandiardi on 20/11/2018.
 */
interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerList(data: List<PlayerModel>)
}