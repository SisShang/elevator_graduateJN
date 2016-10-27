package com.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Shujuku{
	private static final String DATABASE_NAME="datastorage";
	private static final int DATABASE_VERSION=1;
	private static final String TABLE_NAME="users";
	private static final String ID="_id";
	private static final String REPORT_NUMBER="reportNo";
	private static final String UNIT="unit";
	private static final String ADDRESS="address";
	private static final String CONTACT="contact";
	private static final String PHONE_NUMBER="phoneNo";
	private static final String DEVICE_NUMBER="deviceNo";
	private static final String GOVERNORSPEC="governorSpec";
	private static final String GOVERNOR_NUMBER="governorNo";
	private static final String PRODUCE_TIME="produceTime";
	private static final String PRODUCE_UNIT="produceUnit";
	private static final String DIAMETER="diameter";
	private static final String PERIMETER="perimeter";
	private static final String SPEED="speed";
	private static final String TYPE="type";
	
	private DBOpenHelper helper;
	private SQLiteDatabase db;
	
	private static class DBOpenHelper extends SQLiteOpenHelper{

		private static final String CREATE_TABLE="create table if not exists "+TABLE_NAME+"("+ID
				+" integer primary key autoincrement,"+REPORT_NUMBER+" text,"+UNIT+" text,"+ADDRESS+" text,"+
				CONTACT+" text,"+PHONE_NUMBER+" text,"+ GOVERNORSPEC+" text,"+DEVICE_NUMBER+" text, "+GOVERNOR_NUMBER+" text,"+
				PRODUCE_TIME+" text,"+PRODUCE_UNIT+" text,"+DIAMETER+" text,"+PERIMETER+" text,"+SPEED+" text,"+TYPE+" text)";
		
		public DBOpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(CREATE_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("drop table if exists "+TABLE_NAME);
			onCreate(db);
		}
	}
	public Shujuku(Context context){
		helper=new DBOpenHelper(context);
		db=helper.getWritableDatabase();	//获得可写数据库
	}
	public void insert(User user)
	{
		
		ContentValues values=new ContentValues();
		values.put(ADDRESS,user.getAddress());
		values.put(CONTACT,user.getContact());
		values.put(PHONE_NUMBER,user.getPhoneNo());
		values.put(REPORT_NUMBER,user.getReportNo());
		values.put(UNIT,user.getUnit());
		values.put(DEVICE_NUMBER,user.getDeviceNo());
		values.put(GOVERNORSPEC,user.getGovernorSpec());
		values.put(GOVERNOR_NUMBER,user.getGovernorNo());
		values.put(PRODUCE_TIME,user.getProduceTime());
		values.put(PRODUCE_UNIT,user.getProduceUnit());
		values.put(DIAMETER,user.getGovernorDiameter());
		values.put(PERIMETER,user.getGovernorPerimeter());
		values.put(SPEED,user.getSpeed());
		values.put(TYPE,user.getType());
		db.insert(TABLE_NAME, null, values);
	}
	public void update(User user)
	{
		Cursor cursor=db.query(TABLE_NAME, new String[] {REPORT_NUMBER,UNIT,ADDRESS,CONTACT,PHONE_NUMBER,DEVICE_NUMBER,GOVERNORSPEC,
				GOVERNOR_NUMBER,PRODUCE_TIME,PRODUCE_UNIT,DIAMETER,PERIMETER,SPEED,TYPE},user.getReportNo(),null,null,null,null);
		if(cursor.getCount()<=0){
			insert(user);
		}
		else
		{
			ContentValues values=new ContentValues();
			values.put(ADDRESS,user.getAddress());
			values.put(CONTACT,user.getContact());
			values.put(PHONE_NUMBER,user.getPhoneNo());
			values.put(UNIT,user.getUnit());
			values.put(DEVICE_NUMBER,user.getDeviceNo());
			values.put(GOVERNORSPEC,user.getGovernorSpec());
			values.put(GOVERNOR_NUMBER,user.getGovernorNo());
			values.put(PRODUCE_TIME,user.getProduceTime());
			values.put(PRODUCE_UNIT,user.getProduceUnit());
			values.put(DIAMETER,user.getGovernorDiameter());
			values.put(PERIMETER,user.getGovernorPerimeter());
			values.put(SPEED,user.getSpeed());
			values.put(TYPE,user.getType());
			db.update(TABLE_NAME, values, "reportNo=?", new String[] {user.getReportNo()});
		}
		System.out.println("保存成功");
			
	}
	public User query(String reportNo){
		User user=new User();
		Cursor cursor=db.query(TABLE_NAME, new String[] {REPORT_NUMBER,UNIT,ADDRESS,CONTACT,PHONE_NUMBER,DEVICE_NUMBER,GOVERNORSPEC,
				GOVERNOR_NUMBER,PRODUCE_TIME,PRODUCE_UNIT,DIAMETER,PERIMETER,SPEED},"reportNo="+reportNo,null,null,null,null);
		if(cursor.getCount()>0){
			cursor.moveToFirst();
			user.setReportNo(cursor.getString(0));
			user.setUnit(cursor.getString(1));
			user.setAddress(cursor.getString(2));
			user.setContact(cursor.getString(3));
			user.setPhoneNo(cursor.getString(4));
			user.setDeviceNo(cursor.getString(5));
			user.setGovernorSpec(cursor.getString(6));
			user.setGovernorNo(cursor.getString(7));
			user.setProduceTime(cursor.getString(8));
			user.setProduceUnit(cursor.getString(9));
			user.setGovernorDiameter(cursor.getString(10));
			user.setGovernorPerimeter(cursor.getString(11));
			user.setSpeed(cursor.getString(12));
			user.setType(cursor.getString(13));
			return user;
		}
		cursor.close();
		return null;
	}
	public List<User> findAll() {// 查询所有记录  
        List<User> lists = new ArrayList<User>();  
        User user = null;  
        SQLiteDatabase db1 = helper.getReadableDatabase();   
        Cursor cursor = db1.rawQuery("select * from "+TABLE_NAME, null);  
        while (cursor.moveToNext()) {  
            user = new User();  
			user.setReportNo(cursor.getString(cursor.getColumnIndex(REPORT_NUMBER)));
			user.setUnit(cursor.getString(cursor.getColumnIndex(UNIT)));
			user.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
			user.setContact(cursor.getString(cursor.getColumnIndex(CONTACT)));
			user.setPhoneNo(cursor.getString(cursor.getColumnIndex(PHONE_NUMBER))); 
			user.setDeviceNo(cursor.getString(cursor.getColumnIndex(DEVICE_NUMBER)));
			user.setGovernorSpec(cursor.getString(cursor.getColumnIndex(GOVERNORSPEC)));
			user.setGovernorNo(cursor.getString(cursor.getColumnIndex(GOVERNOR_NUMBER)));
			user.setProduceTime(cursor.getString(cursor.getColumnIndex(PRODUCE_TIME)));
			user.setProduceUnit(cursor.getString(cursor.getColumnIndex(PRODUCE_UNIT)));
			user.setGovernorDiameter(cursor.getString(cursor.getColumnIndex(DIAMETER)));
			user.setGovernorPerimeter(cursor.getString(cursor.getColumnIndex(PERIMETER)));
			user.setSpeed(cursor.getString(cursor.getColumnIndex(SPEED)));
			user.setType(cursor.getString(cursor.getColumnIndex(TYPE)));
            lists.add(user);  
        }  
        db1.close();  
        return lists;  
    } 
	public void delete(String reportNo)
	{
		db.delete(TABLE_NAME, "reportNo="+reportNo, null);
	}
	public void clearTable() {
		// TODO Auto-generated method stub
		db.execSQL("delete from "+TABLE_NAME);
	}
	 public void deleteDatabase(Context context) {  
	     context.deleteDatabase(DATABASE_NAME);  
	     }  

}