package com.example.swen303.domainObjects;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public void addTask(Date date, ITask task) {
		int points = ((Activity)task).GetPoints();
		
		this.tasksAchieved.add(task);
		this.totalPoints += points;
		this.pointsThisWeek += points;	
		ApplicationState.recentActivities.add((Activity)task);
	}

	public List<Acheivement> getAcheivementsAchieved() {
		return acheivementsAchieved;
	}

	public void addAcheivement(Date date, Acheivement acheivement) {
		this.acheivementsAchieved.add(acheivement);
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
