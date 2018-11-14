package k.com.alvin.footballmatchschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import k.com.alvin.footballmatchschedule.R.id.*
import k.com.alvin.footballmatchschedule.fragment.FavoritesFragment
import k.com.alvin.footballmatchschedule.fragment.LastMatchFragment
import k.com.alvin.footballmatchschedule.fragment.NextMatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation_view.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                last_match -> {
                    loadLastMatchFragment(savedInstanceState)
                }
                next_match -> {
                    loadNextMatchFragment(savedInstanceState)
                }
                favorite_match -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }

        bottom_navigation_view.selectedItemId = last_match

    }

    private fun loadLastMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, LastMatchFragment(), LastMatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadNextMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, NextMatchFragment(), NextMatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, FavoritesFragment(), FavoritesFragment::class.java.simpleName)
                    .commit()
        }
    }
}
