package k.com.alvin.footballmatchschedule.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import k.com.alvin.footballmatchschedule.PlayerDetailActivity

import k.com.alvin.footballmatchschedule.R
import k.com.alvin.footballmatchschedule.adapter.RecyclerPlayerAdapter
import k.com.alvin.footballmatchschedule.api.ApiRepository
import k.com.alvin.footballmatchschedule.model.PlayerModel
import k.com.alvin.footballmatchschedule.presenter.PlayerPresenter
import k.com.alvin.footballmatchschedule.util.invisible
import k.com.alvin.footballmatchschedule.util.visible
import k.com.alvin.footballmatchschedule.view.PlayerView
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 */
class PlayersFragment : Fragment(), PlayerView {

    private var playerList: MutableList<PlayerModel> = mutableListOf()
    private lateinit var presenter: PlayerPresenter
    private lateinit var adapter : RecyclerPlayerAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    private lateinit var teamName : String

    companion object {
        const val KEY_PLAYER = "KEY_PLAYER"

        fun newPlayerInstance(teamName: String): PlayersFragment {

            val bundle = Bundle()
            bundle.putString(KEY_PLAYER, teamName)

            val fragment = PlayersFragment()
            fragment.arguments = bundle

            return fragment

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val bindData = arguments
        teamName = bindData?.getString(KEY_PLAYER) ?: "teamName"
        teamName = teamName.replace(" ", "%20")

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this, request, gson)

        presenter.gePlayerList(teamName)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_players, container, false)

        recyclerView = view.findViewById(R.id.rv_player_list)
        progressBar = view.findViewById(R.id.progress_bar)

        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = RecyclerPlayerAdapter(playerList) {
            startActivity<PlayerDetailActivity>(
                "player" to it
            )
        }
        recyclerView.adapter = adapter

        return view
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPlayerList(data: List<PlayerModel>) {
        playerList.clear()
        playerList.addAll(data)
        adapter.notifyDataSetChanged()
    }

}// Required empty public constructor
