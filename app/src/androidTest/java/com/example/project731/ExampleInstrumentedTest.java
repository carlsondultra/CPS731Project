package com.example.project731;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule mActivityRule = new ActivityScenarioRule<>(Splash.class);

    private String maleUsername = "instrumentedmale@m.com";
    private String malePassword = "123456";

    private String loginUsername = "male@m.com";
    private String loginPassword = "123456";

    //Test checking if the Splash Screen goes to the main screen
    @Test
    public void activityLaunch(){
        SystemClock.sleep(5200);
        onView(withId(R.id.register)).perform(click());
    }

    //Test checking if store locator works
    @Test
    public void storeActivityLaunch(){
        SystemClock.sleep(6000);
        onView(withId(R.id.menu)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.stores)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.back)).perform(click());
    }

    //Test checking if the menu works
    @Test
    public void menuActivityLaunch(){
        SystemClock.sleep(6000);
        onView(withId(R.id.menu)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.back)).perform(click());
    }

    //Test checking if the about us section works
    @Test
    public void aboutActivityLaunch(){
        SystemClock.sleep(6000);
        onView(withId(R.id.menu)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.btnAbout)).perform(click());
    }

    //Test checking if registration works
    //maleUsername needs to be changed every time this test is run because there cannot be two of the same usernames.
    @Test
    public void registerActivityLaunch(){
        SystemClock.sleep(6000);
        onView(withId(R.id.register)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.email_login)).perform(typeText(maleUsername)); //inputs text into text box
        closeSoftKeyboard(); // close the keyboard
        onView(withId(R.id.name)).perform(typeText("name"));
        closeSoftKeyboard();
        onView(withId(R.id.password_first)).perform(typeText(malePassword));
        closeSoftKeyboard();
        onView(withId(R.id.password_confirm_box)).perform(typeText(malePassword));
        closeSoftKeyboard();
        onView(withId(R.id.male)).perform(click());
        onView(withId(R.id.register)).perform(click());
        SystemClock.sleep(2000);
    }

    //Test checking if login works
    @Test
    public void loginActivityLaunch(){
        SystemClock.sleep(6000);
        onView(withId(R.id.login)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.email_login)).perform(typeText(loginUsername));
        closeSoftKeyboard();
        onView(withId(R.id.password_confirm_box)).perform(typeText(loginPassword));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        SystemClock.sleep(2000);
    }
}