package k.com.alvin.footballmatchschedule.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Alvin Tandiardi on 29/10/2018.
 */
data class MatchModel(

        @SerializedName("idEvent")
        var eventId: String? = "",

        @SerializedName("strHomeTeam")
        var homeTeam: String? = "",

        @SerializedName("strAwayTeam")
        var awayTeam: String? = "",

        @SerializedName("intHomeScore")
        var homeScore: String? = "",

        @SerializedName("intAwayScore")
        var awayScore: String? = "",

        @SerializedName("dateEvent")
        var matchDate: Date? ,

        @SerializedName("idHomeTeam")
        var homeId: String? = "",

        @SerializedName("idAwayTeam")
        var awayId: String? = "",

        @SerializedName("strTime")
        var matchTime: String? = "",

        @SerializedName("strSport")
        var sportType: String?

)