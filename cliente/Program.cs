using System;
using System.IO;
using System.Net.Sockets;
using System.Text;
using Newtonsoft.Json;

class Program {
    static void Main() {
        TcpClient client = new TcpClient("192.168.3.86", 12345); // Conecta al servidor

        using (NetworkStream stream = client.GetStream()) {
            StreamReader reader = new StreamReader(stream, Encoding.UTF8);
            StreamWriter writer = new StreamWriter(stream, Encoding.UTF8) { AutoFlush = true };

            for (int i = 0; i < 3; i++) { // Envía 3 mensajes, puedes ajustar según necesites
                // Enviar mensaje JSON al servidor
                var messageObject = new { key = $"value{i}" };
                string jsonMessage = JsonConvert.SerializeObject(messageObject);
                writer.WriteLine(jsonMessage);

                // Recibir respuesta del servidor
                string response = reader.ReadLine();
                Console.WriteLine("Respuesta del servidor: " + response);
            }
        }

        client.Close();
    }
}

