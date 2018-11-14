package k.com.alvin.footballmatchschedule.presenter

import com.google.gson.Gson
import k.com.alvin.footballmatchschedule.api.ApiRepository
import k.com.alvin.footballmatchschedule.api.TheSportDBApi
import k.com.alvin.footballmatchschedule.model.MatchResponse
import k.com.alvin.footballmatchschedule.util.CoroutineContextProvider
import k.com.alvin.footballmatchschedule.view.NextView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by Alvin Tandiardi on 30/10/2018.
 */
class NextMatchPresenter(private val view: NextView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getNextMatchList(leagueId: String?) {

        async(context.main) {

            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getNextMatch(leagueId)), MatchResponse::class.java)
            }

            view.showNextMatchList(data.await().events)
            view.hideLoading()
        }
    }
}