package com.papitomarket.model.cart;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ShoppingCart extends ArrayAdapter<Order>{

	public ShoppingCart(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Order> orders = new ArrayList<Order>();
	public String address = "";
	
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		
		
		
		return parent;
		
		
		
		
	}
	
	
	
	
}
