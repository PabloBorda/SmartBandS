/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papitomarket.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

/**
 *
 * @author Pablo Tomas Borda Di Berardino
 */
public class InviteFriends extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, 
                             WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        // ToDo add your GUI initialization code here        
    }
}
