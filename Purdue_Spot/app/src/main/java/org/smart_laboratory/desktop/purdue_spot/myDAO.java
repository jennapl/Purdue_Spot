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
    private String qSound, qPrint, qLocation, qLight, qCrowd;

    public myDAO(Context context) {
        dbHelper = new DBHelper(context);
    }
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
        qLocation = fc.getSelectedLocation();
        qLight = fc.getSelectedLight();
        qCrowd = fc.getSelectedCrowd();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME + " WHERE " + DBHelper.PRINTING_COL
                + " = '" + qPrint + "' AND "
                + DBHelper.SOUND_COL + " = '" + qSound + "' AND "
                + DBHelper.LOCATION_COL + " = '" + qLocation + "' AND "
                + DBHelper.LIGHTING_COL + " = '" + qLight //+ "' AND "
                //+ DBHelper.CROWD_COL + " = '" + qCrowd
                + "'", null);

        if (cursor.moveToFirst()) {
            do {
                myDBModel dbModel = new myDBModel();
                dbModel.setId(cursor.getString(cursor.getColumnIndex(DBHelper.ID_COL)));
                dbModel.setName(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COL)));
                dbModel.setPrint(cursor.getString(cursor.getColumnIndex(DBHelper.PRINTING_COL)));
                dbModel.setSound(cursor.getString(cursor.getColumnIndex(DBHelper.SOUND_COL)));
                dbModel.setLocation(cursor.getString(cursor.getColumnIndex(DBHelper.LOCATION_COL)));
                dbModel.setLight(cursor.getString(cursor.getColumnIndex(DBHelper.LIGHTING_COL)));
                dbModel.setCrowd(cursor.getString(cursor.getColumnIndex(DBHelper.CROWD_COL)));

                dataList.add(dbModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dataList;
    }
}
