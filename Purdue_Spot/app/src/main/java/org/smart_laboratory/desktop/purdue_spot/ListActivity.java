package org.smart_laboratory.desktop.purdue_spot;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    TextView flag1;
    String vPrint, vSound;
    Button mBack, mChange;

    TextView qId, qName, qSound, qPrint;
    private RecyclerView mSpotViewer;
    private myDAO dao;
    private spotAdapter adapter;
    DBHelper dbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_page);

        //myDBModel DBModel = new myDBModel();
        FiltersClass fa = new FiltersClass();
        dbHelper = new DBHelper(this);

        flag1 = (TextView) findViewById(R.id.flag1);
        mBack = (Button) findViewById(R.id.lpBackBtn);
        mChange = (Button) findViewById(R.id.changeBtn);
        qId = (TextView) findViewById(R.id.idTxt);
        qName = (TextView) findViewById(R.id.nameTxt);
        qSound = (TextView) findViewById(R.id.soundTxt);
        qPrint = (TextView) findViewById(R.id.printTxt);
        mSpotViewer = (RecyclerView) findViewById(R.id.spotsRv);
        dao = new myDAO(this);
        //new DatabaseTask().execute();

        // Takes list of values and show on recyclerView
        List<myDBModel> spotList = dao.getAllData();
        adapter = new spotAdapter(ListActivity.this, spotList);
        mSpotViewer.setLayoutManager(new LinearLayoutManager(ListActivity.this));
        mSpotViewer.setAdapter(adapter);
        if (spotList.isEmpty()){
            Toast.makeText(ListActivity.this, "No spots match your filters.",
                    Toast.LENGTH_LONG).show();
        }

        //DELETE ME
        //mSpotViewer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //Send list of DB values to adapter\
        //List<Map<String, String>> songList = new ArrayList<>();
        //spotAdapter sAdapter = new spotAdapter(songList);
        //mSpotViewer.setAdapter(sAdapter);

        // SET TEXT VIEW -- Flag
        vPrint = fa.getSelectedPrint();
        vSound = fa.getSelectedSound();

        flag1.setText("FLAG: Print " + vPrint + " Sound: " + vSound);
        dbHelper.queryData(qId, qName, qSound, qPrint);

        //Change Activity to Filters
        mChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Move to Filters Page
                Intent aboutIntent = new Intent(ListActivity.this, FiltersActivity.class);
                startActivity(aboutIntent);
            }
        });

        //Change Activity to Home
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Move to Filters Page
                Intent aboutIntent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(aboutIntent);
            }
        });


    }
//DELETE ME
/*    private class DatabaseTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            // Create and populate the database in the background
            DBHelper dbHelper = new DBHelper(ListActivity.this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            try {
                db = openOrCreateDatabase(
                        "spot.db.db",
                        MainActivity.MODE_PRIVATE,
                        null);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            // Create the table
            db.execSQL("create table if not exists " + "spotTbl"
                    + "(spotID char(4), "
                    + " spotName char(50), "
                    + " spotPrinting char(50), "
                    + " spotSoundLevel char(50));");

*//*            // Insert sample data (replace this with your actual data)
            ContentValues values = new ContentValues();
            values.put(DBHelper.COLUMN_NAME, "John Doe");
            values.put(DBHelper.COLUMN_AGE, 25);
            values.put(DBHelper.COLUMN_EMAIL, "john.doe@example.com");
            db.insert(DBHelper.TABLE_NAME, null, values);

            values.clear();

            values.put(DBHelper.COLUMN_NAME, "Jane Smith");
            values.put(DBHelper.COLUMN_AGE, 30);
            values.put(DBHelper.COLUMN_EMAIL, "jane.smith@example.com");
            db.insert(DBHelper.TABLE_NAME, null, values);

            // Close the database
            db.close();

            *//*
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // Refresh the data after creating and populating the database
            List<myDBModel> spotList = dao.getAllData();
            adapter = new spotAdapter(spotList);
            mSpotViewer.setLayoutManager(new LinearLayoutManager(ListActivity.this));
            mSpotViewer.setAdapter(adapter);
            if (spotList.isEmpty()){
                Toast.makeText(ListActivity.this, "No spots match your filters.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }*/
}
