package org.smart_laboratory.desktop.purdue_spot;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/*
SpotActivity
This activity is used to show all the info of a spot after a user clicks
view more from the List Activity. All the info and images are shown of a specific spot.
*/

public class SpotActivity extends AppCompatActivity {
    TextView mNameTxt, mIdTxt, mSoundTxt, mPrintTxt, mLightTxt, mCrowdTxt, mStudyTxt, mHoursTxt, mOpenTxt, mAddressTxt, mCityTxt, mStateTxt, mZipTxt, mLocationTxt, mFood, mComp;
    Button mBackBtn, mUniTimeBtn;
    ImageView mImage;
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
        mUniTimeBtn = (Button) findViewById(R.id.unitimeBtn);
        mImage = (ImageView) findViewById(R.id.spotImg);

        // Back Button Code
        mBackBtn = (Button) findViewById(R.id.sBackBtn);
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Returns to List Page
                Intent listIntent = new Intent(SpotActivity.this, ListActivity.class);
                startActivity(listIntent);
            }
        });

        // Unitime button for private rooms - NOT WORKING for some reason the AVD does NOT like this
        mUniTimeBtn = (Button) findViewById(R.id.unitimeBtn);
        mUniTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String website = "https://timetable.mypurdue.purdue.edu/Timetabling/gwt.jsp?page=personal";
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
                startActivity(mIntent);
            }
        });

        // Gets the put value of the ID from the randomSpot and search button press to
        // show the spot info of the selected or random id
        cSpotId = getIntent().getStringExtra("SPOT_ID");
        dbHelper = new DBHelper(this);
        setSpotFields(dbHelper);
    }

    public void setSpotFields(DBHelper dbH){
        // Queries the spot with the putValue ID
        SQLiteDatabase db = dbH.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME + " WHERE " + DBHelper.ID_COL
                + " = '" + cSpotId +"'", null);
        mNameTxt.setText(cSpotId);
        // Sets all the text values with the ID's info
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

                // Sets color black if printing, grey if not
                if(cursor.getString(cursor.getColumnIndex(DBHelper.PRINTING_COL)).equals("Yes")){
                    mPrintTxt.setTextColor(Color.parseColor("#000000"));
                } else {
                    mPrintTxt.setTextColor(Color.parseColor("#d3d3d3"));
                }

                // Sets color black if 24/7, grey if not
                if(cursor.getString(cursor.getColumnIndex(DBHelper.ALWAYS_OPEN_COL)).equals("True")){
                    mOpenTxt.setTextColor(Color.parseColor("#000000"));
                } else {
                    mOpenTxt.setTextColor(Color.parseColor("#d3d3d3"));
                }

                // Sets color black if food, grey if not
                if(cursor.getString(cursor.getColumnIndex(DBHelper.FOOD_COL)).equals("Yes")){
                    mFood.setTextColor(Color.parseColor("#000000"));
                } else {
                    mFood.setTextColor(Color.parseColor("#d3d3d3"));
                }

                // Sets color black if computers, grey if not
                if(cursor.getString(cursor.getColumnIndex(DBHelper.COMP_COL)).equals("Yes")){
                    mComp.setTextColor(Color.parseColor("#000000"));
                } else {
                    mComp.setTextColor(Color.parseColor("#d3d3d3"));
                }

                // Sets color black if quiet, grey if not
                if(cursor.getString(cursor.getColumnIndex(DBHelper.SOUND_COL)).equals("Yes")){
                    mSoundTxt.setTextColor(Color.parseColor("#000000"));
                    mSoundTxt.setTypeface(null, Typeface.BOLD);
                } else {
                    mSoundTxt.setTextColor(Color.parseColor("#d3d3d3"));
                    mSoundTxt.setTypeface(null, Typeface.NORMAL);
                }

                // Shows the layout containing private room info if that is offered at the spot
                if(cursor.getString(cursor.getColumnIndex(DBHelper.STUDY_ROOM_COL)).equals("True")){
                    privateRoom.setVisibility(View.VISIBLE);
                } else {
                    privateRoom.setVisibility(View.GONE);
                }

                // Sets image using PIC_COL value to img path in drawables
                String imgpath = cursor.getString(cursor.getColumnIndex(DBHelper.PIC_COL));
                int resourceID = getResources().getIdentifier(imgpath, "drawable", getPackageName());
                mImage.setImageResource(resourceID);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }
}