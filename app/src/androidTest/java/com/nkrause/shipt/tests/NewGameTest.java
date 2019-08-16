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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.nkrause.shipt.utils.EspressoTestsMatchers.noDrawable;
import static com.nkrause.shipt.utils.EspressoTestsMatchers.withDrawable;

@RunWith(AndroidJUnit4ClassRunner.class)
public class NewGameTest {

    private Context context;

    @Rule
    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init(){
        context = ApplicationProvider.getApplicationContext();
    }

    /**
     * Checks UI after starting new game
     */
    @Test
    public void newGame(){

        //x takes a turn at space 0 (upper left)
        takeTurn(0,true);

        //o takes a turn at space 1 (upper mid)
        takeTurn(1,false);

        //click new game button
        onView(ViewMatchers.withId(R.id.button_new_game))
                .check(matches(isCompletelyDisplayed()))
                .perform(click());

        //confirm start new game
        onView(withText(R.string.yes))
                .inRoot(isDialog()) // <---
                .check(matches(isDisplayed()))
                .perform(click());

        //check upper left (space 0) has no drawable
        onView(ViewMatchers.withId(R.id.space_0))
                .check(matches(noDrawable()));
    }

    /**
     * Helper function for taking a turn
     * Checks drawable, performs a click, and checks turn display changed
     */
    private void takeTurn(int space, boolean xTurn){
        int resId = getResourceId(space);
        int drawable = xTurn ? R.drawable.ic_x : R.drawable.ic_o;
        int turn = xTurn ? R.string.o_turn : R.string.x_turn;

        //check space has no drawable at first
        onView(ViewMatchers.withId(resId))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(noDrawable()));

        //click space
        onView(ViewMatchers.withId(resId))
                .perform(click());

        //check space has a drawable now
        onView(ViewMatchers.withId(resId))
                .check(matches(withDrawable(drawable)));

        //check turn display changed
        onView(ViewMatchers.withId(R.id.turn_display))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(turn)));
    }

    /**
     * Check resource id of certain space
     */
    private int getResourceId(int id){
        return this.context.getApplicationContext().getResources().getIdentifier("space_" + id,"id",this.context.getPackageName());
    }
}
