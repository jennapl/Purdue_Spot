package org.smart_laboratory.desktop.purdue_spot;

import android.content.Intent;
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

/*
ListActivity
This activity is used to show the list of spots
that fit the users filters, shown in a RecyclerView.

Keep in mind, there are not a lot of spots in the DB
so often many filters only show one spot, as there are
few spots that share filter values.
*/

public class ListActivity extends AppCompatActivity {
    Button mBack, mChange;
    private RecyclerView mSpotViewer;
    private myDAO dao;
    private spotAdapter adapter;
    DBHelper dbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_page);

        dbHelper = new DBHelper(this);

        mBack = (Button) findViewById(R.id.lpBackBtn);
        mChange = (Button) findViewById(R.id.changeBtn);
        mSpotViewer = (RecyclerView) findViewById(R.id.spotsRv);
        // Gets all of the data that match the filters
        dao = new myDAO(this);

        // Shows list in RecyclerView
        List<myDBModel> spotList = dao.getAllData();
        adapter = new spotAdapter(ListActivity.this, spotList);
        mSpotViewer.setLayoutManager(new LinearLayoutManager(ListActivity.this));
        mSpotViewer.setAdapter(adapter);

        // Shows if no spots match the users filters
        if (spotList.isEmpty()){
            Toast.makeText(ListActivity.this, "No spots match your filters.",
                    Toast.LENGTH_LONG).show();
        }

        // Change users filters
        mChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Moves to Filters Page
                Intent aboutIntent = new Intent(ListActivity.this, FiltersActivity.class);
                startActivity(aboutIntent);
            }
        });

        // Return to Home
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Move home
                Intent aboutIntent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(aboutIntent);
            }
        });
    }
}
