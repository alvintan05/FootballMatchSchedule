package k.com.alvin.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Alvin Tandiardi on 01/11/2018.
 */
data class TeamInfoModel(

        @SerializedName("strTeamBadge")
        var teamLogoLink: String? = ""

)