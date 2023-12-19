/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papitomarket.android;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.papitomarket.android.adapter.MobileArrayAdapter;
import com.papitomarket.global.GlobalData;
import com.papitomarket.gps.android.Gps;
import com.papitomarket.io.android.Search;
import com.papitomarket.model.facebook.android.User;
import com.papitomarket.model.stores.android.Store;
import com.papitomarket.model.tags.android.Tag;
import com.papitomarket.util.Util;
import com.papitomarket.widgets.TagAutoCompleteTextView;
/**
 *
 * @author Pablo Tomas Borda Di Berardino
 */
public class SearchActivity extends ListActivity {
    
    static Store[] SEARCH_RESULTS;
    static boolean tagSelected;
    public ProgressBar pb;
    static int width;
    static int height;
    
      
    

    
    
        @Override
        public void onCreate(Bundle savedInstanceState){
      
            super.onCreate(savedInstanceState);
      
            setContentView(R.layout.search);
            
            
            LinearLayout rl = (LinearLayout)findViewById(R.id.rellay);
//            String pic = "@drawable/" + calculateBackgroundPic("l");
//            Log.i("papitomarket","LOADING BACKGROUND: " + pic);
            Util.resizeImageBackground("l", this, R.id.rellay,rl);
            //rl.setBackgroundResource(getResId(calculatePic("l"), R.drawable.class));
            //rl.setBackgroundDrawable(Drawable.createFromPath("@drawable/l960x540"));
            
            		
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, 
                                 WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            
            tagSelected = false;
            //requestWindowFeature(Window.FEATURE_NO_TITLE);
            //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
            //                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            
           

            pb = (ProgressBar)findViewById(R.id.widget34);

            pb.setVisibility(View.GONE);

            //AbsoluteLayout al = (AbsoluteLayout)findViewById(R.id.widget0);
           // al.addView(new Company(this));
            
            TagAutoCompleteTextView textView = (TagAutoCompleteTextView)
                     findViewById(R.id.widget33);

            //textView.setVisibility(View.GONE);
            textView.setText("");
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundColor(Color.parseColor("#FA42A3"));
            textView.setThreshold(2);
            textView.setCursorVisible(true);
            textView.setWidth(getWindowManager().getDefaultDisplay().getWidth()-100);
            textView.addTextChangedListener(new TextWatcher(){
              public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			
			
				
			
              }

              public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

 
                            TagGetter tg = new TagGetter(getBaseContext());
                            tg.execute("");
                            tagSelected = false;
                   
              }

              public void afterTextChanged(Editable arg0) {
             
              } 


            });
            
            textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            	
            	private void hidekeyboard(){
            		InputMethodManager imm = (InputMethodManager)getSystemService(
            			      Context.INPUT_METHOD_SERVICE);
            		TagAutoCompleteTextView textView = (TagAutoCompleteTextView)
                            findViewById(R.id.widget33);
            			imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
            	}

                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
 
                  StoreFinder sf = new StoreFinder();
                  sf.execute("");
                  tagSelected = true;
                  hidekeyboard();
                  
                  //Company cmp = new Company(getApplicationContext());
                  //setContentView(cmp);
                }
            });
            ImageButton cartBtn = (ImageButton)findViewById(R.id.cartBtn);
            cartBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 Intent shopping_cart_screen = new Intent(getApplicationContext(),ShoppingCartActivity.class);
		             startActivity(shopping_cart_screen);
					
				}
			});
            
    
    
    } 

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {                   
                   InputMethodManager imm = (InputMethodManager)getSystemService(
                   Context.INPUT_METHOD_SERVICE);
                   imm.hideSoftInputFromWindow(((TagAutoCompleteTextView)findViewById(R.id.widget33)).getWindowToken(), 0);
            
                   ShowStoreActivity.store = SEARCH_RESULTS[position];
                   GlobalData.selectedCompany = SEARCH_RESULTS[position].getCompanyname();
                   Intent showStore = new Intent(getApplicationContext(), ShowStoreActivity.class);
                   startActivity(showStore);
              		//String selectedValue = (String) getListAdapter().getItem(position);
		//Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();

	}
   	
        
        private class StoreFinder extends AsyncTask<String,Void,Store[]>{

          private boolean first = false;  
            
          @Override
          protected Store[] doInBackground(String... arg0) {
                  publishProgress();
                  String search_tag = ((TagAutoCompleteTextView)findViewById(R.id.widget33)).getText().toString();
 
                 Gps gps = new Gps(getApplicationContext());
                 gps.update();
                  
                  Search s = Search.getSearch(getBaseContext());
                  Store[] stores = s.load_stores(search_tag,"-34.6035707","-58.4170134");
                  
                  Log.i("PAPITOMARKET","SEARCHING " + search_tag);

                  

                  return stores;
          }
            
          @Override
          protected void onPostExecute(Store[] result){
            SearchActivity.SEARCH_RESULTS = result;
            setListAdapter(new MobileArrayAdapter(getBaseContext(),SearchActivity.SEARCH_RESULTS));
            publishProgress();
            pb.setVisibility(View.GONE);
          }

          @Override
          protected void onProgressUpdate(Void... values){  
            super.onProgressUpdate(values);
            if (first){
              pb.setVisibility(View.VISIBLE);
              first = false;
            } else {
              pb.setVisibility(View.GONE);
              first = true;
            }
          }
            
            
            
        }
        
        
	private class TagGetter extends AsyncTask<String,Void,String>
	{

                private Context ctx;
                private boolean first = false;

                public TagGetter(Context ctx){
                  this.ctx = ctx;
                }

		protected String doInBackground(String[] p1)
		{
                    publishProgress();
                    
		    TagAutoCompleteTextView  textView = (TagAutoCompleteTextView)findViewById(R.id.widget33);


		    Search s = Search.getSearch(getBaseContext());

                    Tag[] tags = s.tags(textView.getText().toString());
		    String tmp = "";
                    for (int i=0;i<tags.length;i++){
                        tmp = tmp + tags[i].getLabel() + ",";
                    }
                    
                    return tmp.substring(0,tmp.length());		
			
		}
       
	        @Override
                protected void onPostExecute(String result){

			TagAutoCompleteTextView  textView = (TagAutoCompleteTextView)findViewById(R.id.widget33);


			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_dropdown_item_1line,result.split(","));
			textView.setAdapter(adapter);
			if (!tagSelected){
                          textView.showDropDown();
                         
                        }
                        publishProgress();
		}

                @Override
                protected void onProgressUpdate(Void... values){  
                  super.onProgressUpdate(values);
                  if (first){
                    pb.setVisibility(View.VISIBLE);
                    first = false;
                  } else {
                      pb.setVisibility(View.GONE);
                      first = true;
                  }
                 }

    }
	
}
    
    

