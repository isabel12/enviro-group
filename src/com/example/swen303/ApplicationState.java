package com.example.swen303;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.swen303.domainObjects.Acheivement;
import com.example.swen303.domainObjects.Activity;
import com.example.swen303.domainObjects.Colour;
import com.example.swen303.domainObjects.IQuantityActivity;
import com.example.swen303.domainObjects.ISimpleActivity;
import com.example.swen303.domainObjects.QuantityTask;
import com.example.swen303.domainObjects.SingleTask;
import com.example.swen303.domainObjects.ITask;
import com.example.swen303.domainObjects.User;

public class ApplicationState {

	public static String username;
	public static Map<String, User> users = new HashMap<String, User>(); // from name to User	
	public static List<Activity> recentActivities = new ArrayList<Activity>();
	public static Map<String, ITask> availableTasks = new HashMap<String, ITask>(); // from task name to Task
	
	
    /**
     * Sets up profiles in ApplicationState
     */
    public static void setupProfiles(){
    	
    	// Set logged in
    	ApplicationState.username = "Isabel";
    	
    	// set up available tasks
    	ApplicationState.availableTasks.put("5 Min Shower", new SingleTask("5 Min Shower", R.drawable.settings, " had a five minute shower today.", 3));
    	ApplicationState.availableTasks.put("Recycling", new QuantityTask("Recycling", R.drawable.settings, " recycled {0} items.", "Number of items recycled", 1)); 
		ApplicationState.availableTasks.put("Catching Bus", new SingleTask("Catching Bus", R.drawable.settings, " caught the bus instead of driving.", 1));	
		ApplicationState.availableTasks.put("Walking", new SingleTask("Walking", R.drawable.settings, " walked instead of catching the bus.", 3));	
		ApplicationState.availableTasks.put("Taking the Stairs", new SingleTask("Taking the Stairs", R.drawable.settings, " took the stairs instead of using the lift.", 1));
    	
		
    	// user profiles
    	User me = new User("Isabel", Colour.Purple);
    	ApplicationState.users.put(me.getName(), me);
    	
    	User tim = new User("Tim", Colour.Orange);
    	ApplicationState.users.put(tim.getName(), tim);
    	
    	User kate = new User("Kate", Colour.Blue);
    	ApplicationState.users.put(kate.getName(), kate);
    	
    	
    	// get some cool dates
    	long day = 86400000;
    	long hour = 3600000;
    	long minute = 60000;
    	
    	long currentDate = new Date().getTime() + 12*hour;
    	
    	Date fiveDaysAgo = new Date(currentDate - 5*day + 2*hour);
    	Date fiveDaysAgo2 = new Date(currentDate - 5*day + 2*hour + 30*minute);
    	Date fiveDaysAgo3 = new Date(currentDate - 5*day + 6*hour - 3*minute);
    	
    	Date fourDaysAgo = new Date(currentDate - 4*day + 2*hour + 6*minute);
    	Date fourDaysAgo2 = new Date(currentDate - 4*day + 4*hour + 36*minute);
    	Date fourDaysAgo3 = new Date(currentDate - 4*day + 6*hour - 3*minute);
    	
    	Date threeDaysAgo = new Date(currentDate - 3*day + 1*hour + 20*minute);
    	Date threeDaysAgo2 = new Date(currentDate - 3*day + 2*hour + 30*minute);
    	Date threeDaysAgo3 = new Date(currentDate - 3*day + 6*hour - 45*minute);
    	
    	Date twoDaysAgo = new Date(currentDate - 2*day + 4*hour + 8*minute);
    	Date twoDaysAgo2 = new Date(currentDate - 3*day + 5*hour + 30*minute);
    	Date twoDaysAgo3 = new Date(currentDate - 3*day + 6*hour - 3*minute);
    	
    	Date oneDayAgo = new Date(currentDate - 1*day - 4*hour + 32*minute);
    	Date oneDayAgo2 = new Date(currentDate - 1*day + 6*hour + 35*minute);
    	Date oneDayAgo3 = new Date(currentDate - 1*day + 7*hour - 12*minute);
    	
    	
    	// 5 days ago
    	//-----------
    	Activity task = ((IQuantityActivity)ApplicationState.availableTasks.get("Recycling")).GetInstance("Isabel", fiveDaysAgo, 3);
    	me.addTask(task.getDate(), (ITask)task);   	
    	
    	task = ((ISimpleActivity)ApplicationState.availableTasks.get("5 Min Shower")).GetInstance("Kate", fiveDaysAgo2);
    	kate.addTask(task.getDate(), (ITask)task);
    	
    	task = ((ISimpleActivity)ApplicationState.availableTasks.get("Catching Bus")).GetInstance("Tim", fiveDaysAgo3);
    	tim.addTask(task.getDate(), (ITask)task);
    	
    	
    	// 4 days ago
    	//-----------
    	
    	task = ((ISimpleActivity)ApplicationState.availableTasks.get("Walking")).GetInstance("Isabel", fourDaysAgo);
    	me.addTask(task.getDate(), (ITask)task);
    	
    	task = ((ISimpleActivity)ApplicationState.availableTasks.get("Taking the Stairs")).GetInstance("Tim", fourDaysAgo2);
    	tim.addTask(task.getDate(), (ITask)task);
    	
    	task = ((IQuantityActivity)ApplicationState.availableTasks.get("Recycling")).GetInstance("Tim", fourDaysAgo3, 6);
    	tim.addTask(task.getDate(), (ITask)task);
    	
    	
    	// 3 days ago
    	//-----------
    	
    	task = ((ISimpleActivity)ApplicationState.availableTasks.get("Walking")).GetInstance("Isabel", threeDaysAgo);
    	me.addTask(task.getDate(), (ITask)task);
    	
    	task = ((ISimpleActivity)ApplicationState.availableTasks.get("Walking")).GetInstance("Kate", threeDaysAgo2);
    	kate.addTask(task.getDate(), (ITask)task);
    	
    	
    	
    	// 2 days ago
    	//-----------
    	task = ((ISimpleActivity)ApplicationState.availableTasks.get("Walking")).GetInstance("Isabel", twoDaysAgo);
    	me.addTask(task.getDate(), (ITask)task);
    	
    	task = ((ISimpleActivity)ApplicationState.availableTasks.get("5 Min Shower")).GetInstance("Kate", twoDaysAgo2);
    	kate.addTask(task.getDate(), (ITask)task);
    	
    	task = ((ISimpleActivity)ApplicationState.availableTasks.get("Walking")).GetInstance("Kate", twoDaysAgo3);
    	kate.addTask(task.getDate(), (ITask)task);
    	
    	// 1 days ago
    	//-----------
   	
    	task = ((ISimpleActivity)ApplicationState.availableTasks.get("Walking")).GetInstance("Isabel", oneDayAgo);
    	me.addTask(task.getDate(), (ITask)task);
    	
    	task = ((IQuantityActivity)ApplicationState.availableTasks.get("Recycling")).GetInstance("Tim", oneDayAgo, 4);
    	tim.addTask(task.getDate(), (ITask)task);

    	    	
    }
	
	
	
	
}
