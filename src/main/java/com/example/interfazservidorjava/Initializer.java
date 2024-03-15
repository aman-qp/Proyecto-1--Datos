package com.example.interfazservidorjava;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import java.util.HashSet;
import java.util.Set;

public class Initializer {

    public static ObservableList<Cancion> initializeTableMusica() {
        ObservableList<Cancion> listaCanciones = FXCollections.observableArrayList();
        try {
            HelloApplication app = new HelloApplication();
            ListaCanciones bibliotecaMusical = app.cargarBibliotecaMusical(HelloApplication.RUTA_CANCIONES);
            listaCanciones.addAll(bibliotecaMusical.getLista());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCanciones;
    }

    public static ObservableList<String> initializeTableArtistas() {
        ObservableList<String> artistasList = FXCollections.observableArrayList();
        try {
            HelloApplication app = new HelloApplication();
            ListaCanciones bibliotecaMusical = app.cargarBibliotecaMusical(HelloApplication.RUTA_CANCIONES);
            Set<String> artistasSet = new HashSet<>();
            for (Cancion cancion : bibliotecaMusical.getLista()) {
                String[] artistas = cancion.getArtista().split(",");
                for (String artista : artistas) {
                    artistasSet.add(artista.trim());
                }
            }
            artistasList.addAll(artistasSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return artistasList;
    }

    public static ObservableList<Cancion> filtrarCancionesPorArtista(String artista) {
        ObservableList<Cancion> listaFiltrada = FXCollections.observableArrayList();
        try {
            HelloApplication app = new HelloApplication();
            ListaCanciones bibliotecaMusical = app.cargarBibliotecaMusical(HelloApplication.RUTA_CANCIONES);
            for (Cancion cancion : bibliotecaMusical.getLista()) {
                if (cancion.getArtista().contains(artista)) {
                    listaFiltrada.add(cancion);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaFiltrada;
    }
}

