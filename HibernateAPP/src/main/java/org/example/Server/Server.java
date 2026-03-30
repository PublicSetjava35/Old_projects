package org.example.Server;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static int PORT = 8080;
    public static Configuration configuration;
    public static SessionFactory sessionFactory;
    public static Session session;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        while(true) {
            Socket socket = serverSocket.accept();
            if(socket == null) return;
            new Thread(new ClientHandler(socket)).start();
        }
    }
    static class ClientHandler implements Runnable {
        public Socket socket;
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }
        @Override
        public void run() {
           try {
               DataInputStream inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
               DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
               while(true) {
                   byte buffer = inputStream.readByte();
                   if(buffer == -1) return;
                   if(buffer == 10) {
                       boolean isValid = inputStream.readBoolean();
                       outputStream.writeBoolean(isValid);
                       outputStream.flush();
                   }
               }
           } catch (IOException exception) {
               if(socket == null) ErrorServer("Client disconnected");
           }
        }
        public void ErrorServer(String message) {
            System.err.println(message);
        }
    }
}