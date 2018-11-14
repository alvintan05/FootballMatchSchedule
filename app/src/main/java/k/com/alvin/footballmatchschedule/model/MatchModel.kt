package k.com.alvin.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

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

        @SerializedName("strDate")
        var matchDate: String? = "",

        @SerializedName("idHomeTeam")
        var homeId: String? = "",

        @SerializedName("idAwayTeam")
        var awayId: String? = ""

)