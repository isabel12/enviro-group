package com.example.swen303.domainObjects;

import java.util.Date;

public class SingleTask extends Activity implements SimpleActivity, Task {

	private final int points;
	
	public SingleTask(String taskName, int iconId, String messageBase, int points){
		super(taskName, iconId, messageBase);
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
			
		SingleTask instance = new SingleTask(activity_name, icon_id, message_base, points);	
		instance.username = username;
		instance.date = date;
		
		return instance;
	}

}
