package com.example.swen303.domainObjects;

import java.util.Date;

public class Message {
	protected String from;
	protected String to;
	protected Date date;
	protected String message;	
	
	public Message(String to, String from, Date date, String message){
		this.to = to;
		this.from = from;
		this.date = date;
		this.message = message;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public Date getDate() {
		return date;
	}

	public String getMessage() {
		return message;
	}

}
