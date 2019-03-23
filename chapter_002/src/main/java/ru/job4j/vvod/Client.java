package ru.job4j.vvod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by Lenovo2 on 22.03.2019.
 */
public class Client {
    private final Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner console = new Scanner(System.in)) {
            String consoleStr;
            do {
                consoleStr = console.nextLine();
                out.println(consoleStr);
                String str = in.readLine();
                while (!str.isEmpty()) {
                    System.out.println(str);
                    str = in.readLine();
                }
            } while (!consoleStr.equals("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket(InetAddress.getByName("localhost"), 1111)) {
            new Client(socket).start();
        }
    }
}
