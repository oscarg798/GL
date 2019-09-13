package com.reyesmagos.gl

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import junit.framework.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class SplashActivityTest {

    @get:Rule
    var activityTestRule: ActivityTestRule<SplashScreenActivity> =
        ActivityTestRule<SplashScreenActivity>(SplashScreenActivity::class.java)

    @Test
    fun shouldHaveAViewWithTheIcon() {
        onView(withId(R.id.ivIcon)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldHaveBackgroundColor() {
        val view = activityTestRule.activity.findViewById<View>(R.id.clMain)
        Assert.assertEquals(
            (view.background as ColorDrawable).color,
            activityTestRule.activity.getColor(R.color.colorPrimary)
        )

    }
}
