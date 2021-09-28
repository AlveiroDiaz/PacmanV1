package com.example.pacmanv11;

import android.widget.ImageView;
import android.widget.TextView;

public class hiloPacman extends Thread{

    private ImageView [][]matriz;
    private TextView puntaje;
    private static int puntos = 0;
    private static String dir = "quieto";
    private int xi = 0;
    private int yi = 0;
    private int xf = 0;
    private int yf = 0;
    private ImageView imagen;
    private volatile boolean apagar = false;
    hiloFantasma_3 hilo3;
    private int time;


    public hiloPacman(ImageView[][]m, TextView puntaje, ImageView image, int time, hiloFantasma_3 hilo3) {
        this.puntaje = puntaje;
        this.imagen = image;
        this.matriz = m;
        this.time = time;
        this.hilo3 = hilo3;
    }

    public void run(){


        System.out.println("me volvi a ejecutar");
        int auxAxul = 0;

        while (!apagar) {

       /*    if(auxAxul<15 && auxAxul>1){
               auxAxul++;
           }
           if(auxAxul > 15){
               hilo3.azulOff();
               auxAxul = 0;
           }*/

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

            if(dir== "quieto"){
                xf = xi;
                yf = yi;
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
                puntaje.setText("Score: " + puntos);

            }
            if ((String) matriz[xf][yf].getTag() == "galletota") {
                matriz[xi][yi].setImageResource(R.mipmap.fondo);
                matriz[xi][yi].setTag("vacio");
                matriz[xf][yf].setImageResource(R.mipmap.pac_izquierda);
                matriz[xf][yf].setTag("pac");
                //hilo3.azul();
                puntos = puntos + 50;
                puntaje.setText("Score: " + puntos);
               // auxAxul++;

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
                Thread.sleep(400);
            } catch (InterruptedException e) {
                Thread.currentThread().isInterrupted();
            }


        }


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
    public void  quieto(){
        dir = "quieto";
    }

    public void acabar() {
        this.apagar = true;
    }

}
