package k.com.alvin.footballmatchschedule.presenter

import android.widget.Toast
import com.google.gson.Gson
import k.com.alvin.footballmatchschedule.api.ApiRepository
import k.com.alvin.footballmatchschedule.api.TheSportDBApi
import k.com.alvin.footballmatchschedule.model.TeamInfoResponse
import k.com.alvin.footballmatchschedule.util.CoroutineContextProvider
import k.com.alvin.footballmatchschedule.view.TeamSearchView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by Alvin Tandiardi on 25/11/2018.
 */
class SearchTeamPresenter(private val view: TeamSearchView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamSearch(teamName: String?) {

        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getSearchTeam(teamName)), TeamInfoResponse::class.java)
            }

            view.showTeamSearch(data.await().teams.filter { it.sportType == "Soccer" })
            view.hideLoading()
        }

    }

}