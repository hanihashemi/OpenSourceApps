package ir.opensourceapps.android.ui.browse


import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import ir.opensourceapps.android.R
import ir.opensourceapps.android.helper.stringFromFile
import ir.opensourceapps.android.ui.repo.RepoActivity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BrowseActivityTest {
    @get:Rule var mActivityTestRule = ActivityTestRule(BrowseActivity::class.java, false, false)
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start(3300)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun conditionNormal_whenLoadRepoList_thenClickOnFirstItem() {
        server.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(stringFromFile(InstrumentationRegistry.getInstrumentation().context, "get_search_repositories_success.json")))

        mActivityTestRule.launchActivity(null)

        Intents.init()

        val viewGroup = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.cardView),
                                childAtPosition(
                                        withId(R.id.rclRepo),
                                        0)),
                        0),
                        isDisplayed()))
        viewGroup.perform(ViewActions.click())

        Intents.intended(IntentMatchers.hasComponent(RepoActivity::class.java.name))
        Intents.release()
    }

    @Test
    fun conditionNormal_whenLoadRepoList_thenCheckFirstItem() {
        server.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(stringFromFile(InstrumentationRegistry.getInstrumentation().context, "get_search_repositories_success.json")))

        mActivityTestRule.launchActivity(null)

        val textView = onView(
                allOf(withId(R.id.lblName), withText("wasabeef/awesome-android-ui"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cardView),
                                        0),
                                1),
                        isDisplayed()))
        textView.check(matches(withText("wasabeef/awesome-android-ui")))

        val textView2 = onView(
                allOf(withId(R.id.lblDescription), withText("A curated list of awesome Android UI/UX libraries"),
                        isDisplayed()))
        textView2.check(matches(withText("A curated list of awesome Android UI/UX libraries")))

        val textView3 = onView(
                allOf(withId(R.id.lblStar), withText("31587"),
                        isDisplayed()))
        textView3.check(matches(withText("31587")))

        val textView4 = onView(
                allOf(withId(R.id.lblFork), withText("8404"),
                        isDisplayed()))
        textView4.check(matches(withText("8404")))

        val textView5 = onView(
                allOf(withId(R.id.lblIssue), withText("14"),
                        isDisplayed()))
        textView5.check(matches(withText("14")))

        val viewGroup = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.cardView),
                                childAtPosition(
                                        withId(R.id.rclRepo),
                                        0)),
                        0),
                        isDisplayed()))
        viewGroup.check(matches(isDisplayed()))

    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }
    }
}
