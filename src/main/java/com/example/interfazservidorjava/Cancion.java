package com.example.interfazservidorjava;

import java.io.File;

import javafx.beans.property.SimpleStringProperty;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringProperty;

public class Cancion {
    private String idUnico;
    private StringProperty nombre;
    private StringProperty artista;
    private StringProperty album;
    private StringProperty genero;
    private int upVotes;
    private int downVotes;
    private File archivoMP3;

    public Cancion(String idUnico, String nombre, String artista, String album, String genero) {
        this.idUnico = idUnico;
        this.nombre = new SimpleStringProperty(nombre);
        this.artista = new SimpleStringProperty(artista);
        this.album = new SimpleStringProperty(album);
        this.genero = new SimpleStringProperty(genero);
        this.upVotes = 0;
        this.downVotes = 0;
    }

    public StringProperty artistaProperty() {
        return artista;
    }

    public StringProperty albumProperty() {
        return album;
    }

    public StringProperty generoProperty() {
        return genero;
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getIdUnico() {
        return idUnico;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getArtista() {
        return artista.get();
    }

    public void setArtista(String artista) {
        this.artista.set(artista);
    }

    public String getAlbum() {
        return album.get();
    }

    public void setAlbum(String album) {
        this.album.set(album);
    }

    public String getGenero() {
        return genero.get();
    }

    public void setGenero(String genero) {
        this.genero.set(genero);
    }

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    public File getArchivoMP3() {
        return archivoMP3;
    }

    public void setArchivoMP3(File archivoMP3) {
        this.archivoMP3 = archivoMP3;
    }

    public void cargarMetadatos() {
        try {
            AudioFile audioFile = AudioFileIO.read(archivoMP3);
            Tag tag = audioFile.getTag();
            setNombre(tag.getFirst(FieldKey.TITLE));
            setArtista(tag.getFirst(FieldKey.ARTIST));
            setAlbum(tag.getFirst(FieldKey.ALBUM));
            setGenero(tag.getFirst(FieldKey.GENRE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
