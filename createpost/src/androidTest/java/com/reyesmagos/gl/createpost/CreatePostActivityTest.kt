package com.reyesmagos.gl.createpost

import android.widget.EditText
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CreatePostActivityTest {

    @get:Rule
    var activityTestRule: ActivityTestRule<CreatePostActivity> =
        ActivityTestRule<CreatePostActivity>(CreatePostActivity::class.java)

    @Test
    fun shouldHaveAViewForEnterThePostBodyAndWhatSOnYourMindAsHint() {
        Espresso.onView(ViewMatchers.withId(R.id.etPost))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun postBodyViewShouldOnlyAllowOneHundredFiftyCharacters(){
        Espresso.onView(ViewMatchers.withId(R.id.etPost)).perform(typeText("ooke;kalsdkalsdkaksdl;akdkasldklas;kdl;aksdlkasl;dkal;kdl;sakdl;ksal;dkal;dkasl;dkal;lskdl;askdl;akdl;askdl;askdl;KSL;dkasl;dkalsdk;ladsasdasdadasdasd1"))
        val view = activityTestRule.activity.findViewById<EditText>(R.id.etPost)
        Assert.assertEquals("ooke;kalsdkalsdkaksdl;akdkasldklas;kdl;aksdlkasl;dkal;kdl;sakdl;ksal;dkal;dkasl;dkal;lskdl;askdl;akdl;askdl;askdl;KSL;dkasl;dkalsdk;ladsasdasdadasdasd", view.text.toString())

    }
}
