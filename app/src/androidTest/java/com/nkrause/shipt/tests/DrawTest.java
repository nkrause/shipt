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
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4ClassRunner.class)
public class DrawTest {

    private Context context;

    @Rule
    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init(){
        context = ApplicationProvider.getApplicationContext();
    }

    /**
     * Checks when game is a draw
     */
    @Test
    public void drawTest(){

        //X's Turn - click upper left (space 0)
        performClick(0);
        checkTurnDisplay(R.string.o_turn);

        //O's Turn - click upper middle (space 4)
        performClick(4);
        checkTurnDisplay(R.string.x_turn);

        //X's Turn - click upper right (space 8)
        performClick(8);
        checkTurnDisplay(R.string.o_turn);

        //O's Turn - click middle left (space 2)
        performClick(2);
        checkTurnDisplay(R.string.x_turn);

        //X's Turn - click middle (space 6)
        performClick(6);
        checkTurnDisplay(R.string.o_turn);

        //O's Turn - click middle right (space 7)
        performClick(7);
        checkTurnDisplay(R.string.x_turn);

        //X's Turn - click bottom middle (space 1)
        performClick(1);
        checkTurnDisplay(R.string.o_turn);

        //O's Turn - click bottom left (space 3)
        performClick(3);
        checkTurnDisplay(R.string.x_turn);

        //X's Turn - click bottom right (space 5)
        performClick(5);

        //check winning text
        checkTurnDisplay(R.string.draw);
    }

    /**
     * Check turn display
     */
    private void checkTurnDisplay(int text){
        onView(ViewMatchers.withId(R.id.turn_display))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(text)));
    }

    /**
     * Perform a click at a certain space
     */
    private void performClick(int space){
        int resId = getResourceId(space);
        onView(ViewMatchers.withId(resId))
                .perform(click());
    }

    /**
     * Check resource id of certain space
     */
    private int getResourceId(int id){
        return this.context.getApplicationContext().getResources().getIdentifier("space_" + id,"id",this.context.getPackageName());
    }
}
