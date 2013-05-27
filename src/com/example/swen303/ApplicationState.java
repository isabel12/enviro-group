package com.example.swen303;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.swen303.domainObjects.Activity;
import com.example.swen303.domainObjects.Task;
import com.example.swen303.domainObjects.User;

public class ApplicationState {

	public static String username;
	public static Map<String, User> users = new HashMap<String, User>(); // from name to User	
	public static List<Activity> recentActivities = new ArrayList<Activity>();
	public static Map<String, Task> availableTasks = new HashMap<String, Task>(); // from task name to Task
}
