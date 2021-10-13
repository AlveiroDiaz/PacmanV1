package org.example.logica;

public class usuario {

    private int codigo;
    private String nombre;
    private int puntaje;
    private boolean estado;
    private boolean galleta;
    private boolean restart;


    public usuario() {

    }

    public usuario(int codigo, String nombre, int puntaje, boolean estado, boolean galleta, boolean restart) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.puntaje=puntaje;
        this.estado=estado;
        this.galleta=galleta;
        this.restart = restart;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getGalleta() {
        return galleta;
    }

    public void setGalleta(boolean galleta) {
        this.galleta = galleta;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public boolean getRestart() {
        return restart;
    }

    public void setRestart(boolean restart) {
        this.restart = restart;
    }

    @Override
    public String toString() {
        return  "codigo='" + this.codigo + "\n" +
                "nombre='" + this.nombre + "\n" +
                "puntaje= " + this.puntaje + "\n" +
                "estado= " + this.estado +"\n" +
                "galleta= " + this.galleta + "\n";
    }
}
