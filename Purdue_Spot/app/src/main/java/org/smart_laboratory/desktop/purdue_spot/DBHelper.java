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


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table if not exists " + TABLE_NAME
                + "(spotID char(4), "
                + " spotName char(50), "
                + " spotPrinting char(50), "
                + " spotSoundLevel char(50));";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addValues(String id, String name, String printing, String sound){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_COL, id);
        values.put(NAME_COL, name);
        values.put(PRINTING_COL, printing);
        values.put(SOUND_COL, sound);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public void queryData(TextView qId, TextView qName, TextView qPrint, TextView qSound){
        SQLiteDatabase db = this.getWritableDatabase();

        //GET VALUES
        FiltersClass fa = new FiltersClass();
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
