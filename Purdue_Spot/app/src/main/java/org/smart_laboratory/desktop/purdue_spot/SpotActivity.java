package org.smart_laboratory.desktop.purdue_spot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SpotActivity extends AppCompatActivity {
    TextView mNameTxt, mIdTxt, mSoundTxt, mPrintTxt;
    Button mBackBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_page);

        mNameTxt = (TextView) findViewById(R.id.sNameTxt);
        mIdTxt = (TextView) findViewById(R.id.sIdTxt);
        mSoundTxt = (TextView) findViewById(R.id.sSoundTxt);
        mPrintTxt = (TextView) findViewById(R.id.sPrintTxt);

        mBackBtn = (Button) findViewById(R.id.sBackBtn);
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Move to List Page
                Intent aboutIntent = new Intent(SpotActivity.this, ListActivity.class);
                startActivity(aboutIntent);
            }
        });

    }
}
