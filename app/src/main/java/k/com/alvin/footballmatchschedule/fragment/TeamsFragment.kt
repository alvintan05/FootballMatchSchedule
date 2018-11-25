package k.com.alvin.footballmatchschedule.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson

import k.com.alvin.footballmatchschedule.R
import k.com.alvin.footballmatchschedule.TeamDetailActivity
import k.com.alvin.footballmatchschedule.TeamSearchActivity
import k.com.alvin.footballmatchschedule.adapter.RecyclerTeamsAdapter
import k.com.alvin.footballmatchschedule.api.ApiRepository
import k.com.alvin.footballmatchschedule.model.TeamInfoModel
import k.com.alvin.footballmatchschedule.presenter.TeamPresenter
import k.com.alvin.footballmatchschedule.util.invisible
import k.com.alvin.footballmatchschedule.view.TeamView
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast


/**
 * A simple [Fragment] subclass.
 */
class TeamsFragment : Fragment(), TeamView {

    private var teams: MutableList<TeamInfoModel> = mutableListOf()

    private lateinit var recyclerViewTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: RecyclerTeamsAdapter
    private lateinit var leagueName: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_teams, container, false)
        setHasOptionsMenu(true)

        recyclerViewTeam = view.findViewById(R.id.rv_teams)
        swipeRefresh = view.findViewById(R.id.teams_swipe_refresh)
        progressBar = view.findViewById(R.id.progress_bar)
        spinner = view.findViewById(R.id.teams_spinner)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        recyclerViewTeam.layoutManager = LinearLayoutManager(context)
        adapter = RecyclerTeamsAdapter(teams) {
            ctx.startActivity<TeamDetailActivity>(
                    "id" to "${it.teamId}",
                    "name" to "${it.teamName}",
                    "status" to "1")
        }
        recyclerViewTeam.adapter = adapter

        swipeRefresh.setColorSchemeResources(R.color.colorAccent,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this, request, gson)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                val removeSpace = leagueName.replace(" ", "%20")
                presenter.getTeamList(removeSpace)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

        swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
        }

        return view
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<TeamInfoModel>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.menu_search)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    ctx.startActivity<TeamSearchActivity>(
                            "search" to query
                    )
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }

            })
        }
    }

}
