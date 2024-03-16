package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfiguracionApp {
    private static Properties propiedades;

    static {
        // Carga las propiedades desde el archivo (puedes hacerlo en el constructor también)
        try {
            InputStream entrada = new FileInputStream("C:\\Users\\Usuario 2024\\Downloads\\Proyecto-1--Datos-master\\src\\main\\java\\config\\config.ini");
            propiedades = new Properties();
            propiedades.load(entrada);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String obtenerRutaCanciones() {
        return propiedades.getProperty("RutaCanciones");
    }
}
