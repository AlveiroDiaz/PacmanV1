package com.example.pacmanv11;


import android.widget.ImageView;

public class hiloFantasma_1 extends Thread{



    private ImageView[][]matriz;
    private String dir;
    private int xiPac = 0;
    private int yiPac= 0;
    private int xi = 0;
    private int yi = 0;
    private int xf = 0;
    private int yf = 0;
    private static boolean apagar;


    public hiloFantasma_1(ImageView[][] matriz) {
        this.matriz = matriz;
    }

    public void run(){

        while (apagar){

            for(int i=0; i<31;i++){
                for(int j=0; j<26; j++) {

                    String aux = (String) matriz[i][j].getTag();

                    if( aux == "pac"){
                        xiPac = i;
                        yiPac = j;
                    }

                }
            }

            for(int i=0; i<31;i++){
                for(int j=0; j<26; j++) {

                    String aux = (String) matriz[i][j].getTag();

                    if( aux == "fan1"){
                        xi = i;
                        yi = j;
                    }

                }
            }


            if(yiPac>yi){
                yf=yi+1;
            }else{
                yf=yi-1;
            }

            if(xiPac>xi){
                xf=xi+1;
            }else{
                xf=xi-1;
            }


            if((String)matriz[xf][yf].getTag() == "vacio") {
                matriz[xi][yi].setImageResource(R.mipmap.fondo);
                matriz[xi][yi].setTag("vacio");
                matriz[xf][yf].setImageResource(R.mipmap.fantasma1);
                matriz[xf][yf].setTag("fan1");
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
