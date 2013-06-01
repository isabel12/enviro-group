package com.example.swen303;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.swen303.domainObjects.Activity;
import android.content.Context;
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
        return super.getCount() + 1;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Activity getItem(int position) {
        if (position == (getCount() - 1))
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
		TextView description = (TextView)view.findViewById(R.id.recent_activity_description);
		TextView points = (TextView)view.findViewById(R.id.recent_activity_points);
		
		// return an empty view if null
		if(activity == null){
			description.setText("that view doesn't exist...");
			return view;
		}
		
		// set the values
		icon.setImageDrawable(parent.getResources().getDrawable(activity.GetIconId()));
		Date date = activity.getDate();
		dateText.setText(String.format("%d/%d/%d", date.getDay(), date.getMonth(), date.getYear()));
		description.setText(activity.GetMessage());
		points.setText(activity.GetPoints() + " points");
		
		return view;
	}
}
