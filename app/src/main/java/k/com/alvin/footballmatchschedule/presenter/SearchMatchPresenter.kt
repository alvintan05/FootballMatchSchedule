package k.com.alvin.footballmatchschedule.presenter

import com.google.gson.Gson
import k.com.alvin.footballmatchschedule.api.ApiRepository
import k.com.alvin.footballmatchschedule.api.TheSportDBApi
import k.com.alvin.footballmatchschedule.model.MatchSearchResponse
import k.com.alvin.footballmatchschedule.util.CoroutineContextProvider
import k.com.alvin.footballmatchschedule.view.MatchSearchView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by Alvin Tandiardi on 25/11/2018.
 */
class SearchMatchPresenter (private val view : MatchSearchView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson,
                            private val context : CoroutineContextProvider = CoroutineContextProvider()) {

    fun getMatchSearch(eventName: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getSearchMatch(eventName)), MatchSearchResponse::class.java)
            }
            view.showMatchSearch(data.await().event.filter { it.sportType == "Soccer"})
            view.hideLoading()
        }
    }

}