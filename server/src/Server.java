import java.io.*; // Importa las clases necesarias para la entrada/salida de datos
import java.net.*; // Importa las clases necesarias para la red

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000); // Crea un nuevo socket de servidor en el puerto 5000
        System.out.println("Esperando conexión..."); // Imprime un mensaje en la consola

        Socket clientSocket = serverSocket.accept(); // Acepta una conexión entrante de un cliente
        System.out.println("Cliente conectado"); // Imprime un mensaje en la consola

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Crea un BufferedReader que nos permitirá leer los datos enviados por el cliente
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // Crea un PrintWriter que nos permitirá enviar datos al cliente

        String inputLine;
        while ((inputLine = in.readLine()) != null) { // Lee los datos enviados por el cliente línea por línea
            System.out.println("Mensaje del cliente: " + inputLine); // Imprime el mensaje del cliente en la consola
            out.println("Mensaje recibido"); // Envía un mensaje de confirmación al cliente
        }

        in.close(); // Cierra el BufferedReader
        out.close(); // Cierra el PrintWriter
        clientSocket.close(); // Cierra el socket del cliente
        serverSocket.close(); // Cierra el socket del servidor
    }
}
