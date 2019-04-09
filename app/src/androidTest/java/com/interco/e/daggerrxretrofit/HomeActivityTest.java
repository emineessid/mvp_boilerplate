package com.interco.e.daggerrxretrofit;


import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.interco.e.daggerrxretrofit.ui.home.HomeActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);


    @Before
    public void setup() {

    }

    @Test
    public void testSearch() {

        ViewInteraction searchEditText = Espresso.onView(withId(R.id.btn_add));

        searchEditText.perform(click());


    }


}
