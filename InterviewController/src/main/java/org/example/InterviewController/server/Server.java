package org.example.InterviewController.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private static final List<ClientHandler> handlers = new CopyOnWriteArrayList<>();
    private static final int SPEED = 10;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7070);
        System.out.println("Сервер запущен...");

        while (true) {
            Socket socket = serverSocket.accept();
            // Создаем обработчика для нового клиента
            ClientHandler handler = new ClientHandler(socket);
            handlers.add(handler);
            new Thread(handler).start();
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket socket;
        private DataInputStream in;
        private DataOutputStream out;
        private int x = 450, y = 500;

        public ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            this.in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            this.out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        }

        @Override
        public void run() {
            try {
                while (true) {
                    char command = in.readChar();
                    if (command == 'W') y -= SPEED;
                    else if (command == 'S') y += SPEED;
                    else if (command == 'A') x -= SPEED;
                    else if (command == 'D') x += SPEED;
                    broadcastPosition();
                }
            } catch (IOException e) {
                System.err.println("Клиент отключился");
            } finally {
                handlers.remove(this);
                try { socket.close(); } catch (IOException e) { e.printStackTrace(); }
            }
        }

        private void broadcastPosition() {
            for (ClientHandler h : handlers) {
                try {
                    h.out.writeInt(this.x);
                    h.out.writeInt(this.y);
                    h.out.flush();
                } catch (IOException e) {
                    System.err.println("Ошибка рассылки");
                }
            }
        }
    }
}