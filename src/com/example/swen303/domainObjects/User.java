package com.example.swen303.domainObjects;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User {

	private String name;
	private Colour colour;
	private int pointsThisWeek;
	private int totalPoints;

	private Map<Date, Task> tasksAchieved;
	private Map<Date, Acheivement> acheivementsAchieved;
	
	public User(String name, Colour colour){
		this.name = name;
		this.colour = colour;
		this.tasksAchieved = new HashMap<Date, Task>();
		this.acheivementsAchieved = new HashMap<Date, Acheivement>();
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
		this.totalPoints += totalPoints;
	}

	public Map<Date, Task> getTasksAchieved() {
		return tasksAchieved;
	}

	public void addTask(Date date, Task task) {
		this.tasksAchieved.put(date, task);
	}

	public Map<Date, Acheivement> getAcheivementsAchieved() {
		return acheivementsAchieved;
	}

	public void addAcheivement(Date date, Acheivement acheivement) {
		this.acheivementsAchieved.put(date, acheivement);
	}

	public String getName() {
		return name;
	}

	public Colour getColour() {
		return colour;
	}
}
