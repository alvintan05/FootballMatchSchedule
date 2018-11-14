package k.com.alvin.footballmatchschedule.presenter

import com.google.gson.Gson
import k.com.alvin.footballmatchschedule.api.ApiRepository
import k.com.alvin.footballmatchschedule.api.TheSportDBApi
import k.com.alvin.footballmatchschedule.model.MatchResponse
import k.com.alvin.footballmatchschedule.util.CoroutineContextProvider
import k.com.alvin.footballmatchschedule.view.LastView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by Alvin Tandiardi on 29/10/2018.
 */
class LastMatchPresenter(private val view: LastView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getLastMatchList(leagueId: String?) {

        async(context.main) {

            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getLastMatch(leagueId)), MatchResponse::class.java)
            }

            view.showLastMatchList(data.await().events)
            view.hideLoading()
        }
    }

}