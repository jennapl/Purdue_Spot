package org.smart_laboratory.desktop.purdue_spot;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/*
myDOA
This class is used to get all of the users filters, run a query using them, then
populate a list with the all of the data to be shown in the adapter
for the recycle viewer.
*/
public class myDAO {
    private DBHelper dbHelper;
    private String qSound, qPrint, qLocation, qLight, qCrowd, qFood, qComp, qOpen;

    public myDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    // getAllData - gets all data that matches user query and outputs into list for adapter
    public List<myDBModel> getAllData() {
        List<myDBModel> dataList = new ArrayList<>();
        FiltersClass fc = new FiltersClass();

        // Get Filters
        qSound = fc.getSelectedQuiet();
        qPrint =  fc.getSelectedPrint();
        qLocation = fc.getSelectedLocation();
        qLight = fc.getSelectedLight();
        qCrowd = fc.getSelectedCrowd();
        qFood = fc.getSelectedFood();
        qOpen = fc.getSelectedOpen();
        qComp = fc.getSelectedComp();

        //Query using filters
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME + " WHERE " + DBHelper.PRINTING_COL
                + " = '" + qPrint + "' AND "
                + DBHelper.SOUND_COL + " = '" + qSound + "' AND "
                + DBHelper.LOCATION_COL + " = '" + qLocation + "' AND "
                + DBHelper.LIGHTING_COL + " = '" + qLight + "' AND "
                + DBHelper.CROWD_COL + " = '" + qCrowd + "' AND "
                + DBHelper.FOOD_COL + " = '" + qFood + "' AND "
                + DBHelper.ALWAYS_OPEN_COL + " = '" + qOpen + "' AND "
                + DBHelper.COMP_COL + " = '" + qComp
                + "'", null);

        //Add values into dbModel and then add into list
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
                dbModel.setFood(cursor.getString(cursor.getColumnIndex(DBHelper.FOOD_COL)));
                dbModel.setComp(cursor.getString(cursor.getColumnIndex(DBHelper.COMP_COL)));
                dbModel.setOpen(cursor.getString(cursor.getColumnIndex(DBHelper.ALWAYS_OPEN_COL)));
                dbModel.setPath(cursor.getString(cursor.getColumnIndex(DBHelper.PIC_COL)));
                // DBModel into list
                dataList.add(dbModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // Return list of values
        return dataList;
    }
}
