package org.smart_laboratory.desktop.purdue_spot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button spotPickBtn, randomBtn, aboutBtn;
    DBHelper dbHelper;
    SQLiteDatabase sqlDB;
    String dbName;
    String tblName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED) {
            if
            (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
            }
        }
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED) {
            if
            (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            ) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }

        // BUTTONS
        spotPickBtn = (Button) findViewById(R.id.spotPickBtn);
        spotPickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Move to Filters Page
                Intent filtersIntent = new Intent(MainActivity.this, FiltersActivity.class);
                startActivity(filtersIntent);
            }
        });

        randomBtn = (Button) findViewById(R.id.randomBtn);

        aboutBtn = (Button) findViewById(R.id.aboutBtn);
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Move to About Page
                Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
            }
        });



        //SQL
        dbName = "spot.db";
        tblName = "spotTbl";

        dbHelper = new DBHelper(this);
        sqlDB = dbHelper.getWritableDatabase();

        //Make DB
        try {
            sqlDB = openOrCreateDatabase(
                    dbName,
                    MainActivity.MODE_PRIVATE,
                    null);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        //Drop Table - FLAG keep to end
        sqlDB.execSQL("DROP TABLE IF EXISTS " + tblName);

        dbHelper.onCreate(sqlDB);


        // FLAG - Insert Data - keep to end
        dbHelper.addValues("0001", "HICKS", "Yes", "Quiet",
                "Fluorescent Light", "Medium", "[INSERT LATER]",
                "False", "True", "504 W State St., GROUND",
                "West Lafayette", "IN", "47907", "Academic");
        dbHelper.addValues("0002", "WALC", "Yes", "Loud",
                "Natural Light", "Crowded", "[INSERT LATER]",
                "True", "True", "340 Centennial Mall Dr.",
                "West Lafayette", "IN", "47907", "Central");
        dbHelper.addValues("0003", "PMU", "No", "Loud",
                "Natural Light", "Crowded", "[INSERT LATER]",
                "False", "False", "101 Grant St.",
                "West Lafayette", "IN", "47907", "Academic");
        dbHelper.addValues("0004", "GreyHouse", "No", "Loud",
                "Natural Light", "Crowded", "[INSERT LATER]",
                "False", "False", "100 Northwestern Ave.",
                "West Lafayette", "IN", "47907", "Chauncey");
        dbHelper.addValues("0005", "Stewart", "Yes", "Quiet",
                "Fluorescent Light", "Medium", "[INSERT LATER]",
                "False", "True", "128 Memorial Mall Dr.",
                "West Lafayette", "IN", "47907", "Central");
        dbHelper.addValues("0006", "KRACH", "Yes", "Quiet",
                "Natural Light", "Crowded", "[INSERT LATER]",
                "False", "True", "1198 3rd St.",
                "West Lafayette", "IN", "47906", "Residential");

    }
}
