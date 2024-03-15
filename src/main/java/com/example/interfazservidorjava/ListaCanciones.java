package com.example.interfazservidorjava;

import java.util.LinkedList;

public class ListaCanciones {
    private LinkedList<Cancion> lista;

    public ListaCanciones() {
        lista = new LinkedList<>();
    }

    public void agregarCancion(Cancion cancion) {
        lista.add(cancion);
    }

    public LinkedList<Cancion> getLista() {
        return lista;
    }
}
