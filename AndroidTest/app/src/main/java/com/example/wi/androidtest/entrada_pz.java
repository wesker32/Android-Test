package com.example.wi.androidtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.view.MenuItem;
import android.app.Activity;
import android.os.Bundle;


public class entrada_pz extends AppCompatActivity {
    Button button, btn_Cad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrada_pz);

        btn_Cad =(Button)findViewById(R.id.btn_Cad);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(entrada_pz.this, login.class);
                startActivity(intent);
                finish();
            }


        });

        btn_Cad.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(entrada_pz.this, CadUsuarioActivity.class);
                startActivity(intent);
                finish();


            }
        });


    }
}
