package k.com.alvin.footballmatchschedule.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import k.com.alvin.footballmatchschedule.DetailMatchActivity

import k.com.alvin.footballmatchschedule.R
import k.com.alvin.footballmatchschedule.adapter.RecyclerNextMatchAdapter
import k.com.alvin.footballmatchschedule.api.ApiRepository
import k.com.alvin.footballmatchschedule.model.MatchModel
import k.com.alvin.footballmatchschedule.presenter.NextMatchPresenter
import k.com.alvin.footballmatchschedule.util.invisible
import k.com.alvin.footballmatchschedule.util.visible
import k.com.alvin.footballmatchschedule.view.NextView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 */
class NextMatchFragment : Fragment(), NextView {

    private var nextMatch: MutableList<MatchModel> = mutableListOf()
    private lateinit var presenter: NextMatchPresenter
    private lateinit var adapter: RecyclerNextMatchAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private val leagueId: String = "4328"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_next_match, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_next_match)
        swipeRefresh = view.findViewById(R.id.swipe_refresh)

        progressBar = view.findViewById(R.id.progress_bar)

        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = RecyclerNextMatchAdapter(nextMatch) {
            startActivity<DetailMatchActivity>(
                    "eventId" to "${it.eventId}",
                    "homeId" to "${it.homeId}",
                    "awayId" to "${it.awayId}",
                    "status" to "1")
        }
        recyclerView.adapter = adapter

        swipeRefresh.setColorSchemeResources(R.color.colorAccent,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)

        val request = ApiRepository()
        val gson = Gson()
        presenter = NextMatchPresenter(this, request, gson)
        presenter.getNextMatchList(leagueId)

        swipeRefresh.onRefresh {
            presenter.getNextMatchList(leagueId)
        }

        return view
    }

    override fun showNextMatchList(data: List<MatchModel>) {
        swipeRefresh.isRefreshing = false
        nextMatch.clear()
        nextMatch.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

}// Required empty public constructor
