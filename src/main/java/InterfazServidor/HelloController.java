package InterfazServidor;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class HelloController {

    @FXML
    private TableView<Cancion> tableMusica;

    @FXML
    private TableColumn<Cancion, String> musicaColumn;

    @FXML
    private TableColumn<Cancion, String> artistaColumn;

    @FXML
    private TableColumn<Cancion, String> albumColumn;

    @FXML
    private TableColumn<Cancion, String> generoColumn;

    @FXML
    private Button playButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button backButton;

    @FXML
    private Button forwardButton;

    @FXML
    private Button backwardButton;

    @FXML
    private Slider volumen;

    @FXML
    private TableView<String> tableArtistas;

    @FXML
    private TableColumn<String, String> artistasColumn;

    @FXML
    private ProgressBar barra;

    @FXML
    private Label cancionActual;

    private MusicPlayer musicPlayer = null;
    private List<Cancion> playlist = new ArrayList<>();

    @FXML
    private void initialize() {
        musicaColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        artistaColumn.setCellValueFactory(cellData -> cellData.getValue().artistaProperty());
        albumColumn.setCellValueFactory(cellData -> cellData.getValue().albumProperty());
        generoColumn.setCellValueFactory(cellData -> cellData.getValue().generoProperty());

        ObservableList<Cancion> listaCanciones = Initializer.initializeTableMusica();
        tableMusica.setItems(listaCanciones);
        playlist.addAll(listaCanciones);


        ObservableList<String> artistasList = Initializer.initializeTableArtistas();
        artistasColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        tableArtistas.setItems(artistasList);
    }

    @FXML
    private void playButtonClicked() {
        Cancion selectedSong = tableMusica.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            if (musicPlayer != null) {
                musicPlayer.pause();
            }
            List<File> fileList = new ArrayList<>();
            for (Cancion cancion : playlist) {
                fileList.add(cancion.getArchivoMP3());
            }
            int selectedIndex = playlist.indexOf(selectedSong);
            musicPlayer = new MusicPlayer(fileList);
            musicPlayer.playSongAtIndex(selectedIndex); // Nuevo método para reproducir la canción seleccionada
            barra.progressProperty().bind(musicPlayer.progressProperty());
            cancionActual.setText(selectedSong.getNombre());
        }
    }


    @FXML
    private void pauseButtonClicked() {
        if (musicPlayer != null) {
            musicPlayer.pause();
        }
    }

    @FXML
    private void filtrarPorArtista() {
        String artistaSeleccionado = tableArtistas.getSelectionModel().getSelectedItem();
        if (artistaSeleccionado != null) {
            ObservableList<Cancion> cancionesDelArtista = Initializer.filtrarCancionesPorArtista(artistaSeleccionado);
            tableMusica.setItems(cancionesDelArtista);
            playlist.clear();
            playlist.addAll(cancionesDelArtista);
            if (musicPlayer != null) {
                musicPlayer.pause();
            }
            List<File> fileList = new ArrayList<>();
            for (Cancion cancion : cancionesDelArtista) {
                fileList.add(cancion.getArchivoMP3());
            }
            musicPlayer = new MusicPlayer(fileList);
        }
    }

    @FXML
    private void nextButtonClicked() {
        if (musicPlayer != null) {
            musicPlayer.playNext();
            barra.progressProperty().bind(musicPlayer.progressProperty());
            cancionActual.setText(playlist.get(musicPlayer.getCurrentIndex()).getNombre());
        }
    }

    @FXML
    private void backButtonClicked() {
        if (musicPlayer != null) {
            musicPlayer.playPrevious();
            barra.progressProperty().bind(musicPlayer.progressProperty());
            cancionActual.setText(playlist.get(musicPlayer.getCurrentIndex()).getNombre());
        }
    }

    @FXML
    private void forwardButtonClicked() {
        if (musicPlayer != null) {
            musicPlayer.forward();
            cancionActual.setText(playlist.get(musicPlayer.getCurrentIndex()).getNombre());
        }
        barra.progressProperty().bind(musicPlayer.progressProperty());
    }

    @FXML
    private void backwardButtonClicked() {
        if (musicPlayer != null) {
            musicPlayer.backward();
            cancionActual.setText(playlist.get(musicPlayer.getCurrentIndex()).getNombre());
        }
        barra.progressProperty().bind(musicPlayer.progressProperty());
    }

    @FXML
    private void volumeSliderChanged() {
        if (musicPlayer != null) {
            double volume = Math.pow(volumen.getValue() / 100.0, 2); // Aplica una función exponencial al valor del slider para ajustar el volumen de manera más gradual
            musicPlayer.setVolume(volume);
        }
    }

}
