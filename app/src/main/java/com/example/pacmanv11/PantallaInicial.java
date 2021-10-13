package com.example.pacmanv11;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.example.logica.SingletonFireBase;
import org.example.logica.usuario;

public class PantallaInicial extends AppCompatActivity {

    private Button btnIniciar;
    private EditText labelNombre;
    private TextView txtEspera;
    private int usuarios = 0;
    private TextView puntaje2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicial);
        SingletonFireBase.getInstance().borrar();
        this.btnIniciar = findViewById(R.id.btnIniciar);
        this.labelNombre = findViewById(R.id.lblNombre);
        this.txtEspera = findViewById(R.id.txtEspera);
        this.puntaje2 = findViewById(R.id.Score2);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregar();
                MainActivity.getInstance().setNombre("" + labelNombre.getText());
                MainActivity.getInstance().setUsuario(usuarios+1);

                if(usuarios == 0){
                    txtEspera.setVisibility(View.VISIBLE);
                }

            }
        });


        ValueEventListener estudianteListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                   DataSnapshot ultimo = null;
                  if( dataSnapshot.getChildrenCount() != 0){
                       for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                           ultimo = snapshot;
                       }

                       usuario user = ultimo.getValue(usuario.class);
                       usuarios = usuarios+1;

                      if(usuarios == 2){
                          Intent intent = new Intent(PantallaInicial.this, MainActivity.class);
                          startActivity(intent);
                      }
                       procesarUsuario(user);
                   }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Error", "loadPost:onCancelled", databaseError.toException());
            }
        };
        SingletonFireBase.getInstance().getmDatabase().child("Usuarios").addValueEventListener(estudianteListener);
    }

    private void procesarUsuario(usuario estudiante) {
       // System.out.println(estudiante);

    }

    private void agregar() {
        SingletonFireBase.getInstance().guardar((usuarios+1),String.valueOf(this.labelNombre.getText()),0, true, false,false);
    }
}