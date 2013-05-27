package com.example.swen303.domainObjects;

import java.util.Date;

import com.example.swen303.ApplicationState;


/**
 * This class represents an activity that is worth points - either a task or an acheivement.
 * @author Izzi
 *
 */
public abstract class Activity {

	// related to the activity itself
	protected String activity_name;	
	protected int icon_id;
	protected String message_base;
		
	// related to the instance
	protected String username;	
	protected Date date;
	
	protected Activity(String activity_name, int icon_id, String message_base){
		this.activity_name = activity_name;
		this.icon_id = icon_id;
		this.message_base = message_base;
	}
	
	
	public String GetName(){
		return activity_name;
	}
	
	public int GetIconId(){
		return icon_id;
	}
	
	public String GetMessage(){	
		if(username == null || username.equals(""))
			return null;
		else
			return username + message_base;	
	}
	
	public Date getDate() {
		return date;
	}


	public abstract int GetPoints();
	
}
