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

        onView(withId(R.id.rv_last_match)).check(matches(isDisplayed()))
        Thread.sleep(5000)

        onView(withId(R.id.rv_last_match)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        Thread.sleep(2000)
        onView(withId(R.id.rv_last_match)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        Thread.sleep(2000)

        onView(withId(R.id.rv_last_match)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, ViewActions.click()))
        Thread.sleep(8000)

        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.add_to_favorite)).perform(click())
        Thread.sleep(3000)
        pressBack()

        onView(withId(R.id.bottom_navigation_view)).check(matches(isDisplayed()))
        onView(withId(R.id.next_match)).perform(click())
        Thread.sleep(8000)

        onView(withId(R.id.rv_next_match)).check(matches(isDisplayed()))
        Thread.sleep(5000)

        onView(withId(R.id.rv_next_match)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        Thread.sleep(2000)
        onView(withId(R.id.rv_next_match)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        Thread.sleep(2000)

        onView(withId(R.id.rv_next_match)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, ViewActions.click()))
        Thread.sleep(8000)

        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.add_to_favorite)).perform(click())
        Thread.sleep(3000)
        pressBack()

        onView(withId(R.id.bottom_navigation_view)).check(matches(isDisplayed()))
        onView(withId(R.id.favorite_match)).perform(click())
        Thread.sleep(5000)

        onView(withId(R.id.rv_favorites)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        Thread.sleep(2000)
        onView(withId(R.id.rv_favorites)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        Thread.sleep(2000)

        onView(withId(R.id.rv_favorites)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))
        Thread.sleep(5000)
        pressBack()

    }
}