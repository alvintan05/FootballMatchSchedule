package k.com.alvin.footballmatchschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import com.google.gson.Gson
import k.com.alvin.footballmatchschedule.adapter.RecyclerSearchMatchAdapter
import k.com.alvin.footballmatchschedule.api.ApiRepository
import k.com.alvin.footballmatchschedule.model.MatchModel
import k.com.alvin.footballmatchschedule.presenter.SearchMatchPresenter
import k.com.alvin.footballmatchschedule.util.invisible
import k.com.alvin.footballmatchschedule.util.visible
import k.com.alvin.footballmatchschedule.view.MatchSearchView
import kotlinx.android.synthetic.main.activity_match_search.*
import org.jetbrains.anko.startActivity

class MatchSearchActivity : AppCompatActivity(), MatchSearchView {

    private var searchResultList: MutableList<MatchModel> = mutableListOf()
    private var request: ApiRepository = ApiRepository()
    private var gson: Gson = Gson()
    private var menuItem: Menu? = null

    private lateinit var searchEvent: String
    private lateinit var presenter: SearchMatchPresenter
    private lateinit var adapter : RecyclerSearchMatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_search)

        val intent = intent
        searchEvent = intent.getStringExtra("search")

        supportActionBar?.title = searchEvent
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rv_search_match.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerSearchMatchAdapter(searchResultList) {
            startActivity<DetailMatchActivity>(
                    "eventId" to "${it.eventId}",
                    "homeId" to "${it.homeId}",
                    "awayId" to "${it.awayId}",
                    "status" to "1")
        }
        rv_search_match.adapter = adapter

        presenter = SearchMatchPresenter(this, request, gson)
        presenter.getMatchSearch(searchEvent.replace(" ", "_"))
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

    override fun showMatchSearch(data: List<MatchModel>) {
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
                        presenter.getMatchSearch(newText.replace(" ", "_"))
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
