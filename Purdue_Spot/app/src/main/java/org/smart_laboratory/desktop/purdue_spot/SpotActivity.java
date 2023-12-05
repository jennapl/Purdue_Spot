package org.smart_laboratory.desktop.purdue_spot;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SpotActivity extends AppCompatActivity {
    TextView mNameTxt, mIdTxt, mSoundTxt, mPrintTxt, mLightTxt, mCrowdTxt, mStudyTxt, mHoursTxt, mOpenTxt, mAddressTxt, mCityTxt, mStateTxt, mZipTxt, mLocationTxt, mFood, mComp;
    Button mBackBtn;
    static String  cSpotId;
    private DBHelper dbHelper;
    LinearLayout privateRoom;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_page);

        mNameTxt = (TextView) findViewById(R.id.sNameTxt);
        mIdTxt = (TextView) findViewById(R.id.sIdTxt);
        mSoundTxt = (TextView) findViewById(R.id.sSoundTxt);
        mPrintTxt = (TextView) findViewById(R.id.sPrintTxt);
        mLightTxt = (TextView) findViewById(R.id.sLightTxt);
        mCrowdTxt = (TextView) findViewById(R.id.sCrowdTxt);
        mStudyTxt = (TextView) findViewById(R.id.sStudyTxt);
        mHoursTxt = (TextView) findViewById(R.id.sHoursTxt);
        mOpenTxt = (TextView) findViewById(R.id.sOpenTxt);
        mAddressTxt = (TextView) findViewById(R.id.sAddressTxt);
        mCityTxt = (TextView) findViewById(R.id.sCityTxt);
        mStateTxt = (TextView) findViewById(R.id.sStateTxt);
        mZipTxt = (TextView) findViewById(R.id.sZipTxt);
        mLocationTxt = (TextView) findViewById(R.id.sLocationTxt);
        privateRoom = (LinearLayout) findViewById(R.id.privateLayOut);
        mFood = (TextView) findViewById(R.id.sFoodTxt);
        mComp = (TextView) findViewById(R.id.sUinCompTxt);

        mBackBtn = (Button) findViewById(R.id.sBackBtn);
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Move to List Page
                Intent aboutIntent = new Intent(SpotActivity.this, ListActivity.class);
                startActivity(aboutIntent);
            }
        });

        cSpotId = getIntent().getStringExtra("SPOT_ID");
        dbHelper = new DBHelper(this);
        setSpotFields(dbHelper);

    }

    public void setSpotFields(DBHelper dbH){

        SQLiteDatabase db = dbH.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME + " WHERE " + DBHelper.ID_COL
                + " = '" + cSpotId +"'", null);

        if (cursor.moveToFirst()) {
            do {
                mIdTxt.setText(cursor.getString(cursor.getColumnIndex(DBHelper.ID_COL)));
                mNameTxt.setText(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COL)));
                mLightTxt.setText(cursor.getString(cursor.getColumnIndex(DBHelper.LIGHTING_COL)));
                mCrowdTxt.setText(cursor.getString(cursor.getColumnIndex(DBHelper.CROWD_COL)));
                mHoursTxt.setText(cursor.getString(cursor.getColumnIndex(DBHelper.HOURS_COL)));
                mAddressTxt.setText(cursor.getString(cursor.getColumnIndex(DBHelper.ADDRESS_COl)));
                mCityTxt.setText(cursor.getString(cursor.getColumnIndex(DBHelper.CITY_COl)));
                mStateTxt.setText(cursor.getString(cursor.getColumnIndex(DBHelper.STATE_COl)));
                mZipTxt.setText(cursor.getString(cursor.getColumnIndex(DBHelper.ZIP_COL)));
                mLocationTxt.setText(cursor.getString(cursor.getColumnIndex(DBHelper.LOCATION_COL)));

                if(cursor.getString(cursor.getColumnIndex(DBHelper.PRINTING_COL)).equals("Yes")){
                    mPrintTxt.setTextColor(Color.parseColor("#000000"));
                } else {
                    mPrintTxt.setTextColor(Color.parseColor("#d3d3d3"));
                }

                if(cursor.getString(cursor.getColumnIndex(DBHelper.ALWAYS_OPEN_COL)).equals("True")){
                    mOpenTxt.setTextColor(Color.parseColor("#000000"));
                } else {
                    mOpenTxt.setTextColor(Color.parseColor("#d3d3d3"));
                }

                if(cursor.getString(cursor.getColumnIndex(DBHelper.FOOD_COL)).equals("Yes")){
                    mFood.setTextColor(Color.parseColor("#000000"));
                } else {
                    mFood.setTextColor(Color.parseColor("#d3d3d3"));
                }

                if(cursor.getString(cursor.getColumnIndex(DBHelper.COMP_COL)).equals("Yes")){
                    mComp.setTextColor(Color.parseColor("#000000"));
                } else {
                    mComp.setTextColor(Color.parseColor("#d3d3d3"));
                }

                if(cursor.getString(cursor.getColumnIndex(DBHelper.SOUND_COL)).equals("Yes")){
                    mSoundTxt.setTextColor(Color.parseColor("#000000"));
                    mSoundTxt.setTypeface(null, Typeface.BOLD);
                } else {
                    mSoundTxt.setTextColor(Color.parseColor("#d3d3d3"));
                    mSoundTxt.setTypeface(null, Typeface.NORMAL);
                }

                if(cursor.getString(cursor.getColumnIndex(DBHelper.STUDY_ROOM_COL)).equals("True")){
                    privateRoom.setVisibility(View.VISIBLE);
                } else {
                    privateRoom.setVisibility(View.GONE);
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

    }
}