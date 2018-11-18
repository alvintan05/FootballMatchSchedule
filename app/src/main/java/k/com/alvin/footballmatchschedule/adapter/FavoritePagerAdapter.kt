package k.com.alvin.footballmatchschedule.adapter


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import k.com.alvin.footballmatchschedule.fragment.FavoriteMatchFragment
import k.com.alvin.footballmatchschedule.fragment.FavoriteTeamFragment

/**
 * Created by Alvin Tandiardi on 18/11/2018.
 */
class FavoritePagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> FavoriteMatchFragment()
            else -> return  FavoriteTeamFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position) {
            0 -> "MATCHES"
            else -> return "TEAMS"
        }
    }
}