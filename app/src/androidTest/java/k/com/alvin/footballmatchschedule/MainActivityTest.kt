package k.com.alvin.footballmatchschedule

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Alvin Tandiardi on 13/11/2018.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {

        // Check recycler view next match tampil
        onView(withId(R.id.rv_next_match)).check(matches(isDisplayed()))
        Thread.sleep(5000)

        // Scroll recycler view next match ke item posisi 10
        onView(withId(R.id.rv_next_match)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        Thread.sleep(2000)
        // Scroll recycler view next match ke item posisi 1
        onView(withId(R.id.rv_next_match)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        Thread.sleep(2000)

        // Check Spinner
        onView(withId(R.id.next_match_spinner)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        // Click spinner
        onView(withId(R.id.next_match_spinner)).perform(click())
        // Pilih spinner italian serie a
        onView(withText("Italian Serie A")).perform(click())
        Thread.sleep(8000)

        // Click recycler view next match posisi 0
        onView(withId(R.id.rv_next_match)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Thread.sleep(8000)

        // tambah ke favorit
        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.add_to_favorite)).perform(click())
        Thread.sleep(3000)
        pressBack()

        // pindah ke tab last match
        onView(withId(R.id.match_tab_layout)).check(matches(isDisplayed()))
        onView(withText("LAST MATCH")).perform(click())
        Thread.sleep(3000)

        // Memastikan recycler view last match telah ditampilkan
        onView(withId(R.id.rv_last_match)).check(matches(isDisplayed()))
        Thread.sleep(2000)

        // Scroll recycler view last match ke item posisi 10
        onView(withId(R.id.rv_last_match)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        Thread.sleep(2000)
        // Scroll recycler view last match ke item posisi 1
        onView(withId(R.id.rv_last_match)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        Thread.sleep(2000)

        // Check Spinner
        onView(withId(R.id.last_match_spinner)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        // Click spinner
        onView(withId(R.id.last_match_spinner)).perform(click())
        // Pilih spinner German Bundesliga
        onView(withText("German Bundesliga")).perform(click())
        Thread.sleep(8000)

        // Click recycler view last match posisi 2
        onView(withId(R.id.rv_last_match)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, ViewActions.click()))
        Thread.sleep(8000)

        // tambah ke favorit
        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.add_to_favorite)).perform(click())
        Thread.sleep(3000)
        pressBack()

        // Check bottom navigasi
        onView(withId(R.id.bottom_navigation_view)).check(matches(isDisplayed()))
        // Pindah ke team
        onView(withId(R.id.teams)).perform(click())
        Thread.sleep(8000)

        // Check recycler view team
        onView(withId(R.id.rv_teams)).check(matches(isDisplayed()))
        Thread.sleep(2000)

        // Scroll ke posisi 10
        onView(withId(R.id.rv_teams)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        Thread.sleep(2000)
        // Scroll ke posisi 1
        onView(withId(R.id.rv_teams)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        Thread.sleep(2000)

        // Check Spinner
        onView(withId(R.id.teams_spinner)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        // Click spinner
        onView(withId(R.id.teams_spinner)).perform(click())
        // Pilih spinner Spanish La Liga
        onView(withText("Spanish La Liga")).perform(click())
        Thread.sleep(8000)

        // Click item ke 3
        onView(withId(R.id.rv_teams)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, ViewActions.click()))
        Thread.sleep(8000)

        // Tambah ke favorit
        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.add_to_favorite)).perform(click())
        Thread.sleep(3000)

        // Click tab players
        onView(withId(R.id.team_tab_layout)).check(matches(isDisplayed()))
        onView(withText("PLAYERS")).perform(click())
        Thread.sleep(3000)

        // Scroll players ke posisi 13
        onView(withId(R.id.rv_player_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(13))
        Thread.sleep(2000)

        // Click player ke 9
        onView(withId(R.id.rv_player_list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(9, ViewActions.click()))
        Thread.sleep(5000)
        pressBack()
        Thread.sleep(2000)
        pressBack()

        // Pindah ke bottom navigation favorit
        onView(withId(R.id.bottom_navigation_view)).check(matches(isDisplayed()))
        onView(withId(R.id.favorite_match)).perform(click())
        Thread.sleep(3000)

        // Check recycler view favorite match
        onView(withId(R.id.rv_favorites_match)).check(matches(isDisplayed()))

        // Scroll favorit ke posisi 5
        onView(withId(R.id.rv_favorites_match)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        Thread.sleep(2000)
        // Scroll favorit ke posisi 1
        onView(withId(R.id.rv_favorites_match)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        Thread.sleep(2000)

        // Click item posisi 0
        onView(withId(R.id.rv_favorites_match)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))
        Thread.sleep(5000)
        pressBack()

        // Click tab teams
        onView(withId(R.id.favorite_tab_layout)).check(matches(isDisplayed()))
        onView(withText("TEAMS")).perform(click())

        // Click item posisi 0
        onView(withId(R.id.rv_favorites_team)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorites_team)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Thread.sleep(5000)
        pressBack()

    }
}