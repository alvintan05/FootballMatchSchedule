package k.com.alvin.footballmatchschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import k.com.alvin.footballmatchschedule.fragment.LastMatchFragment
import k.com.alvin.footballmatchschedule.fragment.NextMatchFragment

/**
 * Created by Alvin Tandiardi on 16/11/2018.
 */
class MatchPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> NextMatchFragment()
            else -> return LastMatchFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "NEXT MATCH"
            else -> return "LAST MATCH"
        }
    }
}