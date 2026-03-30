package org.example;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws IOException {
        ArrayList<Player> players = new ArrayList<>();
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();
            Player player = new Player(200,200, 10);
            players.add(player);
            Thread thread = new Thread(new ClientHandler(socket, players, player));
            System.out.println("New, Client -> " + thread.getName());
            thread.start();
        }
    }
    static class Player {
        public int playerX = 0, playerY = 0, speed = 0;
        public Player(int playerX, int playerY, int speed) {
            this.playerX = playerX;
            this.playerY = playerY;
            this.speed = speed;
        }
    }
    static class ClientHandler implements Runnable {
        public int UP = 85, DOWN = 65, LEFT = 50, RIGHT = 45;
        Player player;
        static ArrayList<Player> players;
        Socket socket;
        public ClientHandler(Socket socket, ArrayList<Player> players, Player player) {
            this.socket = socket;
            this.player = player;
            ClientHandler.players = players;
        }
        @Override
        public void run() {
            try {
                DataOutputStream output = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                DataInputStream input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                while (true) {
                    int command = input.readByte();
                    if(command == -1) continue;
                    if (command == UP && player.playerY >= 0) {
                        player.playerY -= player.speed;
                    } else if (command == DOWN) {
                        player.playerY += player.speed;
                    } else if (command == LEFT && player.playerX >= 0) {
                        player.playerX -= player.speed;
                    } else if (command == RIGHT) {
                        player.playerX += player.speed;
                    }
                    synchronized (players) {
                        output.writeInt(players.size());
                        for(Player player1:players) {
                            output.writeInt(player1.playerY);
                            output.writeInt(player1.playerX);
                        }
                        output.flush();
                    }
                }
            } catch (IOException e) {
                System.out.println("Client disconnected - > " + e);
            } finally {
                synchronized (players) {
                    players.remove(player);
                }
            }
        }
    }
}
