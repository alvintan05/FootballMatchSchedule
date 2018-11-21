package k.com.alvin.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Alvin Tandiardi on 20/11/2018.
 */
data class PlayerModel(
        @SerializedName("idPlayer")
        val playerId: String? = "",

        @SerializedName("strPlayer")
        val playerName: String? = "",

        @SerializedName("strPosition")
        val playerPosition: String? = "",

        @SerializedName("strCutout")
        val playerImage: String? = "",

        @SerializedName("strDescriptionEN")
        val playerDescription: String? = "",

        @SerializedName("strWeight")
        val playerWeight: String? = "",

        @SerializedName("strHeight")
        val playerHeight: String? = "",

        @SerializedName("strFanart1")
        val playerBanner: String? = "",

        @SerializedName("strBirthLocation")
        val playerBirthLocation: String? = ""
)