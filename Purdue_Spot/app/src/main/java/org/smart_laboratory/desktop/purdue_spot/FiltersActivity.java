package org.smart_laboratory.desktop.purdue_spot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FiltersActivity extends AppCompatActivity {
    Button mSearch, mBack;
    RadioButton mPrint;
    Spinner mSound, mLocation, mLighting, mCrowd;

    String [] soundLevels = {"Quiet", "Loud"};
    String [] crowdLevel = {"Crowded", "Medium"};
    String [] lightTypes = {"Natural Light", "Fluorescent Light"};
    String [] locationTypes = {"Academic", "Central", "Residential", "Chauncey"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        ArrayAdapter<String> soundAdapter = new ArrayAdapter
                <String>(this, android.R.layout.simple_spinner_item, soundLevels);
        ArrayAdapter<String> lightAdapter = new ArrayAdapter
                <String>(this, android.R.layout.simple_spinner_item, lightTypes);
        ArrayAdapter<String> crowdAdapter = new ArrayAdapter
                <String>(this, android.R.layout.simple_spinner_item, crowdLevel);
        ArrayAdapter<String> locAdapter = new ArrayAdapter
                <String>(this, android.R.layout.simple_spinner_item, locationTypes);

        mSearch = (Button) findViewById(R.id.searchBtn);
        mBack = (Button) findViewById(R.id.backBtn);


        mPrint = (RadioButton) findViewById(R.id.printRb);

        mSound = (Spinner)findViewById(R.id.soundSpin);
        mSound.setAdapter(soundAdapter);
        mLocation = (Spinner) findViewById(R.id.locSpin);
        mLocation.setAdapter(locAdapter);
        mLighting = (Spinner) findViewById(R.id.lightSpin);
        mLighting.setAdapter(lightAdapter);
        mCrowd = (Spinner) findViewById(R.id.crowdSpin);
        mCrowd.setAdapter(crowdAdapter);

        // LISTENERS
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Move to Filters Page
                Intent aboutIntent = new Intent(FiltersActivity.this, MainActivity.class);
                startActivity(aboutIntent);
            }
        });

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Move to Filters Page
                FiltersClass fc = new FiltersClass();
                fc.setSelectedSound(mSound);
                fc.setSelectedPrint(mPrint);
                fc.setSelectedLocation(mLocation);
                fc.setSelectedLight(mLighting);
                fc.setSelectedCrowd(mCrowd);

                Intent aboutIntent = new Intent(FiltersActivity.this, ListActivity.class);
                startActivity(aboutIntent);
            }
        });

    }
}
