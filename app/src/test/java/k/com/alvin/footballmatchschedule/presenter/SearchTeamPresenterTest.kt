package k.com.alvin.footballmatchschedule.presenter

import com.google.gson.Gson
import k.com.alvin.footballmatchschedule.TestContextProvider
import k.com.alvin.footballmatchschedule.api.ApiRepository
import k.com.alvin.footballmatchschedule.api.TheSportDBApi
import k.com.alvin.footballmatchschedule.model.TeamInfoModel
import k.com.alvin.footballmatchschedule.model.TeamInfoResponse
import k.com.alvin.footballmatchschedule.view.TeamSearchView
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by Alvin Tandiardi on 25/11/2018.
 */
class SearchTeamPresenterTest {

    @Mock
    private lateinit var view: TeamSearchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: SearchTeamPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchTeamPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamSearch() {
        val teams: MutableList<TeamInfoModel> = mutableListOf()
        val response = TeamInfoResponse(teams)
        val teamName = "Arsenal"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getSearchTeam(teamName)),
                TeamInfoResponse::class.java
        )).thenReturn(response)

        presenter.getTeamSearch(teamName)
        Thread.sleep(8000)

        verify(view).showLoading()
        verify(view).showTeamSearch(teams.filter { it.sportType == "Soccer" })
        verify(view).hideLoading()
    }

}