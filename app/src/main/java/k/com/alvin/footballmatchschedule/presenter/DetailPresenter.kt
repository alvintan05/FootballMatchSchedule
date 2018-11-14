package k.com.alvin.footballmatchschedule.presenter

import com.google.gson.Gson
import k.com.alvin.footballmatchschedule.api.ApiRepository
import k.com.alvin.footballmatchschedule.api.TheSportDBApi
import k.com.alvin.footballmatchschedule.model.DetailMatchResponse
import k.com.alvin.footballmatchschedule.model.TeamInfoResponse
import k.com.alvin.footballmatchschedule.util.CoroutineContextProvider
import k.com.alvin.footballmatchschedule.view.DetailView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by Alvin Tandiardi on 31/10/2018.
 */
class DetailPresenter(private val view: DetailView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getDetailMatchList(eventId: String) {

        async(context.main) {

            val dataDetailMatch = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getMatchDetail(eventId)), DetailMatchResponse::class.java)
            }

            view.showDetailMatchList(dataDetailMatch.await().events)
            view.hideLoading()
        }

    }

    fun getHomeTeamInfo(teamId: String) {

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamInfo(teamId)), TeamInfoResponse::class.java)
            }

            view.showHomeInfo(data.await().teams)
        }

    }

    fun getAwayTeamInfo(teamId: String) {

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamInfo(teamId)), TeamInfoResponse::class.java)
            }

            view.showAwayInfo(data.await().teams)
        }

    }

}