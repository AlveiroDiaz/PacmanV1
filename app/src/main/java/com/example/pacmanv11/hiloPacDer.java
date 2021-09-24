package com.example.pacmanv11;

import android.widget.ImageView;

public class hiloPacDer extends Thread {


    private ImageView[][]matriz;
    private String dir;
    private int xi = 0;
    private int yi = 0;
    private int xf = 0;
    private int yf = 0;
    public static boolean apagar;

    public hiloPacDer(ImageView[][]m) {
        matriz = m;

    }

    public void run(){





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

                xf=xi;
                yf=yi-1;




            if((String)matriz[xf][yf].getTag() == "vacio" || (String)matriz[xf][yf].getTag() == "galleta") {
                matriz[xi][yi].setImageResource(R.mipmap.fondo);
                matriz[xi][yi].setTag("vacio");
                matriz[xf][yf].setImageResource(R.mipmap.pac_izquierda);
                matriz[xf][yf].setTag("pac");
            }else{
                break;
            }



            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


    public  void start(){

        this.apagar = true;
        new Thread(this).start();
    }

    public void acabar() {

        this.apagar = false;
        this.interrupt();

    }
}
