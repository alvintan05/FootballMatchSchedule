package k.com.alvin.footballmatchschedule.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
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
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 */
class NextMatchFragment : Fragment(), NextView {

    private var nextMatch: MutableList<MatchModel> = mutableListOf()
    private lateinit var presenter: NextMatchPresenter
    private lateinit var adapter: RecyclerNextMatchAdapter
    private lateinit var spinner: Spinner
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var leagueName: String
    private var leagueId: String = "4328"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_next_match, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_next_match)
        swipeRefresh = view.findViewById(R.id.swipe_refresh)
        progressBar = view.findViewById(R.id.progress_bar)
        spinner = view.findViewById(R.id.next_match_spinner)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

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

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()

                if (leagueName == spinnerItems[0]) {
                    Log.d("nama league", spinnerItems[0])
                    leagueId = "4328"
                    presenter.getNextMatchList(leagueId)
                }

                if (leagueName == spinnerItems[1]) {
                    Log.d("nama league", spinnerItems[1])
                    leagueId = "4329"
                    presenter.getNextMatchList(leagueId)
                }

                if (leagueName == spinnerItems[2]) {
                    Log.d("nama league", spinnerItems[2])
                    leagueId = "4331"
                    presenter.getNextMatchList(leagueId)
                }

                if (leagueName == spinnerItems[3]) {
                    Log.d("nama league", spinnerItems[3])
                    leagueId = "4332"
                    presenter.getNextMatchList(leagueId)
                }

                if (leagueName == spinnerItems[4]) {
                    Log.d("nama league", spinnerItems[4])
                    leagueId = "4334"
                    presenter.getNextMatchList(leagueId)
                }

                if (leagueName == spinnerItems[5]) {
                    Log.d("nama league", spinnerItems[5])
                    leagueId = "4335"
                    presenter.getNextMatchList(leagueId)
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

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
