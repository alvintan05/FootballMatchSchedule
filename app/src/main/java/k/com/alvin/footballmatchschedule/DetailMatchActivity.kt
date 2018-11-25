package k.com.alvin.footballmatchschedule

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.google.gson.Gson
import k.com.alvin.footballmatchschedule.R.drawable.ic_add_to_favorites
import k.com.alvin.footballmatchschedule.R.drawable.ic_added_to_favorites
import k.com.alvin.footballmatchschedule.R.id.add_to_favorite
import k.com.alvin.footballmatchschedule.R.menu.favorite_detail_menu
import k.com.alvin.footballmatchschedule.api.ApiRepository
import k.com.alvin.footballmatchschedule.database.Favorite
import k.com.alvin.footballmatchschedule.database.database
import k.com.alvin.footballmatchschedule.model.DetailMatchModel
import k.com.alvin.footballmatchschedule.model.TeamInfoModel
import k.com.alvin.footballmatchschedule.presenter.DetailPresenter
import k.com.alvin.footballmatchschedule.util.invisible
import k.com.alvin.footballmatchschedule.util.visible
import k.com.alvin.footballmatchschedule.view.DetailView
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class DetailMatchActivity : AppCompatActivity(), DetailView {

    private var detailModels: MutableList<DetailMatchModel> = mutableListOf()
    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var presenter: DetailPresenter
    private var eventId: String = ""
    private var homeId: String = ""
    private var awayId: String = ""
    private var checkType: String = ""

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        setSupportActionBar(toolbar_detail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        eventId = intent.getStringExtra("eventId")
        homeId = intent.getStringExtra("homeId")
        awayId = intent.getStringExtra("awayId")
        checkType = intent.getStringExtra("status")

        val request = ApiRepository()
        val gson = Gson()

        presenter = DetailPresenter(this, request, gson)

        checkStatus()

        favoriteState()

        presenter.getHomeTeamInfo(homeId)
        presenter.getAwayTeamInfo(awayId)

    }

    private fun getFromApi() {

        presenter.getDetailMatchList(eventId)

    }

    override fun showDetailMatchList(data: List<DetailMatchModel>) {
        detailModels.addAll(data)

        val dateMatch = toGMTFormat(data[0].matchDate, data[0].matchTime)

        tv_detail_date.text = dateFormat(dateMatch)
        tv_detail_time.text = timeFormat(dateMatch)
        tv_detail_home_team.text = data[0].homeTeam
        tv_detail_away_team.text = data[0].awayTeam
        tv_detail_home_score.text = data[0].homeScore
        tv_detail_away_score.text = data[0].awayScore
        tv_detail_home_goals.text = data[0].homeGoalDetails?.replace(";", "\n")
        tv_detail_away_goals.text = data[0].awayGoalDetails?.replace(";", "\n")
        tv_detail_home_shots.text = data[0].homeShots
        tv_detail_away_shots.text = data[0].awayShots
        tv_detail_home_goal_keeper.text = data[0].homeLineupGoalkeeper?.replace("; ", "\n")
        tv_detail_away_goal_keeper.text = data[0].awayLineupGoalkeeper?.replace("; ", "\n")
        tv_detail_home_defense.text = data[0].homeLineupDefense?.replace("; ", "\n")
        tv_detail_away_defense.text = data[0].awayLineupDefense?.replace("; ", "\n")
        tv_detail_home_midfield.text = data[0].homeLineupMidfield?.replace("; ", "\n")
        tv_detail_away_midfield.text = data[0].awayLineupMidfield?.replace("; ", "\n")
        tv_detail_home_forward.text = data[0].homeLineupForward?.replace("; ", "\n")
        tv_detail_away_forward.text = data[0].awayLineupForward?.replace("; ", "\n")
        tv_detail_home_subtitute.text = data[0].homeLineupSubtitutes?.replace("; ", "\n")
        tv_detail_away_subtitute.text = data[0].awayLineupSubtitutes?.replace("; ", "\n")

    }

    override fun showHomeInfo(data: List<TeamInfoModel>) {
        Glide.with(this).load(data[0].teamBadge).override(120, 120).into(image_home_team)
    }

    override fun showAwayInfo(data: List<TeamInfoModel>) {
        Glide.with(this).load(data[0].teamBadge).override(120, 120).into(image_away_team)
    }

    @SuppressLint("SimpleDateFormat")
    fun dateFormat(matchDate : Date?): String {
        val newFormat: SimpleDateFormat = SimpleDateFormat("EEE, dd MMM yyyy")
        val finalDate: String = newFormat.format(matchDate)
        return finalDate
    }

    @SuppressLint("SimpleDateFormat")
    fun timeFormat(matchTime : Date?): String {
        val newFormat: SimpleDateFormat = SimpleDateFormat("HH:mm")
        val finalTime: String = newFormat.format(matchTime)
        return finalTime
    }

    fun dateFormatSaveDB(matchDate: Date?) : String {
        val format: SimpleDateFormat = SimpleDateFormat("dd/MM/yy")
        val result: String = format.format(matchDate)
        return result
    }

    @SuppressLint("SimpleDateFormat")
    fun toGMTFormat(date: Date?, time: String?): Date? {
        var result: Date? = null

        if (time.isNullOrBlank()) {
            val dateStringFormat: SimpleDateFormat = SimpleDateFormat("dd/MM/yy")
            val dateString: String = dateStringFormat.format(date)
            val formatter = SimpleDateFormat("dd/MM/yy")
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            result = formatter.parse(dateString)
        }

        if (date == null) {
            val formatter = SimpleDateFormat("HH:mm")
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            result = formatter.parse(time)
        }

        if (!time.isNullOrBlank() && date != null) {
            val dateStringFormat: SimpleDateFormat = SimpleDateFormat("dd/MM/yy")
            val dateString: String = dateStringFormat.format(date)
            val formatter = SimpleDateFormat("dd/MM/yy HH:mm")
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            val dateTime = "$dateString $time"
            result = formatter.parse(dateTime)
        }

        return result
    }

    @SuppressLint("SimpleDateFormat")
    fun toGMTFormatString(date: String?, time: String?): Date? {
        var result: Date? = null

        if (time.isNullOrBlank()) {
            val formatter = SimpleDateFormat("dd/MM/yy")
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            result = formatter.parse(date)
        }

        if (date == null) {
            val formatter = SimpleDateFormat("HH:mm")
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            result = formatter.parse(time)
        }

        if (!time.isNullOrBlank() && date != null) {
            val formatter = SimpleDateFormat("dd/MM/yy HH:mm")
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            val dateTime = "$date $time"
            result = formatter.parse(dateTime)
        }

        return result
    }

    override fun showLoading() {
        progress_bar.visible()
    }

    override fun hideLoading() {
        progress_bar.invisible()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(favorite_detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            add_to_favorite -> {

                if (isFavorite) removeFromFavorite()
                else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.EVENT_ID to detailModels[0].eventId,
                        Favorite.HOME_ID to homeId,
                        Favorite.AWAY_ID to awayId,
                        Favorite.HOME_TEAM_NAME to detailModels[0].homeTeam,
                        Favorite.AWAY_TEAM_NAME to detailModels[0].awayTeam,
                        Favorite.HOME_SCORE to detailModels[0].homeScore,
                        Favorite.AWAY_SCORE to detailModels[0].awayScore,
                        Favorite.HOME_SHOTS to detailModels[0].homeShots,
                        Favorite.AWAY_SHOTS to detailModels[0].awayShots,
                        Favorite.MATCH_DATE to dateFormatSaveDB(detailModels[0].matchDate),
                        Favorite.MATCH_TIME to detailModels[0].matchTime,
                        Favorite.AWAY_GOAL_DETAILS to detailModels[0].awayGoalDetails,
                        Favorite.AWAY_LINEUP_DEFENSE to detailModels[0].awayLineupDefense,
                        Favorite.AWAY_LINEUP_FORWARD to detailModels[0].awayLineupForward,
                        Favorite.AWAY_LINEUP_GOAL_KEEPER to detailModels[0].awayLineupGoalkeeper,
                        Favorite.AWAY_LINEUP_MIDFIELD to detailModels[0].awayLineupMidfield,
                        Favorite.AWAY_LINEUP_SUBTITUTES to detailModels[0].awayLineupSubtitutes,
                        Favorite.HOME_GOAL_DETAILS to detailModels[0].homeGoalDetails,
                        Favorite.HOME_LINEUP_DEFENSE to detailModels[0].homeLineupDefense,
                        Favorite.HOME_LINEUP_FORWARD to detailModels[0].homeLineupForward,
                        Favorite.HOME_LINEUP_GOAL_KEEPER to detailModels[0].homeLineupGoalkeeper,
                        Favorite.HOME_LINEUP_MIDFIELD to detailModels[0].homeLineupMidfield,
                        Favorite.HOME_LINEUP_SUBTITUTES to detailModels[0].homeLineupSubtitutes)
            }
            toast("Added to favorite")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})",
                        "id" to eventId)
            }
            toast("Removed from Favorite")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(EVENT_ID = {id})",
                            "id" to eventId)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun checkStatus() {
        if (checkType == "1") {
            Log.d("DetailActivity", "Dari API")
            getFromApi()
        }
        if (checkType == "0") {
            Log.d("DetailActivity", "Dari database")
            showFavorite()
        }
    }

    private fun getFavorite() {
        this.database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(EVENT_ID = {id})",
                            "id" to eventId)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
        }

        progress_bar.invisible()
    }

    private fun showFavorite() {
        getFavorite()

        val dateMatchString = toGMTFormatString(favorites[0].matchDate, favorites[0].matchTime)

        tv_detail_date.text = dateFormat(dateMatchString)
        tv_detail_time.text = timeFormat(dateMatchString)
        tv_detail_home_team.text = favorites[0].homeTeamName
        tv_detail_away_team.text = favorites[0].awayTeamName
        tv_detail_home_score.text = favorites[0].homeScore
        tv_detail_away_score.text = favorites[0].awayScore
        tv_detail_home_goals.text = favorites[0].homeGoalDetails?.replace(";", "\n")
        tv_detail_away_goals.text = favorites[0].awayGoalDetails?.replace(";", "\n")
        tv_detail_home_shots.text = favorites[0].homeShots
        tv_detail_away_shots.text = favorites[0].awayShots
        tv_detail_home_goal_keeper.text = favorites[0].homeLineupGoalkeeper?.replace("; ", "\n")
        tv_detail_away_goal_keeper.text = favorites[0].awayLineupGoalkeeper?.replace("; ", "\n")
        tv_detail_home_defense.text = favorites[0].homeLineupDefense?.replace("; ", "\n")
        tv_detail_away_defense.text = favorites[0].awayLineupDefense?.replace("; ", "\n")
        tv_detail_home_midfield.text = favorites[0].homeLineupMidfield?.replace("; ", "\n")
        tv_detail_away_midfield.text = favorites[0].awayLineupMidfield?.replace("; ", "\n")
        tv_detail_home_forward.text = favorites[0].homeLineupForward?.replace("; ", "\n")
        tv_detail_away_forward.text = favorites[0].awayLineupForward?.replace("; ", "\n")
        tv_detail_home_subtitute.text = favorites[0].homeLineupSubtitutes?.replace("; ", "\n")
        tv_detail_away_subtitute.text = favorites[0].awayLineupSubtitutes?.replace("; ", "\n")

    }

}
