using System; // Importa las clases necesarias para el sistema
using System.Net.Sockets; // Importa las clases necesarias para la red
using System.IO; // Importa las clases necesarias para la entrada/salida de datos

class Program {
    static void Main(string[] args) {
        TcpClient client = new TcpClient("localhost", 5000); // Crea un nuevo cliente TCP que se conectará al servidor en localhost en el puerto 5000
        StreamWriter writer = new StreamWriter(client.GetStream()); // Crea un StreamWriter que nos permitirá enviar datos al servidor
        StreamReader reader = new StreamReader(client.GetStream()); // Crea un StreamReader que nos permitirá leer los datos enviados por el servidor

        writer.WriteLine("Hola desde el cliente C#"); // Envía un mensaje al servidor
        writer.Flush(); // Asegura que todos los datos se envíen al servidor

        String response = reader.ReadLine(); // Lee la respuesta del servidor
        Console.WriteLine("Respuesta del servidor: " + response); // Imprime la respuesta del servidor en la consola

        writer.Close(); // Cierra el StreamWriter
        reader.Close(); // Cierra el StreamReader
        client.Close(); // Cierra el cliente TCP
    }
}

