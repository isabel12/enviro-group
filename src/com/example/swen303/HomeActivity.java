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
        setupProfiles();
        
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
     * Sets up profiles in ApplicationState
     */
    public void setupProfiles(){
    	
    	// Set logged in
    	ApplicationState.username = "Isabel";
    	
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
    	ApplicationState.users.put(me.getName(), me);
    	
    	// add a task
    	Activity task = ((IQuantityActivity)ApplicationState.availableTasks.get("Recycling")).GetInstance("Isabel", new Date(2013, 5, 25, 18, 32), 3);
    	me.addTask(task.getDate(), (Task)task);
    	ApplicationState.recentActivities.add(task);
    	
    	// add an achievement
    	Activity acheivement = new Acheivement("Recycling", R.drawable.settings, " recycled their first items.", 1, "Isabel", new Date(2013, 5, 25, 18, 32));
    	me.addAcheivement(acheivement.getDate(), (Acheivement)acheivement);
    	ApplicationState.recentActivities.add(acheivement);    	    	
    	
    	
    	User tim = new User("Tim", Colour.Orange);
    	tim.setPointsThisWeek(9);
    	tim.addToTotalPoints(36);
    	ApplicationState.users.put(tim.getName(), tim);
    	
    	User kate = new User("Kate", Colour.Blue);
    	kate.setPointsThisWeek(14);
    	kate.addToTotalPoints(27);
    	ApplicationState.users.put(kate.getName(), kate);
    	    	
    }
    
    
	private void insertPointsButtons(){
		LinearLayout layout = (LinearLayout)findViewById(R.id.current_points_button_list);
			
		layout.removeAllViews();
		
		for (User user: ApplicationState.users.values()){
			Button b = new Button(this);
			b.setText(user.getName() + " : " + user.getPointsThisWeek());
			b.setId(user.getId());
			layout.addView(b);
		}
				
	}
	
	private void insertRecentActivities(){

		List<Activity> recentActivities = new ArrayList<Activity>(ApplicationState.recentActivities);
		Collections.reverse(recentActivities);
		
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
        
        SimpleAdapter adapter = new SimpleAdapter(this, aList, R.layout.recent_activity_row, from, to );
			
        ListView listView = (ListView)findViewById(R.id.recent_activity_list);
        
        listView.setAdapter(adapter);
	}
    
    
    
    
   
}
