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

    public void run() {

        matriz[13][13].setImageResource(R.mipmap.fondo);
        matriz[13][13].setTag("vacio");
        matriz[11][13].setImageResource(R.mipmap.fantasma1);
        matriz[11][13].setTag("fan1");


        while (apagar) {

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

                    if (aux == "fan1") {
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
            if (xiPac > xi) {
                xf = xi + 1;
                aba = aba + 1;
            }
            if(xiPac < xi) {
                xf = xi - 1;
                arr = arr + 1;
            }
            if (xiPac == xi) {

                igualVer    = igualVer + 1;
            }


            if (yiPac > yi) {
                yf = yi + 1;
                der = der + 1;
            }
            if (yiPac < yi) {
                yf = yi - 1;
                izq = izq + 1;
            }
            if (yiPac == yi) {

                igualHor    = igualHor + 1;
            }



            if (matriz[xf][yf].getTag() == "pared" && aba != 0) {
                xf = xf - 1;
            }
                if (matriz[xf][yf].getTag() == "pared" && arr != 0) {
                    xf = xf + 1;
                }
                if (matriz[xf][yf].getTag() == "pared" && der != 0) {
                    System.out.println("PARED");
                    yf = yf + 1;
                }
                if (matriz[xf][yf].getTag() == "pared" && izq != 0) {
                    yf = yf - 1;
                }

            if (matriz[xf][yf].getTag() == "pared" && igualHor != 0) {
                if (matriz[xf][yf+1].getTag() == "pared") {
                    yf = yf - 1;
                }
                if (matriz[xf][yf-1].getTag() == "pared") {
                    yf = yf + 1;
                }

            }

            if (matriz[xf][yf].getTag() == "pared" && igualVer != 0) {
                if (matriz[xf+1][yf].getTag() == "pared") {
                    xf = xf - 1;
                }
                if (matriz[xf-1][yf].getTag() == "pared") {
                    xf = xf + 1;
                }

            }




            if ((String) matriz[xf][yf].getTag() == "galleta") {
                matriz[xi][yi].setImageResource(R.mipmap.galleta);
                matriz[xi][yi].setTag("galleta");
                matriz[xf][yf].setImageResource(R.mipmap.fantasma1);
                matriz[xf][yf].setTag("fan1");
            }

                if ((String) matriz[xf][yf].getTag() == "vacio") {
                    matriz[xi][yi].setImageResource(R.mipmap.fondo);
                    matriz[xi][yi].setTag("vacio");
                    matriz[xf][yf].setImageResource(R.mipmap.fantasma1);
                    matriz[xf][yf].setTag("fan1");
                }


          /*  if((String)matriz[xf][yf].getTag() != "vacio" && (String)matriz[xf][yf].getTag() != "pac" ) {
                matriz[xi][yi].setImageResource(R.mipmap.fondo);
                matriz[xi][yi].setTag("vacio");
                matriz[xf+1][yf].setImageResource(R.mipmap.fantasma1);
                matriz[xf+1][yf].setTag("fan1");
            }*/


                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }


        public void start () {

            this.apagar = true;
            new Thread(this).start();
        }


        public void acabar () {
            this.apagar = false;
            this.interrupt();


        }


}
