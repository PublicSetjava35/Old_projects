package org.example;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

class Client extends JPanel {
    public boolean[] keys = new boolean[256];
    public int playerX = 150, playerY = 150;
    JFrame frame;
    static class PlayerLeft extends Point {
        PlayerLeft(int x, int y) {
           this.x = x;
           this.y = y;
        }
    }
    static class Bullet {
        public int x, y;
        public int dx, dy;
        public int ownerID;
        Bullet(int x, int y) {
            this.x = x;
            this.y = y;
            this.ownerID = 0;
        }
    }
    public ArrayList<PlayerLeft> left_right = new ArrayList<>();
    public ArrayList<Bullet> bullets = new ArrayList<>();
    Client(Socket socket) throws IOException {
        DataOutputStream output = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        DataInputStream input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        frame = new JFrame("devast.io");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,650);
        frame.add(this);
        frame.setVisible(true);
        // внутри конструктора Client(Socket socket)
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    // создаем пулю от центра игрока к курсору
                    int centerX = playerX + skin.getWidth(Client.this)/4;
                    int centerY = playerY + skin.getHeight(Client.this)/4;

                    double angle = Math.atan2(e.getY() - centerY, e.getX() - centerX);
                    int speed = 10;
                    int dx = (int)(Math.cos(angle) * speed);
                    int dy = (int)(Math.sin(angle) * speed);

                    synchronized (bullets) {
                        bullets.add(new Bullet(centerX, centerY));
                        bullets.get(bullets.size()-1).dx = dx;
                        bullets.get(bullets.size()-1).dy = dy;
                    }

                    // отправляем команду на сервер
                    try {
                        output.writeByte(100); // код для выстрела
                        output.writeInt(centerX);
                        output.writeInt(centerY);
                        output.writeDouble(angle);
                        output.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                keys[e.getKeyCode()] = true; // флаг true
            }
            public void keyReleased(KeyEvent e) {
                keys[e.getKeyCode()] = false; // флаг false
            }
        });
        new Timer(10, event -> {
            try {
                if(keys[KeyEvent.VK_W]) output.writeByte(85);
                if(keys[KeyEvent.VK_S]) output.writeByte(65);
                if(keys[KeyEvent.VK_A]) output.writeByte(50);
                if(keys[KeyEvent.VK_D]) output.writeByte(45);
                output.flush();
            } catch (IOException e) {
                System.out.println("Error -> " + e);
            }
        }).start();
        left_right.add(new PlayerLeft(playerX - 50, playerY - 100));
        new Thread(() -> {
            try {
                while (true) {
                    updateUI();
                    int count = input.readInt();
                    left_right.clear();
                    for(int i = 0; i < count; i++) {
                        int y = input.readInt();
                        int x = input.readInt();
                        if(y < 0) y = 0;
                        if(y > getHeight() - 100) y = getHeight() - 100;
                        if(x < 0) x = 0;
                        if(x > getWidth() - 100) x = getWidth() - 100;
                        left_right.add(new PlayerLeft(x, y));

                    }
                    repaint();
                }
            } catch (IOException e) {
                System.out.println("Error -> " + e);
            }
        }).start();
    }
    URL url = new URL("https://devast.io//img/day-skin0.png");
    Image skin = ImageIO.read(url);
    URL url2 = new URL("https://devast.io//img/day-left-arm0.png");
    Image left = ImageIO.read(url2);
    URL url3 = new URL("https://devast.io//img/day-right-arm0.png");
    Image right = ImageIO.read(url3);
    URL url4 = new URL("https://devast.io//img/day-AK47.png");
    Image ak47 = ImageIO.read(url4);
    URL url5 = new URL("https://devast.io//img/day-bunker-wall0.png");
    Image bunker = ImageIO.read(url5);
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        ArrayList<PlayerLeft> snapshot;
        synchronized (left_right) {
            snapshot = new ArrayList<>(left_right);
        }
            for(PlayerLeft player:snapshot) {
                int bodyW = skin.getWidth(this) / 2;
                int bodyH = skin.getHeight(this) / 2;
                // центр тела
                int centerX = player.x + bodyW / 2;
                int centerY = player.y + bodyH / 2;
                // угол (например, к мышке)
                double angleLeft = Math.toRadians(100); // потом заменишь на угол до мыши
                double angleRight = Math.toRadians(10);
                // смещения рук
                int armOffsetLeft = 20; // расстояние от центра
                int armOffsetRight = 40;
                int dxLeft = -armOffsetLeft, dyLeft = -30;
                int dxRight = armOffsetRight, dyRight = 5;
                // поворот смещений
                int rotXLeft  = (int) (dxLeft  * Math.cos(angleLeft) - dyLeft  * Math.sin(angleLeft));
                int rotYLeft  = (int) (dxLeft  * Math.sin(angleLeft) + dyLeft  * Math.cos(angleLeft));
                int rotXRight = (int) (dxRight * Math.cos(angleRight) - dyRight * Math.sin(angleRight));
                int rotYRight = (int) (dxRight * Math.sin(angleRight) + dyRight * Math.cos(angleRight));

                int handW = left.getWidth(this) / 2;
                int handH = left.getHeight(this) / 2;
                // рисуем левую руку
                g2.drawImage(left, centerX + rotXLeft - handW / 2, centerY + rotYLeft - handH / 2,
                        handW, handH, this);
                // рисуем правую руку
                g2.drawImage(right, centerX + rotXRight - handW / 2, centerY + rotYRight - handH / 2,
                        handW, handH, this);
                g.drawImage(ak47, player.x+65, player.y + 30, ak47.getHeight(this)*2, ak47.getHeight(this)/2,this);
                // рисуем тело
                g2.drawImage(skin, player.x, player.y, skin.getWidth(this)/2, skin.getHeight(this)/2, this);
                // Добавляем стену
                g2.drawImage(bunker, 900,450, bunker.getWidth(this)/2,bunker.getHeight(this)/2, this);

        }
    }
    public static void main(String[] args) throws IOException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        Socket socket = new Socket(inetAddress, 8080);
        new Client(socket);
    }
}
