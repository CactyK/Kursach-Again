package alen.mirea.turistinrussia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Button1Activity extends AppCompatActivity {

        Button backbutt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button1);

        backbutt = findViewById(R.id.backbut);
        backbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Button1Activity.this, MapActivity.class));
                finish();
            }
        });
    }}