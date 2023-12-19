package com.papitomarket.android;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.papitomarket.contacts.android.Contact;
import com.papitomarket.contacts.android.ContactAdapter;
import com.papitomarket.contacts.android.ContactList;

public class ContactListActivity extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, 
                             WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        
        ContactList contactList=this.getContacts();
        ArrayAdapter<Contact> adapter=new ContactAdapter(this,contactList.getContacts());
        setListAdapter(adapter);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
    	super.onListItemClick(l, v, position, id);
    	Object o=this.getListAdapter().getItem(position);
    	Contact c=(Contact)o;
    	Toast.makeText(this, c.getDisplayName(), Toast.LENGTH_SHORT).show();
    }
    
    private ContactList getContacts()
    {
    	ContactList contactList=new ContactList();
    	Uri uri=ContactsContract.Contacts.CONTENT_URI;
    	ContentResolver cr=getContentResolver();
    	String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
    	Cursor cur=cr.query(uri, null, null, null, sortOrder);
    	if(cur.getCount()>0)
    	{
    		String id;
    		String name;
    		while(cur.moveToNext())
    		{
    			Contact c =new Contact();
    			id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
    			name=cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
    			c.setId(id);
    			c.setDisplayName(name);
    			contactList.addContact(c);
    		}
    	}
    	cur.close();
    	return contactList;
    }
}