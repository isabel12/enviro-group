package com.example.swen303.domainObjects;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
	private static int idCount = 0;
	
	
	private int id;
	private String name;
	private Colour colour;
	private int pointsThisWeek;
	private int totalPoints;

	private List<Task> tasksAchieved;
	private List<Acheivement> acheivementsAchieved;
	
	public User(String name, Colour colour){
		this.name = name;
		this.colour = colour;
		this.tasksAchieved = new ArrayList<Task>();
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
		this.totalPoints += totalPoints;
	}

	public List<Task> getTasksAchieved() {
		return tasksAchieved;
	}

	/**
	 * Adds the task to the list of tasks achieved, and adds the points
	 * @param date
	 * @param task
	 */
	public void addTask(Date date, Task task) {
		int points = ((Activity)task).GetPoints();
		
		this.tasksAchieved.add(task);
		this.totalPoints += points;
		this.pointsThisWeek += points;	
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
}
