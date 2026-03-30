package org.example.InterviewController.controller;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Interview extends JPanel {
    private static final List<Cube> players = new CopyOnWriteArrayList<>();
    private DataOutputStream out;
    private DataInputStream in;
    private static final boolean[] keys = new boolean[256];
    public void game(JFrame frame, Socket socket) throws IOException {
        out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keys[e.getKeyCode()] = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keys[e.getKeyCode()] = false;
            }
        });
        frame.setFocusable(true);
        frame.getWindowFocusListeners();
        new Timer(10, e -> {
            updateUI();
            for (Cube cube : players) {
                try {
                    int width = this.getWidth(), height = this.getHeight();
                    if(keys[KeyEvent.VK_W]) cube.key('W', out, width, height);
                    if(keys[KeyEvent.VK_S]) cube.key('S', out, width, height);
                    if(keys[KeyEvent.VK_A]) cube.key('A', out, width, height);
                    if(keys[KeyEvent.VK_D]) cube.key('D', out, width, height);
                } catch (IOException exception) {
                    System.err.println(exception.getMessage());
                }
            }
            repaint();
        }).start();
        players.add(new Player(450,500,100,100));
        new Thread(() -> {
            while (true) {
                try {
                    int x = in.readInt();
                    int y = in.readInt();
                    for (Cube cube : players) {
                        cube.inputKey(x, y, this.getWidth(), this.getHeight());
                    }
                } catch (IOException exception) {
                    System.err.println(exception.getMessage());
                }
            }
        }).start();
        frame.add(this);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Cube cube : players) {
            cube.drawCube(g);
        }
    }
    public static void main(String[] args) throws IOException {
         Socket socket = new Socket("localhost", 7070);
         JFrame frame = new JFrame("Управление с кубиком, (ООП)");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(new Dimension(1000,650));
         frame.setLocationRelativeTo(null);
         new Interview().game(frame, socket);
         frame.setVisible(true);
    }
}