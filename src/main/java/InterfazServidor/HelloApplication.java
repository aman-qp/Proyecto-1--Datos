package InterfazServidor;

import config.ConfiguracionApp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class HelloApplication extends Application {

    public static final String RUTA_CANCIONES = ConfiguracionApp.obtenerRutaCanciones();

    @Override
    public void start(Stage stage) {
        try {
            ListaCanciones bibliotecaMusical = cargarBibliotecaMusical(RUTA_CANCIONES);

            if (!bibliotecaMusical.getLista().isEmpty()) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Servidor");
                stage.setScene(scene);
                stage.show();
            } else {
                System.out.println("La lista de canciones está vacía.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public ListaCanciones cargarBibliotecaMusical(String rutaCanciones) {
        ListaCanciones biblioteca = new ListaCanciones();

        try {
            System.out.println("Cargando canciones desde la ruta: " + rutaCanciones);
            File carpetaCanciones = new File(rutaCanciones);
            File[] archivosMP3 = carpetaCanciones.listFiles((dir, nombre) -> nombre.toLowerCase().endsWith(".mp3"));

            if (archivosMP3 != null) {
                System.out.println("Se encontraron " + archivosMP3.length + " archivos MP3 en la carpeta.");

                for (File archivo : archivosMP3) {
                    System.out.println("Archivo MP3 encontrado: " + archivo.getAbsolutePath());
                    String idUnico = UUID.randomUUID().toString();
                    Cancion cancion = new Cancion(idUnico, "", "", "", "");
                    cancion.setArchivoMP3(archivo);
                    cancion.cargarMetadatos();
                    System.out.println("Metadatos de la canción cargados:");
                    System.out.println("Nombre: " + cancion.getNombre());
                    System.out.println("Artista: " + cancion.getArtista());
                    biblioteca.agregarCancion(cancion);
                    System.out.println("Canción agregada: " + cancion.getNombre());
                }
            } else {
                System.out.println("No se encontraron archivos MP3 en la carpeta.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return biblioteca;
    }
}
