/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papitomarket.gps.android;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

/**
 *
 * @author Pablo Tomas Borda Di Berardino
 */
public class Gps {
    
    private LocationManager mlocManager;
    private LocationListener mlocListener;
    public Double lat;
    public Double lng;
    public Double height;
    private Location l;
    public Gps(Context ctx){    
      /* Use the LocationManager class to obtain GPS locations */
      mlocManager = (LocationManager)ctx.getSystemService(Context.LOCATION_SERVICE);
      
      mlocListener = new GpsListener();
      l = mlocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
      
        
    }
    
    public void update(){
        if (l != null && mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
          this.lat = l.getLatitude();
          this.lng = l.getLongitude();
          this.height = l.getAltitude();
        }
    }
    
}
