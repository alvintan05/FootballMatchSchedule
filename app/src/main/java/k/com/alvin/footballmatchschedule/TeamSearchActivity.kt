package k.com.alvin.footballmatchschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import com.google.gson.Gson
import k.com.alvin.footballmatchschedule.adapter.RecyclerTeamsAdapter
import k.com.alvin.footballmatchschedule.api.ApiRepository
import k.com.alvin.footballmatchschedule.model.TeamInfoModel
import k.com.alvin.footballmatchschedule.presenter.SearchTeamPresenter
import k.com.alvin.footballmatchschedule.util.invisible
import k.com.alvin.footballmatchschedule.util.visible
import k.com.alvin.footballmatchschedule.view.TeamSearchView
import kotlinx.android.synthetic.main.activity_team_search.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class TeamSearchActivity : AppCompatActivity(), TeamSearchView {

    private var searchResultList: MutableList<TeamInfoModel> = mutableListOf()
    private var request: ApiRepository = ApiRepository()
    private var gson: Gson = Gson()
    private var menuItem: Menu? = null

    private lateinit var searchTeam: String
    private lateinit var presenter: SearchTeamPresenter
    private lateinit var adapter: RecyclerTeamsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_search)

        val intent = intent
        searchTeam = intent.getStringExtra("search")

        supportActionBar?.title = searchTeam
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rv_teams_search.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerTeamsAdapter(searchResultList) {
            startActivity<TeamDetailActivity>(
                    "id" to "${it.teamId}",
                    "name" to "${it.teamName}",
                    "status" to "1")
        }
        rv_teams_search.adapter = adapter

        presenter = SearchTeamPresenter(this, request, gson)
        presenter.getTeamSearch(searchTeam)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showLoading() {
        progress_bar.visible()
    }

    override fun hideLoading() {
        progress_bar.invisible()
    }

    override fun showTeamSearch(data: List<TeamInfoModel>) {
        searchResultList.clear()
        searchResultList.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        menuItem = menu
        val searchItem = menu?.findItem(R.id.menu_search)

        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {

                    if (newText.isNotEmpty()) {
                        presenter.getTeamSearch(newText)
                    } else {
                        searchResultList.clear()
                    }

                    return true
                }

            })
        }
        return true
    }

}
