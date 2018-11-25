package k.com.alvin.footballmatchschedule.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Alvin Tandiardi on 31/10/2018.
 */
data class DetailMatchModel(

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

        @SerializedName("intHomeShots")
        var homeShots: String? = "",

        @SerializedName("intAwayShots")
        var awayShots: String? = "",

        @SerializedName("dateEvent")
        var matchDate: Date?,

        @SerializedName("strTime")
        var matchTime: String? = "",

        @SerializedName("strAwayGoalDetails")
        var awayGoalDetails: String? = "",

        @SerializedName("strAwayLineupDefense")
        var awayLineupDefense: String? = "",

        @SerializedName("strAwayLineupForward")
        var awayLineupForward: String? = "",

        @SerializedName("strAwayLineupGoalkeeper")
        var awayLineupGoalkeeper: String? = "",

        @SerializedName("strAwayLineupMidfield")
        var awayLineupMidfield: String? = "",

        @SerializedName("strAwayLineupSubstitutes")
        var awayLineupSubtitutes: String? = "",

        @SerializedName("strHomeGoalDetails")
        var homeGoalDetails: String? = "",

        @SerializedName("strHomeLineupDefense")
        var homeLineupDefense: String? = "",

        @SerializedName("strHomeLineupForward")
        var homeLineupForward: String? = "",

        @SerializedName("strHomeLineupGoalkeeper")
        var homeLineupGoalkeeper: String? = "",

        @SerializedName("strHomeLineupMidfield")
        var homeLineupMidfield: String? = "",

        @SerializedName("strHomeLineupSubstitutes")
        var homeLineupSubtitutes: String? = ""

)