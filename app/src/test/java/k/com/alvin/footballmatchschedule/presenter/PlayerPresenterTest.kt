package k.com.alvin.footballmatchschedule.presenter

import com.google.gson.Gson
import k.com.alvin.footballmatchschedule.TestContextProvider
import k.com.alvin.footballmatchschedule.api.ApiRepository
import k.com.alvin.footballmatchschedule.api.TheSportDBApi
import k.com.alvin.footballmatchschedule.model.PlayerModel
import k.com.alvin.footballmatchschedule.model.PlayerResponse
import k.com.alvin.footballmatchschedule.view.PlayerView
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by Alvin Tandiardi on 25/11/2018.
 */
class PlayerPresenterTest {

    @Mock
    private lateinit var view: PlayerView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: PlayerPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PlayerPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetPlayerList() {
        val teams: MutableList<PlayerModel> = mutableListOf()
        val response = PlayerResponse(teams)
        val teamName = "Arsenal"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPlayers(teamName)),
                PlayerResponse::class.java
        )).thenReturn(response)

        presenter.gePlayerList(teamName)
        Thread.sleep(8000)

        verify(view).showLoading()
        verify(view).showPlayerList(teams)
        verify(view).hideLoading()
    }
}