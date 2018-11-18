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
import k.com.alvin.footballmatchschedule.DetailMatchActivity

import k.com.alvin.footballmatchschedule.R
import k.com.alvin.footballmatchschedule.R.string.favorites
import k.com.alvin.footballmatchschedule.adapter.RecyclerFavoritesAdapter
import k.com.alvin.footballmatchschedule.database.Favorite
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
class FavoriteMatchFragment : Fragment() {

    private var favoritesMatch: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: RecyclerFavoritesAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite_match, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_favorites_match)
        swipeRefresh = view.findViewById(R.id.swipe_refresh)

        progressBar = view.findViewById(R.id.progress_bar)

        progressBar.visible()

        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = RecyclerFavoritesAdapter(favoritesMatch) {
            startActivity<DetailMatchActivity>(
                    "eventId" to "${it.eventId}",
                    "homeId" to "${it.homeId}",
                    "awayId" to "${it.awayId}",
                    "status" to "0")
        }
        recyclerView.adapter = adapter

        swipeRefresh.setColorSchemeResources(R.color.colorAccent,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)

        showFavorite()

        swipeRefresh.onRefresh {
            favoritesMatch.clear()
            showFavorite()
        }

        return view
    }

    private fun showFavorite() {
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favoritesMatch.addAll(favorite)
            adapter.notifyDataSetChanged()
            progressBar.invisible()
        }
    }


}
