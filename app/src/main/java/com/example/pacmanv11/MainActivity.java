package com.example.pacmanv11;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private GridLayout gridLayout;
    private ImageView [][]casillas = new ImageView[31][26];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.linearLayout = findViewById(R.id.linearlayout);
        this.gridLayout = new GridLayout(this);
        this.gridLayout.setColumnCount(26);
        this.gridLayout.setRowCount(31);
        this.linearLayout.addView(this.gridLayout);
        Display display = getWindowManager().getDefaultDisplay();
        Point tam = new Point();
        display.getSize(tam);
        int ancho = tam.x;
        int alto = tam.y;

        /*
            1 Esquina superior izquierda
            2 Esquina superior derecha
            3 Esquina inferior izquierda
            4 Esquina inferior derecha
            5 Horizontal
            6 Vertical
         */




        for(int i=0;i<31; i++){
            for(int j=0; j<26; j++){
                this.casillas[i][j] = new ImageView(this);
               if(i==0){
                   this.casillas[i][j].setImageResource(R.mipmap.horizo_arri);
                }
                if(j==0){
                    this.casillas[i][j].setImageResource(R.mipmap.vertical_izq);
                }
                if(i==30){
                    this.casillas[i][j].setImageResource(R.mipmap.horizo_aba);
                }
                if(j==25){
                      this.casillas[i][j].setImageResource(R.mipmap.vertical_der);
                }
                if(i==0 & j==0){
                    this.casillas[i][j].setImageResource(R.mipmap.esquina_sup_der);
                }
               if(i==0 & j==25){
                   this.casillas[i][j].setImageResource(R.mipmap.esquina_sup_izq);
                }
                if(i==30 & j==0){
                    this.casillas[i][j].setImageResource(R.mipmap.inferior_der);
                }
                if(i==30 & j==25){
                    this.casillas[i][j].setImageResource(R.mipmap.inferior_izq);
                }
                if(i== 15 & j==12){
                    this.casillas[i][j].setImageResource(R.mipmap.pac_izquierda);
                }

/*
              this.casillas[i][j].setWidth(ancho/26);
                this.casillas[i][j].setHeight(ancho/26);
                this.casillas[i][j].setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                this.casillas[i][j].setTextSize(10);*/
                this.gridLayout.addView(this.casillas[i][j]);
            }
        }




    }
}