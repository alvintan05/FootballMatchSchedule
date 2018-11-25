package k.com.alvin.footballmatchschedule.fragment


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
import android.view.*
import k.com.alvin.footballmatchschedule.MatchSearchActivity

import k.com.alvin.footballmatchschedule.R
import k.com.alvin.footballmatchschedule.adapter.MatchPagerAdapter
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class MatchFragment : Fragment() {

    private lateinit var matchViewPager: ViewPager
    private lateinit var matchTabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_match, container, false)

        setHasOptionsMenu(true)

        matchViewPager = view.findViewById(R.id.match_view_pager)
        matchTabLayout = view.findViewById(R.id.match_tab_layout)

        val fragmentMatchAdapter = MatchPagerAdapter(childFragmentManager)
        matchViewPager.adapter = fragmentMatchAdapter

        matchTabLayout.setupWithViewPager(matchViewPager)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.menu_search)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    ctx.startActivity<MatchSearchActivity>(
                            "search" to query
                    )
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })
        }
    }

}// Required empty public constructor
