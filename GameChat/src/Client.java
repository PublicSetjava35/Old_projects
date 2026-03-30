import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
public class Client extends JPanel {
    public boolean[] keys = new boolean[256];
    static ArrayList<Point> players = new ArrayList<>();
    public String stringUTF = "";
    public boolean showText = false;
    DataOutputStream outputStream;
    DataInputStream inputStream;
    JTextField textField;
    JFrame frame;
    Client(Socket socket) throws IOException {
        outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        frame = new JFrame("GameChat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 650);
        textField = new JTextField("");
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.WHITE);
        textField.setVisible(false);
        add(textField);
        frame.add(this);
        frame.setVisible(true);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keys[e.getKeyCode()] = true; // on
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_ENTER) {
                    showText = !showText;
                    textField.setVisible(showText);
                    if(showText) {
                        textField.requestFocus();
                    } else  {
                        textField.setText("");
                        textField.setVisible(false);
                        showText = false;
                        requestFocus();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                keys[e.getKeyCode()] = false; // off
            }
        });
        textField.addActionListener(e -> {
            try {
                String text = textField.getText();
                if(!text.isEmpty()) {
                    outputStream.writeByte(35);
                    byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
                    outputStream.writeInt(bytes.length);
                    outputStream.write(bytes);
                    outputStream.flush();
                }
                textField.setText("");
                textField.setVisible(false);
                showText = false;
                frame.requestFocus();
            } catch (Exception exception) {
                System.out.println("Error chat -> " + exception.getMessage());
            }
        });
        new Timer(10, _-> {
            try {
                if(keys[KeyEvent.VK_W]) outputStream.write('W');
                if(keys[KeyEvent.VK_S]) outputStream.write('S');
                if(keys[KeyEvent.VK_A]) outputStream.write('A');
                if(keys[KeyEvent.VK_D]) outputStream.write('D');
                outputStream.flush();
            } catch (Exception exception) {
                System.out.println("Error, Keys -> " + exception.getMessage());
            }
        }).start();
        players.add(new Point(220,220)); // начальная позиция
        new Thread(() -> {
            try {
                while (true) {
                    int packetId = inputStream.readInt();
                    switch (packetId) {
                        case 1 -> { // движение
                            updateUI();
                            int count = inputStream.readInt();
                            players.clear();
                            for(int i = 0; i < count; i++) {
                                int playerY = inputStream.readInt();
                                System.out.println(playerY + " <- from, server coordinates");
                                int playerX = inputStream.readInt();
                                System.out.println(playerX + " <- from, server coordinates");
                                players.add(new Point(playerX, playerY));
                            }
                            for(Point player:players) {
                                if(player.x < 0) player.x = 0;
                                if(player.x > getWidth() - 100) player.x = getWidth() - 100;
                                if(player.y < 0) player.y = 0;
                                if(player.y > getHeight() - 100) player.y = getHeight() - 100;
                            }
                            repaint();
                        }
                        case 35 -> { // чат
                            int length = inputStream.readInt();
                            byte[] bytes = new byte[length];
                            inputStream.readFully(bytes);
                            String text = new String(bytes, StandardCharsets.UTF_8);
                            System.out.println(text);
                        }
                        default -> { /* игнорируем */ }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for(Point player:players) {
            g.fillOval(player.x, player.y, 100, 100);
        }
        for(Point player:players) {
            textField.setBounds(player.x - 40, player.y - 50,205,40);
            if(!stringUTF.isEmpty()) {
                g.setColor(Color.BLACK);
                g.drawString(stringUTF, player.x - 40, player.y - 40);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        Socket socket = new Socket(inetAddress, 8080);
        new Client(socket);
    }
}