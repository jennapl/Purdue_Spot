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

public class FiltersActivity extends AppCompatActivity {
    Button mSearch, mBack;
    RadioButton mPrint;
    Spinner mSound;

    String [] soundLevels = {"Quiet", "Loud"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        ArrayAdapter<String> adapter = new ArrayAdapter
                <String>(this, android.R.layout.simple_spinner_item, soundLevels);

        mSearch = (Button) findViewById(R.id.searchBtn);
        mBack = (Button) findViewById(R.id.backBtn);
        mSound = (Spinner)findViewById(R.id.soundSpin);
        mSound.setAdapter(adapter);
        mPrint = (RadioButton) findViewById(R.id.printRb);

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
                /*FiltersClass fa = new FiltersClass();
                fa.setSound(mSound);
                fa.setPrint(mPrint);*/

                Intent aboutIntent = new Intent(FiltersActivity.this, ListActivity.class);
                startActivity(aboutIntent);
            }
        });

    }
}
