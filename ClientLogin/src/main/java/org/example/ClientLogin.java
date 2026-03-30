package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientLogin extends JPanel {
    private static JFrame frame;
    private static final String TITLE = "PostGres", login = "Спасибо за регистрацию!";
    private static final String register = "регистрироваться";
    private static final int WIDTH = 1000, HEIGHT = 650, port = 6565;
    private static final String Address = "localhost";
    private static DataInputStream input;
    private static JPanel panel;
    private static DataOutputStream output;
    public ClientLogin() {
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        panel = new JPanel();
        panel.setBackground(new Color(3, 41, 167, 255));
        panel.setLayout(null);

        JLabel label = new JLabel("Ожидание...");
        label.setBounds(280, 120, 300,100);
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        label.setForeground(Color.RED);
        label.setVisible(false);
        panel.add(label);

        JButton button = new JButton(register);
        button.setFocusable(false);

        JTextField[] field = new JTextField[2];
        for(int i = 0; i < field.length; i++) {
            panel.add(field[i] = new JTextField());
        }
        panel.add(button);
        field[0].setBounds(280, 200, 500, 45);
        field[0].setFont(new Font("Arial", Font.PLAIN, 30));
        field[0].setToolTipText("User name");

        field[1].setBounds(field[0].getX(), field[0].getY() + 100, field[0].getWidth(), field[0].getHeight());
        field[1].setFont(new Font("Arial", Font.PLAIN, 30));
        field[1].setToolTipText("Password");
        button.setBounds(field[0].getX(), field[1].getY() + 100, field[0].getWidth(), field[0].getHeight());
        button.setFont(new Font("Arial", Font.PLAIN, 30));
        this.setBackground(Color.BLACK);

        button.addActionListener(e -> {
                String email = field[0].getText(), password = field[1].getText();
                if(!email.isEmpty() && !password.isEmpty())
                    try {
                        label.setVisible(true);
                        doServer(email, password, new Socket(Address, port));
                    } catch (IOException exception) {
                        throw new RuntimeException("Error flush: " + exception.getMessage());
                    }
                else
                    System.out.println("null");

        });
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = frame.getWidth(), height = frame.getHeight();
                setBounds(0,0, width, height);
                panel.setBounds(0,0, width, height);
            }
        });
        panels(panel, this);
        isVisiblePanels(true);
    }
    public static void panels(JPanel panel, JPanel panel2) {
        for(JPanel panels : new JPanel[] {panel, panel2}) frame.add(panels);
    }
    public void isVisiblePanels(boolean isVisible) {
        this.setVisible(isVisible);
        frame.setVisible(isVisible);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.PLAIN, 70));
        g.setColor(Color.RED);
        g.drawString(login, 65,100);
    }
    public void doServer(String email, String password, Socket socket) {
        try {
            output = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            byte[] bytes = email.getBytes(StandardCharsets.UTF_8);
            byte[] bytePas = password.getBytes(StandardCharsets.UTF_8);

            output.writeByte(bytes.length);
            output.write(bytes);

            output.writeByte(bytePas.length);
            output.write(bytePas);
            output.flush();

            new Thread(() -> {
                try {
                    byte readByte = input.readByte();
                    if(readByte == 100) SwingUtilities.invokeLater(() -> panel.setVisible(false));
                } catch (IOException exception) {
                    throw new RuntimeException(exception.getMessage());
                }
            }).start();

        } catch (IOException exception) {
            throw new RuntimeException("Error flush: " + exception.getMessage());
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClientLogin().list());
    }
}
