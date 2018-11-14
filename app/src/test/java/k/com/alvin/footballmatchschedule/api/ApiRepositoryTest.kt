package k.com.alvin.footballmatchschedule.api

import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

/**
 * Created by Alvin Tandiardi on 13/11/2018.
 */
class ApiRepositoryTest {

    @Test
    fun testDoRequestLastMatch() {
        val apiRepository = mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328"
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }

    @Test
    fun testDoRequestNextMatch() {
        val apiRepository = mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328"
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }

    @Test
    fun testDoRequestMatchDetail() {
        val apiRepository = mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id=576587"
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }

    @Test
    fun testDoRequestTeamInfo() {
        val apiRepository = mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=134777"
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }

}