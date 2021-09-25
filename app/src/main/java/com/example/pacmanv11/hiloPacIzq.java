package com.example.pacmanv11;

import android.widget.ImageView;
import android.widget.TextView;

public class hiloPacIzq extends Thread{

    private ImageView [][]matriz;
    private TextView puntaje;
    private static int puntos = 0;
    private static String dir = "izq";
    private int xi = 0;
    private int yi = 0;
    private int xf = 0;
    private int yf = 0;
    private ImageView imagen;
    private volatile boolean apagar = false;
    private int time;


    public hiloPacIzq(ImageView[][]m,TextView puntaje, ImageView image, int time ) {
        this.puntaje = puntaje;
        this.imagen = image;
        this.matriz = m;
        this.time = time;
    }

    public void run(){


        System.out.println("me volvi a ejecutar");


        while (!apagar) {

            System.out.println("ejectuta hilo " + getName());
            for (int i = 0; i < 31; i++) {
                for (int j = 0; j < 26; j++) {

                    String aux = (String) matriz[i][j].getTag();

                    if (aux == "pac") {
                        xi = i;
                        yi = j;
                    }
                }
            }

            if (dir == "der") {
                xf = xi;
                yf = yi + 1;
            }
            if (dir == "izq") {
                xf = xi;
                yf = yi - 1;
            }
            if (dir == "arr") {
                xf = xi - 1;
                yf = yi;
            }
            if (dir == "aba") {
                xf = xi + 1;
                yf = yi;
            }


            if ((String) matriz[xf][yf].getTag() == "vacio") {
                matriz[xi][yi].setImageResource(R.mipmap.fondo);
                matriz[xi][yi].setTag("vacio");
                matriz[xf][yf].setImageResource(R.mipmap.pac_izquierda);
                matriz[xf][yf].setTag("pac");
            }
            if ((String) matriz[xf][yf].getTag() == "galleta") {
                matriz[xi][yi].setImageResource(R.mipmap.fondo);
                matriz[xi][yi].setTag("vacio");
                matriz[xf][yf].setImageResource(R.mipmap.pac_izquierda);
                matriz[xf][yf].setTag("pac");
                puntos = puntos + 10;
                puntaje.setText("Puntaje: " + puntos);

            }
            if ((String) matriz[xf][yf].getTag() == "pared") {
                xf = xi;
                yf = yi;
            }
            if ((String) matriz[xf][yf].getTag() == "fan1") {

                imagen.setImageResource(R.mipmap.perdio);
                break;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().isInterrupted();
            }


        }

;
        System.out.println("Murio el hilo");

    }




    public void reinicioP(){
        puntos = 0;
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
        this.apagar = true;
    }

}
