package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class HibernateAPP extends JPanel {
    public JFrame frame;
    public HibernateAPP(Socket socket) throws IOException {
        DataInputStream inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        frame = new JFrame("Test Hibernate");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,650);
        frame.setLocationRelativeTo(null);

        JTextField[] fields = new JTextField[2];
        for(int i = 0; i < fields.length; i++){
            frame.add(fields[i] = new JTextField());
        }

        fields[0].setBounds(400,100,300,25);
        fields[0].setFont(new Font("arial", Font.BOLD, 20));
        fields[1].setBounds(400,300,300,25);
        fields[1].setFont(new Font("arial", Font.BOLD, 20));

        JButton button = new JButton("save");
        button.setBounds(400,425, fields[0].getWidth(),50);

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                  setBounds(0,0, frame.getWidth(), frame.getHeight());
            }
        });
        button.addActionListener(e -> {
        });
        fields[0].addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (fields[0].getText().length() >= 20) e.consume();
            }
        });
        fields[1].addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if(fields[1].getText().length() >= 20) e.consume();
            }
        });
        button.addActionListener(e -> {
            try {
                outputStream.writeByte(10);
                outputStream.writeBoolean(false);
                outputStream.flush();
            } catch (IOException exception) {
                System.err.println("error byte: " + exception.getMessage());
            }
        });
        new Thread(() -> {
            try {
                while (true) {
                   boolean isValid = inputStream.readBoolean();
                   isValid = !isValid;
                   Collections_input(isValid);
                }
            } catch (IOException exception) {
                System.out.println("error byte: " +  exception.getMessage());
            }
        }).start();
        this.setBackground(Color.BLACK);
        frame.add(button);
        frame.add(this);
        this.setLayout(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    public void Collections_input(boolean isValid) {
        frame.setVisible(isValid);
    }
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.1.1", 8080);
        SwingUtilities.invokeLater(() -> {
            try {
                new HibernateAPP(socket).repaint();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}