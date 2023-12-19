package com.papitomarket.notifications.android;

import com.papitomarket.android.SBAnnouncementActivity;

import android.app.Activity;


/*
 * Notification types: each type of notification renders to the attached Activity
 * When a message arrives, the json is parsed, so an instance of the matching notification
 * is created.
 * 
 * Author: Pablo Tomas Borda Di Berardino
 * 
 * */
public class SBAnnouncementNotification extends SBNotification{

	public SBAnnouncementNotification(String body){
		super(body);
	}
	
	@Override
    public Class<? extends Activity> process() {
		
		return SBAnnouncementActivity.class;
	}
	
}
