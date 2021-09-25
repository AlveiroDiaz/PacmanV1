package com.example.pacmanv11;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private TextView puntaje;
    private static int puntos;
    private GridLayout gridLayout;
    private ImageView imagen ;
    private ImageView [][]casillas = new ImageView[31][26];
    private Button reiniciar;
    private float x1, x2;
    private float y1, y2;
    private Juego game ;
    private hiloPacIzq hiloPac;
    private  Runnable r;
    private  Thread t;
    private  int lab[][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  nuevoJuego();
        this.game = new Juego();
        this.lab = game.Datos();
        //this.imagen.setImageResource(R.mipmap.galleta);
        this.linearLayout = findViewById(R.id.linearlayout);
        this.imagen = findViewById(R.id.imageView);
        this.puntaje = findViewById(R.id.puntaje);
        this.gridLayout = new GridLayout(this);
        this.reiniciar = new Button(this);
        this.gridLayout.setColumnCount(26);
        this.gridLayout.setRowCount(31);
        this.linearLayout.addView(this.gridLayout);
        this.linearLayout.addView(this.reiniciar);
        reiniciar.setText("Reiniciar");

        Display display = getWindowManager().getDefaultDisplay();
        Point tam = new Point();
        display.getSize(tam);
        int ancho = tam.x;
        int alto = tam.y;



             nuevoJuego();


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


      /*  r = new hiloPacIzq(casillas,puntaje,imagen,600);
         t = new Thread(r);
         t.start();*/

        hiloPac = new hiloPacIzq(casillas,puntaje,imagen,600);
        hiloPac.start();
        hiloFantasma_1 fantasma_1 = new hiloFantasma_1(casillas);
        fantasma_1.start();
        int cont = 0;
        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hiloPac.acabar();
                nuevoJuego();
                hiloPac = new hiloPacIzq(casillas,puntaje,imagen,600);
                hiloPac.reinicioP();
                hiloPac.start();

            }
        });


    }

    private void nuevoJuego() {


        gridLayout.removeAllViews();

        for(int i=0; i<31;i++){
            for(int j=0; j<26; j++){

                this.casillas[i][j] = new ImageView(this);

                if(lab[i][j] == -1){
                    this.casillas[i][j].setImageResource(R.mipmap.galleta);
                    this.casillas[i][j].setTag("galleta");
                }

                if(lab[i][j] == 0){
                    this.casillas[i][j].setImageResource(R.mipmap.fondo);
                    this.casillas[i][j].setTag("vacio");
                }
                if(lab[i][j]  == 1){
                    this.casillas[i][j].setImageResource(R.mipmap.esquina_sup_der);
                    this.casillas[i][j].setTag("pared");
                }
                if(lab[i][j]  == 2){
                    this.casillas[i][j].setImageResource(R.mipmap.esquina_sup_izq);
                    this.casillas[i][j].setTag("pared");
                }
                if(lab[i][j]  == 3){
                    this.casillas[i][j].setImageResource(R.mipmap.inferior_der);
                    this.casillas[i][j].setTag("pared");
                }
                if(lab[i][j]  == 4){
                    this.casillas[i][j].setImageResource(R.mipmap.inferior_izq);
                    this.casillas[i][j].setTag("pared");
                }
                if(lab[i][j]  == 5){
                    this.casillas[i][j].setImageResource(R.mipmap.horizo_arri);
                    this.casillas[i][j].setTag("pared");
                }
                if(lab[i][j]  == 6){
                    this.casillas[i][j].setImageResource(R.mipmap.horizo_aba);
                    this.casillas[i][j].setTag("pared");
                }
                if(lab[i][j]  == 7){
                    this.casillas[i][j].setImageResource(R.mipmap.vertical_izq);
                    this.casillas[i][j].setTag("pared");
                }
                if(lab[i][j]  == 8){
                    this.casillas[i][j].setImageResource(R.mipmap.vertical_der);
                    this.casillas[i][j].setTag("pared");
                }
                if(lab[i][j]  == 9){
                    this.casillas[i][j].setImageResource(R.mipmap.pac_izquierda);
                    this.casillas[i][j].setTag("pac");
                }
                if(lab[i][j]  == 10){
                    this.casillas[i][j].setImageResource(R.mipmap.fantasma1);
                    this.casillas[i][j].setTag("fan1");
                }
                if(lab[i][j]  == 11){
                    this.casillas[i][j].setImageResource(R.mipmap.puerta);
                    this.casillas[i][j].setTag("pared");
                }

                this.gridLayout.addView(this.casillas[i][j]);
            }
        }



    }


    private void movimiento() {
        String dir = null;
        float dx = Math.abs(this.x2 - this.x1);
        float dy = Math.abs(this.y2 - this.y1);

        if(dx > dy){
            if(this.x2 > this.x1){
                hiloPac.izq();
            }else{
                hiloPac.derecha();
            }
        }else{
            if(this.y2 > this.y1){
                hiloPac.arriba();
            }else{
                hiloPac.aba();
            }
        }
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