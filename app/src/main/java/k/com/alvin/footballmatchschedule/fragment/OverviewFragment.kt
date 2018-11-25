package k.com.alvin.footballmatchschedule.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.gson.Gson

import k.com.alvin.footballmatchschedule.R
import k.com.alvin.footballmatchschedule.api.ApiRepository
import k.com.alvin.footballmatchschedule.model.TeamInfoModel
import k.com.alvin.footballmatchschedule.presenter.TeamDetailPresenter
import k.com.alvin.footballmatchschedule.view.TeamDetailView


/**
 * A simple [Fragment] subclass.
 */
class OverviewFragment : Fragment(), TeamDetailView {

    private lateinit var presenter: TeamDetailPresenter
    private lateinit var teamId: String

    private lateinit var teamOverview: TextView

    companion object {
        const val KEY_TEAM = "KEY_TEAM"

        fun newOverviewInstance(teamId: String): OverviewFragment {

            val bundle = Bundle()
            bundle.putString(KEY_TEAM, teamId)

            val fragment = OverviewFragment()
            fragment.arguments = bundle

            return fragment

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val bindData = arguments
        teamId = bindData?.getString(KEY_TEAM) ?: "teamId"

        Log.d("teamId", teamId)

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getTeamDetail(teamId)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_overview, container, false)

        teamOverview = view.findViewById(R.id.team_overview)

        return view
    }

    override fun showTeamDetail(data: List<TeamInfoModel>) {
        teamOverview.text = data[0].teamDescription
    }

}
