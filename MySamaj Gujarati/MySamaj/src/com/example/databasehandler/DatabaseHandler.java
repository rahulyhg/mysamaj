package com.example.databasehandler;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.model.PersonDetailsItem;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	private SQLiteDatabase db;
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "contactsManager";

	// Contacts table name
	private static final String TABLE_CONTACTS = "contacts";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_PH_NO = "phone_number";
	private static final String KEY_EMAIL = "email";
	private final ArrayList<PersonDetailsItem> contact_list = new ArrayList<PersonDetailsItem>();

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		db = getWritableDatabase();
		// context.deleteDatabase(DATABASE_NAME);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		/*
		 * String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
		 * + KEY_ID + " INTEGER PRIMARY KEY," + "intFamilyNo" + " TEXT," +
		 * KEY_PH_NO + " TEXT," + KEY_EMAIL + " TEXT" + ")";
		 */
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + "intFamilyNo" + " TEXT,"
				+ "personId" + " TEXT," + "intIsFamilyHead" + " TEXT,"
				+ "strSurnameEN" + " TEXT," + "strNameEN" + " TEXT,"
				+ "strFatherNameEN" + " TEXT," + "strSurnameGJ" + " TEXT,"
				+ "strNameGJ" + " TEXT," + "strFatherNameGJ" + " TEXT,"
				+ "strJobDetailGJ" + " TEXT," + "intAge" + " TEXT,"
				+ "intGender" + " TEXT," + "intAddressID" + " TEXT,"
				+ "intRelationID" + " TEXT," + "intMaritalStatus" + " TEXT,"
				+ "intShakhID" + " TEXT," + "intWardID" + " TEXT,"
				+ "intMosalEN" + " TEXT," + "strMosalOtherEN" + " TEXT,"
				+ "strEducationEN" + " TEXT," + "intEducationEN" + " TEXT,"
				+ "intSem" + " TEXT," + "intJobEN" + " TEXT,"
				+ "strJobDetailEN" + " TEXT," + "strMobile" + " TEXT,"
				+ "strMobile2" + " TEXT," + "strFbLink" + " TEXT,"
				+ "dtBirthDate" + " TEXT," + "strEmailid" + " TEXT,"
				+ "strProfileImage" + " TEXT," + "intVillageID" + " TEXT" + ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void Add_Contact(PersonDetailsItem personItem) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("intFamilyNo", personItem.getIntFamilyNo());
		values.put("personId", personItem.getPersonId());
		values.put("intIsFamilyHead", personItem.getIntIsFamilyHead());
		values.put("strSurnameEN", personItem.getStrSurnameEN());
		values.put("strNameEN", personItem.getStrNameEN());
		values.put("strFatherNameEN", personItem.getStrFatherNameEN());
		values.put("strSurnameGJ", personItem.getStrSurnameGJ());
		values.put("strNameGJ", personItem.getStrNameGJ());
		values.put("strFatherNameGJ", personItem.getStrFatherNameGJ());
		values.put("strJobDetailGJ", personItem.getStrJobDetailGJ());
		values.put("intAge", personItem.getIntAge());
		values.put("intGender", personItem.getIntGender());
		values.put("intAddressID", personItem.getIntAddressID());
		values.put("intRelationID", personItem.getIntRelationID());
		values.put("intMaritalStatus", personItem.getIntMaritalStatus());
		values.put("intShakhID", personItem.getIntShakhID());
		values.put("intWardID", personItem.getIntWardID());
		values.put("intMosalEN", personItem.getIntMosalEN());
		values.put("strMosalOtherEN", personItem.getstrMosalOtherGJ());
		values.put("strEducationEN", personItem.getStrEducationEN());
		values.put("intEducationEN", personItem.getIntEducationEN());
		values.put("intSem", personItem.getIntSem());
		values.put("intJobEN", personItem.getIntJobEN());
		values.put("strJobDetailEN", personItem.getStrJobDetailEN());
		values.put("strMobile", personItem.getStrMobile());
		values.put("strMobile2", personItem.getStrMobile2());
		values.put("strFbLink", personItem.getStrFbLink());
		values.put("dtBirthDate", personItem.getDtBirthDate());
		values.put("strEmailid", personItem.getStrEmailid());
		values.put("strProfileImage", personItem.getStrProfileImage());
		values.put("intVillageID", personItem.getIntVillageID());
		// Inserting Row
		db.insert(TABLE_CONTACTS, null, values);
		db.close(); // Closing database connection
	}

	// Getting single contact
	PersonDetailsItem Get_Contact(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
				"strNameEN", "strMobile", "strEmailid" }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		PersonDetailsItem contact = new PersonDetailsItem(
				Integer.parseInt(cursor.getString(0)), cursor.getString(1),
				cursor.getString(2), cursor.getString(3));
		// return contact
		cursor.close();
		db.close();

		return contact;
	}

	// Getting All Contacts
	public ArrayList<PersonDetailsItem> Get_Contacts() {
		try {
			contact_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					PersonDetailsItem contact = new PersonDetailsItem();
					contact.setId(Integer.parseInt(cursor.getString(0)));
					contact.setIntFamilyNo(cursor.getString(1));
					contact.setPersonId((cursor.getString(2)));
					contact.setIntIsFamilyHead((cursor.getString(3)));
					contact.setStrSurnameEN((cursor.getString(4)));
					contact.setStrNameEN((cursor.getString(5)));
					contact.setStrFatherNameEN((cursor.getString(6)));
					contact.setStrSurnameGJ((cursor.getString(7)));
					contact.setStrNameGJ((cursor.getString(8)));
					contact.setStrFatherNameGJ((cursor.getString(9)));
					contact.setStrJobDetailGJ((cursor.getString(10)));
					contact.setIntAge((cursor.getString(11)));
					contact.setIntGender((cursor.getString(12)));
					contact.setIntAddressID((cursor.getString(13)));
					contact.setIntRelationID((cursor.getString(14)));
					contact.setIntMaritalStatus((cursor.getString(15)));
					contact.setIntShakhID((cursor.getString(16)));
					contact.setIntWardID((cursor.getString(17)));
					contact.setIntMosalEN((cursor.getString(18)));
					contact.setstrMosalOtherGJ((cursor.getString(19)));
					contact.setStrEducationEN((cursor.getString(20)));
					contact.setIntEducationEN((cursor.getString(21)));
					contact.setIntSem((cursor.getString(22)));
					contact.setIntJobEN((cursor.getString(23)));
					contact.setStrJobDetailEN((cursor.getString(24)));
					contact.setStrMobile((cursor.getString(25)));
					contact.setStrMobile2((cursor.getString(26)));
					contact.setStrFbLink((cursor.getString(27)));
					contact.setDtBirthDate((cursor.getString(28)));
					contact.setStrEmailid((cursor.getString(29)));
					contact.setStrProfileImage((cursor.getString(30)));
					contact.setIntVillageID((cursor.getString(31)));
					// Adding contact to list
					contact_list.add(contact);
				} while (cursor.moveToNext());
			}

			// return contact list
			cursor.close();
			db.close();
			return contact_list;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("all_contact", "" + e);
		}

		return contact_list;
	}

	public void clearAll() {
		db.delete(TABLE_CONTACTS, null, null);

	}

	/*
	 * // Updating single contact public int Update_Contact(Contact contact) {
	 * SQLiteDatabase db = this.getWritableDatabase();
	 * 
	 * ContentValues values = new ContentValues(); values.put(KEY_NAME,
	 * contact.getName()); values.put(KEY_PH_NO, contact.getPhoneNumber());
	 * values.put(KEY_EMAIL, contact.getEmail());
	 * 
	 * // updating row return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
	 * new String[] { String.valueOf(contact.getID()) }); }
	 */

	// Deleting single contact
	public void Delete_Contact(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
				new String[] { String.valueOf(id) });
		db.close();
	}

	// Getting contacts Count
	public int Get_Total_Contacts() {
		String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

}
