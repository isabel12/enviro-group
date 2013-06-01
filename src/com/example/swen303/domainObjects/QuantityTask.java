package com.example.swen303.domainObjects;

import java.util.Date;

public class QuantityTask extends Activity implements IQuantityActivity, ITask {

	// fields belonging to the task type
	private final int pointsPerQuantity;
	private final String quantityMessage;
	
	// instance fields
	private int quantity;
	
	
	/**
	 * Creates a QuantityTask that is not bound to a username or quantity.
	 * @param activity_name
	 * @param icon_id
	 * @param message_base
	 * @param quantityMessage
	 * @param pointsPerQuantity
	 */
	public QuantityTask(String activity_name, int icon_id,
			String message_base, String quantityMessage, int pointsPerQuantity) {
		super(activity_name, icon_id, message_base);
		
		this.pointsPerQuantity = pointsPerQuantity;
		this.quantityMessage = quantityMessage;
	}

	/**
	 * This returns 0 if it isn't an instance
	 */
	@Override
	public int GetPoints() {
		return quantity * pointsPerQuantity;
	}

	public int GetPointsPerItem(){
		return pointsPerQuantity;
	}
	
	/**
	 * Returns a copy of the QuantityTask that is bound to the username and the quantity.
	 */
	public Activity GetInstance(String username, Date date, int quantity){
		if(super.username != null){
			throw new IllegalArgumentException("Cannot make an instance of an instance");
		}
		
		// create new instance
		QuantityTask instance = new QuantityTask(activity_name, icon_id, message_base, quantityMessage, pointsPerQuantity);		
		instance.username = username;
		instance.quantity = quantity;
		instance.date = date;
		
		return instance;
	}
	
	/**
	 * Returns the message to display information about what the quantity field represents.
	 * @return
	 */
	public String GetQuantityMessage(){	
		return quantityMessage;
	}
	
	/**
	 * Returns the message to display information about what the quantity field represents.
	 * @return
	 */
	@Override
	public String GetMessage(){
		
		String messageToReturn = super.GetMessage().replace("{0}", quantity + "");
		
		return messageToReturn;
	}

	@Override
	public int compareTo(ITask another) {
		return this.GetName().compareTo(another.GetName());
	}
		
}
