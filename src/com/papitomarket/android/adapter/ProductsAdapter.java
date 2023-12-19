/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papitomarket.android.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import com.papitomarket.android.R;
import com.papitomarket.model.facebook.android.User;
import com.papitomarket.model.products.android.Product;

/**
 *
 * @author Pablo Tomas Borda Di Berardino
 */
public class ProductsAdapter extends ArrayAdapter<Product> {
    
    public final Context context;
    public static Product[] products;
    public static ArrayList<View> rows = new ArrayList<View>();
    public static ArrayList<Spinner> spins = new ArrayList<Spinner>();
    private int spinid = 0;
    public static ArrayList<Integer> amounts = new ArrayList<Integer>();
    
    
    public ProductsAdapter(Context ctx,Product[] values){
        super(ctx,R.layout.showstore,values);
        this.context = ctx;
        ProductsAdapter.products = values;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        
    	
    	
    	
    	if (position <= rows.size()){ 
    	  LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
        		
          spinid = Integer.parseInt(products[position].id);
        
          View rowView = inflater.inflate(R.layout.list_products, parent, false);
		
          TableRow tr = (TableRow) rowView.findViewById(R.id.rowcito);
       
          Spinner sp = new Spinner(rowView.getContext());
        
          sp.setId(spinid);
        
          List<String> list = new ArrayList<String>();
    	  list.add("0");
    	  list.add("1");
    	  list.add("2");
    	  list.add("3");
    	  list.add("4");
    	  list.add("5");
    	  list.add("6");
    	  list.add("7");
    	  list.add("8");
    	  list.add("9");
    	  list.add("More...");
    	    	
    	  ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,list);
        
    	  dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	  sp.setAdapter(dataAdapter);
          
    	  sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				System.out.println("spinner selectedddd: " + arg2 + "");
				// TODO Auto-generated method stub
				if (arg2 > 0){
					
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		
    	
    	});
    	
    	
    	
    	
    	spins.add(sp);
    	tr.addView(sp);
        
        
		//ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.amount, spinid);
		// Specify the layout to use when the list of choices appears
		//adapter.setDropDownViewResource(R.layout.list_products);
		// Apply the adapter to the spinner
		//sp.setAdapter(adapter);
		
		TextView textView = (TextView) rowView.findViewById(R.id.productname);
		
		
		TextView price = (TextView)rowView.findViewById(R.id.price);                
        price.setText(products[position].price);
        
        textView.setMaxWidth(User.scrwidth - (price.getWidth() + sp.getWidth() + 5));
        //textView.setMaxWidth(200);
        textView.setHorizontallyScrolling(true);
        //textView.setLines(textView.getWidth() / (User.scrwidth - (price.getWidth() - sp.getWidth()) - 5));
        textView.setText(products[position].name);
        
        rows.add(rowView);       
  	} 
        return rows.get(position);
    	
    	
    }
    
    
}
