package com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ipData {
	private static final String DATABASE_NAME="datastorage1";
	private static final int DATABASE_VERSION=1;
	private static final String TABLE_NAME="ip";
	private static final String ID="_id";
	private static final String PHONE_NUMBER="ipline";
	private DBOpenHelper helper;
	private SQLiteDatabase db;
	
	private static class DBOpenHelper extends SQLiteOpenHelper{

		private static final String CREATE_TABLE="create table if not exists "+TABLE_NAME+"("+ID
				+" integer primary key autoincrement,"+PHONE_NUMBER+" text not null);";
		
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
			db.execSQL("drop table if exists"+TABLE_NAME);
			onCreate(db);
		}
	}
	public ipData(Context context){
		helper=new DBOpenHelper(context);
		db=helper.getWritableDatabase();	//获得可写数据库
	}
	public void insert(ipUser user)
	{
		
		ContentValues values=new ContentValues();
		values.put(PHONE_NUMBER,user.getNumber());
		db.insert(TABLE_NAME, null, values);
	}
	public void update(ipUser user)
	{
		Cursor cursor=db.query(TABLE_NAME, new String[] {PHONE_NUMBER},"1",null,null,null,null);
		if(cursor.getCount()<=0){
			insert(user);
		}
		else
		{
			ContentValues values=new ContentValues();
			values.put(PHONE_NUMBER,user.getNumber());
			db.update(TABLE_NAME, values, "_id=?", new String[] {"1"});
		}
			
	}
	public ipUser query(int id){
		ipUser user=new ipUser();
		Cursor cursor=db.query(TABLE_NAME, new String[] {PHONE_NUMBER},"_id="+id,null,null,null,null);
		if(cursor.getCount()>0){
			cursor.moveToFirst();
			user.setNumber(cursor.getString(0));
			return user;
		}
		cursor.close();
		return null;
	}

}
