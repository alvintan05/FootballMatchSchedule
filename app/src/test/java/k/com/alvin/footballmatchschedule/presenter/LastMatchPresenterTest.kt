package k.com.alvin.footballmatchschedule.presenter

import com.google.gson.Gson
import k.com.alvin.footballmatchschedule.TestContextProvider
import k.com.alvin.footballmatchschedule.api.ApiRepository
import k.com.alvin.footballmatchschedule.api.TheSportDBApi
import k.com.alvin.footballmatchschedule.model.MatchModel
import k.com.alvin.footballmatchschedule.model.MatchResponse
import k.com.alvin.footballmatchschedule.view.LastView
import org.junit.Test
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by Alvin Tandiardi on 13/11/2018.
 */
class LastMatchPresenterTest {

    @Mock
    private lateinit var view: LastView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: LastMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LastMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetLastMatchList() {
        val teams: MutableList<MatchModel> = mutableListOf()
        val response = MatchResponse(teams)
        val leagueId = "4328"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLastMatch(leagueId)),
                MatchResponse::class.java
        )).thenReturn(response)

        presenter.getLastMatchList(leagueId)
        Thread.sleep(8000)

        verify(view).showLastMatchList(teams)
        verify(view).hideLoading()
    }
}