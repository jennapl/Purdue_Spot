package org.smart_laboratory.desktop.purdue_spot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class myDAO {
    private DBHelper dbHelper;
    private String qSound, qPrint;

    public myDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    //EVENTUALLY DELETE - Used to insert data in DB
    public long insertData(myDBModel data) {
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

    public List<myDBModel> getAllData() {
        List<myDBModel> dataList = new ArrayList<>();
        FiltersClass fc = new FiltersClass();

        qSound = fc.getSelectedSound();
        qPrint =  fc.getSelectedPrint();

        //int ageNum = 30;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME + " WHERE " + DBHelper.PRINTING_COL
                + " = '" + qPrint +"' AND "
                + DBHelper.SOUND_COL + " = '"
                + qSound + "'", null);
        //Cursor cursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME , null);

        if (cursor.moveToFirst()) {
            do {
                myDBModel dbModel = new myDBModel();
                dbModel.setId(cursor.getString(cursor.getColumnIndex(DBHelper.ID_COL)));
                dbModel.setName(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COL)));
                dbModel.setPrint(cursor.getString(cursor.getColumnIndex(DBHelper.PRINTING_COL)));
                dbModel.setSound(cursor.getString(cursor.getColumnIndex(DBHelper.SOUND_COL)));

                dataList.add(dbModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dataList;
    }
}
