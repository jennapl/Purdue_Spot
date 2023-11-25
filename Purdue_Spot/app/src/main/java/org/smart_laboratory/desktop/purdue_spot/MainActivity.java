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

    Button spotPickBtn;

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
                Intent aboutIntent = new Intent(MainActivity.this, FiltersActivity.class);
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
        //Drop Table
        //sqlDB.execSQL("DROP TABLE IF EXISTS " + tblName);

        dbHelper.onCreate(sqlDB);
        /*dbHelper.addValues("0001", "HICKS", "Yes", "Quiet");
        dbHelper.addValues("0002", "WALC", "Yes", "Loud");
        dbHelper.addValues("0003", "PMU", "No", "Loud");*/
    }
}
