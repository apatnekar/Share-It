package com.example.shareit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "shareit_db";
	public static final String TABLE = "shareit_table";
	public static final String C_ID = "_id";
	public static final String EVENT = "event";
	public static final String NAME = "name";
	public static final String SPENT = "spent";
	
	private final String createDb = "create table if not exists " + TABLE + "( "
			+ C_ID + " integer primary key autoincrement, "
			+ EVENT + " text, "
			+ NAME + " text, "
			+ SPENT + " text);";
		
	
	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createDb);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("drop table " + TABLE);

	}

}
