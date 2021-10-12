package org.example.logica;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingletonFireBase {

        private final static SingletonFireBase INSTANCE = new SingletonFireBase();
        private static DatabaseReference mDatabase;

        public static DatabaseReference getmDatabase() {
            return mDatabase;
        }

        public static SingletonFireBase getInstance(){
            return INSTANCE;
        }

        public SingletonFireBase(){
            mDatabase = FirebaseDatabase.getInstance().getReference();
        }

        public void guardar(int codigo,String nombre,int puntaje,boolean estado,boolean galleta){
            usuario user = new usuario(codigo,nombre,puntaje,estado,galleta);
            this.mDatabase.child("Usuarios").child(String.valueOf(codigo)).setValue(user);
        }

        public void borrar(){
            this.mDatabase.removeValue();
        }


}
