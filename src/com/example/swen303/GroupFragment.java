package com.example.swen303;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class GroupFragment extends Fragment {
	
	
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
        
		int[] icons = new int[]{R.drawable.settings, R.drawable.ic_launcher};
		String[] dates = new String[]{"12/05/13", "10/05/13"};
		String[] descriptions = new String[]{"You recycled a 3 cans.", "Bill climbed 5 flights of stairs."};
		String[] points = new String[]{"2 points", "3 points"};
		
		
		ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_group, null);
        
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
        
		return view;
	}
	
	
	

}
