package ir.phoenix_iran.library;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import korush.kabir.IntroHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button multi = findViewById(R.id.multi);
        Button single = findViewById(R.id.single);

        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(SingleIntroActivity.class);
            }
        });

        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(MultiIntroActivity.class);
            }
        });
    }

    private void intent(Class<? extends AppCompatActivity> activity) {
        startActivity(
                new Intent(
                        MainActivity.this , activity
                )
        );
    }
}
