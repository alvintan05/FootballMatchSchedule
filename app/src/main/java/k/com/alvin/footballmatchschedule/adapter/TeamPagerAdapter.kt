package k.com.alvin.footballmatchschedule.adapter


import android.os.Bundle
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
            0 -> newOverviewInstance(teamId)
            else -> return newPlayerInstance(teamId)
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

    companion object {
        const val KEY_TEAM = "KEY_TEAM"
        const val KEY_PLAYER = "KEY_PLAYER"

        fun newOverviewInstance(teamId: String): OverviewFragment {

            val bundle = Bundle()
            bundle.putString(KEY_TEAM, teamId)

            val fragment = OverviewFragment()
            fragment.arguments = bundle

            return fragment

        }

        fun newPlayerInstance(teamId: String): PlayersFragment {

            val bundle = Bundle()
            bundle.putString(KEY_PLAYER, teamId)

            val fragment = PlayersFragment()
            fragment.arguments = bundle

            return fragment

        }
    }

}