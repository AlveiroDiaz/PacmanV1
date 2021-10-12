package com.example.pacmanv11;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.example.logica.SingletonFireBase;
import org.example.logica.usuario;


public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private TextView puntaje;
    private TextView puntaje2;
    private static TextView galleta1;
    private int contGalletas = 0;
    private TextView galleta2;
    private TextView galleta3;
    private TextView galleta4;
    private static int usuario = 0;
    private static String nombre;
    private static int puntos;
    private static boolean estado = true;
    public static boolean galleta = false;
    private GridLayout gridLayout;
    private ImageView imagen;
    private ImageView[][] casillas = new ImageView[31][26];
    private Button reiniciar;
    private float x1, x2;
    private float y1, y2;
    private boolean apagar;
    private Juego game;
    private static hiloPacman hiloPac;
    private static hiloFantasma_1 fantasma_1;
    private static hiloFantasma_2 fantasma_2;
    private static hiloFantasma_3 fantasma_3;
    private int lab[][];
    private  usuario user2;
    private static MainActivity instance = new MainActivity();
    private Button base;

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
        this.puntaje2 = findViewById(R.id.Score2);
        this.gridLayout = new GridLayout(this);
        this.reiniciar = findViewById(R.id.restart);
        this.base = findViewById(R.id.add);
        this.reiniciar.setBackgroundColor(Color.parseColor("#FA730A"));
        this.gridLayout.setColumnCount(26);
        this.gridLayout.setRowCount(31);
        this.galleta1 = new TextView(this);
        this.galleta2 = new TextView(this);
        this.galleta3 = new TextView(this);
        this.galleta4 = new TextView(this);
        this.galleta1.setTextSize(20);
        this.galleta2.setTextSize(20);
        this.galleta1.setGravity(View.TEXT_ALIGNMENT_CENTER);
        this.galleta2.setGravity(View.TEXT_ALIGNMENT_CENTER);
        this.galleta1.setTextColor(Color.RED);
        this.galleta2.setTextColor(Color.RED);
        this.linearLayout.addView(this.gridLayout);
        this.linearLayout.addView(this.galleta1);
        this.linearLayout.addView(this.galleta2);
        this.linearLayout.addView(this.galleta3);
        this.linearLayout.addView(this.galleta4);
        linearLayout.setGravity(View.TEXT_ALIGNMENT_CENTER);

      //  this.linearLayout.addView(this.reiniciar);
     //  reiniciar.setText("Reiniciar");

        Display display = getWindowManager().getDefaultDisplay();
        Point tam = new Point();
        display.getSize(tam);
        int ancho = tam.x;
        int alto = tam.y;


        nuevoJuego();
        System.out.println("Soy el usuario" + getUser());

        View pantalla = getWindow().peekDecorView();
        pantalla.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    tocarPantalla(event.getX(), event.getY());
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    soltarPantalla(event.getX(), event.getY());
                    //
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



         fantasma_1= new hiloFantasma_1(casillas, imagen);
        fantasma_1.start();
        fantasma_2 = new hiloFantasma_2(casillas, imagen);
        fantasma_2.start();
        fantasma_3 = new hiloFantasma_3(casillas, imagen);
        fantasma_3.start();
        hiloPac = new hiloPacman(casillas, puntaje,puntaje2, imagen, 600,fantasma_3);
        hiloPac.start();
        int cont = 0;

        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hiloPac.acabar();
                fantasma_1.acabar();
                fantasma_2.acabar();
                fantasma_3.acabar();
                nuevoJuego();
                fantasma_1 = new hiloFantasma_1(casillas, imagen);
                fantasma_1.start();
                fantasma_2 = new hiloFantasma_2(casillas, imagen);
                fantasma_2.start();
                fantasma_3 = new hiloFantasma_3(casillas, imagen);
                fantasma_3.start();
                hiloPac = new hiloPacman(casillas, puntaje,puntaje2, imagen, 600,fantasma_3);
                hiloPac.quieto();
                hiloPac.reinicioP();
                hiloPac.start();
            }
        });

        ValueEventListener estudianteListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                DataSnapshot primero = null;
                DataSnapshot ultimo = null;

                if( dataSnapshot.getChildrenCount() != 0) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        ultimo = snapshot;
                        usuario user = ultimo.getValue(usuario.class);

                        if(user.getCodigo() == getUser()){
                            setGalleta(user.getGalleta());

                            if(galleta == true){
                                galleta = false;
                                SingletonFireBase.getInstance().guardar(usuario, nombre,puntos,estado,galleta);
                                contGalletas = contGalletas+1;
                                galleta1.setText(user2.getNombre() + " SE COMIO UNA GALLETA ");
                                galleta2.setText("Velocidad Fantasmas X" + contGalletas);
                                fantasma_1.tiempo();
                            }
                        }

                        if(user.getCodigo() != getUser()){

                            user2 = user;
                            hiloPac.editar(user);
                            puntaje2.setText(user.getNombre() +" : " +user.getPuntaje());

                            if(user.getEstado() == false){
                                imagen.setImageResource(R.mipmap.ganador);
                                hiloPac.acabar();
                                fantasma_1.acabar();
                                fantasma_2.acabar();
                                fantasma_3.acabar();
                            }

                        }
                    }




                    }



                }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Error", "loadPost:onCancelled", databaseError.toException());
            }
        };
        SingletonFireBase.getInstance().getmDatabase().child("Usuarios").addValueEventListener(estudianteListener);



    }


    private void nuevoJuego() {

        imagen.setImageResource(R.mipmap.pacman);
        gridLayout.removeAllViews();

        for (int i = 0; i < 31; i++) {
            for (int j = 0; j < 26; j++) {

                this.casillas[i][j] = new ImageView(this);

                if (lab[i][j] == -2) {
                    this.casillas[i][j].setImageResource(R.mipmap.galletota);
                    this.casillas[i][j].setTag("galletota");
                }
                if (lab[i][j] == -1) {
                    this.casillas[i][j].setImageResource(R.mipmap.galleta);
                    this.casillas[i][j].setTag("galleta");
                }
                if (lab[i][j] == 0) {
                    this.casillas[i][j].setImageResource(R.mipmap.fondo);
                    this.casillas[i][j].setTag("vacio");
                }
                if (lab[i][j] == 1) {
                    this.casillas[i][j].setImageResource(R.mipmap.esquina_sup_der);
                    this.casillas[i][j].setTag("pared");
                }
                if (lab[i][j] == 2) {
                    this.casillas[i][j].setImageResource(R.mipmap.esquina_sup_izq);
                    this.casillas[i][j].setTag("pared");
                }
                if (lab[i][j] == 3) {
                    this.casillas[i][j].setImageResource(R.mipmap.inferior_der);
                    this.casillas[i][j].setTag("pared");
                }
                if (lab[i][j] == 4) {
                    this.casillas[i][j].setImageResource(R.mipmap.inferior_izq);
                    this.casillas[i][j].setTag("pared");
                }
                if (lab[i][j] == 5) {
                    this.casillas[i][j].setImageResource(R.mipmap.horizo_arri);
                    this.casillas[i][j].setTag("pared");
                }
                if (lab[i][j] == 6) {
                    this.casillas[i][j].setImageResource(R.mipmap.horizo_aba);
                    this.casillas[i][j].setTag("pared");
                }
                if (lab[i][j] == 7) {
                    this.casillas[i][j].setImageResource(R.mipmap.vertical_izq);
                    this.casillas[i][j].setTag("pared");
                }
                if (lab[i][j] == 8) {
                    this.casillas[i][j].setImageResource(R.mipmap.vertical_der);
                    this.casillas[i][j].setTag("pared");
                }
                if (lab[i][j] == 9) {
                    this.casillas[i][j].setImageResource(R.mipmap.pac_izquierda);
                    this.casillas[i][j].setTag("pac");
                }
                if (lab[i][j] == 10) {
                    this.casillas[i][j].setImageResource(R.mipmap.fantasma1);
                    this.casillas[i][j].setTag("fan1");
                }
                if (lab[i][j] == 11) {
                    this.casillas[i][j].setImageResource(R.mipmap.puerta);
                    this.casillas[i][j].setTag("pared");
                }
                if (lab[i][j] == 12) {
                    this.casillas[i][j].setImageResource(R.mipmap.fantasma2);
                    this.casillas[i][j].setTag("fan2");
                }
                if(lab[i][j] == 13){
                    this.casillas[i][j].setImageResource(R.mipmap.fantasma3);
                    this.casillas[i][j].setTag("fan3");
                }
          /*      if(lab[i][j] == 14){
                    this.casillas[i][j].setImageResource(R.mipmap.fantasma4);
                    this.casillas[i][j].setTag("fan4");
                }*/
                this.gridLayout.addView(this.casillas[i][j]);
            }
        }
    }


    private void movimiento() {
        String dir = null;
        float dx = Math.abs(this.x2 - this.x1);
        float dy = Math.abs(this.y2 - this.y1);

        if (dx > dy) {
            if (this.x2 > this.x1) {
                hiloPac.izq();
            } else {
                hiloPac.derecha();
            }
        } else {
            if (this.y2 > this.y1) {
                hiloPac.arriba();
            } else {
                hiloPac.aba();
            }
        }
    }



    public void setUsuario(int num){
        this.usuario = num;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public usuario getUser2() {
        return user2;
    }

    private void soltarPantalla(float x, float y) {
        this.x2 = x;
        this.y2 = y;
    }

    private void tocarPantalla(float x, float y) {
        this.x1 = x;
        this.y1 = y;
    }

    public static MainActivity getInstance() {
        return instance;
    }

    public int getUser(){
        return this.usuario;
    }

    public String getNom(){
        return this.nombre;
    }

    public  boolean getEstado() {
        return estado;
    }

    public  boolean getGalleta() {
        return galleta;
    }

    public  void setGalleta(boolean galleta) {
        this.galleta = galleta;
    }

    public static void acabar(){

        estado = false;
        SingletonFireBase.getInstance().guardar(usuario, nombre ,puntos, estado, galleta );
        hiloPac.acabar();
        fantasma_1.acabar();
        fantasma_2.acabar();
        fantasma_3.acabar();
       // fantasma_4.acabar();



    }
}