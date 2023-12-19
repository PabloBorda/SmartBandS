/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papitomarket.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.papitomarket.android.R;
import com.papitomarket.model.facebook.android.User;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;
import android.view.ViewGroup;

/**
 *
 * @author Pablo Tomas Borda Di Berardino
 */
public class Util {
    
    public static String get(String url){
        try{
          HttpClient httpclient = new DefaultHttpClient();          
          HttpResponse response = httpclient.execute(new HttpGet(url));
          
          StatusLine statusLine = response.getStatusLine();
          if(statusLine.getStatusCode() == HttpStatus.SC_OK){
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            response.getEntity().writeTo(out);
            out.close();
            return out.toString();
          } else{
          //Closes the connection.
            response.getEntity().getContent().close();
            throw new IOException(statusLine.getReasonPhrase());
          }
        } catch (IOException ex) {
            
        } 
        return "error";

    }
    
    private String getScreenSize(String prefix, Activity activity){
        int width;
        int height;
        
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        width = display.getWidth();
        height = display.getHeight();

    	return (prefix + width + "x" + height);
    }
    
	////////////////////////////////// Seteo de imagenes en las activities
    
    public static void resizeImageBackground(String prefix, Activity activity, int idResource,ViewGroup vGroup ){
    	vGroup.setBackgroundResource(getResId(calculatePic("l",activity), R.drawable.class));
    	
    }
    
    static int width;
    static int height;
	private static String calculatePic(String prefix, Activity activity){
    	
	     
    	Field[] drawables = com.papitomarket.android.R.drawable.class.getFields();
    	ArrayList<String> files = new ArrayList<String>();
    	for (Field f : drawables) {
    	    try {
               if (f.getName().contains("l") && f.getName().contains("x")){
    	    	  files.add(f.getName());
               }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	}
    	  	
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        width = display.getWidth();
        height = display.getHeight();
        User.scrwidth = width;
        User.scrheight = height;
        
        int minwdiff = 999999;
        int minhdiff = 999999;
        String bestpic = "";
        for (String s : files){
        	
          String without_prefix = s.substring(1);
          String[] halfs= without_prefix.split("x");
          
          
        	
          int w = Integer.parseInt(halfs[0]);
          int h = Integer.parseInt(halfs[1]);
          if ((Math.abs(width - w) <= minwdiff) && (Math.abs(height - h) <= minhdiff)){
        	  minwdiff = Math.abs(width - w);
        	  minhdiff = Math.abs(height - h);
        	  bestpic = s;
          }
        }
        
        
    	return bestpic;
    }

	private static int getResId(String variableName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } 
    }
}
