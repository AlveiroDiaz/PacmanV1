package com.example.pacmanv11;

import android.widget.ImageView;

public class hiloFantasma_2 extends Thread{



    private ImageView[][]matriz;
    private String dir;
    private int xiPac = 0;
    private int yiPac= 0;
    private int xi = 0;
    private int yi = 0;
    private int xf = 0;
    private int yf = 0;
    private static int time = 600;
    private ImageView imagen;
    private  volatile boolean apagar = false;


    public hiloFantasma_2(ImageView[][] matriz,ImageView imagen) {
        this.matriz = matriz;
        this.imagen = imagen;

    }

    public void run() {


        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        matriz[13][12].setImageResource(R.mipmap.fondo);
        matriz[13][12].setTag("vacio");
        matriz[11][13].setImageResource(R.mipmap.fantasma2);
        matriz[11][13].setTag("fan2");


        while (!apagar) {

            for (int i = 0; i < 31; i++) {
                for (int j = 0; j < 26; j++) {

                    String aux = (String) matriz[i][j].getTag();

                    if (aux == "pac") {
                        xiPac = i;
                        yiPac = j;
                    }

                }
            }

            for (int i = 0; i < 31; i++) {
                for (int j = 0; j < 26; j++) {

                    String aux = (String) matriz[i][j].getTag();

                    if (aux == "fan2") {
                        xi = i;
                        yi = j;
                    }
                }
            }


            int der = 0;
            int izq = 0;
            int arr = 0;
            int aba = 0;
            int igualHor = 0;
            int igualVer = 0;


            yf = yi;
            xf = xi;
            if (xiPac == xf) {

                if(yiPac>yf){
                    yf = yf +1;
                    if(matriz[xf][yf].getTag() == "pared"){
                        yf = yi -1;
                    }
                }else{
                    yf = yi -1;
                    if(matriz[xf][yf].getTag() == "pared"){
                        yf = yf +1;
                    }
                }

            }


            if (xiPac > xf) {
                xf = xi + 1;
                if(matriz[xf][yf].getTag() == "pared"){
                    xf = xf -1;
                    if(yiPac>yf){
                        yf = yf +1;
                        if(matriz[xf][yf].getTag() == "pared"){
                            yf = yi -1;
                        }
                    }else{
                        yf = yi -1;
                        if(matriz[xf][yf].getTag() == "pared"){
                            yf = yf +1;
                        }
                    }
                }
            }

            if(xiPac < xf){
                xf = xf -1;
                if(matriz[xf][yf].getTag() == "pared"){
                    xf = xf +1;
                    if(yiPac>yf){
                        yf = yf +1;
                        if(matriz[xf][yf].getTag() == "pared"){
                            yf = yf -2;
                        }
                    }
                    if(yiPac<yf){
                        yf = yf -1;
                        if(matriz[xf][yf].getTag() == "pared"){
                            yf = yf +1;
                        }
                    }
                }
            }

      //      System.out.println("xf"+xf + "yf"+yf);


            if ((String) matriz[xf][yf].getTag() == "galleta") {
                matriz[xi][yi].setImageResource(R.mipmap.galleta);
                matriz[xi][yi].setTag("galleta");
                matriz[xf][yf].setImageResource(R.mipmap.fantasma2);
                matriz[xf][yf].setTag("fan2");
            }
            if ((String) matriz[xf][yf].getTag() == "galletota") {
                matriz[xi][yi].setImageResource(R.mipmap.galleta);
                matriz[xi][yi].setTag("galletota");
                matriz[xf][yf].setImageResource(R.mipmap.fantasma2);
                matriz[xf][yf].setTag("fan2");
            }

            if ((String) matriz[xf][yf].getTag() == "vacio") {
                matriz[xi][yi].setImageResource(R.mipmap.fondo);
                matriz[xi][yi].setTag("vacio");
                matriz[xf][yf].setImageResource(R.mipmap.fantasma2);
                matriz[xf][yf].setTag("fan2");
            }

            if ((String) matriz[xf][yf].getTag() == "pac") {
                imagen.setImageResource(R.mipmap.perdio);
                MainActivity.getInstance().acabar();
                break;
            }

            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


    public void tiempo () {this.time = time-100;}
    public void acabar () {
        this.apagar = true;
    }





}
