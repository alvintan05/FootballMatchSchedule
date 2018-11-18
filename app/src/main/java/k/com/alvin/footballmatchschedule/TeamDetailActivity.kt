package k.com.alvin.footballmatchschedule

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.google.gson.Gson
import k.com.alvin.footballmatchschedule.R.drawable.ic_add_to_favorites
import k.com.alvin.footballmatchschedule.R.drawable.ic_added_to_favorites
import k.com.alvin.footballmatchschedule.R.menu.favorite_detail_menu
import k.com.alvin.footballmatchschedule.adapter.TeamPagerAdapter
import k.com.alvin.footballmatchschedule.api.ApiRepository
import k.com.alvin.footballmatchschedule.database.Team
import k.com.alvin.footballmatchschedule.database.database
import k.com.alvin.footballmatchschedule.fragment.OverviewFragment
import k.com.alvin.footballmatchschedule.model.TeamInfoModel
import k.com.alvin.footballmatchschedule.presenter.TeamDetailPresenter
import k.com.alvin.footballmatchschedule.view.TeamDetailView
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {

    private var teamModels: MutableList<TeamInfoModel> = mutableListOf()
    private var favorites: MutableList<Team> = mutableListOf()

    private lateinit var presenter: TeamDetailPresenter
    private lateinit var teamId: String

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        val intent = intent
        teamId = intent.getStringExtra("id")

        val teamAdapter = TeamPagerAdapter(teamId, supportFragmentManager)
        team_view_pager.adapter = teamAdapter
        team_tab_layout.setupWithViewPager(team_view_pager)

        setSupportActionBar(tb_team_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapse_tb.setExpandedTitleColor(resources.getColor(android.R.color.transparent))

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getTeamDetail(teamId)

    }

    override fun showTeamDetail(data: List<TeamInfoModel>) {
        teamModels.addAll(data)

        Glide.with(this).load(data[0].teamBadge).into(detail_team_badge)
        Glide.with(this).load(data[0].teamStadiumImage).into(img_banner)
        detail_team_name.text = data[0].teamName
        detail_team_year.text = data[0].teamFormedYear
        detail_team_stadium.text = data[0].teamStadium

        collapse_tb.title = data[0].teamName

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(favorite_detail_menu, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_to_favorite -> {

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
                insert(Team.TABLE_TEAM,
                        Team.TEAM_ID to teamModels[0].teamId,
                        Team.TEAM_NAME to teamModels[0].teamName,
                        Team.TEAM_FORMED_YEAR to teamModels[0].teamFormedYear,
                        Team.TEAM_STADIUM to teamModels[0].teamStadium,
                        Team.TEAM_BADGE to teamModels[0].teamBadge)
            }
            toast("Added to favorite")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Team.TABLE_TEAM, "(TEAM_ID = {id})",
                        "id" to teamId)
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
            val result = select(Team.TEAM_ID)
                    .whereArgs("(EVENT_ID = {id})",
                            "id" to teamId)
            val favorite = result.parseList(classParser<Team>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

}
