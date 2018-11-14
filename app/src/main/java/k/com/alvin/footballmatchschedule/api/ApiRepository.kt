package k.com.alvin.footballmatchschedule.api

import java.net.URL

/**
 * Created by Alvin Tandiardi on 29/10/2018.
 */
class ApiRepository {

    fun doRequest(url: String): String {
        return URL(url).readText()
    }

}