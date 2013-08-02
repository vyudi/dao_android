package com.example.testedatabase.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "agenda";
	
	public static int DATABASE_VERSION = 1;
	
	public static class Contato {
		public static final String TABLE_NAME = "contato";
		public static final String _ID = "_id";
		public static final String NOME = "nome";
		public static final String[] ALL_COLUMNS = {_ID, NOME};
	}
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL("CREATE TABLE " + Contato.TABLE_NAME + " (" + 
				Contato._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				Contato.NOME + " TEXT NOT NULL)");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		database.execSQL("drop table " + Contato.TABLE_NAME);
	}
}
