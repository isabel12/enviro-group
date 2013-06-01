package com.example.swen303;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.swen303.domainObjects.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * An array adapter for recent activities.
 * @author Izzi
 *
 */
public class RecentActivityAdapter extends ArrayAdapter<Activity> {

	public RecentActivityAdapter (Context context, List<Activity> recentActivities){
		super(context, R.layout.recent_activity_row, recentActivities);
	}
	
	public RecentActivityAdapter (Context context, Activity[] recentActivities){
		super(context, R.layout.recent_activity_row, recentActivities);
	}
	
	/**
     * {@inheritDoc}
     */
    @Override
    public int getCount() {
    	// not sure why this has to be + 1?
        return super.getCount();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Activity getItem(int position) {
        if (position == (getCount()))
            return null;
        
        return super.getItem(position);
    }
	
    /**
     * {@inheritDoc}
     */
    @Override
	public View getView(int position, View convertView, ViewGroup parent){
		Activity activity = getItem(position);
		
		
		// inflate the view
		View view = LayoutInflater.from(getContext()).inflate(R.layout.recent_activity_row, parent, false);
						
		// get all the widgets to set
		ImageView icon = (ImageView)view.findViewById(R.id.recent_activity_icon);
		TextView dateText = (TextView)view.findViewById(R.id.recent_activity_date);
		TextView timeText = (TextView)view.findViewById(R.id.recent_activity_time);
		TextView description = (TextView)view.findViewById(R.id.recent_activity_description);
		TextView points = (TextView)view.findViewById(R.id.recent_activity_points);
		
		// return an empty view if null
		if(activity == null){
			description.setText("that view doesn't exist...");
			return view;
		}
		
		// set the values
		icon.setImageDrawable(parent.getResources().getDrawable(activity.GetIconId()));
		
		// date
		//-------
		
		// get today and yesterday
		Date date = activity.getDate();	
		Date today = new Date(new Date().getTime() + 43200000);
		long ticks = today.getTime();
		Date yesterday = new Date(ticks - 86400000);
		
		// make the date string
		String dateString;
		if (date.getDay()==today.getDay() && date.getMonth() == today.getMonth() && date.getYear()==today.getYear()){
			dateString = "Today     ";
		} else if (date.getDay()==yesterday.getDay() && date.getMonth() == yesterday.getMonth() && date.getYear()==yesterday.getYear()){
			dateString = "Yesterday";
		} else {
			
	        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			dateString = format.format(date);	
		}
		
		// set the date string
		dateText.setText(dateString);
		description.setText(activity.GetMessage());
		
		// time
		//------
        SimpleDateFormat format = new SimpleDateFormat("h.mm a");
		String timeString = format.format(date);
		timeText.setText(timeString);
		
		int numPoints = activity.GetPoints();
		String pointsString = numPoints == 1 ? " point  " : " points";
		points.setText(numPoints + pointsString);
		
		return view;
	}
}
