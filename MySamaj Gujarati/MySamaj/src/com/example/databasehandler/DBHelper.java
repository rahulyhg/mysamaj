/*
 * Copyright 2010 C. Enrique Ortiz | http://CEnriqueOrtiz.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.databasehandler;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.model.SpinerItem;

public class DBHelper extends SQLiteOpenHelper {
	private SQLiteDatabase db;
	private static final int DATABASE_VERSION = 1;
	private static final String DB_NAME = "spinnerMaster.db";
	private static final String Table_villageMaster = "villageMaster";// "friends";
	private static final String Table_relationMaster = "relationMaster";
	private static final String Table_maritalMaster = "maritalMaster";
	private static final String Table_educationMaster = "educationMaster";
	private static final String Table_jobMaster = "jobMaster";

	/**
	 * Constructor
	 * 
	 * @param context
	 *            the application context
	 */

	public DBHelper(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);
		db = getWritableDatabase();
	}

	/**
	 * Called at the time to create the DB. The create DB statement
	 * 
	 * @param the
	 *            SQLite DB
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		try {

			db.execSQL("create table " + Table_villageMaster
					+ " (_id integer primary key autoincrement, "
					+ " fid text not null, name text not null) ");
			db.execSQL("create table " + Table_relationMaster
					+ " (_id integer primary key autoincrement, "
					+ " rid text not null, rname text not null) ");
			db.execSQL("create table " + Table_maritalMaster
					+ " (_id integer primary key autoincrement, "
					+ " mid text not null, mname text not null) ");
			db.execSQL("create table " + Table_educationMaster
					+ " (_id integer primary key autoincrement, "
					+ " eid text not null, ename text not null) ");
			db.execSQL("create table " + Table_jobMaster
					+ " (_id integer primary key autoincrement, "
					+ " jid text not null, jname text not null) ");
		} catch (SQLException e) {
			Log.v("Db Create Error", e.toString());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Here add any steps needed due to version upgrade
		// for example, data format conversions, old tables no longer needed,
		// etc
		db.execSQL("DROP TABLE IF EXISTS " + Table_villageMaster);
		db.execSQL("DROP TABLE IF EXISTS " + Table_relationMaster);
		db.execSQL("DROP TABLE IF EXISTS " + Table_maritalMaster);
		db.execSQL("DROP TABLE IF EXISTS " + Table_educationMaster);
		db.execSQL("DROP TABLE IF EXISTS " + Table_jobMaster);
		onCreate(db);
	}

	/**
	 * The Insert DB statement
	 * 
	 * @param id
	 *            the friends id to insert
	 * @param name
	 *            the friend's name to insert
	 */
	public void insertVillage(String id, String name) {
		db.execSQL("INSERT INTO " + Table_villageMaster
				+ "('fid', 'name') values ('" + id + "', '" + name + "')");
	}

	public void insertRelation(String id, String name) {
		try {
			if (name.contains("'")) {
				name = name.replaceAll("'", "''");
			}
			db.execSQL("INSERT INTO " + Table_relationMaster
					+ "('rid', 'rname') values ('" + id + "', '" + name + "')");
		} catch (SQLException e) {
			Log.v("insert Error", e.toString());
		}

	}

	public void insertMaritalStatus(String id, String name) {
		try {

			if (name.contains("'")) {
				name = name.replaceAll("'", "''");
			}
			db.execSQL("INSERT INTO " + Table_maritalMaster
					+ "('mid', 'mname') values ('" + id + "', '" + name + "')");
		} catch (SQLException e) {
			Log.v("insert Error", e.toString());
		}
	}

	public void insertEducation(String id, String name) {
		try {
			if (name.contains("'")) {
				name = name.replaceAll("'", "''");
			}
			db.execSQL("INSERT INTO " + Table_educationMaster
					+ "('eid', 'ename') values ('" + id + "', '" + name + "')");
		} catch (SQLException e) {
			Log.v("insert Error", e.toString());
		}

	}

	public void insertJob(String id, String name) {
		try {
			if (name.contains("'")) {
				name = name.replaceAll("'", "''");
			}
			db.execSQL("INSERT INTO " + Table_jobMaster
					+ "('jid', 'jname') values ('" + id + "', '" + name + "')");
		} catch (SQLException e) {
			Log.v("insert Error", e.toString());
		}

	}

	/**
	 * Wipe out the DB
	 */
	public void clearAll() {
		db.delete(Table_villageMaster, null, null);
		db.delete(Table_relationMaster, null, null);
		db.delete(Table_maritalMaster, null, null);
		db.delete(Table_educationMaster, null, null);
		db.delete(Table_jobMaster, null, null);

	}

	/**
	 * Select All the returns a Cursor
	 * 
	 * @return the cursor for the DB selection
	 */
	public Cursor cursorSelectAllVillage() {
		Cursor cursor = this.db.query(Table_villageMaster, // Table Name
				new String[] { "fid", "name" }, // Columns to return
				null, // SQL WHERE
				null, // Selection Args
				null, // SQL GROUP BY
				null, // SQL HAVING
				"name"); // SQL ORDER BY
		return cursor;
	}

	public Cursor cursorSelectAllRelation() {
		Cursor cursor = this.db.query(Table_relationMaster, // Table Name
				new String[] { "rid", "rname" }, // Columns to return
				null, // SQL WHERE
				null, // Selection Args
				null, // SQL GROUP BY
				null, // SQL HAVING
				"rname"); // SQL ORDER BY
		return cursor;
	}

	public Cursor cursorSelectAllMarital() {
		Cursor cursor = this.db.query(Table_maritalMaster, // Table Name
				new String[] { "mid", "mname" }, // Columns to return
				null, // SQL WHERE
				null, // Selection Args
				null, // SQL GROUP BY
				null, // SQL HAVING
				"mname"); // SQL ORDER BY
		return cursor;
	}

	public Cursor cursorSelectAllEducation() {
		Cursor cursor = this.db.query(Table_educationMaster, // Table Name
				new String[] { "eid", "ename" }, // Columns to return
				null, // SQL WHERE
				null, // Selection Args
				null, // SQL GROUP BY
				null, // SQL HAVING
				"ename"); // SQL ORDER BY
		return cursor;
	}

	public Cursor cursorSelectAllJob() {
		Cursor cursor = this.db.query(Table_jobMaster, // Table Name
				new String[] { "jid", "jname" }, // Columns to return
				null, // SQL WHERE
				null, // Selection Args
				null, // SQL GROUP BY
				null, // SQL HAVING
				"jname"); // SQL ORDER BY
		return cursor;
	}

	/**
	 * Select All that returns an ArrayList
	 * 
	 * @return the ArrayList for the DB selection
	 */
	public ArrayList<SpinerItem> listSelectAllVillage() {
		ArrayList<SpinerItem> list = new ArrayList<SpinerItem>();
		Cursor cursor = this.db.query(Table_villageMaster, new String[] {
				"fid", "name" }, null, null, null, null, "name");
		if (cursor.moveToFirst()) {
			do {
				SpinerItem f = new SpinerItem();
				f.spinerId = cursor.getString(0);
				f.spinerName = cursor.getString(1);
				list.add(f);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return list;
	}

	public ArrayList<SpinerItem> listSelectAllRelation() {
		ArrayList<SpinerItem> list = new ArrayList<SpinerItem>();
		Cursor cursor = this.db.query(Table_relationMaster, new String[] {
				"rid", "rname" }, null, null, null, null, "rname");
		if (cursor.moveToFirst()) {
			do {
				SpinerItem f = new SpinerItem();
				f.spinerId = cursor.getString(0);
				f.spinerName = cursor.getString(1);
				list.add(f);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return list;
	}

	public ArrayList<SpinerItem> listSelectAllMarital() {
		ArrayList<SpinerItem> list = new ArrayList<SpinerItem>();
		Cursor cursor = this.db.query(Table_maritalMaster, new String[] {
				"mid", "mname" }, null, null, null, null, "mname");
		if (cursor.moveToFirst()) {
			do {
				SpinerItem f = new SpinerItem();
				f.spinerId = cursor.getString(0);
				f.spinerName = cursor.getString(1);
				list.add(f);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return list;
	}

	public ArrayList<SpinerItem> listSelectAllEducation() {
		ArrayList<SpinerItem> list = new ArrayList<SpinerItem>();
		Cursor cursor = this.db.query(Table_educationMaster, new String[] {
				"eid", "ename" }, null, null, null, null, "ename");
		if (cursor.moveToFirst()) {
			do {
				SpinerItem f = new SpinerItem();
				f.spinerId = cursor.getString(0);
				f.spinerName = cursor.getString(1);
				list.add(f);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return list;
	}

	public ArrayList<SpinerItem> listSelectAllJob() {
		ArrayList<SpinerItem> list = new ArrayList<SpinerItem>();
		Cursor cursor = this.db.query(Table_jobMaster, new String[] { "jid",
				"jname" }, null, null, null, null, "jname");
		if (cursor.moveToFirst()) {
			do {
				SpinerItem f = new SpinerItem();
				f.spinerId = cursor.getString(0);
				f.spinerName = cursor.getString(1);
				list.add(f);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return list;
	}

	/**
	 * Invoked if a DB upgrade (version change) has been detected
	 */
	// closing database
	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}
}
