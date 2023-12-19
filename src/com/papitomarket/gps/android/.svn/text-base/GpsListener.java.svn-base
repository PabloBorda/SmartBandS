/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papitomarket.gps.android;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

/**
 *
 * @author Pablo Tomas Borda Di Berardino
 */
public class GpsListener implements LocationListener{
    public Double lat;
    public Double lng;
    public Double height;
    
    
    
    public GpsListener(){
      
    }


    public void onLocationChanged(Location loc) {
        this.lat = loc.getLatitude();
        this.lng = loc.getLongitude();
        this.height = loc.getAltitude();
        Log.i("GPSReady","lat: " + this.lat.toString() + " lng: " + this.lng + " alt: " + this.height.toString());
    }

    public void onStatusChanged(String string, int i, Bundle bundle) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onProviderEnabled(String string) {
        Log.i("GPSReady", "Gps is enabled");
    }

    public void onProviderDisabled(String string) {
        Log.i("GPSNotReady", "Gps is disabled");
    }
}
