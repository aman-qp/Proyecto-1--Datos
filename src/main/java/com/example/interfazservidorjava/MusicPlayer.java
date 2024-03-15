package com.example.interfazservidorjava;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.io.File;
import java.util.List;

public class MusicPlayer {
    private MediaPlayer mediaPlayer;
    private DoubleProperty progressProperty;
    private List<File> playlist;
    private int currentIndex = 0;
    private boolean playing = false;

    public MusicPlayer(List<File> playlist) {
        this.playlist = playlist;
        progressProperty = new SimpleDoubleProperty(0.0);
        initializeMediaPlayer();
    }

    private void initializeMediaPlayer() {
        mediaPlayer = new MediaPlayer(new Media(playlist.get(0).toURI().toString()));
        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (newTime != null) {
                double progress = newTime.toSeconds() / mediaPlayer.getTotalDuration().toSeconds();
                progressProperty.set(progress);
            }
        });
        mediaPlayer.setOnEndOfMedia(this::playNext);
    }

    public void playSongAtIndex(int index) {
        if (index >= 0 && index < playlist.size()) {
            currentIndex = index;
            mediaPlayer.stop();
            mediaPlayer = new MediaPlayer(new Media(playlist.get(currentIndex).toURI().toString()));
            mediaPlayer.setOnEndOfMedia(this::playNext);
            mediaPlayer.play();
        }
    }

    public void pause() {
        mediaPlayer.pause();
        playing = false;
    }

    public void forward() {
        Duration currentTime = mediaPlayer.getCurrentTime();
        Duration newTime = currentTime.add(Duration.seconds(10));
        mediaPlayer.seek(newTime);
    }

    public void backward() {
        Duration currentTime = mediaPlayer.getCurrentTime();
        Duration newTime = currentTime.subtract(Duration.seconds(10));
        mediaPlayer.seek(newTime);
    }

    public void playNext() {
        if (currentIndex < playlist.size() - 1) {
            currentIndex++;
        } else {
            currentIndex = 0; // Reinicia al principio de la lista de reproducción
        }

        mediaPlayer.stop();
        mediaPlayer = new MediaPlayer(new Media(playlist.get(currentIndex).toURI().toString()));
        mediaPlayer.setOnEndOfMedia(this::playNext);
        mediaPlayer.play();
    }

    public void playPrevious() {
        if (currentIndex > 0) {
            currentIndex--;
        } else {
            currentIndex = playlist.size() - 1; // Retrocede al final de la lista de reproducción
        }

        mediaPlayer.stop();
        mediaPlayer = new MediaPlayer(new Media(playlist.get(currentIndex).toURI().toString()));
        mediaPlayer.setOnEndOfMedia(this::playNext);
        mediaPlayer.play();
    }

    public void setVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }

    public boolean isPlaying() {
        return playing;
    }

    public DoubleProperty progressProperty() {
        return progressProperty;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }
}

