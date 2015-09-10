package com.example.databasehandler;

import java.util.ArrayList;

import com.example.model.PeopleDetailsItem;
import com.example.model.PersonDetailsItem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseTable {
	private static final String TAG = "DictionaryDatabase";

	// The columns we'll include in the dictionary table
	// public static final String COL_WORD = "WORD";
	// public static final String COL_DEFINITION = "DEFINITION";
	private static final String KEY_ID = "id";
	public static final String intIsFamilyHead = "intIsFamilyHead";
	public static final String personId = "personId";
	// public static final String intFamilyNo = "intFamilyNo";
	public static final String NameEN = "NameEN";
	public static final String AGE = "AGE";
	public static final String Gender = "Gender";
	public static final String AddressEN = "AddressEN";
	public static final String RelationEN = "RelationEN";
	public static final String MaritalStatusEN = "MaritalStatusEN";
	public static final String ShakhEN = "ShakhEN";
	public static final String MosalEN = "MosalEN";
	public static final String strEducationEN = "strEducationEN";
	public static final String JobDetailEN = "JobDetailEN";
	public static final String Mobile = "Mobile";
	public static final String Mobile2 = "Mobile2";
	public static final String strEmialID = "strEmialID";
	public static final String member = "member";

	private static final String DATABASE_NAME = "DICTIONARY";
	private static final String DICTIONARY_TABLE = "DICTIONARY_TABLE";
	private static final int DATABASE_VERSION = 1;

	private final DatabaseOpenHelper mDatabaseOpenHelper;
	private final ArrayList<PeopleDetailsItem> dictionary_list = new ArrayList<PeopleDetailsItem>();

	public DatabaseTable(Context context) {
		mDatabaseOpenHelper = new DatabaseOpenHelper(context);
	}

	private static class DatabaseOpenHelper extends SQLiteOpenHelper {

		private final Context mHelperContext;
		private SQLiteDatabase mDatabase;

		private static final String DICTIONARY_TABLE_CREATE = "CREATE TABLE "
				+ DICTIONARY_TABLE + "(" + KEY_ID + "INTEGER PRIMARY KEY,"
				+ personId + "TEXT, " + NameEN + "TEXT, " + AGE + "TEXT,"
				+ Gender + "TEXT," + AddressEN + "TEXT," + RelationEN + "TEXT,"
				+ MaritalStatusEN + "TEXT," + ShakhEN + "TEXT," + MosalEN
				+ "TEXT," + strEducationEN + "TEXT," + JobDetailEN + "TEXT,"
				+ Mobile + "TEXT," + Mobile2 + "TEXT," + strEmialID + "TEXT"
				+ ")";

		DatabaseOpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			mHelperContext = context;
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			mDatabase = db;
			mDatabase.execSQL(DICTIONARY_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + DICTIONARY_TABLE);
			onCreate(db);
		}

		public void Add_Contact(PeopleDetailsItem peopleItem) {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(NameEN, peopleItem.getNameEN()); // Contact Name
			values.put(AGE, peopleItem.getAGE());
			values.put(Gender, peopleItem.getGender());
			values.put(AddressEN, peopleItem.getAddressEN());
			values.put(RelationEN, peopleItem.getRelationEN());
			values.put(MaritalStatusEN, peopleItem.getMaritalStatusEN());
			values.put(ShakhEN, peopleItem.getShakhEN());
			values.put(MosalEN, peopleItem.getMosalEN());
			values.put(strEducationEN, peopleItem.getStrEducationEN());
			values.put(JobDetailEN, peopleItem.getJobDetailEN());
			values.put(Mobile, peopleItem.getMobile());
			values.put(Mobile2, peopleItem.getMobile2());
			values.put(strEmialID, peopleItem.getStrEmialID());
			// Inserting Row
			db.insert(DICTIONARY_TABLE, null, values);
			db.close(); // Closing database connection
		}
	}
}
