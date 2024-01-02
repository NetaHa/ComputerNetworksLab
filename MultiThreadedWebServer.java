import java.io.*;
import java.net.*;

public class MultiThreadedWebServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(new ClientHandler(clientSocket)).start();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html");
            out.println("\r\n");
            out.println("<h1>Hello, World!</h1>");
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
