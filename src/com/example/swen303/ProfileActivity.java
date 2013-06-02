package com.example.swen303;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.swen303.domainObjects.Acheivement;
import com.example.swen303.domainObjects.Activity;
import com.example.swen303.domainObjects.User;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class ProfileActivity extends android.app.Activity {

	public static String USERNAME = "username";
	
	private User user;
	private RecentActivityAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		// Show the Up button in the action bar.
		setupActionBar();
		
		// get the user
		String username = getIntent().getStringExtra(USERNAME);
		user = ApplicationState.users.get(username);
		
		// set adapter on list view
		adapter = new RecentActivityAdapter(this, new ArrayList<Activity>());  
        ListView listView = (ListView)findViewById(R.id.profile_achievements_list);
        listView.setAdapter(adapter);
		
        // refresh the page
        refreshPage();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("Profiles");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.statistics, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				// This ID represents the Home or Up button. In the case of this
				// activity, the Up button is shown. Use NavUtils to allow users
				// to navigate up one level in the application structure. For
				// more details, see the Navigation pattern on Android Design:
				//
				// http://developer.android.com/design/patterns/navigation.html#up-vs-back
				//
				NavUtils.navigateUpFromSameTask(this);
				return true;
				
			case R.id.statistics_option:
				Intent intent = new Intent(this, StatisticsActivity.class);
				startActivity(intent);
				return true;	
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	private void refreshPage(){
		// get views to update
		ImageView profileImage = (ImageView)findViewById(R.id.profile_image_imageView);
		TextView username = (TextView)findViewById(R.id.profile_name_textView);
		TextView totalPoints = (TextView)findViewById(R.id.profile_total_points_textView);
		TextView pointsThisWeek = (TextView)findViewById(R.id.profile_points_this_week_textView);
		
		// update views
		profileImage.setImageResource(user.getProfilePictureId());
		username.setText(user.getName());
		totalPoints.setText("Total points: " + user.getTotalPoints());
		pointsThisWeek.setText("Points this week: " + user.getPointsThisWeek());	
		
		// update achievements adapter
		List<Acheivement> achievements = user.getAcheivementsAchieved();
		adapter.clear();
		adapter.addAll(achievements);
		
		
		
		
	}

}
