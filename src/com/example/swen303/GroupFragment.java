package com.example.swen303;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.example.swen303.domainObjects.Activity;
import com.example.swen303.domainObjects.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class GroupFragment extends Fragment {
	
	private ViewGroup view;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
        if (container == null) {
            // We have different layouts, and in one of them this
            // fragment's containing frame doesn't exist.  The fragment
            // may still be created from its saved state, but there is
            // no reason to try to create its view hierarchy because it
            // won't be displayed.  Note this is not needed -- we could
            // just run the code below, where we would create and return
            // the view hierarchy; it would just never be used.
            return null;
        }	
        
        view = (ViewGroup) inflater.inflate(R.layout.fragment_group, null);
                
		return view;
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		insertPointsButtons();
		insertRecentActivities();
		
	}
	
	
	
	
	private void insertPointsButtons(){
		LinearLayout layout = (LinearLayout)view.findViewById(R.id.current_points_button_list);
			
		layout.removeAllViews();
		
		for (User user: ApplicationState.users.values()){
			Button b = new Button(getActivity());
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
        
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), aList, R.layout.recent_activity_row, from, to );
			
        ListView listView = (ListView) view.findViewById(R.id.recent_activity_list);
        
        
        listView.setAdapter(adapter);
    
		
	}

}
