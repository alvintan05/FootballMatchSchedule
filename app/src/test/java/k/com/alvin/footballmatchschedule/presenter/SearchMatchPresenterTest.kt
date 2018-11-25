package k.com.alvin.footballmatchschedule.presenter

import com.google.gson.Gson
import k.com.alvin.footballmatchschedule.TestContextProvider
import k.com.alvin.footballmatchschedule.api.ApiRepository
import k.com.alvin.footballmatchschedule.api.TheSportDBApi
import k.com.alvin.footballmatchschedule.model.MatchModel
import k.com.alvin.footballmatchschedule.model.MatchSearchResponse
import k.com.alvin.footballmatchschedule.model.PlayerModel
import k.com.alvin.footballmatchschedule.model.PlayerResponse
import k.com.alvin.footballmatchschedule.view.MatchSearchView
import k.com.alvin.footballmatchschedule.view.PlayerView
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by Alvin Tandiardi on 25/11/2018.
 */
class SearchMatchPresenterTest {

    @Mock
    private lateinit var view: MatchSearchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: SearchMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetMatchSearch() {
        val teams: MutableList<MatchModel> = mutableListOf()
        val response = MatchSearchResponse(teams)
        val eventName = "Arsenal_vs_Chelsea"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getSearchMatch(eventName)),
                MatchSearchResponse::class.java
        )).thenReturn(response)

        presenter.getMatchSearch(eventName)
        Thread.sleep(8000)

        verify(view).showLoading()
        verify(view).showMatchSearch(teams.filter { it.sportType == "Soccer" })
        verify(view).hideLoading()
    }

}