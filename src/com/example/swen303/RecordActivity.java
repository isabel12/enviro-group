package com.example.swen303;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.example.swen303.domainObjects.ISimpleActivity;
import com.example.swen303.domainObjects.ITask;
import com.example.swen303.domainObjects.QuantityTask;
import com.example.swen303.domainObjects.SingleTask;
import com.example.swen303.domainObjects.User;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class RecordActivity extends Activity {

	private List<String> tasks;
	
	private List<ITask> tasksInSpinner;
	
	private ITask task;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record);
		// Show the Up button in the action bar.
		setupActionBar();
		setUpActivitySpinner();
		setQuantityVisibility();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.record, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	        NavUtils.navigateUpFromSameTask(this);
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	
	private void setQuantityVisibility(){
		
		TextView quantityMessage = (TextView)findViewById(R.id.task_quantity_message);
		EditText quantity = (EditText)findViewById(R.id.task_quantity_edit_text);
		
		if (task == null || !(task instanceof QuantityTask)){	
			quantityMessage.setVisibility(View.GONE);
			quantity.setVisibility(View.GONE);	
			
			
		} else {
			quantityMessage.setText(((QuantityTask) task).GetQuantityMessage());
			quantityMessage.setVisibility(View.VISIBLE);
			quantity.setVisibility(View.VISIBLE);	
		}

	}
	
	
	private void setUpActivitySpinner(){
		Spinner spinner = (Spinner) findViewById(R.id.activities_spinner);
		
		tasks = new ArrayList<String>();
		tasksInSpinner = new ArrayList<ITask>(ApplicationState.availableTasks.values());
		Collections.sort(tasksInSpinner);
		
		for(ITask task: tasksInSpinner){
			
			if (task instanceof SingleTask){
				SingleTask singleTask = (SingleTask)task;
				tasks.add( singleTask.GetName() + "  (" + singleTask.GetPoints() + " points)");
			} else {
				QuantityTask quantityTask = (QuantityTask)task;
				tasks.add(quantityTask.GetName() + "  (" + quantityTask.GetPointsPerItem() + " points per item)");
			}	
			
		}

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, tasks);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);	
		
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
					int position, long id) {
				// TODO Auto-generated method stub
				
				String taskName = tasksInSpinner.get(position).GetName();
				task = ApplicationState.availableTasks.get(taskName);
				
				setQuantityVisibility();		
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub			
				task = null;
				setQuantityVisibility();
			}	
			
		});
		
		// set task initially
		task = ApplicationState.availableTasks.get(tasks.get(0));
	
	}
	
	
	/**
	 * Responds to the record button
	 * @param view
	 */
	public void recordActivity(View view){
		
		ITask newInstance = null;
		
		int quantity = 0;
		String username = ApplicationState.username;
		Date date = new Date();
		long plus12hours = date.getTime() + 43200000;
		date = new Date(plus12hours);
		
		// if quantity task
		if(task instanceof QuantityTask){
			// get the quantity
			EditText quantityEditText = (EditText)findViewById(R.id.task_quantity_edit_text);
			String quantityText = quantityEditText.getText().toString().trim();
			if(quantityText.equals("")){
				quantityEditText.setError("You must enter a quantity");
				return;
			}		
			try{
				quantity = Integer.parseInt(quantityText);	
			} catch(NumberFormatException e){
				quantityEditText.setError("Quantity must be a number");
				return;
			}	
			
			// make new instance
			newInstance = (ITask)((QuantityTask)task).GetInstance(username, date, quantity);
		}
		// if single task
		else{ 	
			newInstance = (ITask)((SingleTask)task).GetInstance(username, date);
		}
		
		
		// add to user profile
		User user = ApplicationState.users.get(username);
		user.addTask(date, newInstance);
			
		// add to recentActivities
		ApplicationState.recentActivities.add((com.example.swen303.domainObjects.Activity) newInstance);
		
		String pointsString = newInstance.GetPoints() == 1? " point." : " points.";
		
    	// display a message
    	new AlertDialog.Builder(this)
        .setTitle("Task Recorded")
        .setMessage("You received " + newInstance.GetPoints() + pointsString + "\r\n\r\nYou also unlocked an achievement - \"Walk every day for a week\"!")
        .setPositiveButton("Return to My Group", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            	NavUtils.navigateUpFromSameTask(RecordActivity.this);
            }
        }).setNegativeButton("Record New Task", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                  //nothing
            }
        }).show();
		
		
		
		
	}
	
	

}
