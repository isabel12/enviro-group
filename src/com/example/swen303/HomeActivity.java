/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.swen303;

import java.util.ArrayList;
import java.util.Date;

import com.example.swen303.domainObjects.Acheivement;
import com.example.swen303.domainObjects.Activity;
import com.example.swen303.domainObjects.Colour;
import com.example.swen303.domainObjects.QuantityActivity;
import com.example.swen303.domainObjects.QuantityTask;
import com.example.swen303.domainObjects.SimpleActivity;
import com.example.swen303.domainObjects.SingleTask;
import com.example.swen303.domainObjects.Task;
import com.example.swen303.domainObjects.User;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeActivity extends FragmentActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * three primary sections of the app. We use a {@link android.support.v4.app.FragmentPagerAdapter}
     * derivative, which will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will display the three primary sections of the app, one at a
     * time.
     */
    ViewPager mViewPager;
    
    ActionBar actionBar;
    
    

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

        // Set up the action bar.
        actionBar = getActionBar();

        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        // Specify that we will be displaying tabs in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set up the ViewPager, attaching the adapter and setting up a listener for when the
        // user swipes between sections.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // set up PagerAdapter with all the fragments to return
        Fragment group = new GroupFragment();
        Fragment notifications = new MessagesFragment();
        Fragment statistics = new StatisticsFragment();        
        mAppSectionsPagerAdapter.addFragment(group);
        mAppSectionsPagerAdapter.addFragment(notifications);
        mAppSectionsPagerAdapter.addFragment(statistics);
                
        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mAppSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }

    }

    
    public void redisplayTabs(){
  	
    	actionBar.removeAllTabs();
    	
        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mAppSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    		
    }
    
    
    public void SetupProfiles(){
    	
    	// set up available tasks
    	ApplicationState.availableTasks.put("5 Min Shower", new SingleTask("5 Min Shower", R.drawable.settings, " had a five minute shower today.", 3));
    	ApplicationState.availableTasks.put("Recycling", new QuantityTask("Recycling", R.drawable.settings, " recycled {0} items.", "Number of items recycled", 1)); 
		ApplicationState.availableTasks.put("Catching Bus", new SingleTask("Catching Bus", R.drawable.settings, " caught the bus instead of driving.", 1));	
		ApplicationState.availableTasks.put("Walking", new SingleTask("Walking", R.drawable.settings, " walked instead of catching the bus.", 3));	
		ApplicationState.availableTasks.put("Taking the Stairs", new SingleTask("Taking the Stairs", R.drawable.settings, " took the stairs instead of using the lift.", 1));
    	
		
    	// my profile	
    	User me = new User("Isabel", Colour.Purple);
    	me.setPointsThisWeek(12);
    	me.addToTotalPoints(34);
    	
    	// add a task
    	Activity task = ((QuantityActivity)ApplicationState.availableTasks.get("Recycling")).GetInstance("Isabel", new Date(2013, 5, 25, 18, 32), 3);
    	me.addTask(task.getDate(), (Task)task);
    	ApplicationState.recentActivities.add(task);
    	
    	// add an achievement
    	Activity acheivement = new Acheivement("Recycling", R.drawable.settings, " recycled their first items.", 1, "Isabel", new Date(2013, 5, 25, 18, 32));
    	me.addAcheivement(acheivement.getDate(), (Acheivement)acheivement);
    	ApplicationState.recentActivities.add(acheivement);    	    	
    	
    	
    	User tim = new User("Tim", Colour.Orange);
    	tim.setPointsThisWeek(9);
    	tim.addToTotalPoints(36);
    	
    	User kate = new User("Kate", Colour.Blue);
    	kate.setPointsThisWeek(14);
    	kate.addToTotalPoints(27);
    	
    	
    	
    	
    	
    	
    	
    	
    }
    
    
    
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

    	private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();
    	
        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
      
        public void addFragment(Fragment fragment) {
            mFragments.add(fragment);
            notifyDataSetChanged();
        }
        
        @Override
        public Fragment getItem(int i) {
        	return mFragments.get(i);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position){
            case 0:
            	return "Group";
            case 1: 
            	int numMessages = ((MessagesFragment)mFragments.get(1)).GetNumberOfUnreadMessages();
            	String toAdd = numMessages == 0 ? "" : " - " + numMessages;                 	
            	return "Messages" + toAdd;		
            case 2: 
            	return "Stats";
            default:
            	return "Unknown";            
            }
            
        }
    }
   
    
    
}
