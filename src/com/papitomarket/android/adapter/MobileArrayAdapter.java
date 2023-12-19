package com.papitomarket.android.adapter;

import com.papitomarket.android.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.papitomarket.model.stores.android.Store;
import java.io.InputStream;
import java.net.URL;

public class MobileArrayAdapter extends ArrayAdapter<Store> {
	private final Context context;
	private final Store[] values;

	public MobileArrayAdapter(Context context, Store[] values) {
		super(context, R.layout.search, values);
		this.context = context;
		this.values = values;
	} 

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.list_mobile, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		textView.setText(values[position].getCompanyname());

		// Change icon based on name
		String s = values[position].getCompanyname();

		System.out.println(s);

		
                InputStream content = null;
                
                try {
                    String addr = "http://dev.papitomarket.com/images/" +  values[position].getCompanyname().replaceAll(" ","") + "/" + values[position].getCompanylogo();
                    URL url = new URL(addr);
                    content = (InputStream)url.getContent();
                    Drawable image = Drawable.createFromStream(content, "src");
                    imageView.setImageDrawable(image);
                }
                catch(Exception ex)
                {
                  ex.printStackTrace();
                }
                
                
                
                
                
                
                
		return rowView;
	}
        
     
}
