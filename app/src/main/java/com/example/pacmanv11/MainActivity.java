package com.example.pacmanv11;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private GridLayout gridLayout;
    private ImageView [][]casillas = new ImageView[31][26];
    private float x1, x2;
    private float y1, y2;
    private Juego game ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.game = new Juego();

        this.linearLayout = findViewById(R.id.linearlayout);
        this.gridLayout = new GridLayout(this);
        this.gridLayout.setColumnCount(26);
        this.gridLayout.setRowCount(31);
        this.linearLayout.addView(this.gridLayout);
        Display display = getWindowManager().getDefaultDisplay();
        Point tam = new Point();
        display.getSize(tam);
        int ancho = tam.x;
        int alto = tam.y;



        /*
            1 = esq sup izq
            2 = esq sup der
            3 = esq inf izq
            4 = esq inf der
            5 = horiontal arriba
            6 = horizontal abajo
            7 = vertical izq
            8 = vertical der
            9 = pacman
         */



        int [][]laberinto = new int [31][26];

                int lab[][] = {
                             {1,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,2},
                             {7,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,1,5,2,0,0,1,5,2,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,7,0,8,0,0,7,0,8,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,3,6,4,0,0,3,6,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,7,0,1,5,5,5,5,5,5,5,2,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,7,0,7,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,7,0,7,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,8},
                             {7,0,0,6,6,6,6,7,0,3,6,6,6,6,6,6,6,4,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,0,0,0,0,0,0,9,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,0,6,6,6,0,7,0,0,6,6,6,6,6,6,6,0,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
                             {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
                             {3,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,4}
                            };




                for(int i=0; i<31;i++){
                    for(int j=0; j<26; j++){

                        this.casillas[i][j] = new ImageView(this);

                        if(lab[i][j] == 0){
                            this.casillas[i][j].setImageResource(R.mipmap.fondo);
                            this.casillas[i][j].setTag("vacio");
                        }
                        if(lab[i][j]  == 1){
                            this.casillas[i][j].setImageResource(R.mipmap.esquina_sup_der);
                            this.casillas[i][j].setTag("sup_izq");
                        }
                        if(lab[i][j]  == 2){
                            this.casillas[i][j].setImageResource(R.mipmap.esquina_sup_izq);
                            this.casillas[i][j].setTag("sup_der");
                        }
                        if(lab[i][j]  == 3){
                            this.casillas[i][j].setImageResource(R.mipmap.inferior_der);
                            this.casillas[i][j].setTag("inf_izq");
                        }
                        if(lab[i][j]  == 4){
                            this.casillas[i][j].setImageResource(R.mipmap.inferior_izq);
                            this.casillas[i][j].setTag("ind_der");
                        }
                        if(lab[i][j]  == 5){
                            this.casillas[i][j].setImageResource(R.mipmap.horizo_arri);
                            this.casillas[i][j].setTag("hor_arr");
                        }
                        if(lab[i][j]  == 6){
                            this.casillas[i][j].setImageResource(R.mipmap.horizo_aba);
                            this.casillas[i][j].setTag("hor_aba");
                        }
                        if(lab[i][j]  == 7){
                            this.casillas[i][j].setImageResource(R.mipmap.vertical_izq);
                            this.casillas[i][j].setTag("ver_izq");
                        }
                        if(lab[i][j]  == 8){
                            this.casillas[i][j].setImageResource(R.mipmap.vertical_der);
                            this.casillas[i][j].setTag("ver_der");
                        }
                        if(lab[i][j]  == 9){
                            this.casillas[i][j].setImageResource(R.mipmap.pac_izquierda);
                            this.casillas[i][j].setTag("pac");
                        }

                        this.gridLayout.addView(this.casillas[i][j]);
                    }
                }





           /*
            1 = esq sup izq
            2 = esq sup der
            3 = esq inf izq
            4 = esq inf der
            5 = horiontal arriba
            6 = horizontal abajo
            7 = vertical izq
            8 = vertical der
            9 = pacman
         */

        casillas[0][0].setTag("esq_izq");


        View pantalla = getWindow().peekDecorView();
        pantalla.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    tocarPantalla(event.getX(), event.getY());
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    soltarPantalla(event.getX(), event.getY());
                    movimiento();
                }
                return false;
            }
        });

        pantalla.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );




    }




    private void movimiento() {
        hiloPacIzq hilo = new hiloPacIzq(casillas);
        hiloPacDer hiloDer = new hiloPacDer(casillas);;
        hiloPacArr hiloPacArr = new hiloPacArr(casillas);
        hiloPacAba hiloPacAba = new hiloPacAba(casillas);
        float dx = Math.abs(this.x2 - this.x1);
        float dy = Math.abs(this.y2 - this.y1);
        String dir = null;


            hiloDer.acabar();
            hilo.acabar();
            hiloPacAba.acabar();
            hiloPacArr.acabar();





        if(dx > dy){
            if(this.x2 > this.x1){
                hilo.start();

            }else{

                hiloDer.start();

            }
        }else{
            if(this.y2 > this.y1){
                hiloPacArr.start();

            }else{
                hiloPacAba.start();
            }
        }
     //   Toast.makeText(getApplicationContext(), dir, Toast.LENGTH_SHORT).show();
    }



    private void soltarPantalla(float x, float y) {
        this.x2 = x;
        this.y2 = y;
    }

    private void tocarPantalla(float x, float y) {
        this.x1 = x;
        this.y1 = y;
    }
}