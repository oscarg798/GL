package com.reyesmagos.gl.dashboard

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.reyesmagos.gl.core.extensions.parseToDateFormat
import com.reyesmagos.gl.core.extensions.toPostDateFormat
import org.hamcrest.CoreMatchers.allOf
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DashboardActivityTest {

    @get:Rule
    var activityTestRule: ActivityTestRule<DashboardActivity> =
        ActivityTestRule<DashboardActivity>(DashboardActivity::class.java)


    private val calendar = Calendar.getInstance().apply {
        set(2019,8,12)
    }

    @Test
    fun shouldHaveAViewToShowTheHeader() {
        onView(withId(R.id.clHeader)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldHaveAViewToDisplayTheDate() {
        onView(withId(R.id.tvDate)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldGetTheDateWithFormatDayNameCommaMonthDayAsNumber() {

        Assert.assertEquals("Thursday, September 12", calendar.parseToDateFormat())

    }

    @Test
    fun shouldDisplayTodayDateFormattedAsDayNameCommaMonthDayAsNumber() {
        onView(withId(R.id.tvDate)).check(matches(withText(Calendar.getInstance().parseToDateFormat())))
    }

    @Test
    fun shouldHaveAViewToDisplayHelloJaneAndShouldHaveThatText() {
        onView(withId(R.id.tvTitle)).check(matches(allOf(isDisplayed(), withText("Hello Jane"))))
    }

    @Test
    fun shouldHaveAViewToDisplayWhatsOnYourMindAndWhiteBackground() {
        onView(withId(R.id.tvPost)).check(
            matches(
                allOf(
                    isDisplayed(),
                    withText(R.string.tv_post_text)
                )
            )
        )
        val view = activityTestRule.activity.findViewById<View>(R.id.tvPost)
        Assert.assertEquals(
            (view.background as ColorDrawable).color,
            activityTestRule.activity.getColor(R.color.white)
        )
    }

    @Test
    fun shouldHaveARecyclerViewToDisplayPost() {
        onView(withId(R.id.rvPost)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldShowDateAsdMMYYYY() {
        Assert.assertEquals("12/09/2019", calendar.toPostDateFormat())
    }

    @Test
    fun shouldShowUnixTimeStampAsdMMYYYY() {
        Assert.assertEquals("10/05/2018", 1525940534.toLong().toPostDateFormat())
    }

    @Test
    fun shouldDisplayFirstPost() {
        hasViewWithTextAtPosition(0, "Averill Erricke1r")
        hasViewWithTextAtPosition(1, "10/05/2018")
        hasViewWithTextAtPosition(
            2,
            "Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl."
        )
    }


    private fun hasViewWithTextAtPosition(
        index: Int,
        text: CharSequence
    ): ViewAssertion {
        return ViewAssertion { view, e ->
            if (view !is androidx.recyclerview.widget.RecyclerView) {
                throw e
            }
            val rv = view
            val outviews = ArrayList<View>()
            rv.findViewHolderForAdapterPosition(index)
                ?.itemView?.findViewsWithText(
                outviews, text,
                View.FIND_VIEWS_WITH_TEXT
            )
            Assert.assertTrue(outviews.isNotEmpty())
        }
    }

}
