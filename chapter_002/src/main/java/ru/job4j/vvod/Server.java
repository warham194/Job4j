package ru.job4j.vvod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Lenovo2 on 22.03.2019.
 */
public class Server {
    private final Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)){
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String ask = "";
            do {
                System.out.println("wait command ...");
                ask = in.readLine();
                System.out.println(ask);
                switch (ask) {
                    case "Hello" :
                        out.println("Hello, dear friend, I'm a oracle.");
                        break;
                    case "exit" :
                        out.println("good luck");
                        break;
                    default:
                        out.println("?????");
                        break;
                }
            } while (!"exit".equals(ask));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        try (final Socket socket = new ServerSocket(1111).accept()){
            new Server(socket).start();
        }
    }
}
