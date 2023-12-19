package com.papitomarket.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GlobalData extends SQLiteOpenHelper{

	
	public static String selectedCompany = "";
	
	
	public static final String TABLE_USER = "users";
    public static final String COLUMN_ID = "user_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_LOCALE = "locale";
    public static final String COLUMN_LINK = "link";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_BIRTHDAY = "birthday"; 
    public static final String COLUMN_ADDR_FK = "addr_fk";
    public static final String COLUMN_STATUS = "status";
    
	  private static final String TABLE_USER_CREATE = "create table "
          + TABLE_USER + "(" +
			  COLUMN_ID + " integer primary key autoincrement, " +
              COLUMN_USERNAME + " text not null," + 
	          COLUMN_NAME + " varchar(255)," +
              COLUMN_FIRST_NAME + " varchar(255)," + 
	          COLUMN_LAST_NAME + " varchar(255)," +
              COLUMN_LOCALE + " varchar(255)," +
	          COLUMN_LINK + " varchar(1024)," +
	          COLUMN_EMAIL + " varchar(1024)," +
	          COLUMN_GENDER + " integer(1)," +
	          COLUMN_BIRTHDAY + " TEXT," + 
	          COLUMN_ADDR_FK + " varchar(1024)," +
	          COLUMN_STATUS + " varchar(10)" +	      
	      ");";
	  
	  
    
    
    
    public static final String TABLE_PHONE = "phone";
    public static final String COLUMN_PHONE_ID = "phone_id";    
    public static final String COLUMN_SCRWIDTH = "scrwidth";
    public static final String COLUMN_SCRHEIGHT = "scrheight";
	
    private static final String TABLE_PHONE_CREATE = "create table " + TABLE_PHONE + "(" +
    COLUMN_PHONE_ID + " integer primary key autoincrement," +
    COLUMN_SCRWIDTH + " INTEGER," +
    COLUMN_SCRHEIGHT + " INTEGER);";
    
    
    
    
    
	public static final String TABLE_TAGS = "tags";  // Tags the user is subscribed
	public static final String TAG_ID = "tag_id";
	public static final String TAG_NAME= "tag_name";
	
	private static final String TABLE_TAGS_CREATE = "create table " + TABLE_TAGS + "(" +
	TAG_ID + " integer primary key autoincrement," +
	TAG_NAME + " TEXT);";
	
	
	
	
	
	
	public static final String TABLE_ADDR =  "addrs";
	public static final String COLUMN_ADDR_ID = "addr_id";
	public static final String COLUMN_LAT = "lat";
	public static final String COLUMN_LNG = "lng";
	public static final String COLUMN_ADDR = "addr";
	public static final String COLUMN_HEIGHT = "height";
	
	private static final String TABLE_ADDR_CREATE = "create table " + TABLE_ADDR + "(" +
	COLUMN_ADDR_ID + " integer primary key autoincrement," +
	COLUMN_LAT + " FLOAT," + 
	COLUMN_LNG + " FLOAT," +
	COLUMN_ADDR + " TEXT," + 
	COLUMN_HEIGHT + " FLOAT);";
	
	
	
	
	
	
	
	
	public static final String TABLE_CART = "cart";  // set of orders the user does
	public static final String COLUMN_CART_ID = "cart_id";
	public static final String COLUMN_ADDR_FROM = "from_addr";  // Where the items should be delivered
	
	private static final String TABLE_CART_CREATE = "create table " + TABLE_CART + " (" + 
	COLUMN_CART_ID + " integer primary key autoincrement, " + 
	COLUMN_ADDR_FROM + " varchar(2048));";
	
	
	
	
	public static final String TABLE_ORDERS = "orders";
	public static final String COLUMN_ORDER_ID = "order_id";
	public static final String COLUMN_AMOUNT = "amount";
	public static final String COLUMN_DELIVER = "deliver";
	public static final String COLUMN_PRODUCT_FK = "product_fk";
	public static final String COLUMN_PRICE = "price";

	private static final String TABLE_ORDERS_CREATE = "create table " + TABLE_ORDERS + " (" + 
	COLUMN_ORDER_ID + " integer primary key autoincrement," +
	COLUMN_AMOUNT+ " INTEGER," +
	COLUMN_DELIVER + " INTEGER," +
	COLUMN_PRODUCT_FK + " INTEGER," +
	COLUMN_PRICE + " FLOAT);";
	

	
	public static final String TABLE_PRODUCTS = "products";
	public static final String COLUMN_PRODUCT_ID = "product_id";
	public static final String COLUMN_PRODUCT_TITLE = "title";
	public static final String COLUMN_PRODUCT_PRICE = "price";
	public static final String COLUMN_PRODUCT_DELIVER = "deliver";
	public static final String COLUMN_PRODUCT_RATIO = "ratio";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_PRODUCT_NAME = "name";
	public static final String COLUMN_PICS = "pics";
	
	private static final String TABLE_PRODUCTS_CREATE = "create table " + TABLE_PRODUCTS + " (" + 
	COLUMN_PRODUCT_ID + " integer primary key autoincrement," +
	COLUMN_PRODUCT_TITLE + " TEXT," +
	COLUMN_PRODUCT_PRICE + " FLOAT," +
	COLUMN_PRODUCT_DELIVER + " INTEGER," +
	COLUMN_PRODUCT_RATIO + " FLOAT," +
	COLUMN_DESCRIPTION + " TEXT," +
	COLUMN_PRODUCT_NAME + " TEXT);";
	
	
	
	
	public static final String TABLE_CATEGORY = "category";
	public static final String COLUMN_COLLAPSED = "collapsed";
	public static final String COLUMN_CATEGORY_NAME = "name";
	
	private static final String TABLE_CATEGORY_CREATE = "create table " + TABLE_CATEGORY + " (" + 
	COLUMN_COLLAPSED + " integer primary key autoincrement," +
	COLUMN_CATEGORY_NAME + " TEXT);";
	
	
	
	
	public static final String TABLE_STORES = "stores";
	public static final String COLUMN_STORE_ID = "store_id";
	public static final String COLUMN_ADDRESS = "address";
	public static final String COLUMN_COMPANYLOGO = "companylogo";
	public static final String COLUMN_COMPANYNAME = "companyname";
	public static final String COLUMN_STORE_EMAIL = "email";
	public static final String COLUMN_STORE_LAT = "lat";
	public static final String COLUMN_STORE_LNG = "lng";
	public static final String COLUMN_WEBPAGE = "webpage";
	
	private static final String TABLE_STORES_CREATE = "create table " + TABLE_STORES + " (" + 
	COLUMN_STORE_ID + " integer primary key autoincrement," +
	COLUMN_ADDRESS + " TEXT," +
	COLUMN_COMPANYLOGO + " TEXT," +
	COLUMN_COMPANYNAME + " varchar(1024)," +
	COLUMN_STORE_EMAIL + " varchar(2048)," +
	COLUMN_STORE_LAT + " FLOAT," +
	COLUMN_STORE_LNG + " FLOAT," +
	COLUMN_WEBPAGE + " TEXT);"; 

	
	
	
	public static final String DATABASE_NAME = "smartbands.db";
	public static final int DATABASE_VERSION = 1;
	


	
	
	
	
	public GlobalData(Context context) {
		super(context,DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		SQLiteDatabase mDatabase = db;
        mDatabase.execSQL(TABLE_USER_CREATE);
        mDatabase.execSQL(TABLE_PHONE_CREATE);
        mDatabase.execSQL(TABLE_TAGS_CREATE);
        mDatabase.execSQL(TABLE_ADDR_CREATE);
        mDatabase.execSQL(TABLE_CART_CREATE);
        mDatabase.execSQL(TABLE_ORDERS_CREATE);
        mDatabase.execSQL(TABLE_PRODUCTS_CREATE);
        mDatabase.execSQL(TABLE_CATEGORY_CREATE);
        mDatabase.execSQL(TABLE_STORES_CREATE);
	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_CREATE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHONE_CREATE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAGS_CREATE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDR_CREATE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART_CREATE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS_CREATE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS_CREATE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY_CREATE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORES_CREATE);
	    onCreate(db);
	}

}
