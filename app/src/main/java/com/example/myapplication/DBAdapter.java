package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

	public static final String Id = "_id";
	public static final String Classname = "classname";
	public static final String Address = "address";
	public static final String TeacherName = "teachername";

	private static final String DATABASE_NAME = "kechengbiao.db";
	private static final String DATABASE_TABLE = "even";
	private static final String TAG = "DBAdapter";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE = "create table even (_id integer not null primary key autoincrement,myid integer ,classname text ,	address text ,tel text ,teachername text,weekday text ,beginweek text ,endweek text)";

	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context) 
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) 
		{
			db.execSQL(DATABASE_CREATE);
			Log.i("TAG", "create database--------");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS titles");
			onCreate(db);
		}
	}


	public DBAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		DBHelper.close();
	}

	public long inserttable(int myid, String classname, String address,
			String tel, String teachername, String weekday, String beginweek,
			String endweek) {
		ContentValues cv = new ContentValues();
		cv.put("myid", myid);
		cv.put("classname", classname);
		cv.put("address", address);
		cv.put("tel", tel);
		cv.put("teachername", teachername);
		cv.put("weekday", weekday);
		cv.put("beginweek", beginweek);
		cv.put("endweek", endweek);
		return db.insert(DATABASE_TABLE, null, cv);
	}

	public boolean deleteTitle(long id) {
		return db.delete(DATABASE_TABLE, Id + "=" + id, null) > 0;
	}

	public Cursor getAllTitles() {
		return db.query(DATABASE_TABLE,
				new String[] { "myid", "classname", "address", "teachername",
				"tel", "weekday", "beginweek", "endweek" }, null, null,
				null, null, null);
	}



	public boolean updateTitle(int id, int myid, String classname,
			String address, String tel, String teachername, String weekday,
			String beginweek, String endweek) {
		ContentValues cv = new ContentValues();
		cv.put("myid", myid);
		cv.put("classname", classname);
		cv.put("address", address);
		cv.put("tel", tel);
		cv.put("teachername", teachername);
		cv.put("weekday", weekday);
		cv.put("beginweek", beginweek);
		cv.put("endweek", endweek);
		return db.update(DATABASE_TABLE, cv, Id + "=" + id, null) > 0;
	}
}
