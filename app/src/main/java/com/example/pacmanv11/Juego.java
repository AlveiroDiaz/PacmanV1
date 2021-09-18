package com.example.pacmanv11;

import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Juego {

    private  ImageView [][]matriz = new ImageView[31][26];


    public String izquieda(ImageView [][]m) {

        String aux1 = null;

        int xi = 0;
        int yi = 0;
        int xf = 0;
        int yf = 0;


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

        aux1 = xi+","+yi+","+xf+","+yf;

        return aux1 ;

    }


    public String derecha(ImageView [][]m) {

        String aux1 = null;

        int xi = 0;
        int yi = 0;
        int xf = 0;
        int yf = 0;


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

        aux1 = xi+","+yi+","+xf+","+yf;

        return aux1 ;

    }

    public String arriba(ImageView [][]m) {

        String aux1 = null;

        int xi = 0;
        int yi = 0;
        int xf = 0;
        int yf = 0;


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

        aux1 = xi+","+yi+","+xf+","+yf;

        return aux1 ;

    }
    public String abajo(ImageView [][]m) {

        String aux1 = null;

        int xi = 0;
        int yi = 0;
        int xf = 0;
        int yf = 0;


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

        aux1 = xi+","+yi+","+xf+","+yf;

        return aux1 ;

    }
}
