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

        /*// SET TEXT VIEW -- Flag
        vPrint = fa.getSelectedPrint();
        vSound = fa.getSelectedSound();

        flag1.setText("FLAG: Print " + vPrint + " Sound: " + vSound);
        dbHelper.queryData(qId, qName, qSound, qPrint);*/

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
}
