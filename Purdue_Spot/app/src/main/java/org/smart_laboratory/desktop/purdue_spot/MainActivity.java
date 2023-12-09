package org.smart_laboratory.desktop.purdue_spot;

import static org.smart_laboratory.desktop.purdue_spot.DBHelper.ID_COL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
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
    private Context c;

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
        //SQL
        dbName = "spot.db";
        tblName = "spotTbl";

        dbHelper = new DBHelper(this);
        sqlDB = dbHelper.getWritableDatabase();

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

        aboutBtn = (Button) findViewById(R.id.aboutBtn);
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Move to About Page
                Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
            }
        });

        randomBtn = (Button) findViewById(R.id.randomBtn);
        randomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Move to About Page
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                //String queryId = db.rawQuery("SELECT " + ID_COL + " FROM " + DBHelper.TABLE_NAME + " ORDER BY RANDOM() LIMIT 1", null).toString();
                /*
                String cSpotId = spotRow.getId();*/
                Cursor c = db.rawQuery("SELECT " + ID_COL + " FROM " + DBHelper.TABLE_NAME + "  ORDER BY RANDOM() LIMIT 1", null);
                String queryId = null;
                if (c.moveToFirst()){
                    queryId = c.getString(c.getColumnIndex(ID_COL));
                }
                c.close();
                //randomBtn.setText(queryId);
                Intent spotIntent = new Intent(MainActivity.this, SpotActivity.class);
                spotIntent.putExtra("SPOT_ID", queryId);
                startActivity(spotIntent);
            }

        });

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
        /*dbHelper.addValues("0001", "HICKS", "Yes", "Quiet",
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
                "West Lafayette", "IN", "47906", "Residential");*/
        /*dbHelper.addValues("0001", "HICKS", "Yes", "Yes",

                "Fluorescent Light", "Medium", "[INSERT LATER]",

                "False", "True", "504 W State St., GROUND",

                "West Lafayette", "IN", "47907", "Academic",

                " No", " Yes");


        dbHelper.addValues("0002", "WALC", "Yes", "No",

                "Natural Light", "Crowded", "[INSERT LATER]",

                "True", "True", "340 Centennial Mall Dr.",

                "West Lafayette", "IN", "47907", "Central",

                "Yes", "Yes");


        dbHelper.addValues("0003", "PMU", "No", "No",

                "Natural Light", "Crowded", "[INSERT LATER]",

                "False", "False", "101 Grant St.",

                "West Lafayette", "IN", "47907", "Academic",

                "Yes", "No");


        dbHelper.addValues("0004", "GreyHouse", "No", "No",

                "Natural Light", "Crowded", "[INSERT LATER]",

                "False", "False", "100 Northwestern Ave.",

                "West Lafayette", "IN", "47907", "Chauncey",

                "Yes", "No");


        dbHelper.addValues("0005", "Stewart", "Yes", "Yes",

                "Fluorescent Light", "Medium", "[INSERT LATER]",

                "False", "True", "128 Memorial Mall Dr.",

                "West Lafayette", "IN", "47907", "Central",

                "No", "Yes");


        dbHelper.addValues("0006", "KRACH", "Yes", "Yes",

                "Natural Light", "Crowded", "[INSERT LATER]",

                "False", "True", "1198 3rd St.",

                "West Lafayette", "IN", "47906", "Residential",

                "No", "No");


        dbHelper.addValues("0007", "Chaney", "Yes", "Yes",

                "Natural Light",  "Empty", "[INSERT LATER]",

                "False", "True", "460 N University St",

                "West Lafayette", "IN", "47907",  "Academic",

                "No", "Yes");



        dbHelper.addValues("0008", "Starbucks (EE)", "No", "No",

                "Fluorescent Light", "Crowded", "[INSERT LATER]",

                "False", "False", "501 Northwestern Ave",

                "West Lafayette", "IN", "47906", "Academic",

                "Yes", "No");



        dbHelper.addValues("0009", "Knoy", "Yes", "Yes",

                "Dim", "Medium", "[INSERT LATER]",

                "False", "False", "401 Grant St",

                "West Lafayette", "IN", "47906",  "Academic",

                "No", "Yes");


        dbHelper.addValues("0010", "Dudley", "Yes", "Yes",

                "Natural Light", "Crowded", "[INSERT LATER]",

                "True", "False", "420 Central Dr",

                "West Lafayette", "IN", "47906", "Academic",

                "No", "Yes");


        dbHelper.addValues("0011", "Hillenbrand", "Yes",  "Yes",

                "Dim", "Empty", "[INSERT LATER]",

                "True", "False", "1301 3rd Street",

                "West Lafayette", "IN", "47906", "Residential",

                "Yes", "No");


        dbHelper.addValues("0012", "Honors", "Yes", "Yes",

                "Fluorescent Light", "Medium", "[INSERT LATER]",

                "False", "False", "1101 3rd Street",

                "West Lafayette", "IN", "47906", "Central",

                "No", "No");*/

        dbHelper.addValues("0001", "HICKS", "Yes", "Yes",

                "Fluorescent Light", "Medium", "8am-12am",

                "False", "True", "504 W State St., GROUND",

                "West Lafayette", "IN", "47907", "Academic",

                " No", "Yes", "hicks");


        dbHelper.addValues("0002", "WALC", "Yes", "No",

                "Natural Light", "Crowded", "7:30am-12am",

                "True", "True", "340 Centennial Mall Dr.",

                "West Lafayette", "IN", "47907", "Central",

                "Yes", "Yes", "walc");


        dbHelper.addValues("0003", "PMU", "No", "No",

                "Natural Light", "Crowded", "6am-12am",

                "False", "False", "101 Grant St.",

                "West Lafayette", "IN", "47907", "Academic",

                "Yes", "No", "pmu");


        dbHelper.addValues("0004", "GreyHouse", "No", "No",

                "Natural Light", "Crowded", "7am-9pm",

                "False", "False", "100 Northwestern Ave.",

                "West Lafayette", "IN", "47907", "Chauncey",

                "Yes", "No", "greyhouse");


        dbHelper.addValues("0005", "Stewart", "Yes", "Yes",

                "Fluorescent Light", "Medium", "8am-12am",

                "False", "True", "128 Memorial Mall Dr.",

                "West Lafayette", "IN", "47907", "Central",

                "No", "Yes", "stewart");


        dbHelper.addValues("0006", "KRACH", "Yes", "Yes",

                "Natural Light", "Crowded", "9am-12am",

                "False", "True", "1198 3rd St.",

                "West Lafayette", "IN", "47906", "Residential",

                "No", "No", "krach");


        dbHelper.addValues("0007", "Chaney", "Yes", "Yes",

                "Natural Light",  "Empty", "24/7",

                "False", "True", "460 N University St",

                "West Lafayette", "IN", "47907",  "Academic",

                "No", "Yes", "chaney");



        dbHelper.addValues("0008", "Starbucks (EE)", "No", "No",

                "Fluorescent Light", "Crowded", "7am-7pm",

                "False", "False", "501 Northwestern Ave",

                "West Lafayette", "IN", "47906", "Academic",

                "Yes", "No", "starbucksee");



        dbHelper.addValues("0009", "Knoy", "Yes", "Yes",

                "Dim", "Medium", "6:30-12am",

                "False", "False", "401 Grant St",

                "West Lafayette", "IN", "47906",  "Academic",

                "No", "Yes", "knoy");


        dbHelper.addValues("0010", "Dudley", "Yes", "Yes",

                "Natural Light", "Crowded", "6am-1am",

                "True", "False", "420 Central Dr",

                "West Lafayette", "IN", "47906", "Academic",

                "No", "Yes", "dudley");


        dbHelper.addValues("0011", "Hillenbrand", "Yes", "Yes",

                "Dim", "Empty", "24/7",

                "True", "False", "1301 3rd Street",

                "West Lafayette", "IN", "47906", "Residential",

                "Yes", "No", "hillenbrand");


        dbHelper.addValues("0012", "Honors", "Yes", "Yes",

                "Fluorescent Light", "Medium", "8am-4pm",

                "False", "False", "1101 3rd Street",

                "West Lafayette", "IN", "47906", "Central",

                "No", "No", "honors");


        dbHelper.addValues("0013", "ThirdStreet", "No", "No",

                "Natural Light", "Crowded", "9am-11pm",

                "False", "False", "1196 3rd Street",

                "West Lafayette", "IN", "47906", "Central",

                "Yes", "No", "thirdstreet");


        dbHelper.addValues("0014", "Vienna", "No", "Yes",

                "Dim", "Crowded", "9am-7pm",

                "False", "False", "208 South St",

                "West Lafayette", "IN", "47906", "Chauncey",

                "Yes", "No", "vienna");


        dbHelper.addValues("0015", "Grey House(State)", "No",  "Yes",

                "Natural Light", "Crowded", "7am-7pm",

                "False", "False", "1000 Mitch Daniels Blvd",

                "West Lafayette", "IN", "47906", "Chauncey",

                "Yes", "No", "greyhousestate");


        dbHelper.addValues("0016", "Cosi", "No",  "No",

                "Natural Light", "Crowded", "11am-8pm",

                "False", "False", "1101 3rd Street",

                "West Lafayette", "IN", "47906", "Central",

                "Yes", "No", "cosi");


        dbHelper.addValues("0017", "Meredith South", "No", "No",

                "Natural Light", "Medium", "8am-8pm",

                "False", "False", "1225 1st Street",

                "West Lafayette", "IN", "47906", "Residential",

                "Yes", "No", "meredithsouth");
    }
}
