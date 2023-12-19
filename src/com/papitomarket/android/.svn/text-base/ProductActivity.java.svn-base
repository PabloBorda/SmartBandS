/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papitomarket.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.papitomarket.model.products.android.Product;
import com.papitomarket.model.stores.android.Store;
import java.io.InputStream;
import java.net.URL;

/**
 *
 * @author Pablo Tomas Borda Di Berardino
 */
public class ProductActivity extends Activity{
 
    
    public static Product p;
    public static Store s;
    boolean click = true;
    LinearLayout layout;
    
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, 
                             WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

        
        TextView name = (TextView)findViewById(R.id.productname);
        TextView price = (TextView)findViewById(R.id.price);
        TextView description = (TextView)findViewById(R.id.description);
        ImageView pic = (ImageView)findViewById(R.id.productpic);
        TextView seller = (TextView)findViewById(R.id.productseller);
        ImageView companypic = (ImageView)findViewById(R.id.companylogo);
        TextView stock = (TextView)findViewById(R.id.stock);
        ImageButton order = (ImageButton)findViewById(R.id.orderbutton);
        
        
        name.setText(p.name);
        price.setText(p.price + " $");
        description.setText(p.description);
        seller.setText(s.getCompanyname());
        stock.setText("10 of 34 were sold.");
        

        order.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
              AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext());
              // Get the layout inflater
              LayoutInflater inflater = ((Activity)getBaseContext()).getLayoutInflater();

              // Inflate and set the layout for the dialog
              // Pass null as the parent view because its going in the dialog layout
              
              builder.setView(inflater.inflate(R.layout.dialog_signin, null))
              // Add action buttons
              .setPositiveButton("Sign In", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int id) {
                   // sign in the user ...
                 }
              })
              .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int id) {
                   
                 }
              });
            }
        });
        
        
        
        
        
        
        
        
        if (p.pics.size()>0){
          InputStream content = null;       
          try {
            String addr = "http://dev.papitomarket.com/images/" +  s.getCompanyname().replaceAll(" ","") + "/products/" + p.id.toString() + p.pics.get(0).toString();
            //String addr = "http://maps.googleapis.com/maps/api/staticmap?center=" + store.lat.toString() + "," + store.lng.toString() + "&markers=-34.603571,-58.417013&zoom=14&size=500x106&markers=color:red%7Ccolor:red%7Clabel:C%7C40.718217,-73.998284&sensor=false";
            Log.i("papitomarket", addr); 
            URL url = new URL(addr);
            content = (InputStream)url.getContent();
            Drawable image = Drawable.createFromStream(content, "src");
            pic.setImageDrawable(image);
          }
          catch(Exception ex)
          {
            ex.printStackTrace();
          }   
       }
    
    
          InputStream content = null;       
          try {
            String addr = "http://dev.papitomarket.com/images/" +  s.getCompanyname().replaceAll(" ","") + "/" + s.getCompanylogo();
            //String addr = "http://maps.googleapis.com/maps/api/staticmap?center=" + store.lat.toString() + "," + store.lng.toString() + "&markers=-34.603571,-58.417013&zoom=14&size=500x106&markers=color:red%7Ccolor:red%7Clabel:C%7C40.718217,-73.998284&sensor=false";
            Log.i("papitomarket", addr); 
            URL url = new URL(addr);
            content = (InputStream)url.getContent();
            Drawable image = Drawable.createFromStream(content, "src");
            companypic.setImageDrawable(image);
          }
          catch(Exception ex)
          {
            ex.printStackTrace();
          }   
    
    
    
    
    
    
    
    }
    
}
