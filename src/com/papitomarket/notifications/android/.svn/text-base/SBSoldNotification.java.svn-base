package com.papitomarket.notifications.android;

import com.papitomarket.android.ContactListActivity;
import com.papitomarket.android.MenuActivity;
import com.papitomarket.android.SBSoldActivity;
import com.papitomarket.service.android.Updates;

import android.app.Activity;
import android.content.Intent;


/*
 * Notification types: each type of notification renders to the attached Activity
 * When a message arrives, the json is parsed, so an instance of the matching notification
 * is created.
 * 
 * Aut hor: Pablo Tomas Borda Di Berardino
 * 
 * */
public class SBSoldNotification extends SBNotification {

	public SBSoldNotification(String body){
		super(body);
	}
	
	
	
	public Class<? extends Activity> process(){
		
		return SBSoldActivity.class;
		 
	}
	
	
	
	
	
	
}
