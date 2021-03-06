package com.example.swen303.domainObjects;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.swen303.AchievementHandler;
import com.example.swen303.ApplicationState;
import com.example.swen303.R;

public class User {
	private static int idCount = 0;
	
	
	private int id;
	private String name;
	private Colour colour;
	private int pointsThisWeek;
	private int totalPoints;

	private List<ITask> tasksAchieved;
	private List<Acheivement> acheivementsAchieved;
	
	public User(String name, Colour colour){
		this.name = name;
		this.colour = colour;
		this.tasksAchieved = new ArrayList<ITask>();
		this.acheivementsAchieved = new ArrayList<Acheivement>();
		id = idCount++;
	}

	public int getId(){
		return id;
	}
	
	public int getPointsThisWeek() {
		return pointsThisWeek;
	}

	public void setPointsThisWeek(int pointsThisWeek) {
		this.pointsThisWeek = pointsThisWeek;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void addToTotalPoints(int toAdd) {
		this.totalPoints += toAdd;
	}

	public List<ITask> getTasksAchieved() {
		return tasksAchieved;
	}

	/**
	 * Adds the task to the list of tasks achieved, and adds the points
	 * @param date
	 * @param task
	 */
	public Acheivement addTask(Date date, ITask task) {
		int points = ((Activity)task).GetPoints();
		
		this.tasksAchieved.add(task);
		this.totalPoints += points;
		this.pointsThisWeek += points;	
		ApplicationState.recentActivities.add((Activity)task);
		
		Acheivement achievement = AchievementHandler.checkForAchievements(this, date);
		if (achievement != null){
			addAcheivement(date, achievement);
			return achievement;
		}
		
		return null;
	}

	public List<Acheivement> getAcheivementsAchieved() {
		return acheivementsAchieved;
	}

	public void addAcheivement(Date date, Acheivement acheivement) {
		int points = ((Activity)acheivement).GetPoints();
		
		this.acheivementsAchieved.add(acheivement);
		this.totalPoints += points;
		this.pointsThisWeek += points;	
		ApplicationState.recentActivities.add((Activity)acheivement);
	}
	
	public void undoLastTask(boolean andLastAchievement){
		// remove achievement
		if(andLastAchievement){
			Activity achievement = acheivementsAchieved.remove(acheivementsAchieved.size() -1);
			ApplicationState.recentActivities.remove(ApplicationState.recentActivities.size() - 1);	
		}	
		
		// get the task
		Activity task = (Activity) tasksAchieved.remove(tasksAchieved.size()-1);
		
		// undo it
		int points = task.GetPoints();
		this.totalPoints -= points;
		this.pointsThisWeek -= points;
		ApplicationState.recentActivities.remove(ApplicationState.recentActivities.size() - 1);
	}

	public String getName() {
		return name;
	}

	public Colour getColour() {
		return colour;
	}
	
	public int getProfilePictureId(){
		switch(colour){
			case Blue:
				return R.drawable.blue_profile;
			case Purple:
				return R.drawable.purple_profile;
			case Orange:
				return R.drawable.orange_profile;
			default:
				return R.drawable.purple_profile;
		}
	}
}
