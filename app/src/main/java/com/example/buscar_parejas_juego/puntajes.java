package com.example.buscar_parejas_juego;

import static com.example.buscar_parejas_juego.R.id.volverbtn;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class puntajes extends AppCompatActivity {
    ImageButton volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vistapuntajesmax);

        volver = (ImageButton) findViewById(R.id.volverbtn);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(puntajes.this, MainActivity.class));
            }
        });

    }
}
