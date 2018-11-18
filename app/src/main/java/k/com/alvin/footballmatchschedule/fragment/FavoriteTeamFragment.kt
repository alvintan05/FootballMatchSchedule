package k.com.alvin.footballmatchschedule.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import k.com.alvin.footballmatchschedule.R

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTeamFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite_team, container, false)

        return view
    }

}// Required empty public constructor
