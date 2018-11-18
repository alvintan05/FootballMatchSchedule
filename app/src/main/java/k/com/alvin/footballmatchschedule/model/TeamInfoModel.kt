package k.com.alvin.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Alvin Tandiardi on 01/11/2018.
 */
data class TeamInfoModel(

        @SerializedName("idTeam")
        val teamId: String? = null,

        @SerializedName("strTeam")
        var teamName: String? = null,

        @SerializedName("strTeamBadge")
        var teamBadge: String? = null,

        @SerializedName("intFormedYear")
        var teamFormedYear: String? = null,

        @SerializedName("strStadium")
        var teamStadium: String? = null,

        @SerializedName("strDescriptionEN")
        var teamDescription: String? = null,

        @SerializedName("strStadiumThumb")
        var teamStadiumImage: String? = null

)