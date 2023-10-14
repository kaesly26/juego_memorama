package com.example.buscar_parejas_juego;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    int turno;
    boolean cartas = false;
    Button jugar;
    Button facil;
    Button normal;
    Button dificil;
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
    int imgcarta1= 0;
    int imgcarta2= 0;
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
        setContentView(R.layout.activity_main);

        jugar = (Button)findViewById(R.id.Btnjugar);

        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView inputPlayer1 =(TextView)findViewById(R.id.id_name1);
                TextView inputPlayer2 = (TextView)findViewById(R.id.id_name2);

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.niveles);
                dialog.show();
                facil = (Button) dialog.findViewById(R.id.Btnfacil);
                normal = (Button) dialog.findViewById(R.id.Btnnormal);
                dificil = (Button) dialog.findViewById(R.id.Btndificil);



                /**CON ESTE BOTON SE INGRESA A EL NIVEL FACIL Y AQUI MISMO SE EJECUTA LA LOGICA DE DICHO NIVEL**/
                facil.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        setContentView(R.layout.nivelfacil);

                        player1= (TextView)findViewById(R.id.player1);
                        player2= (TextView)findViewById(R.id.player2);

                        jugador = (TextView) findViewById(R.id.turno);
                        regresar = (ImageButton) findViewById(R.id.Btnsalir);
                        reiniciar= (Button) findViewById(R.id.Btnreiniciar);
                        carta1 = (ImageView) findViewById(R.id.carta1);
                        carta2 = (ImageView) findViewById(R.id.carta2);
                        carta3 = (ImageView) findViewById(R.id.carta3);
                        carta4 = (ImageView) findViewById(R.id.carta4);
                        carta5 = (ImageView) findViewById(R.id.carta5);
                        carta6 = (ImageView) findViewById(R.id.carta6);
                        carta7 = (ImageView) findViewById(R.id.carta7);
                        carta8 = (ImageView) findViewById(R.id.carta8);

                        puntos1 = (TextView)findViewById(R.id.puntos1);
                        puntos2=(TextView) findViewById(R.id.puntos2);



                        ImageView imagview[]= {carta1, carta2, carta3, carta4, carta5, carta6, carta7, carta8 };

                        player1.setText(inputPlayer1.getText());
                        player2.setText(inputPlayer2.getText());

                        tur = (int) (Math.random() * 2) + 1;

                        if (tur == 1) {
                            jugador.setText(inputPlayer1.getText());

                        } else if (tur == 2) {
                            jugador.setText(inputPlayer2.getText());

                        }


                        List<Integer> lista = aleatorio();
                        for (int i= 0; i<imagview.length; i++){
                            imagview[i].setImageResource(lista.get(i));

                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                for (int i= 0; i<imagview.length; i++){
                                    imagview[i].setImageResource(android.R.color.transparent);
                                }
                            }
                        }, 1000);


                        for (int i= 0; i<imagview.length; i++){
                            final int pos =i;

                            imagview[i].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    animarcartas(imagview[pos], lista.get(pos));
                                    validar(imagview,lista,pos,inputPlayer1,inputPlayer2);

//
                                }
                            });
                        }

                        regresar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent( MainActivity.this,MainActivity.class));
                            }
                        });
                        reiniciar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int tur = (int) (Math.random() * 2) + 1;
                                if (tur == 1) {
                                    jugador.setText(inputPlayer1.getText());
                                    turno = 2;
                                } else {
                                    if (tur == 2) {
                                        jugador.setText((CharSequence) inputPlayer2.getText());
                                        turno = 1;
                                    }
                                }
                                puntos1.setText("0");
                                puntos2.setText("0");

                                List<Integer> lista = aleatorio();
                                for (int i= 0; i<imagview.length; i++){
                                    imagview[i].setImageResource(lista.get(i));

                                }
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        for (int i= 0; i<imagview.length; i++){
                                            imagview[i].setImageResource(android.R.color.transparent);
                                        }
                                    }
                                }, 1000);





                            }
                        });

                    }

                });

                /**AQUI SE INICIA EL CODIGO PARA EL NIVEL NORMAL**/
                normal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        setContentView(R.layout.activity_nivel_normal);

                        player1= (TextView)findViewById(R.id.namejugador1);
                        player2= (TextView)findViewById(R.id.namejugador2);
                        jugador= (TextView)findViewById(R.id.iDturno);
                        regresar= (ImageButton) findViewById(R.id.salirBtn);
                        reiniciar= (Button) findViewById(R.id.Btnreiniciar);
                        carta1 = (ImageView) findViewById(R.id.ncarta1);
                        carta2 = (ImageView) findViewById(R.id.ncarta2);
                        carta3 = (ImageView) findViewById(R.id.ncarta3);
                        carta4 = (ImageView) findViewById(R.id.ncarta4);
                        carta5 = (ImageView) findViewById(R.id.ncarta5);
                        carta6 = (ImageView) findViewById(R.id.ncarta6);
                        carta7 = (ImageView) findViewById(R.id.ncarta7);
                        carta8 = (ImageView) findViewById(R.id.ncarta8);
                        carta9 =  (ImageView) findViewById(R.id.ncarta9);
                        carta10 = (ImageView) findViewById(R.id.ncarta10);
                        carta11 = (ImageView) findViewById(R.id.ncarta11);
                        carta12 = (ImageView) findViewById(R.id.ncarta12);

                        puntos1 = (TextView)findViewById(R.id.puntuacion1);
                        puntos2 = (TextView)findViewById(R.id.puntuacion2);

                        ImageView level2[]= {carta1, carta2, carta3, carta4, carta5, carta6,carta7, carta8, carta9, carta10, carta11, carta12};

                        player1.setText(inputPlayer1.getText());
                        player2.setText(inputPlayer2.getText());

                        tur = (int) (Math.random() * 2) + 1;

                        if (tur == 1) {
                            jugador.setText(inputPlayer1.getText());

                        } else if (tur == 2) {
                            jugador.setText(inputPlayer2.getText());

                        }

                        List<Integer> lista = nivel2();
                        for (int i= 0; i<level2.length; i++){
                            level2[i].setImageResource(lista.get(i));

                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                for (int i= 0; i<level2.length; i++){
                                    level2[i].setImageResource(android.R.color.transparent);
                                }
                            }
                        }, 1001);


                        for (int i= 0; i<level2.length; i++){
                            final int pos =i;

                            //validar movimientos//
                            level2[i].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    animarcartas(level2[pos], lista.get(pos));
                                    validarnivel2(level2,lista, pos ,inputPlayer1, inputPlayer2);

                                }
                            });

                        }

                        regresar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent( MainActivity.this,MainActivity.class));

                            }
                        });
                        reiniciar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                int tur = (int) (Math.random() * 2) + 1;
                                if (tur == 1) {
                                    jugador.setText(inputPlayer1.getText());
                                    turno= 2;
                                } else {
                                    if (tur == 2) {
                                        jugador.setText(inputPlayer2.getText());
                                        turno = 1;
                                    }
                                }
                                puntos1.setText("0");
                                puntos2.setText("0");

                                List<Integer> lista = nivel2();
                                for (int i= 0; i<level2.length; i++){
                                    level2[i].setImageResource(lista.get(i));

                                }
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        for (int i= 0; i<level2.length; i++){
                                            level2[i].setImageResource(android.R.color.transparent);
                                        }
                                    }
                                }, 1001);



                            }
                        });
                    }
                });

                dificil.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        setContentView(R.layout.activity_nivel_dificil);

                        player1= (TextView)findViewById(R.id.play1);
                        player2= (TextView)findViewById(R.id.play2);

                        jugador= (TextView)findViewById(R.id.turnoid);
                        regresar= (ImageButton) findViewById(R.id.iDsalir);
                        reiniciar= (Button) findViewById(R.id.reiniciar);
                        carta1 = (ImageView) findViewById(R.id.c1);
                        carta2 = (ImageView) findViewById(R.id.c2);
                        carta3 = (ImageView) findViewById(R.id.c3);
                        carta4 = (ImageView) findViewById(R.id.c4);
                        carta5 = (ImageView) findViewById(R.id.c5);
                        carta6 = (ImageView) findViewById(R.id.c6);
                        carta7 = (ImageView) findViewById(R.id.c7);
                        carta8 = (ImageView) findViewById(R.id.c8);
                        carta9 = (ImageView) findViewById(R.id.c9);
                        carta10 =(ImageView) findViewById(R.id.c10);
                        carta11 =(ImageView) findViewById(R.id.c11);
                        carta12 =(ImageView) findViewById(R.id.c12);
                        carta13 =(ImageView) findViewById(R.id.c13);
                        carta14 =(ImageView) findViewById(R.id.c14);
                        carta15 =(ImageView) findViewById(R.id.c15);
                        carta16 =(ImageView) findViewById(R.id.c16);

                        puntos1=(TextView) findViewById(R.id.punto1);
                        puntos2=(TextView) findViewById(R.id.punto2);

                        ImageView level3[]= {carta1, carta2, carta3, carta4,
                                             carta5, carta6, carta7, carta8,
                                             carta9, carta10, carta11, carta12,
                                             carta13, carta14, carta15, carta16};

                        player1.setText(inputPlayer1.getText());
                        player2.setText(inputPlayer2.getText());

                        tur = (int) (Math.random() * 2) + 1;
                        if (tur == 1) {
                            jugador.setText(inputPlayer1.getText());
                        } else if (tur == 2) {
                            jugador.setText((CharSequence) inputPlayer2.getText());
                        }
                        List<Integer> lista= nivel3();

                        for (int i= 0; i< level3.length; i++){
                            level3[i].setImageResource(lista.get(i));
                        }

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                for (int i= 0; i<level3.length; i++){
                                    level3[i].setImageResource(android.R.color.transparent);
                                }
                            }
                        }, 1001);


                        for (int i= 0; i<level3.length; i++){
                            final int pos =i;

                            //validar movimientos//
                            level3[i].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    animarcartas(level3[pos], lista.get(pos));
                                    validarnivel3(level3,lista,pos,inputPlayer1,inputPlayer2);
                                    v.setEnabled(false);
                                }


                            });

                        }



                        regresar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent( MainActivity.this,MainActivity.class));

                            }
                        });
                        reiniciar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                int tur = (int) (Math.random() * 2) + 1;
                                if (tur == 1) {
                                    jugador.setText(inputPlayer1.getText());
                                    turno= 2;
                                } else {
                                    if (tur == 2) {
                                        jugador.setText((CharSequence) inputPlayer2.getText());
                                        turno= 1;
                                    }
                                }
                                puntos1.setText("0");
                                puntos2.setText("0");

                                List<Integer> lista = nivel3();
                                for (int i= 0; i<level3.length; i++){
                                    level3[i].setImageResource(lista.get(i));

                                }
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        for (int i= 0; i<level3.length; i++){
                                            level3[i].setImageResource(android.R.color.transparent);
                                        }
                                    }
                                }, 1001);


                            }
                        });
                    }
                });

            }

        });
    }
    private List<Integer> validar(ImageView[] imagview, List<Integer> lista, int pos, TextView inputPlayer1, TextView inputPlayer2){

                    if(!cartas){
                        carta_1= imagview[pos];
                        imgcarta1= lista.get(pos);
                        cartas= true;
                    }else {
                        carta_2= imagview[pos];
                        imgcarta2= lista.get(pos);
                        cartas= false;
                        boolean igual= (imgcarta1 == imgcarta2);
                        if(!igual){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    animarcartas(carta_1,android.R.color.transparent);
                                    animarcartas(carta_2,android.R.color.transparent);
                                    if (tur == 1) {
                                        jugador.setText(inputPlayer2.getText());
                                        tur = 2;
                                        //restar puntos//
                                        int puntosActuales= Integer.parseInt(puntos1.getText().toString());
                                        if(puntosActuales != 0){
                                            puntosActuales = puntosActuales - 50;
                                            puntos1.setText(puntosActuales + "");
                                        }

                                    } else if (tur == 2) {
                                        jugador.setText(inputPlayer1.getText());
                                        tur = 1;
                                        int puntosActuales = Integer.parseInt(puntos2.getText().toString());
                                        if (puntosActuales != 0) {
                                            puntosActuales = puntosActuales - 50;
                                            puntos2.setText(puntosActuales + "");
                                        }

                                    }

                                }
                            },1500);



                        }else{
                            // sumar puntos
                            if(tur == 1){
                                int puntosActuales= Integer.parseInt(puntos1.getText().toString());
                                puntosActuales = puntosActuales + 100;
                                puntos1.setText(puntosActuales + "");
                                Log.e("test", puntosActuales + " puntos 1");
                            } else if (tur == 2) {
                                int puntosActuales= Integer.parseInt(puntos2.getText().toString());
                                puntosActuales = puntosActuales + 100;
                                puntos2.setText(puntosActuales + "");
                                Log.e("test", puntosActuales + " puntos 2");

                            }


                        }
                    }
        return lista;
    }

    private List<Integer> validarnivel2(ImageView[] level2, List<Integer> lista, int pos, TextView inputPlayer1, TextView inputPlayer2){

        if (!cartas) {
            carta_1 = level2[pos];
            imgcarta1 = lista.get(pos);
            cartas = true;
        } else {
            carta_2 = level2[pos];
            imgcarta2 = lista.get(pos);
            cartas = false;
            boolean igual = (imgcarta1 == imgcarta2);
            if (!igual) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        animarcartas(carta_1, android.R.color.transparent);
                        animarcartas(carta_2, android.R.color.transparent);
                        if (tur == 1) {
                            jugador.setText(inputPlayer2.getText());
                            tur = 2;
                            //restar puntos//
                            int puntosActuales = Integer.parseInt(puntos1.getText().toString());
                            if (puntosActuales != 0) {
                                puntosActuales = puntosActuales - 50;
                                puntos1.setText(puntosActuales + "");
                            }

                        } else if (tur == 2) {
                            jugador.setText(inputPlayer1.getText());
                            tur = 1;
                            int puntosActuales = Integer.parseInt(puntos2.getText().toString());
                            if (puntosActuales != 0) {
                                puntosActuales = puntosActuales - 50;
                                puntos2.setText(puntosActuales + "");
                            }

                        }

                    }
                }, 1500);
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


            }
        }
        return lista;
    }
    private List<Integer> validarnivel3(ImageView[] level3, List<Integer> lista, int pos, TextView inputPlayer1, TextView inputPlayer2){
        //validar//
        if(!cartas){
            carta_1= level3[pos];
            imgcarta1= lista.get(pos);
            cartas= true;
        }else {
            carta_2= level3[pos];
            imgcarta2= lista.get(pos);
            cartas= false;
            boolean igual= (imgcarta1 == imgcarta2);
            if(!igual){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        animarcartas(carta_1,android.R.color.transparent);
                        animarcartas(carta_2,android.R.color.transparent);
                        if (tur == 1) {
                            jugador.setText(inputPlayer2.getText());
                            tur = 2;
                            //restar puntos//
                            int puntosActuales= Integer.parseInt(puntos1.getText().toString());
                            if(puntosActuales != 0){
                                puntosActuales = puntosActuales - 50;
                                puntos1.setText(puntosActuales + "");
                            }

                        } else if (tur == 2) {
                            jugador.setText(inputPlayer1.getText());
                            tur = 1;
                            int puntosActuales= Integer.parseInt(puntos2.getText().toString());
                            if(puntosActuales != 0){
                                puntosActuales = puntosActuales - 50;
                                puntos2.setText(puntosActuales + "");
                            }

                        }

                    }
                },1500);
            }else{
                // sumar puntos
                if(tur == 1){
                    int puntosActuales= Integer.parseInt(puntos1.getText().toString());
                    puntosActuales = puntosActuales + 100;
                    puntos1.setText(puntosActuales + "");
                    Log.e("test", puntosActuales + " puntos 1");
                } else if (tur == 2) {
                    int puntosActuales= Integer.parseInt(puntos2.getText().toString());
                    puntosActuales = puntosActuales + 100;
                    puntos2.setText(puntosActuales + "");
                    Log.e("test", puntosActuales + " puntos 2");

                }


            }
        }

        return lista;
    }


    private void animarcartas(ImageView img,int image){
        ViewPropertyAnimator animator= img.animate().withLayer().rotationY(90).setDuration(400);
        animator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation){
                super.onAnimationEnd(animation);
                if(img.getRotationY()==90){
                    img.setImageResource(image);
                    img.animate().rotationY(0).setDuration(400).setListener(null);
                }
            }
        });

    }
    private List<Integer> aleatorio(){

        int imgs[]={R.drawable.ponyo, R.drawable.papaponyo, R.drawable.bigwoman, R.drawable.saske};
        ArrayList<Integer> lista = new ArrayList<Integer>();
        while (lista.size() < 8){
            int imgAleatoria= imgs[(int) (Math.random()*4)];
            int cont = 0;
            for (int i= 0; i < lista.size(); i++){
                if (cont >= 2){
                    break;
                }
                if (lista.get(i)== imgAleatoria){
                    cont++;
                }
            }
            if (cont < 2){
                lista.add(imgAleatoria);
            }
        }
        return lista;
    }

    private List<Integer> nivel2(){

        int imags[]={R.drawable.mei, R.drawable.espantapajaros, R.drawable.totoro,
                R.drawable.howl, R.drawable.cositablanca, R.drawable.cositaazul};
        List<Integer> lista = new ArrayList<Integer>();
        while (lista.size() < 12) {
            int imgAleatoria = imags[(int) (Math.random() * 6)];
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
    private List<Integer> nivel3(){
        int img[]={R.drawable.arriety, R.drawable.princesa, R.drawable.chico,
                R.drawable.kiki, R.drawable.haru, R.drawable.marnie,
                R.drawable.castillo,R.drawable.nausicaa};
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



}