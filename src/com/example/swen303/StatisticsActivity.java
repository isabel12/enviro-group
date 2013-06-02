package com.example.swen303;

import java.util.Currency;
import java.util.Locale;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class StatisticsActivity extends FragmentActivity implements
		ActionBar.TabListener {

	boolean initialised;
	int tabIndex;
	int weekMonth3Month = 1;
	
	
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);

		
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("Statistics");

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
						tabIndex = position;
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
				
	}

	@Override
	public void onResume(){
		super.onResume();
		
		if (initialised){
		
			switch(weekMonth3Month){
				case 1:
					changeToWeekView();
					return;
				case 2: 
					changeToMonthView();
					return;
				case 3:
					changeTo3MonthView();
					return;
			}
		}
		
		initialised = true;
		
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	        NavUtils.navigateUpFromSameTask(this);
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	
	public void viewProfile(View view){	
		int id = view.getId();
		
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
		Intent intent = new Intent(this, ProfileActivity.class);
		intent.putExtra(ProfileActivity.USERNAME, username);
		startActivity(intent);
		
	}
	
	
	
	public void changeToWeekView(View view){		
		changeToWeekView();
	}
	
	
	public void changeToWeekView(){	
		
		// get button
		Button totalWeekButton = (Button)findViewById(R.id.total_week_button);
		Button progressWeekButton = (Button)findViewById(R.id.progress_week_button);
		totalWeekButton.setBackground(getResources().getDrawable(R.drawable.selected_graph_button));
		progressWeekButton.setBackground(getResources().getDrawable(R.drawable.selected_graph_button));
		
		Button totalMonthButton = (Button)findViewById(R.id.total_month_button);
		Button progressMonthButton = (Button)findViewById(R.id.progress_month_button);
		totalMonthButton.setBackground(getResources().getDrawable(R.drawable.unselected_graph_button));
		progressMonthButton.setBackground(getResources().getDrawable(R.drawable.unselected_graph_button));
		
		Button totalMonth3Button = (Button)findViewById(R.id.total_month_3_button);
		Button progressMonth3Button = (Button)findViewById(R.id.progress_month_3_button);
		totalMonth3Button.setBackground(getResources().getDrawable(R.drawable.unselected_graph_button));
		progressMonth3Button.setBackground(getResources().getDrawable(R.drawable.unselected_graph_button));
		
		
		
		// get title
		TextView title = (TextView)findViewById(R.id.total_graph_title);
		title.setText("Weekly points");
			
		// get image
		ImageView image = (ImageView)findViewById(R.id.total_graph_image);
		image.setImageDrawable(getResources().getDrawable(R.drawable.week_total));
		
		
		// get title
		title = (TextView)findViewById(R.id.progress_graph_title);
		title.setText("Weekly points");
			
		// get image
		image = (ImageView)findViewById(R.id.progress_graph_image);
		image.setImageDrawable(getResources().getDrawable(R.drawable.week_progress));
		
		weekMonth3Month = 1;
	}
	
	
	
	public void changeToMonthView(View view){
		changeToMonthView();
	}
	
	public void changeToMonthView(){
						
		// get button
		Button totalWeekButton = (Button)findViewById(R.id.total_week_button);
		Button progressWeekButton = (Button)findViewById(R.id.progress_week_button);
		totalWeekButton.setBackground(getResources().getDrawable(R.drawable.unselected_graph_button));
		progressWeekButton.setBackground(getResources().getDrawable(R.drawable.unselected_graph_button));
		
		Button totalMonthButton = (Button)findViewById(R.id.total_month_button);
		Button progressMonthButton = (Button)findViewById(R.id.progress_month_button);
		totalMonthButton.setBackground(getResources().getDrawable(R.drawable.selected_graph_button));
		progressMonthButton.setBackground(getResources().getDrawable(R.drawable.selected_graph_button));
		
		Button totalMonth3Button = (Button)findViewById(R.id.total_month_3_button);
		Button progressMonth3Button = (Button)findViewById(R.id.progress_month_3_button);
		totalMonth3Button.setBackground(getResources().getDrawable(R.drawable.unselected_graph_button));
		progressMonth3Button.setBackground(getResources().getDrawable(R.drawable.unselected_graph_button));
		
		
		// get title
		TextView title = (TextView)findViewById(R.id.progress_graph_title);
		title.setText("Monthly points");
			
		// get title
		title = (TextView)findViewById(R.id.total_graph_title);
		title.setText("Monthly points");
			
		// get image
		ImageView image = (ImageView)findViewById(R.id.total_graph_image);
		image.setImageDrawable(getResources().getDrawable(R.drawable.month_total));
			
		// get image
		image = (ImageView)findViewById(R.id.progress_graph_image);
		image.setImageDrawable(getResources().getDrawable(R.drawable.month_progress));
		
		weekMonth3Month = 2;
		
	}
	
	public void changeTo3MonthView(View view){
		changeTo3MonthView();
	}
	
	public void changeTo3MonthView(){
		// get button
		Button totalWeekButton = (Button)findViewById(R.id.total_week_button);
		Button progressWeekButton = (Button)findViewById(R.id.progress_week_button);
		totalWeekButton.setBackground(getResources().getDrawable(R.drawable.unselected_graph_button));
		progressWeekButton.setBackground(getResources().getDrawable(R.drawable.unselected_graph_button));
		
		Button totalMonthButton = (Button)findViewById(R.id.total_month_button);
		Button progressMonthButton = (Button)findViewById(R.id.progress_month_button);
		totalMonthButton.setBackground(getResources().getDrawable(R.drawable.unselected_graph_button));
		progressMonthButton.setBackground(getResources().getDrawable(R.drawable.unselected_graph_button));
		
		Button totalMonth3Button = (Button)findViewById(R.id.total_month_3_button);
		Button progressMonth3Button = (Button)findViewById(R.id.progress_month_3_button);
		totalMonth3Button.setBackground(getResources().getDrawable(R.drawable.selected_graph_button));
		progressMonth3Button.setBackground(getResources().getDrawable(R.drawable.selected_graph_button));
		
		
		
		// get title
		TextView title = (TextView)findViewById(R.id.progress_graph_title);
		title.setText("Tri-Monthly points");
			
		// get title
		title = (TextView)findViewById(R.id.total_graph_title);
		title.setText("Tri-Monthly points");
			
		// get image
		ImageView image = (ImageView)findViewById(R.id.total_graph_image);
		image.setImageDrawable(getResources().getDrawable(R.drawable.month_3_total));
			
		// get image
		image = (ImageView)findViewById(R.id.progress_graph_image);
		image.setImageDrawable(getResources().getDrawable(R.drawable.month_3_progress));
		
		weekMonth3Month = 3;
	}
	
			
	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new StatisticsFragment();
			Bundle args = new Bundle();	
			int type = position == 0 ? StatisticsFragment.PROGRESS : StatisticsFragment.TOTAL;
			args.putInt(StatisticsFragment.ARG_STATISTICS_TYPE, type);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 2 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return "PROGRESS";
			case 1:
				return "TOTAL";
				
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class StatisticsFragment extends Fragment {
		
		ViewGroup container;
		
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_STATISTICS_TYPE = "statistics_type";
		public static final int TOTAL = 0;
		public static final int PROGRESS = 1;

		public StatisticsFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			this.container = container;
			
			int type = getArguments().getInt(ARG_STATISTICS_TYPE);
			
			switch(type){
				case TOTAL:
					
					View view = inflater.inflate(R.layout.fragment_total_statistics, container, false);											
					return view;
					
				case PROGRESS:
					
					view = inflater.inflate(R.layout.fragment_progress_statistics, container, false);
					return view;
			}
			
			return null;

		}
	}

}
