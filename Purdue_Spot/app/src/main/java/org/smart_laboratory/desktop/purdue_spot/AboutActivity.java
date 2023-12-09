package org.smart_laboratory.desktop.purdue_spot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/*
About Activity
This Activity servers to show the about page on the PurdueSpot App
*/
public class AboutActivity extends AppCompatActivity {
    Button aBackBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);

        //Instantiate Button
        aBackBtn = (Button) findViewById(R.id.aBackBtn);
        aBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Moves to Main Activity
                Intent aboutIntent = new Intent(AboutActivity.this, MainActivity.class);
                startActivity(aboutIntent);
            }
        });
    }
}
