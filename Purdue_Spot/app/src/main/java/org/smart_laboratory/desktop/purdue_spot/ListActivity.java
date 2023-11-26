package org.smart_laboratory.desktop.purdue_spot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListActivity extends AppCompatActivity {

    TextView flag1;
    String vPrint, vSound;
    Button mBack, mChange;

    TextView qId, qName, qSound, qPrint;
    RecyclerView mSpotList;
    DBHelper dbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_page);

        FiltersClass fa = new FiltersClass();
        dbHelper = new DBHelper(this);

        flag1 = (TextView) findViewById(R.id.flag1);
        mBack = (Button) findViewById(R.id.lpBackBtn);
        mChange = (Button) findViewById(R.id.changeBtn);
        qId = (TextView) findViewById(R.id.idTxt);
        qName = (TextView) findViewById(R.id.nameTxt);
        qSound = (TextView) findViewById(R.id.soundTxt);
        qPrint = (TextView) findViewById(R.id.printTxt);
        mSpotList = (RecyclerView) findViewById(R.id.listSpots);
        mSpotList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //Send list of DB values to adapter\
        List<Map<String, String>> songList = new ArrayList<>();
        spotAdapter sAdapter = new spotAdapter(songList);
        mSpotList.setAdapter(sAdapter);

        // SET TEXT VIEW
        vPrint = fa.getPrint();
        vSound = fa.getSound();

        flag1.setText("FLAG: Print " + vPrint + " Sound: " + vSound);
        dbHelper.queryData(qId, qName, qSound, qPrint);


        mChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Move to Filters Page
                Intent aboutIntent = new Intent(ListActivity.this, FiltersActivity.class);
                startActivity(aboutIntent);
            }
        });

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
