import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args){
        try {
            Clients clients = new Clients();
            Data data = new Data();
            ServerSocket serverSocket = new ServerSocket(16000);

            while (true) {
                System.out.println("Wait client");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                Client client = new Client(socket, clients);
                clients.addClient(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
