package com.example.buscar_parejas_juego;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button jugar;
    Button facil;
    Button normal;
    Button dificil;
    ImageButton ajustes ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jugar = (Button) findViewById(R.id.Btnjugar);
        ajustes = (ImageButton) findViewById(R.id.ajustes);

        ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,puntajes.class));
            }
        });

        uploadPreferences();
        jugar.setOnClickListener(view -> {
            mediaplayer();

            SharedPreferences guardarNamePlayer = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = guardarNamePlayer.edit();


            TextView inputPlayer1 = (TextView) findViewById(R.id.id_name1);
            TextView inputPlayer2 = (TextView) findViewById(R.id.id_name2);
            String nameplayer1 = inputPlayer1.getText().toString();
            String nameplayer2 = inputPlayer2.getText().toString();
            editor.putString("inputplayer1", nameplayer1);
            editor.putString("inputplayer2", nameplayer2);
            editor.apply();


            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.niveles);
            dialog.show();
            facil = (Button) dialog.findViewById(R.id.Btnfacil);
            normal = (Button) dialog.findViewById(R.id.Btnnormal);
            dificil = (Button) dialog.findViewById(R.id.Btndificil);


            /* SE INGRESA A EL NIVEL FACIL*/
            facil.setOnClickListener(view1 -> {
                dialog.dismiss();
                mediaplayer();
                startActivity(new Intent(MainActivity.this, NivelFacil.class).putExtra("nameplayer1", nameplayer1).putExtra("nameplayer2", nameplayer2));

            });

            /*SE INGRESA A EL NIVEL NORMAL*/
            normal.setOnClickListener(view12 -> {
                dialog.dismiss();
                mediaplayer();
                startActivity(new Intent(MainActivity.this, NivelNormal.class).putExtra("nameplayer1", nameplayer1).putExtra("nameplayer2", nameplayer2));
            });

           /*SE INGRESA A EL NIVEL DIFICIL*/
            dificil.setOnClickListener(view13 -> {
                dialog.dismiss();
                mediaplayer();
                startActivity(new Intent(MainActivity.this, NivelDificil.class).putExtra("nameplayer1", nameplayer1).putExtra("nameplayer2", nameplayer2));

            });

        });
    }


    private void uploadPreferences() {
        SharedPreferences preferences = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        String namePlayerOne = preferences.getString("inputplayer1", "Player One");
        String namePlayerTwo = preferences.getString("inputplayer2", "Player Two");
        TextView inputPlayer1 = findViewById(R.id.id_name1);
        TextView inputPlayer2 = findViewById(R.id.id_name2);
        inputPlayer1.setText(namePlayerOne);
        inputPlayer2.setText(namePlayerTwo);
    }

   public void mediaplayer(){
       final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.click);
       mediaPlayer.start();
   }


}