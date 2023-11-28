package org.smart_laboratory.desktop.purdue_spot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class myDAO {
    private DBHelper dbHelper;

    public myDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public long insertData(FiltersClass data) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.ID_COL, data.getId());
        values.put(DBHelper.NAME_COL, data.getName());
        values.put(DBHelper.SOUND_COL, data.getSound());
        values.put(DBHelper.PRINTING_COL, data.getPrint());

        long newRowId = db.insert(DBHelper.TABLE_NAME, null, values);

        db.close();
        return newRowId;
    }

    public List<FiltersClass> getAllData() {
        List<FiltersClass> dataList = new ArrayList<>();

        int ageNum = 30;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        /*Cursor cursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME + " WHERE " + DBHelper.COLUMN_AGE
                + " = " + ageNum, null);*/
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME , null);

        if (cursor.moveToFirst()) {
            do {
                FiltersClass fa = new FiltersClass();
                fa.setId(cursor.getString(cursor.getColumnIndex(DBHelper.ID_COL)));
                fa.setName(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COL)));
                fa.setPrint(cursor.getString(cursor.getColumnIndex(DBHelper.PRINTING_COL)));
                fa.setSound(cursor.getString(cursor.getColumnIndex(DBHelper.SOUND_COL)));

                dataList.add(fa);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dataList;
    }
}
