package k.com.alvin.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Alvin Tandiardi on 01/11/2018.
 */
data class TeamInfoModel(

        @SerializedName("idTeam")
        val teamId: String? = "",

        @SerializedName("strTeam")
        var teamName: String? = "",

        @SerializedName("strTeamBadge")
        var teamBadge: String? = "",

        @SerializedName("intFormedYear")
        var teamFormedYear: String? = "",

        @SerializedName("strStadium")
        var teamStadium: String? = "",

        @SerializedName("strDescriptionEN")
        var teamDescription: String? = "",

        @SerializedName("strStadiumThumb")
        var teamStadiumImage: String? = ""

)