package com.example.pacmanv11;

import android.widget.ImageView;
import android.widget.TextView;

import org.example.logica.SingletonFireBase;
import org.example.logica.usuario;

public class hiloPacman extends Thread{

    private ImageView [][]matriz;
    private TextView puntaje;
    private TextView puntaje2;

    private static int puntos = 0;
    private static String dir = "quieto";
    private int xi = 0;
    private int yi = 0;
    private int xf = 0;
    private int yf = 0;
    private ImageView imagen;
    private volatile boolean apagar = false;
    hiloFantasma_3 hilo3;
    private usuario user2;
    private int time;


    public hiloPacman(ImageView[][]m, TextView puntaje,TextView puntaje2, ImageView image, int time, hiloFantasma_3 hilo3) {
        this.puntaje = puntaje;
        this.puntaje2 = puntaje2;
        this.imagen = image;
        this.matriz = m;
        this.time = time;
        this.hilo3 = hilo3;

    }

    public void run(){


        System.out.println("me volvi a ejecutar");
        int auxAxul = 0;

        while (!apagar) {

            MainActivity.getInstance().setPuntos(puntos);

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

                SingletonFireBase.getInstance().guardar(MainActivity.getInstance().getUser(), MainActivity.getInstance().getNom() ,puntos, MainActivity.getInstance().getEstado(),MainActivity.getInstance().getGalleta(),MainActivity.getInstance().getRestart());

       //         puntaje.setText("Score: " + puntos);


            }
            if ((String) matriz[xf][yf].getTag() == "galletota") {
                matriz[xi][yi].setImageResource(R.mipmap.fondo);
                matriz[xi][yi].setTag("vacio");
                matriz[xf][yf].setImageResource(R.mipmap.pac_izquierda);
                matriz[xf][yf].setTag("pac");
                puntos = puntos + 50;
            //    puntaje.setText("Score: " + puntos);
             //   user2.setGalleta(true);
                SingletonFireBase.getInstance().guardar(user2.getCodigo(),user2.getNombre() ,user2.getPuntaje(),user2.getEstado(), true,user2.getRestart());
               // MainActivity.getInstance().setGalleta(true);

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

    public void editar(usuario user2){
        this.user2 = user2;
    }

}
