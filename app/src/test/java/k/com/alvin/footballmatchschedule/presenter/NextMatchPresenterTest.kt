package k.com.alvin.footballmatchschedule.presenter

import com.google.gson.Gson
import k.com.alvin.footballmatchschedule.TestContextProvider
import k.com.alvin.footballmatchschedule.api.ApiRepository
import k.com.alvin.footballmatchschedule.api.TheSportDBApi
import k.com.alvin.footballmatchschedule.model.MatchModel
import k.com.alvin.footballmatchschedule.model.MatchResponse
import k.com.alvin.footballmatchschedule.view.NextView
import org.junit.Test
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by Alvin Tandiardi on 13/11/2018.
 */
class NextMatchPresenterTest {

    @Mock
    private lateinit var view: NextView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: NextMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetNextMatchList() {
        val teams: MutableList<MatchModel> = mutableListOf()
        val response = MatchResponse(teams)
        val leagueId = "4328"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextMatch(leagueId)),
                MatchResponse::class.java
        )).thenReturn(response)

        presenter.getNextMatchList(leagueId)
        Thread.sleep(8000)

        verify(view).showNextMatchList(teams)
        verify(view).hideLoading()
    }

}