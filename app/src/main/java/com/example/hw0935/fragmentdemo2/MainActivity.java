package com.example.hw0935.fragmentdemo2;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button b;
    private Button b2;
    private Button b3;

    private void addFrag2() {

        FragmentManager fm = getSupportFragmentManager();


        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.fragment, new MyFragment2());
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        b = findViewById(R.id.addFrag);
        b2 = findViewById(R.id.addActivity);
        b3 = findViewById(R.id.startPip);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addFrag2();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(MainActivity.this, MainActivity2.class);

                startActivity(i);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    MainActivity.this.enterPictureInPictureMode();
                }
            }
        });

        FragmentManager fm = getSupportFragmentManager();


        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.fragment, new MyFragment());
        //ft.addToBackStack(null);
        ft.commit();
    }


    @Override
    public void onBackPressed() {

        FragmentManager fm = getSupportFragmentManager();
        if(fm.getBackStackEntryCount() > 0) {


            fm.popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }
}
