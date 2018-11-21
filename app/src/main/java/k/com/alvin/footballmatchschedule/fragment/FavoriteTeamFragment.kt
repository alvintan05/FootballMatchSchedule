package k.com.alvin.footballmatchschedule.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import k.com.alvin.footballmatchschedule.R
import k.com.alvin.footballmatchschedule.TeamDetailActivity
import k.com.alvin.footballmatchschedule.adapter.RecyclerFavoritesTeamAdapter
import k.com.alvin.footballmatchschedule.database.Team
import k.com.alvin.footballmatchschedule.database.database
import k.com.alvin.footballmatchschedule.util.invisible
import k.com.alvin.footballmatchschedule.util.visible
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTeamFragment : Fragment() {

    private var favoritesTeam: MutableList<Team> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerFavoritesTeamAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite_team, container, false)

        recyclerView = view.findViewById(R.id.rv_favorites_team)
        swipeRefreshLayout = view.findViewById(R.id.favorite_teams_swipe_refresh)
        progressBar = view.findViewById(R.id.progress_bar)

        progressBar.visible()

        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = RecyclerFavoritesTeamAdapter(favoritesTeam) {
            startActivity<TeamDetailActivity>(
                    "id" to "${it.teamId}",
                    "name" to "${it.teamName}",
                    "status" to "0"
            )
        }
        recyclerView.adapter = adapter

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)

        showFavorite()

        swipeRefreshLayout.onRefresh {
            favoritesTeam.clear()
            showFavorite()
        }

        return view
    }

    private fun showFavorite() {
        context?.database?.use {
            swipeRefreshLayout.isRefreshing = false
            val result = select(Team.TABLE_TEAM)
            val favorite = result.parseList(classParser<Team>())
            favoritesTeam.addAll(favorite)
            adapter.notifyDataSetChanged()
            progressBar.invisible()
        }
    }

}// Required empty public constructor
