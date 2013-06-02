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
import java.util.Collections;
import java.util.List;
import com.example.swen303.domainObjects.Activity;
import com.example.swen303.domainObjects.User;

import android.R.attr;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class HomeActivity extends android.app.Activity {
    
    ActionBar actionBar;
    RecentActivityAdapter adapter;
       
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   
        
        // initialise state
        if(ApplicationState.recentActivities.size() == 0){
        	AchievementHandler.initialise();
        	ApplicationState.setupProfiles();
        	
        }
        
        // Set up the action bar.
        actionBar = getActionBar();

        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("My Group");
        
        // set content view
        setContentView(R.layout.fragment_group);
          
        // set adapter for the list of recent activities
        adapter = new RecentActivityAdapter(this, new ArrayList<Activity>());        
        ListView listView = (ListView)findViewById(R.id.recent_activity_list);
        
//        TextView header = new TextView(this);
//        header.setText("Recent Activity");
//        header.setTextAppearance(this, attr.textAppearanceLarge);
//        listView.addHeaderView(header);
        listView.setAdapter(adapter);
    }

    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.statistics, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		// handle item selection
		switch(item.getItemId()){
		case R.id.statistics_option:
			//new AlertDialog.Builder(this).setMessage("Statistics button selected").show(); 
			Intent intent = new Intent(this, StatisticsActivity.class);
			startActivity(intent);
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}		
	}
     
	@Override
	public void onResume(){
		super.onResume();
		insertPointsButtons();
		insertRecentActivities();	
	}
     
    /**
     * Responds to the button to Record a new task
     * @param view
     */
	public void recordNewTask(View view){		
		Intent intent = new Intent(this, RecordActivity.class);		
		startActivity(intent);	
	}
    
       
	/**
	 * Inserts all the points buttons
	 */
	private void insertPointsButtons(){
		// get the layout
		Button isabelButton = (Button)findViewById(R.id.isabel_button);
		Button kateButton = (Button)findViewById(R.id.kate_button);
		Button timButton = (Button)findViewById(R.id.tim_button);
			
		
		User isabel = ApplicationState.users.get("Isabel");
		isabelButton.setText(isabel.getPointsThisWeek() + "");
		
		User kate = ApplicationState.users.get("Kate");
		kateButton.setText(kate.getPointsThisWeek() + "");
		
		User tim = ApplicationState.users.get("Tim");
		timButton.setText(tim.getPointsThisWeek() + "");	
	}
	
	
	public void goToProfile(View button){
		int id = button.getId();
			
		String username = null;
		switch(id){
			case R.id.isabel_button:
				username = "Isabel";
				break;
			case R.id.kate_button:
				username = "Kate";
				break;
			case R.id.tim_button:
				username = "Tim";
				break;
			default:
				return;
		}
		
		// start profile activity
		Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
		intent.putExtra(ProfileActivity.USERNAME, username);
		startActivity(intent);		
	}
	
	
	
	
	
	/**
	 * Refreshes the recent activities list
	 */
	private void insertRecentActivities(){

		// get the updated data
		List<Activity> recentActivities = new ArrayList<Activity>(ApplicationState.recentActivities);
		Collections.reverse(recentActivities);
		
		// set new data
		adapter.clear();
		adapter.addAll(recentActivities);
		adapter.notifyDataSetChanged();

	}
    
    
    
    
   
}
