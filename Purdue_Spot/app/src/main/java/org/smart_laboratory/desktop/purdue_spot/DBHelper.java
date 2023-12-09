package org.smart_laboratory.desktop.purdue_spot;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
DBHelper
This code is used to manage our DataBase, it contains:
onCreate code and add values code to populate the table
*/
public class DBHelper extends SQLiteOpenHelper {

    //Defining DB name, TBL name, and Column Names
    private static final String DB_NAME = "spot.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "spotTbl";
    public static final String ID_COL = "spotID";
    public static final String NAME_COL = "spotName";
    public static final String PRINTING_COL = "spotPrinting";
    public static final String SOUND_COL = "spotSoundLevel";
    public static final String LIGHTING_COL = "spotLight";
    public static final String CROWD_COL = "spotCrowd";
    public static final String HOURS_COL = "spotHours";
    public static final String ALWAYS_OPEN_COL = "spotOpen247";
    public static final String STUDY_ROOM_COL = "spotStudyRoom";
    public static final String ADDRESS_COl = "spotStreetAddress1";
    public static final String CITY_COl = "spotCity";
    public static final String STATE_COl = "spotState";
    public static final String ZIP_COL = "spotZip";
    public static final String LOCATION_COL = "spotLocation";
    public static final String FOOD_COL = "spotFood";
    public static final String COMP_COL = "spotComp";
    public static final String PIC_COL = "spotPath";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // onCreate - code to make table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table if not exists " + TABLE_NAME
                + " (" + ID_COL + " char(4), "
                + " " + NAME_COL + " char(50),"
                + " " + PRINTING_COL + " char(50),"
                + " " + SOUND_COL + " char(50),"
                + " " + LIGHTING_COL + " char(50),"
                + " " + CROWD_COL + " char(50),"
                + " " + HOURS_COL + " char(100),"
                + " " + ALWAYS_OPEN_COL + " char(50),"
                + " " + STUDY_ROOM_COL + " char(50),"
                + " " + ADDRESS_COl + " char(100),"
                + " " + CITY_COl + " char(50),"
                + " " + STATE_COl + " char(50),"
                + " " + ZIP_COL + " char(50),"
                + " " + LOCATION_COL + " char(50),"
                + " " + FOOD_COL + " char(50),"
                + " " + COMP_COL + " char(50),"
                + " " + PIC_COL + " char(50));";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // addValues - adds values into table
    // CITATION: https://chat.openai.com/ assited in the put values of this section of the code
    public void addValues(String id, String name, String printing, String sound,
                          String light, String crowd, String hours, String open, String room,
                          String addy, String city, String state, String zip, String location, String food, String comp, String pic){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_COL, id);
        values.put(NAME_COL, name);
        values.put(PRINTING_COL, printing);
        values.put(SOUND_COL, sound);
        values.put(LIGHTING_COL, light);
        values.put(CROWD_COL, crowd);
        values.put(HOURS_COL, hours);
        values.put(ALWAYS_OPEN_COL, open);
        values.put(STUDY_ROOM_COL, room);
        values.put(ADDRESS_COl, addy);
        values.put(CITY_COl, city);
        values.put(STATE_COl, state);
        values.put(ZIP_COL, zip);
        values.put(LOCATION_COL, location);
        values.put(FOOD_COL, food);
        values.put(COMP_COL, comp);
        values.put(PIC_COL, pic);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}
