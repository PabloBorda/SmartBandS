package com.papitomarket.android;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.papitomarket.android.adapter.ProductsAdapter;
import com.papitomarket.global.GlobalData;
import com.papitomarket.io.android.Search;
import com.papitomarket.model.cart.Order;
import com.papitomarket.model.cart.ShoppingCart;
import com.papitomarket.model.facebook.android.User;
import com.papitomarket.model.products.android.Product;
import com.papitomarket.service.android.IUpdatesManager;
import com.papitomarket.widgets.TagAutoCompleteTextView;

public class ShoppingCartActivity extends ListActivity{

	
	static boolean tagSelected;
	public static IUpdatesManager service;
	public static UpdatesServiceConnector usc;
    public static String addr;	
    static String usernameCache;
	
	public static ArrayList<Order> orders = new ArrayList<Order>();
	

	
	
	public void initService(){
		ShoppingCartActivity.usc = new UpdatesServiceConnector();
		Intent i = new Intent();
	    i.setClassName(this, com.papitomarket.service.android.Updates.class.getName());
	    boolean ret = bindService(i, ShoppingCartActivity.usc, Context.BIND_AUTO_CREATE);
	    i.putExtra("user",User.getInstance().getUsername());
	   // startService(i);
	}
	
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.shoppingcart);
		
		TagAutoCompleteTextView  textView = (TagAutoCompleteTextView)findViewById(R.id.addredit);
		//textView.setThreshold(2);
		textView.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				TagAutoCompleteTextView  textView = (TagAutoCompleteTextView)findViewById(R.id.addredit);
				AddrService tg = new AddrService(getBaseContext());
                tg.execute(textView.getText().toString());
                tagSelected = false;
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			
			}
		});
		
		textView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				tagSelected = true;
				TagAutoCompleteTextView  textView = (TagAutoCompleteTextView)findViewById(R.id.addredit);
				ShoppingCartActivity.addr = textView.getText().toString();
				// TODO Auto-generated method stub
				
			}
		});
	 
		setListAdapter(new ShoppingCart(this,0));
		float total=0;
		for (int i=0;i<ProductsAdapter.spins.size();i++){
			int amount = (new Integer(((Spinner)ProductsAdapter.spins.get(i)).getSelectedItem().toString())).intValue(); 
			if (amount > 0){
				Order o = new Order(ProductsAdapter.products[i], amount, true);
				orders.add(o);
				total = total + (amount*(new Float(ProductsAdapter.products[i].price)));
			}
			Log.i("papitomarket","Cart amount: " + ((Spinner)ProductsAdapter.spins.get(i)).getSelectedItem().toString());
		}
		
		TextView tot = (TextView)findViewById(R.id.total);
		tot.setText((new Float(total)).toString());
		
		initService();
		
		Button ordernowbtn = (Button)findViewById(R.id.ordernowbtn);		
		ordernowbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				boolean sent;
				ShoppingCartActivity.usernameCache = User.getInstance().getName();
				String products = "{\"sold\": [";
				String to = GlobalData.selectedCompany.replace(" ","") + "-fb";
				for (int i=0;i<ShoppingCartActivity.orders.size();i++){
					//send order messages to selected stores
					Order currentorder = ((Order)ShoppingCartActivity.orders.get(i));
					Product current = currentorder.getP();
					products = products + "{ \"id\" : \"" + current.id + "\",\"name\":\"" + current.name + "\",\"addr\": \"" +  ShoppingCartActivity.addr + "\", \"amount\": \"" +  currentorder.getAmount() + "\",\"total\":\"" + currentorder.getPrice() + "\",\"who\":" + "\"" + ShoppingCartActivity.usernameCache.replace(" ","") + "-fb" + "\"" + "},";	
				}
				products = products.substring(0, products.length()-1);				
				products = products + "]}";
				try {
					while (service == null) {};
					sent = ShoppingCartActivity.service.sendNotification(to,products);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	
	private class AddrService extends AsyncTask<String,Void,String>
	{

        private Context ctx;
        private boolean first = false;

        public AddrService(Context ctx){
          this.ctx = ctx;
        }

		protected String doInBackground(String[] p1)
		{
            publishProgress();
                    
		   
		    Search s = Search.getSearch(getBaseContext());
		    ArrayList<String> addrs = new ArrayList<String>();
		    addrs = s.getAddrsFor(p1[0]);

                    
		    String tmp = "";
            for (int i=0;i<addrs.size();i++){
              tmp = tmp + addrs.get(i).toString() + "_";
            }
                    
            return tmp.substring(0,tmp.length());		
			
		}
       
	        @Override
         protected void onPostExecute(String result){

			TagAutoCompleteTextView  textView = (TagAutoCompleteTextView)findViewById(R.id.addredit);


			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_dropdown_item_1line,result.split("_"));
			textView.setAdapter(adapter);
			if (!tagSelected){
              textView.showDropDown();
            }
            publishProgress();
		 }

                @Override
         protected void onProgressUpdate(Void... values){  

         }

    }

	
	
	class UpdatesServiceConnector implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder boundService) {
			// TODO Auto-generated method stub
			ShoppingCartActivity.service = IUpdatesManager.Stub.asInterface((IBinder)boundService);
			
		    
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			ShoppingCartActivity.service = null;
			System.gc();
		}
		
	}
	
	
	
	
	
}
