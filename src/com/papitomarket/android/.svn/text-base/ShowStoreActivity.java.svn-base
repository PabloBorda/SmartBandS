/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papitomarket.android;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.papitomarket.android.adapter.CategoriesAdapter;
import com.papitomarket.android.adapter.ProductsAdapter;
import com.papitomarket.global.GlobalData;
import com.papitomarket.model.products.android.Category;
import com.papitomarket.model.products.android.Product;
import com.papitomarket.model.stores.android.Store;
import com.papitomarket.widgets.custom.android.Company;

/**
 *
 * @author Pablo Tomas Borda Di Berardino
 */
public class ShowStoreActivity extends ListActivity {

    
    public static Store store;
    private static int cat;
    
    
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        GlobalData.name = "ShowStoreActivity";
        super.onCreate(icicle);
        
        Company c = new Company(this);
        setContentView(R.layout.showstore);
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, 
                             WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        

        ImageButton cartBtn = (ImageButton)findViewById(R.id.cartBtn);
        cartBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent shopping_cart_screen = new Intent(getApplicationContext(),ShoppingCartActivity.class);
	             startActivity(shopping_cart_screen);
				
			}
		});
        
        InputStream content = null;
        
        ImageView imageView = (ImageView)findViewById(R.id.logo);
        try {
           String addr = "http://dev.papitomarket.com/images/" +  store.getCompanyname().replaceAll(" ","") + "/" + store.getCompanylogo();
           //String addr = "http://maps.googleapis.com/maps/api/staticmap?center=" + store.lat.toString() + "," + store.lng.toString() + "&markers=-34.603571,-58.417013&zoom=14&size=500x106&markers=color:red%7Ccolor:red%7Clabel:C%7C40.718217,-73.998284&sensor=false";
           Log.i("papitomarket", addr); 
           URL url = new URL(addr);
           content = (InputStream)url.getContent();
           Drawable image = Drawable.createFromStream(content, "src");
           imageView.setImageDrawable(image);
         }
         catch(Exception ex)
         {
           ex.printStackTrace();
         }
        // ToDo add your GUI initialization code here        
        ImageView logo = (ImageView)findViewById(R.id.map);
        try {
           //String addr = "http://dev.papitomarket.com/images/" +  store.companyname.replaceAll(" ","") + "/" + store.companylogo;
           String addr = "http://maps.googleapis.com/maps/api/staticmap?center=" + store.getLat().toString() + "," + store.getLng().toString() + "&markers=" + store.getLat().toString() + "," + store.getLng().toString() + "&zoom=15&size=500x106&markers=color:red%7Ccolor:red%7Clabel:C%7C40.718217,-73.998284&sensor=false";
           Log.i("papitomarket", addr); 
           URL url = new URL(addr);
           content = (InputStream)url.getContent();
           Drawable image = Drawable.createFromStream(content, "src");
           logo.setImageDrawable(image);
         }
         catch(Exception ex)
         {
           ex.printStackTrace();
         }
        
         TextView company = (TextView)findViewById(R.id.companyname);
         company.setText(store.getCompanyname());
         
         TextView companyDescription = (TextView)findViewById(R.id.companydesciption);
         companyDescription.setText(store.getWebpage());
        
        
         setListAdapter(new CategoriesAdapter(getBaseContext(),store.getCategories().toArray(new Category[store.getCategories().size()])));
        
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        if ("ShowStoreActivity".equals(GlobalData.name)){
          cat = position;
          ArrayList<Product> products = store.getCategories().get(position).products;
          setListAdapter(new ProductsAdapter(v.getContext(),products.toArray(new Product[products.size()])));
          
          
         
          
          GlobalData.name = "ShowProductsCategory";
        } else {
            if ("ShowProductsCategory".equals(GlobalData.name)){
              ProductActivity.p = store.getCategories().get(cat).products.get(position);
              ProductActivity.s = store;
              Intent viewProduct = new Intent(getApplicationContext(), ProductActivity.class);
              GlobalData.name = "ShowProductsCategory";
              startActivity(viewProduct);
             
            }
        }
        
    }

    
    
    
    
}
