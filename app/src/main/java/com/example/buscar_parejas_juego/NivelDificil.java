package com.example.buscar_parejas_juego;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
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

public class NivelDificil extends AppCompatActivity {

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
    ImageView carta9;
    ImageView carta10;
    ImageView carta11;
    ImageView carta12;
    ImageView carta13;
    ImageView carta14;
    ImageView carta15;
    ImageView carta16;
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
        setContentView(R.layout.activity_nivel_dificil);

        player1 = (TextView) findViewById(R.id.play1);
        player2 = (TextView) findViewById(R.id.play2);

        jugador = (TextView) findViewById(R.id.turnoid);
        regresar = (ImageButton) findViewById(R.id.iDsalir);
        reiniciar = (Button) findViewById(R.id.reiniciar);
        carta1 = (ImageView) findViewById(R.id.c1);
        carta2 = (ImageView) findViewById(R.id.c2);
        carta3 = (ImageView) findViewById(R.id.c3);
        carta4 = (ImageView) findViewById(R.id.c4);
        carta5 = (ImageView) findViewById(R.id.c5);
        carta6 = (ImageView) findViewById(R.id.c6);
        carta7 = (ImageView) findViewById(R.id.c7);
        carta8 = (ImageView) findViewById(R.id.c8);
        carta9 = (ImageView) findViewById(R.id.c9);
        carta10 = (ImageView) findViewById(R.id.c10);
        carta11 = (ImageView) findViewById(R.id.c11);
        carta12 = (ImageView) findViewById(R.id.c12);
        carta13 = (ImageView) findViewById(R.id.c13);
        carta14 = (ImageView) findViewById(R.id.c14);
        carta15 = (ImageView) findViewById(R.id.c15);
        carta16 = (ImageView) findViewById(R.id.c16);

        puntos1 = (TextView) findViewById(R.id.punto1);
        puntos2 = (TextView) findViewById(R.id.punto2);

        // Recibir valores de MainActivity
        String nameplayer1 = getIntent().getStringExtra("nameplayer1");
        String nameplayer2 = getIntent().getStringExtra("nameplayer2");


        ImageView level3[] = {carta1, carta2, carta3, carta4,
                              carta5, carta6, carta7, carta8,
                              carta9, carta10, carta11, carta12,
                              carta13, carta14, carta15, carta16};

        player1.setText(nameplayer1);
        player2.setText(nameplayer2);

        tur = (int) (Math.random() * 2) + 1;

        if (tur == 1) {

            jugador.setText(nameplayer1);

        } else if (tur == 2) {

            jugador.setText(nameplayer2);
        }

        List<Integer> lista = nivel3();
        for (int i = 0; i < level3.length; i++) {
            level3[i].setImageResource(lista.get(i));
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < level3.length; i++) {
                    level3[i].setImageResource(android.R.color.transparent);
                }
            }
        }, 1000);


        for (int i = 0; i < level3.length; i++) {
            final int pos = i;

            //validar movimientos//
            level3[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animarcartas(level3[pos], lista.get(pos));
                    validarnivel3(level3, lista, pos, nameplayer1, nameplayer2);

                }
            });
        }

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NivelDificil.this, MainActivity.class));

            }
        });
        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NivelDificil.this, NivelDificil.class).putExtra("nameplayer1",nameplayer1).putExtra("nameplayer2",nameplayer2));
                NivelDificil.this.finish();
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

    private List<Integer> nivel3() {
        int img[] = {R.drawable.arriety, R.drawable.princesa, R.drawable.chico,
                R.drawable.kiki, R.drawable.haru, R.drawable.marnie,
                R.drawable.castillo, R.drawable.nausicaa};
        ArrayList<Integer> lista = new ArrayList<Integer>();
        while (lista.size() < 16) {
            int imgAleatoria = img[(int) (Math.random() * 8)];
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
    private List<Integer> validarnivel3(ImageView[] level3, List<Integer> lista, int pos, String inputPlayer1, String inputPlayer2) {
        //validar//
        if (!cartas) {
            carta_1 = level3[pos];
            imgcarta1 = lista.get(pos);
            cartas = true;
        } else {
            carta_2 = level3[pos];
            imgcarta2 = lista.get(pos);
            cartas = false;
            boolean igual = (imgcarta1 == imgcarta2);
            if (!igual) {
                new Handler().postDelayed(() -> {
                    animarcartas(carta_1, android.R.color.transparent);
                    animarcartas(carta_2, android.R.color.transparent);
                }, 500);
                if (tur == 1) {
                    jugador.setText(inputPlayer1);
                    tur = 2;
                    //restar puntos//
                    int puntosActuales = Integer.parseInt(puntos1.getText().toString());
                    if (puntosActuales != 0) {
                        puntosActuales = puntosActuales - 50;
                        puntos1.setText(puntosActuales + "");
                    }

                } else if (tur == 2) {
                    jugador.setText(inputPlayer2);
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
