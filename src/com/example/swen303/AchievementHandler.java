package com.example.swen303;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.swen303.domainObjects.Acheivement;
import com.example.swen303.domainObjects.ITask;
import com.example.swen303.domainObjects.User;

public class AchievementHandler {

	
	private static Map<String, Acheivement> possibleAchievements;
	
	
	
	
	public static void initialise(){
		
		possibleAchievements = new HashMap<String, Acheivement>();
		
		// first item recycled
		possibleAchievements.put("Recycled First Items", new Acheivement("Recycled First Items", R.drawable.ribbon_icon, " recycled their first items!", 0, null, null));
		
		// walked all week
		possibleAchievements.put("Walked All Week", new Acheivement("Walked All Week", R.drawable.ribbon_icon, " walked every day this week!", 0, null, null));
			
	}
	
	
	
	public static Acheivement checkForAchievements(User user, Date date){
				
		Acheivement acheivement = checkForRecycledFirstItemsAcheivement(user, date);
		
		if (acheivement == null){
			return checkForWalkedAllWeek(user, date);
		}
		
		return acheivement;	
	}
	
	
	private static Acheivement checkForRecycledFirstItemsAcheivement(User user, Date date){
		
		Acheivement acheivement;
		try{
			acheivement = possibleAchievements.get("Recycled First Items");
		} catch(NullPointerException e){
			return null;
		}
		
		boolean alreadyAchieved = user.getAcheivementsAchieved().contains(acheivement);
		boolean applicable = user.getTasksAchieved().contains(ApplicationState.availableTasks.get("Recycling"));
		
		if (!alreadyAchieved && applicable){
			return (Acheivement) acheivement.GetInstance(user.getName(), date);	
		}
		
		return null;
	}
	
	
	private static Acheivement checkForWalkedAllWeek(User user, Date date){
		
		Acheivement acheivement;
		try{
			acheivement = possibleAchievements.get("Walked All Week");
		} catch(NullPointerException e){
			return null;
		}
		
		boolean alreadyAchieved = user.getAcheivementsAchieved().contains(acheivement);
		
		
		int count = 0;
		for(ITask task : user.getTasksAchieved()){
			if(task.GetName().equals("Walking")){
				count++;	
			}		
		}	
		boolean applicable = count == 5;
		
		if (!alreadyAchieved && applicable){
			return (Acheivement) acheivement.GetInstance(user.getName(), date);	
		}
		
		return null;		
	}
	
	
}
