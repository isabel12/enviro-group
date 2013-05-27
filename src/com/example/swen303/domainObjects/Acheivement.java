package com.example.swen303.domainObjects;

import java.util.Date;

public class Acheivement extends Activity implements SimpleActivity {

	private int points;
	
	public Acheivement(String activity_name, int icon_id, String message_base, int points, String name, Date date) {
		super(activity_name, icon_id, message_base);
		
		super.username = username;
		super.date = date;
		this.points = points;
	}

	@Override
	public int GetPoints() {	
		return points;
	}

	@Override
	public Activity GetInstance(String username, Date date) {
		if(username != null)
			throw new IllegalArgumentException("Cannot get an instance from an instance");
			
		// make new instance
		Acheivement instance = new Acheivement(activity_name, icon_id, message_base, points, username, date);
		
		// return 
		return instance;
	}

}