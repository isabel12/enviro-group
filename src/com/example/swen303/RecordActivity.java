package com.example.swen303;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.example.swen303.domainObjects.QuantityTask;
import com.example.swen303.domainObjects.SingleTask;
import com.example.swen303.domainObjects.Task;
import com.example.swen303.domainObjects.User;

import android.os.Bundle;
import android.app.Activity;
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
	private Task task;
	
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
		for(String taskName: ApplicationState.availableTasks.keySet()){
			tasks.add(taskName);
		}
		Collections.sort(tasks);

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, tasks);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);	
		
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
					int position, long id) {
				// TODO Auto-generated method stub
				
				String taskName = tasks.get(position);
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
		
		Task newInstance = null;
		
		int quantity = 0;
		String username = ApplicationState.username;
		Date date = new Date();
		
		// if quantity task
		if(task instanceof QuantityTask){
			// get the quantity
			EditText quantityEditText = (EditText)findViewById(R.id.task_quantity_edit_text);
			String quantityText = quantityEditText.getText().toString();		
			try{
				quantity = Integer.parseInt(quantityText);	
			} catch(NumberFormatException e){
				quantityEditText.setError("Quantity must be a number");
				return;
			}	
			
			// make new instance
			newInstance = (Task)((QuantityTask)task).GetInstance(username, date, quantity);
		}
		// if single task
		else{ 	
			newInstance = (Task)((SingleTask)task).GetInstance(username, date);
		}
		
		
		// add to user profile
		User user = ApplicationState.users.get(username);
		user.addTask(date, newInstance);
			
		// add to recentActivities
		ApplicationState.recentActivities.add((com.example.swen303.domainObjects.Activity) newInstance);
		
	}
	
	

}
