/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papitomarket.service.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.papitomarket.android.LoginActivity;
import com.papitomarket.android.MenuActivity;
import com.papitomarket.android.R;
import com.papitomarket.model.facebook.android.User;
import com.papitomarket.model.facebook.android.UserDataSource;
import com.papitomarket.notifications.android.SBNotification;
import com.papitomarket.notifications.android.SBNotificationFactory;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;

/**
 * This service retrieves products orders from xmpp and updates
 * from subscribed tags
 * @author Pablo Tomas Borda Di Berardino
 * 
 * Smack API: http://www.igniterealtime.org/builds/smack/docs/latest/javadoc/
 * 
 * 
 */
public class Updates extends Service{

   
    private NotificationManager nm;
    private int count=0;
    private static final int NOTIFY_ME_ID=1987;
    public XMPPConnection jabberConnection;
    private static Updates inst;
    public static Context appContext;
    public static Intent currentNotificationActivity;
    private static SBNotification stack; //Notifications stack, when one is clicked, another appears.. no user doesnt miss any
    
    
    public boolean isOnline() {
        /*ConnectivityManager cm =
            (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;*/
    	return true;
    }
    
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    	android.os.Debug.waitForDebugger();
    	Log.i("SERVICE","com.papitomarket.android: Service Start command");
        
        Updates.inst = this;
        MenuActivity.updates = this;
    	

    	 //Bundle extras = intent.getExtras();
    	 
        UserDataSource uds = new UserDataSource(this);
        User u = (User)uds.getAllUsers().get(0);
        String user = u.getUsername().replace(" ","");
        
        boolean showed = false;
        while (!this.isOnline()){
        	if (!showed){
        		this.online_notify("No internet access...connecting..",MenuActivity.class);
        		showed = true;
        	}
        	
        }
        
        
         //String user = intent.getStringExtra("user");
         Log.i("SERVICE","com.papitomarket.android: Connecting to Jabber");
 
         try {
            SmackConfiguration.setPacketReplyTimeout(20000);
           
            ConnectionConfiguration cc = new ConnectionConfiguration("chat.papitomarket.com",5222);
            
            jabberConnection = new XMPPConnection(cc);
            jabberConnection.connect();
            jabberConnection.login(user + "-fb",user + "-fb");

            Presence presence = new Presence(Presence.Type.available);

            jabberConnection.sendPacket(presence);

            

            
            
            ChatManager cm = jabberConnection.getChatManager();
            
            
            jabberConnection.getChatManager().addChatListener(new ChatManagerListenerImpl());

            this.online_notify("SmartBandS is Online",LoginActivity.class);
          } catch (XMPPException ex) {
            Log.e("SERVICE","com.papitomarket.android: jabber connection failed");
            Logger.getLogger(Updates.class.getName()).log(Level.SEVERE, null, ex);
          }
          return super.onStartCommand(intent, flags, startId);
    }

    
    
    private class ChatManagerListenerImpl implements ChatManagerListener {

        /** {@inheritDoc} */
        @Override
        public void chatCreated(final Chat chat, final boolean createdLocally) {
        	android.os.Debug.waitForDebugger();
        	chat.addMessageListener(new MessageListener() {
				@Override
				public void processMessage(Chat arg0, Message arg1) {
					// TODO Auto-generated method stub
					Log.i("papitomarket","I got message " + arg1.getBody());
					Updates.inst.online_notify_jabber(arg1.getBody(),SBNotificationFactory.processNotification(arg1.getBody()).process());
					Updates.appContext = getApplicationContext();
				    //This process notification and opens the apropiate activity
				}
			});
        }
    }
    
    
    
    
    @Override
    public void onStart(Intent intent, int startId) {
        
    
    }

    
    
    @Override
    public void onCreate() {
    	Log.i("SERVICE","com.papitomarket.android: Service created");
    	
    	
    }

    @Override
    public void onDestroy() {
     Log.i("SERVICE","com.papitomarket.android: Service destroyed");
    }

    
    public void online_notify(String msgbody,Class<? extends Activity> notificationActivity){
        Log.i("SERVICE","com.papitomarket.android: Running status bar notification");
        nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification note=new Notification(R.drawable.stat_buy,"SmartBandS alert!",System.currentTimeMillis());
        Intent act = new Intent(this.getApplicationContext(), notificationActivity);
        PendingIntent i=PendingIntent.getActivity(this.getApplicationContext(), 0,act,0);
	    note.contentIntent = i;
	    note.setLatestEventInfo(this, "SmartBandS",msgbody, i);
	    note.number=++count;
	    note.vibrate=new long[] {500L, 200L, 200L, 500L};
	    note.flags|=Notification.FLAG_ONGOING_EVENT;
	    
	    nm.notify(NOTIFY_ME_ID, note);
           	
    }

    
    public void online_notify_jabber(String msgbody,Class<? extends Activity> notificationActivity){
    	
        Log.i("SERVICE","com.papitomarket.android: Running status bar notification");
        nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification note=new Notification(R.drawable.stat_buy,"SmartBandS alert!",System.currentTimeMillis());
        Intent act = new Intent(this.getApplicationContext(), notificationActivity);
        
        
        JsonFactory f = new JsonFactory();
        JsonParser jp;
        ObjectMapper om = new ObjectMapper();
     
       	try {
		  jp = f.createJsonParser(msgbody);
		  JsonNode node = om.readTree(msgbody);
		  String passData = node.get("body").toString();
          act.putExtra("body",passData);
	      PendingIntent i=PendingIntent.getActivity(this.getApplicationContext(), 0,act,0);
	      note.contentIntent = i;
	      note.setLatestEventInfo(this, "SmartBandS",msgbody, i);
	      note.number=++count;
	      note.vibrate=new long[] {500L, 200L, 200L, 500L};
	      note.flags|=Notification.FLAG_AUTO_CANCEL;
	    
	      nm.notify(NOTIFY_ME_ID, note);
       	
       	} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       	
       	
       	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return new IUpdatesManager.Stub() {
			
			@Override
			public boolean sendNotification(String to, String message)
					throws RemoteException {
				
		        ChatManager cm = jabberConnection.getChatManager();
	            Chat chat = cm.createChat(to + "@chat.papitomarket.com", new MessageListener() {

	              @Override
	              public void processMessage(Chat chat, Message message) {
                    Log.i("papitomarket", "order confirmed to message: " + message.getBody());
	              }
	            });
                try {
					chat.sendMessage(message);
				} catch (XMPPException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	            return true;
			}
			
			@Override
			public boolean onReceivedNotification(String from, String message)
					throws RemoteException {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
	}
    
    
    
    
    
}
