package com.example.swen303;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.AlertDialog;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class Group extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_group);

		ActionBar actionBar = getActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    actionBar.setDisplayShowTitleEnabled(true);
		
		
	    Tab tab = actionBar.newTab()
	            .setText("Statistics")
	            .setTabListener(new MyTabListener<StatisticsFragment>(
	                    this, "statistics", StatisticsFragment.class));
	    actionBar.addTab(tab);	
	    
	    Tab tab2 = actionBar.newTab()
	            .setText("Statistics2")
	            .setTabListener(new MyTabListener<StatisticsFragment2>(
	                    this, "statistics2", StatisticsFragment2.class));
	    actionBar.addTab(tab);	   
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.group, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		// handle item selection
		switch(item.getItemId()){
		case R.id.group_settings:
			new AlertDialog.Builder(this).setMessage("Settings button selected").show(); 
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}		
	}

}
