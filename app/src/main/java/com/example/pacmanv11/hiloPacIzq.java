package com.example.pacmanv11;

import android.widget.ImageView;
import android.widget.TextView;

public class hiloPacIzq extends Thread{

    private ImageView [][]matriz;
    private TextView puntaje;
    private static int puntos = 0;
    private static String dir;
    private int xi = 0;
    private int yi = 0;
    private int xf = 0;
    private int yf = 0;
    private ImageView imagen;
    private static boolean apagar;


    public hiloPacIzq(ImageView[][]m,TextView puntaje, ImageView image ) {
        this.puntaje = puntaje;
        this.imagen = image;
        this.matriz = m;
    }

    public void run(){


        System.out.println("me volvi a ejecutar");


        while (apagar){


            for(int i=0; i<31;i++){
                for(int j=0; j<26; j++) {

                    String aux = (String) matriz[i][j].getTag();

                    if( aux == "pac"){
                        xi = i;
                        yi = j;
                    }

                }
            }


            if(dir == "der"){
                xf=xi;
                yf=yi+1;
            }
            if(dir == "izq"){
                xf=xi;
                yf=yi-1;
            }
            if(dir == "arr"){
                xf=xi-1;
                yf=yi;
            }
            if(dir == "aba"){
                xf=xi+1;
                yf=yi;
            }





            if((String)matriz[xf][yf].getTag() == "vacio" ) {
                matriz[xi][yi].setImageResource(R.mipmap.fondo);
                matriz[xi][yi].setTag("vacio");
                matriz[xf][yf].setImageResource(R.mipmap.pac_izquierda);
                matriz[xf][yf].setTag("pac");
            }
            if( (String)matriz[xf][yf].getTag() == "galleta" ) {
                matriz[xi][yi].setImageResource(R.mipmap.fondo);
                matriz[xi][yi].setTag("vacio");
                matriz[xf][yf].setImageResource(R.mipmap.pac_izquierda);
                matriz[xf][yf].setTag("pac");
                puntos = puntos +10;
                puntaje.setText("Puntaje: " + puntos);

            }
            if((String)matriz[xf][yf].getTag() == "pared"){
                xf = xi;
                yf = yi;
            }
            if((String)matriz[xf][yf].getTag() == "fan1"){

                imagen.setImageResource(R.mipmap.perdio);
                break;
            }



            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


    public  void start(){

        this.apagar = true;
        this.puntos = 0;
        this.dir = "izq";
        new Thread(this).start();
    }


    public void derecha(){

        dir = "izq";

    }
    public void arriba(){

        dir = "aba";

    }
    public void aba(){

        dir = "arr";

    }
    public void izq(){

        dir = "der";

    }

    public void acabar() {
        this.apagar = false;
        Thread.interrupted();

    }

}
