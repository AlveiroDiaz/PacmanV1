package com.example.pacmanv11;

import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Juego {

    private int xi = 0;
    private int yi = 0;
    private int xf = 0;
    private int yf = 0;

    public int getXi() {
        return xi;
    }

    public void setXi(int xi) {
        this.xi = xi;
    }

    public int getYi() {
        return yi;
    }

    public void setYi(int yi) {
        this.yi = yi;
    }

    public int getXf() {
        return xf;
    }

    public void setXf(int xf) {
        this.xf = xf;
    }

    public int getYf() {
        return yf;
    }

    public void setYf(int yf) {
        this.yf = yf;
    }

    public void izquieda(ImageView [][]m) {




        for(int i=0; i<31;i++){
            for(int j=0; j<26; j++) {

               String aux = (String) m[i][j].getTag();

                if( aux == "pac"){
                    xi = i;
                    yi = j;
                }

            }
        }

        if(xi!=0){
            xf=xi;
            yf=yi+1;
        }





        if((String)m[xf][yf].getTag() == "vacio") {
            m[xi][yi].setImageResource(R.mipmap.fondo);
            m[xi][yi].setTag("vacio");
            m[xf][yf].setImageResource(R.mipmap.pac_izquierda);
            m[xf][yf].setTag("pac");
        }





    }


    public void derecha(ImageView [][]m) {




        for(int i=0; i<31;i++){
            for(int j=0; j<26; j++) {

                String aux = (String) m[i][j].getTag();

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



        if((String)m[xf][yf].getTag() == "vacio") {
            m[xi][yi].setImageResource(R.mipmap.fondo);
            m[xi][yi].setTag("vacio");
            m[xf][yf].setImageResource(R.mipmap.pac_izquierda);
            m[xf][yf].setTag("pac");
        }





    }

    public void arriba(ImageView [][]m) {





        for(int i=0; i<31;i++){
            for(int j=0; j<26; j++) {

                String aux = (String) m[i][j].getTag();

                if( aux == "pac"){
                    xi = i;
                    yi = j;
                }

            }
        }

        if(xi!=0){
            xf=xi-1;
            yf=yi;
        }




        if((String)m[xf][yf].getTag() == "vacio") {
            m[xi][yi].setImageResource(R.mipmap.fondo);
            m[xi][yi].setTag("vacio");
            m[xf][yf].setImageResource(R.mipmap.pac_izquierda);
            m[xf][yf].setTag("pac");
        }




    }
    public void abajo(ImageView [][]m) {






        for(int i=0; i<31;i++){
            for(int j=0; j<26; j++) {

                String aux = (String) m[i][j].getTag();

                if( aux == "pac"){
                    xi = i;
                    yi = j;
                }

            }
        }

        if(xi!=0){
            xf=xi+1;
            yf=yi;
        }



        if((String)m[xf][yf].getTag() == "vacio") {
            m[xi][yi].setImageResource(R.mipmap.fondo);
            m[xi][yi].setTag("vacio");
            m[xf][yf].setImageResource(R.mipmap.pac_izquierda);
            m[xf][yf].setTag("pac");
        }


    }
}
