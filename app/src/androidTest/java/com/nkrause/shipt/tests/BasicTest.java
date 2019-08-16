package com.nkrause.shipt.tests;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.nkrause.shipt.MainActivity;
import com.nkrause.shipt.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.nkrause.shipt.utils.EspressoTestsMatchers.noDrawable;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class BasicTest {

    private Context context;

    @Rule
    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init(){
        context = ApplicationProvider.getApplicationContext();
    }

    /**
     * Check app context
     */
    @Test
    public void checkContext() {
        assertEquals("com.nkrause.shipt", context.getPackageName());
    }

    /**
     * Checks UI is setup correctly
     */
    @Test
    public void checkUI(){

        //checks New Game button is displayed with text
        onView(ViewMatchers.withId(R.id.button_new_game))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(R.string.new_game)));

        //checks turn display is initialized with "X's Turn"
        onView(ViewMatchers.withId(R.id.turn_display))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(R.string.x_turn)));

        //checks all spaces are empty
        for(int i=0;i<9;i++){
            int resId = getResourceId(i);
            onView(ViewMatchers.withId(resId))
                    .check(matches(isCompletelyDisplayed()))
                    .check(matches(noDrawable()));
        }
    }

    /**
     * Check resource id of certain space
     */
    private int getResourceId(int id){
        return this.context.getApplicationContext().getResources().getIdentifier("space_" + id,"id",this.context.getPackageName());
    }

}
