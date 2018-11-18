package k.com.alvin.footballmatchschedule.fragment


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.startActivity
import android.support.v4.view.ViewPager
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
import k.com.alvin.footballmatchschedule.adapter.FavoritePagerAdapter
import k.com.alvin.footballmatchschedule.adapter.RecyclerFavoritesAdapter
import k.com.alvin.footballmatchschedule.database.Favorite
import k.com.alvin.footballmatchschedule.database.database
import k.com.alvin.footballmatchschedule.util.invisible
import k.com.alvin.footballmatchschedule.util.visible
import kotlinx.coroutines.experimental.selects.select
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class FavoritesFragment : Fragment() {

    private lateinit var favoriteViewPager: ViewPager
    private lateinit var favoriteTabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        favoriteViewPager = view.findViewById(R.id.favorite_view_pager)
        favoriteTabLayout = view.findViewById(R.id.favorite_tab_layout)

        val favoriteAdapter = FavoritePagerAdapter(childFragmentManager)
        favoriteViewPager.adapter = favoriteAdapter
        favoriteTabLayout.setupWithViewPager(favoriteViewPager)

        return view
    }

}// Required empty public constructor
