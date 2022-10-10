package alen.mirea.turistinrussia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {

    Button butt1, butt2, butt3, butt4, butt5, butt6, butt7, butt8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        butt1 = findViewById(R.id.but1);
        butt2 = findViewById(R.id.but2);
        butt3 = findViewById(R.id.but3);
        butt4 = findViewById(R.id.but4);
        butt5 = findViewById(R.id.but5);
        butt6 = findViewById(R.id.but6);
        butt7 = findViewById(R.id.but7);
        butt8 = findViewById(R.id.but8);

        butt1.setOnClickListener(view -> {
            startActivity(new Intent(MapActivity.this, Button1Activity.class));
            finish();
        });
        butt2.setOnClickListener(view -> {
            startActivity(new Intent(MapActivity.this, Button2Activity.class));
            finish();
        });
        butt3.setOnClickListener(view -> {
            startActivity(new Intent(MapActivity.this, Button3Activity.class));
            finish();
        });
        butt4.setOnClickListener(view -> {
            startActivity(new Intent(MapActivity.this, Button4Activity.class));
            finish();
        });
        butt5.setOnClickListener(view -> {
            startActivity(new Intent(MapActivity.this, Button5Activity.class));
            finish();
        });
        butt6.setOnClickListener(view -> {
            startActivity(new Intent(MapActivity.this, Button6Activity.class));
            finish();
        });
        butt7.setOnClickListener(view -> {
            startActivity(new Intent(MapActivity.this, Button7Activity.class));
            finish();
        });
        butt8.setOnClickListener(view -> {
            startActivity(new Intent(MapActivity.this, Button8Activity.class));
            finish();
        });



    }
}