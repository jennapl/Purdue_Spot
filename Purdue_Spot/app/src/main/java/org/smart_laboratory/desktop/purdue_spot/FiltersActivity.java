package org.smart_laboratory.desktop.purdue_spot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/*
FiltersActivity
This activity is where users specify their filters.
The information is stored upon the Search button click.
*/
public class FiltersActivity extends AppCompatActivity {
    Button mSearch, mBack;
    RadioButton mPrint, mFood, mComp, mQuiet, mOpen;
    Spinner mLocation, mLighting, mCrowd;
    String [] crowdLevel = {"Crowded", "Medium", "Empty"};
    String [] lightTypes = {"Natural Light", "Dim", "Fluorescent Light"};
    String [] locationTypes = {"Academic", "Central", "Residential", "Chauncey"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        // Setting Spinner Adapters
        ArrayAdapter<String> lightAdapter = new ArrayAdapter
                <String>(this, R.layout.custom_spinner_dropdown_item, lightTypes);
        lightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> crowdAdapter = new ArrayAdapter
                <String>(this, R.layout.custom_spinner_dropdown_item, crowdLevel);
        crowdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> locAdapter = new ArrayAdapter
                <String>(this, R.layout.custom_spinner_dropdown_item, locationTypes);
        locAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Instantiate info
        mSearch = (Button) findViewById(R.id.searchBtn);
        mBack = (Button) findViewById(R.id.backBtn);


        mPrint = (RadioButton) findViewById(R.id.printRb);
        mFood = (RadioButton) findViewById(R.id.foodRb);
        mComp = (RadioButton) findViewById(R.id.uniCompRb);
        mQuiet = (RadioButton) findViewById(R.id.quietRb);
        mOpen = (RadioButton) findViewById(R.id.openRb);

        mLocation = (Spinner) findViewById(R.id.locSpin);
        mLocation.setAdapter(locAdapter);
        mLocation.setDropDownWidth(getResources().getDisplayMetrics().widthPixels);
        mLighting = (Spinner) findViewById(R.id.lightSpin);
        mLighting.setAdapter(lightAdapter);
        mLighting.setDropDownWidth(getResources().getDisplayMetrics().widthPixels);
        mCrowd = (Spinner) findViewById(R.id.crowdSpin);
        mCrowd.setAdapter(crowdAdapter);
        mCrowd.setDropDownWidth(getResources().getDisplayMetrics().widthPixels);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Moves to Home Page
                Intent aboutIntent = new Intent(FiltersActivity.this, MainActivity.class);
                startActivity(aboutIntent);
            }
        });

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Stores filter values and moves to ListActivity
                FiltersClass fc = new FiltersClass();
                fc.setSelectedPrint(mPrint);
                fc.setSelectedLocation(mLocation);
                fc.setSelectedLight(mLighting);
                fc.setSelectedCrowd(mCrowd);
                fc.setSelectedFood(mFood);
                fc.setSelectedComp(mComp);
                fc.setSelectedOpen(mOpen);
                fc.setSelectedQuiet(mQuiet);

                //Moves to ListActivity
                Intent listActivity = new Intent(FiltersActivity.this, ListActivity.class);
                startActivity(listActivity);
            }
        });
    }
}
