package com.example.pacmanv11;

import android.widget.ImageView;

public class hiloPacDer extends Thread {


    private ImageView[][]matriz;
    private String dir;
    private int xi = 0;
    private int yi = 0;
    private int xf = 0;
    private int yf = 0;
    private static int apagar = 0;

    public hiloPacDer(ImageView[][]m) {
        matriz = m;

    }

    public void run(){


        apagar = 0;

        while (true){

            if(!this.isAlive()){
                break;
            }

            for(int i=0; i<31;i++){
                for(int j=0; j<26; j++) {

                    String aux = (String) matriz[i][j].getTag();

                    if( aux == "pac"){
                        xi = i;
                        yi = j;
                    }

                }
            }

            if(xi!=0){
                xf=xi;
                yf=yi-1;
            }


            if(this.apagar ==  1 ){
                break;
            }

            if((String)matriz[xf][yf].getTag() == "vacio") {
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

    public void acabar() {
        this.apagar = 1;

    }
}
