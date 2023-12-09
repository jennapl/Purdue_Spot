package org.smart_laboratory.desktop.purdue_spot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "spot.db";
    private static final int DB_VERSION = 1;

    // Table
    public static final String TABLE_NAME = "spotTbl";
    // Columns
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

    public void queryData(TextView qId, TextView qName, TextView qPrint, TextView qSound){
        SQLiteDatabase db = this.getWritableDatabase();

        //GET VALUES
        myDBModel fa = new myDBModel();
        String vPrint = fa.getPrint();
        String vSound = fa.getSound();

        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM "
                + TABLE_NAME
                + " WHERE " + PRINTING_COL + " = " + "'" + vPrint + "'"
                + " AND "
                + SOUND_COL + " = " + "'" + vSound + "'"
                +";", null);

        String strIDs = "ID" + "\r\n" + "--------" + "\r\n";
        String strName = "Name" + "\r\n" + "--------" + "\r\n";
        String strPrint = "Print" + "\r\n" + "--------" + "\r\n";
        String strSound = "Sound" + "\r\n" + "--------" + "\r\n";
        while (cursor.moveToNext()) {
            strIDs += cursor.getString(0) + "\r\n";
            strName += cursor.getString(1) + "\r\n";
            strPrint += cursor.getString(2) + "\r\n";
            strSound += cursor.getString(3) + "\r\n";
        }
        qId.setText(strIDs);
        qName.setText(strName);
        qPrint.setText(strPrint);
        qSound.setText(strSound);

        cursor.close();
        db.close();
    }
}
