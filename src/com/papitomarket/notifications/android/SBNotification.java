package com.papitomarket.notifications.android;

import android.app.Activity;
import android.content.Intent;


/*
 * Notification types: each type of notification renders to the attached Activity
 * When a message arrives, the json is parsed, so an instance of the matching notification
 * is created.
 * 
 * Author: Pablo Tomas Borda Di Berardino
 * 
 * */
public class SBNotification {

	protected String body;
	protected Boolean read;
	
	public Boolean getRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public SBNotification(String body){
		this.body = body;
	}

	public void setBody(String body){
		this.body = body;
	}
	public Class<? extends Activity> process() {
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
}
