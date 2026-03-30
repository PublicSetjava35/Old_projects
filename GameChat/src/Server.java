import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Server {
    public static void main(String[] args) throws IOException {
        ArrayList<Players> player = new ArrayList<>();
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();
            Players players = new Players(200,200, 10);
            player.add(players);
            Thread thread = new Thread(new ClientHandler(socket, players, player));
            System.out.println("New client -> " + thread.getName());
            thread.start();
        }
    }
    static class Players {
        public int playerX = 0, playerY = 0, speed = 0;
        Players(int playerX, int playerY, int speed) {
            this.playerX = playerX;
            this.playerY = playerY;
            this.speed = speed;
        }
    }
    static class ClientHandler implements Runnable {
        public int speed = 10;
        Socket socket;
        Players player;
        static ArrayList<Players> players;
        public ClientHandler(Socket socket, Players player, ArrayList<Players> players) {
            this.socket = socket;
            this.player = player;
            ClientHandler.players = players;
        }
        @Override
        public void run() {
                try {
                    DataInputStream inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    while (true) {
                    int command = inputStream.readByte();
                    if(command == -1) break;
                    switch (command) {
                        case 'W' -> {
                            player.playerY -= speed;
                            if (player.playerY < 0) player.playerY = 0;
                        }
                        case 'S' -> {
                            player.playerY += speed;
                            if (player.playerY >= 900) player.playerY = 900;
                        }
                        case 'A' -> {
                            player.playerX -= speed;
                            if (player.playerX < 0) player.playerX = 0;
                        }
                        case 'D' -> {
                            player.playerX += speed;
                            if (player.playerX >= 1820) player.playerX = 1820;
                        }
                        case 35 -> {
                            int length = inputStream.readInt(); // читаем длину строки
                            byte[] bytes = new byte[length];
                            inputStream.readFully(bytes);
                            String text = new String(bytes,StandardCharsets.UTF_8);
                            if(text.equals("!close")) {
                                socket.close();
                            }
                            // отправляем обратно
                            outputStream.writeInt(35);
                            byte[] textBytes = text.getBytes(StandardCharsets.UTF_8);
                            outputStream.writeInt(textBytes.length);
                            outputStream.write(textBytes);
                            outputStream.flush();
                        }
                        default -> {continue;} // ignored
                    }
                    synchronized (players) {
                        outputStream.writeInt(1); // packetId = 1 для движения
                        outputStream.writeInt(players.size());
                        for (Players player : players) {
                            outputStream.writeInt(player.playerY);
//                            System.out.println("coordinates client -> " + player.playerY);
                            outputStream.writeInt(player.playerX);
//                            System.out.println("coordinates client -> " + player.playerX);
                        }
                        outputStream.flush();
                    }
                }
            }catch(Exception exception) {
                    System.out.println("Error -> " + exception.getMessage());
                }
        }
    }
}
