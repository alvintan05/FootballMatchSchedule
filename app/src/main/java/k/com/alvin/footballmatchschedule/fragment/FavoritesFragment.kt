package k.com.alvin.footballmatchschedule.fragment


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import k.com.alvin.footballmatchschedule.R
import k.com.alvin.footballmatchschedule.adapter.FavoritePagerAdapter

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
