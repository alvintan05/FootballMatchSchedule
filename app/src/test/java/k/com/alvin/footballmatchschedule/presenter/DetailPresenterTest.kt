package k.com.alvin.footballmatchschedule.presenter

import com.google.gson.Gson
import k.com.alvin.footballmatchschedule.TestContextProvider
import k.com.alvin.footballmatchschedule.api.ApiRepository
import k.com.alvin.footballmatchschedule.api.TheSportDBApi
import k.com.alvin.footballmatchschedule.model.*
import k.com.alvin.footballmatchschedule.view.DetailView
import k.com.alvin.footballmatchschedule.view.NextView
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by Alvin Tandiardi on 13/11/2018.
 */
class DetailPresenterTest {

    @Mock
    private lateinit var view: DetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: DetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetDetailMatchList() {
        val detailTeams: MutableList<DetailMatchModel> = mutableListOf()
        val response = DetailMatchResponse(detailTeams)
        val eventId = "576587"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getMatchDetail(eventId)),
                DetailMatchResponse::class.java
        )).thenReturn(response)

        presenter.getDetailMatchList(eventId)
        Thread.sleep(8000)

        verify(view).showDetailMatchList(detailTeams)
        verify(view).hideLoading()
    }

    @Test
    fun testGetHomeTeamInfo() {
        val homeTeams: MutableList<TeamInfoModel> = mutableListOf()
        val response = TeamInfoResponse(homeTeams)
        val teamId = "134777"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamInfo(teamId)),
                TeamInfoResponse::class.java
        )).thenReturn(response)

        presenter.getHomeTeamInfo(teamId)
        Thread.sleep(8000)

        verify(view).showHomeInfo(homeTeams)
    }

    @Test
    fun testGetAwayTeamInfo() {
        val awayTeams: MutableList<TeamInfoModel> = mutableListOf()
        val response = TeamInfoResponse(awayTeams)
        val teamId = "134301"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamInfo(teamId)),
                TeamInfoResponse::class.java
        )).thenReturn(response)

        presenter.getAwayTeamInfo(teamId)
        Thread.sleep(8000)

        verify(view).showAwayInfo(awayTeams)
    }

}