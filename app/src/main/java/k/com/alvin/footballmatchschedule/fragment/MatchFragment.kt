package k.com.alvin.footballmatchschedule.fragment


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import k.com.alvin.footballmatchschedule.R
import k.com.alvin.footballmatchschedule.adapter.MatchPagerAdapter
import kotlinx.android.synthetic.main.fragment_match.*


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

        matchViewPager = view.findViewById(R.id.match_view_pager)
        matchTabLayout = view.findViewById(R.id.match_tab_layout)

        val fragmentMatchAdapter = MatchPagerAdapter(childFragmentManager)
        matchViewPager.adapter = fragmentMatchAdapter

        matchTabLayout.setupWithViewPager(matchViewPager)

        return view
    }

}// Required empty public constructor
