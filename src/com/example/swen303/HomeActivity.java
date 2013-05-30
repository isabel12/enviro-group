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
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.example.swen303.domainObjects.Acheivement;
import com.example.swen303.domainObjects.Activity;
import com.example.swen303.domainObjects.Colour;
import com.example.swen303.domainObjects.IQuantityActivity;
import com.example.swen303.domainObjects.QuantityTask;
import com.example.swen303.domainObjects.ISimpleActivity;
import com.example.swen303.domainObjects.SingleTask;
import com.example.swen303.domainObjects.Task;
import com.example.swen303.domainObjects.User;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class HomeActivity extends android.app.Activity {
    
    ActionBar actionBar;
       
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   
        
        // initialise state
        ApplicationState.setupProfiles();
        
        // Set up the action bar.
        actionBar = getActionBar();

        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("My Group");
        
        setContentView(R.layout.fragment_group);
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
		LinearLayout layout = (LinearLayout)findViewById(R.id.current_points_button_list);
			
		// remove all the buttons
		layout.removeAllViews();
			
		// make new buttons
		for (User user: ApplicationState.users.values()){
			Button b = new Button(this);
			b.setText(user.getName() + " : " + user.getPointsThisWeek());
			b.setId(user.getId());
			layout.addView(b);
		}
				
	}
	
	/**
	 * Refreshes the recent activities list
	 */
	private void insertRecentActivities(){

		// get the data
		List<Activity> recentActivities = new ArrayList<Activity>(ApplicationState.recentActivities);
		Collections.reverse(recentActivities);
		
		// extract all the data into arrays
		int[] icons = new int[recentActivities.size()];
		String[] dates = new String[recentActivities.size()];
		String[] descriptions = new String[recentActivities.size()];
		String[] points = new String[recentActivities.size()];
		for(int i = 0; i < recentActivities.size(); i++){
			Activity a = recentActivities.get(i);	
			icons[i] = a.GetIconId();
			Date date = a.getDate();
			dates[i] = String.format("%d/%d/%d", date.getDay(), date.getMonth(), date.getYear());
			descriptions[i] = a.GetMessage();
			points[i] = a.GetPoints() + " points";	
		}
		
		// build the list, and the mapping arrays
		List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();			
		for(int i = 0; i < 2; i++){
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("icon", icons[i]+"");
			hm.put("date", dates[i]);
			hm.put("descr", descriptions[i]);
			hm.put("points", points[i]);
			aList.add(hm);
		}		
        String[] from = {"icon", "date", "descr", "points"};
        int[] to = {R.id.recent_activity_icon, R.id.recent_activity_date, R.id.recent_activity_description, R.id.recent_activity_points};
        
        // make a new adapter
        SimpleAdapter adapter = new SimpleAdapter(this, aList, R.layout.recent_activity_row, from, to );
			
        // set adapter for the list view
        ListView listView = (ListView)findViewById(R.id.recent_activity_list);
        listView.setAdapter(adapter);
        
        
        listView = (ListView)findViewById(R.id.recent_activity_list);
        
	}
    
    
    
    
   
}
