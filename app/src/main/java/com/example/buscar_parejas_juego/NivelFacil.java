package com.example.buscar_parejas_juego;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class NivelFacil extends AppCompatActivity {

    boolean cartas = false;
    TextView jugador;
    ImageButton regresar;
    Button reiniciar;
    ImageView carta1;
    ImageView carta2;
    ImageView carta3;
    ImageView carta4;
    ImageView carta5;
    ImageView carta6;
    ImageView carta7;
    ImageView carta8;

    int imgcarta1 = 0;
    int imgcarta2 = 0;
    ImageView carta_1;
    ImageView carta_2;

    TextView player1;
    TextView player2;
    Integer tur;
    // contadores de puntos
    TextView puntos1;
    TextView puntos2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.nivelfacil);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.click);

        player1 = (TextView) findViewById(R.id.player1);
        player2 = (TextView) findViewById(R.id.player2);

        jugador = (TextView) findViewById(R.id.turno);
        regresar = (ImageButton) findViewById(R.id.Btnsalir);
        reiniciar = (Button) findViewById(R.id.Btnreiniciar);
        carta1 = (ImageView) findViewById(R.id.carta1);
        carta2 = (ImageView) findViewById(R.id.carta2);
        carta3 = (ImageView) findViewById(R.id.carta3);
        carta4 = (ImageView) findViewById(R.id.carta4);
        carta5 = (ImageView) findViewById(R.id.carta5);
        carta6 = (ImageView) findViewById(R.id.carta6);
        carta7 = (ImageView) findViewById(R.id.carta7);
        carta8 = (ImageView) findViewById(R.id.carta8);

        puntos1 = (TextView) findViewById(R.id.puntos1);
        puntos2 = (TextView) findViewById(R.id.puntos2);

        // Recibir valores de MainActivity
        String nameplayer1 = getIntent().getStringExtra("nameplayer1");
        String nameplayer2 = getIntent().getStringExtra("nameplayer2");


        ImageView[] imagview = {carta1, carta2, carta3, carta4, carta5, carta6, carta7, carta8};

        player1.setText(nameplayer1);
        player2.setText(nameplayer2);

        tur = (int) (Math.random() * 2) + 1;

        if (tur == 1) {
            jugador.setText(nameplayer1);

        } else if (tur == 2) {
            jugador.setText(nameplayer2);

        }


        List<Integer> lista = aleatorio();
        for (int i = 0; i < imagview.length; i++) {
            imagview[i].setImageResource(lista.get(i));

        }
        new Handler().postDelayed(() -> {
            for (int i = 0; i < imagview.length; i++) {
                imagview[i].setImageResource(android.R.color.transparent);
            }
        }, 1000);


        for (int i = 0; i < imagview.length; i++) {
            final int pos = i;

            imagview[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animarcartas(imagview[pos], lista.get(pos));
                    validar(imagview, lista, pos, nameplayer1, nameplayer2);
                }
            });
        }

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                startActivity(new Intent(NivelFacil.this, MainActivity.class));
            }
        });
        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                startActivity(new Intent(NivelFacil.this, NivelFacil.class).putExtra("nameplayer1",nameplayer1).putExtra("nameplayer2",nameplayer2));
                NivelFacil.this.finish();
            }
        });
    }
    private void animarcartas(ImageView img, int image) {
        ViewPropertyAnimator animator = img.animate().withLayer().rotationY(90).setDuration(50);
        animator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (img.getRotationY() == 90) {
                    img.setImageResource(image);
                    img.animate().rotationY(0).setDuration(50).setListener(null);
                }
            }
        });

    }
    private List<Integer> aleatorio() {

        int imgs[] = {R.drawable.ponyo, R.drawable.papaponyo, R.drawable.bigwoman, R.drawable.saske};
        ArrayList<Integer> lista = new ArrayList<Integer>();
        while (lista.size() < 8) {
            int imgAleatoria = imgs[(int) (Math.random() * 4)];
            int cont = 0;
            for (int i = 0; i < lista.size(); i++) {
                if (cont >= 2) {
                    break;
                }
                if (lista.get(i) == imgAleatoria) {
                    cont++;
                }
            }
            if (cont < 2) {
                lista.add(imgAleatoria);
            }
        }
        return lista;
    }
    public List<Integer> validar(ImageView[] imagview, List<Integer> lista, int pos, String namePlayer1, String namePlayer2) {
        if (!cartas) {
            carta_1 = imagview[pos];
            imgcarta1 = lista.get(pos);
            cartas = true;
        } else {
            carta_2 = imagview[pos];
            imgcarta2 = lista.get(pos);
            cartas = false;
            boolean igual = (imgcarta1 == imgcarta2);
            if (!igual) {
                new Handler().postDelayed(() -> {
                    animarcartas(carta_1, android.R.color.transparent);
                    animarcartas(carta_2, android.R.color.transparent);
                }, 500);
                if (tur == 1) {
                    jugador.setText(namePlayer2);
                    tur = 2;
                    //restar puntos//
                    int puntosActuales = Integer.parseInt(puntos1.getText().toString());
                    if (puntosActuales != 0) {
                        puntosActuales = puntosActuales - 50;
                        puntos1.setText(puntosActuales + "");
                    }

                } else if (tur == 2) {
                    jugador.setText(namePlayer1);
                    tur = 1;
                    int puntosActuales = Integer.parseInt(puntos2.getText().toString());
                    if (puntosActuales != 0) {
                        puntosActuales = puntosActuales - 50;
                        puntos2.setText(puntosActuales + "");
                    }

                }
            } else {
                // sumar puntos
                if (tur == 1) {
                    int puntosActuales = Integer.parseInt(puntos1.getText().toString());
                    puntosActuales = puntosActuales + 100;
                    puntos1.setText(puntosActuales + "");
                    Log.e("test", puntosActuales + " puntos 1");
                } else if (tur == 2) {
                    int puntosActuales = Integer.parseInt(puntos2.getText().toString());
                    puntosActuales = puntosActuales + 100;
                    puntos2.setText(puntosActuales + "");
                    Log.e("test", puntosActuales + " puntos 2");

                }
                carta_1.setEnabled(false);
                carta_2.setEnabled(false);


            }
        }
        return lista;
    }
}
