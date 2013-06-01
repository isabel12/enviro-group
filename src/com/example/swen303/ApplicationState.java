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
    	
		
    	// my profile	
    	User me = new User("Isabel", Colour.Purple);
    	me.setPointsThisWeek(12);
    	me.addToTotalPoints(34);
    	ApplicationState.users.put(me.getName(), me);
    	
    	// add a task
    	Activity task = ((IQuantityActivity)ApplicationState.availableTasks.get("Recycling")).GetInstance("Isabel", new Date(2013, 5, 25, 18, 32), 3);
    	me.addTask(task.getDate(), (ITask)task);
    	ApplicationState.recentActivities.add(task);
    	
    	// add an achievement
    	Activity acheivement = new Acheivement("Recycling", R.drawable.settings, " recycled their first items.", 1, "Isabel", new Date(2013, 5, 25, 18, 32));
    	me.addAcheivement(acheivement.getDate(), (Acheivement)acheivement);
    	ApplicationState.recentActivities.add(acheivement);    	    	
    	
    	
    	User tim = new User("Tim", Colour.Orange);
    	tim.setPointsThisWeek(9);
    	tim.addToTotalPoints(36);
    	ApplicationState.users.put(tim.getName(), tim);
    	
    	User kate = new User("Kate", Colour.Blue);
    	kate.setPointsThisWeek(14);
    	kate.addToTotalPoints(27);
    	ApplicationState.users.put(kate.getName(), kate);
    	    	
    }
	
	
	
	
}
