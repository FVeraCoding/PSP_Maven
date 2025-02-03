import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {

    int SERVER_PORT = 12345;
    String SERVER_IP = "localhost";
    String CLIENT_NAME = "ClienteUDP";
    DatagramSocket socket;
    InetAddress serverAddress;

    public Cliente() {
        try {
            socket = new DatagramSocket();
            serverAddress = InetAddress.getByName(SERVER_IP);
            System.out.println("Cliente UDP iniciado...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void iniciarChat() {
        try (Scanner scanner = new Scanner(System.in)) {
            byte[] buffer = new byte[1024];

            
            String saludo = "@hola#" + CLIENT_NAME + "@";
            enviarMensaje(saludo);

            
            DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
            socket.receive(paqueteRecibido);
            String respuesta = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
            System.out.println("Respuesta del servidor: " + respuesta);

            
            String mensaje;
            do {
                System.out.print("Tu: ");
                mensaje = scanner.nextLine();

                enviarMensaje(mensaje);

                if (mensaje.equals(".")) {
                    System.out.println("Terminando la conversación...");
                    break;
                }

                
                socket.receive(paqueteRecibido);
                String mensajeServidor = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                System.out.println("Servidor: "+mensajeServidor);
            } while (!mensaje.equals("."));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    private void enviarMensaje(String message) {
        try {
            byte[] bytesMensaje = message.getBytes();
            DatagramPacket paqueteMensaje = new DatagramPacket(bytesMensaje, bytesMensaje.length, serverAddress, SERVER_PORT);
            socket.send(paqueteMensaje);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.iniciarChat();
    }
}
