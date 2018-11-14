package k.com.alvin.footballmatchschedule.database

/**
 * Created by Alvin Tandiardi on 05/11/2018.
 */
data class Favorite(val id: Long?,
                    val eventId: String?,
                    val homeId: String?,
                    val awayId: String?,
                    val homeTeamName: String?,
                    val awayTeamName: String?,
                    val homeScore: String?,
                    val awayScore: String?,
                    val homeShots: String?,
                    val awayShots: String?,
                    val matchDate: String?,
                    val awayGoalDetails: String?,
                    val awayLineupDefense: String?,
                    val awayLineupForward: String?,
                    val awayLineupGoalkeeper: String?,
                    val awayLineupMidfield: String?,
                    val awayLineupSubtitutes: String?,
                    val homeGoalDetails: String?,
                    val homeLineupDefense: String?,
                    val homeLineupForward: String?,
                    val homeLineupGoalkeeper: String?,
                    val homeLineupMidfield: String?,
                    val homeLineupSubtitutes: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String ="EVENT_ID"
        const val HOME_ID: String = "HOME_ID"
        const val AWAY_ID: String = "AWAY_ID"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val HOME_SHOTS: String = "HOME_SHOTS"
        const val AWAY_SHOTS: String = "AWAY_SHOTS"
        const val MATCH_DATE: String = "MATCH_DATE"
        const val AWAY_GOAL_DETAILS: String = "AWAY_GOAL_DETAILS"
        const val AWAY_LINEUP_DEFENSE: String = "AWAY_LINEUP_DEFENSE"
        const val AWAY_LINEUP_FORWARD: String = "AWAY_LINEUP_FORWARD"
        const val AWAY_LINEUP_GOAL_KEEPER: String = "AWAY_LINEUP_GOAL_KEEPER"
        const val AWAY_LINEUP_MIDFIELD: String = "AWAY_LINEUP_MIDFIELD"
        const val AWAY_LINEUP_SUBTITUTES: String = "AWAY_LINEUP_SUBTITUTES"
        const val HOME_GOAL_DETAILS: String = "HOME_GOAL_DETAILS"
        const val HOME_LINEUP_DEFENSE: String = "HOME_LINEUP_DEFENSE"
        const val HOME_LINEUP_FORWARD: String = "HOME_LINEUP_FORWARD"
        const val HOME_LINEUP_GOAL_KEEPER: String = "HOME_LINEUP_GOAL_KEEPER"
        const val HOME_LINEUP_MIDFIELD: String = "HOME_LINEUP_MIDFIELD"
        const val HOME_LINEUP_SUBTITUTES: String = "HOME_LINEUP_SUBTITUTES"
    }

}