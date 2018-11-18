package k.com.alvin.footballmatchschedule.presenter

import com.google.gson.Gson
import k.com.alvin.footballmatchschedule.api.ApiRepository
import k.com.alvin.footballmatchschedule.api.TheSportDBApi
import k.com.alvin.footballmatchschedule.model.TeamInfoResponse
import k.com.alvin.footballmatchschedule.util.CoroutineContextProvider
import k.com.alvin.footballmatchschedule.view.TeamDetailView
import k.com.alvin.footballmatchschedule.view.TeamView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by Alvin Tandiardi on 17/11/2018.
 */
class TeamDetailPresenter(private val view: TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamDetail(teamId: String) {

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamInfo(teamId)), TeamInfoResponse::class.java)
            }

            view.showTeamDetail(data.await().teams)
        }

    }

}