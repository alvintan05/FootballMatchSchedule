package k.com.alvin.footballmatchschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import k.com.alvin.footballmatchschedule.fragment.OverviewFragment
import k.com.alvin.footballmatchschedule.fragment.PlayersFragment

/**
 * Created by Alvin Tandiardi on 18/11/2018.
 */
class TeamPagerAdapter(private val teamId: String, fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> OverviewFragment.newOverviewInstance(teamId)
            else -> return PlayersFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "OVERVIEW"
            else -> return "PLAYERS"
        }
    }


}