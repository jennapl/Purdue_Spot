package org.smart_laboratory.desktop.purdue_spot;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "spot.db";
    private static final int DB_VERSION = 1;

    // Table
    private static final String TABLE_NAME = "spotTbl";
    // Columns
    private static final String ID_COL = "spotID";
    private static final String NAME_COL = "spotName";
    private static final String PRINTING_COL = "spotPrinting";
    private static final String SOUND_COL = "spotSoundLevel";

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
}
