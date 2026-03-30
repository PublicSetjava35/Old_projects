package org.example;

import org.example.classDB.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class App extends JPanel {
    private static final String TITLE = "Moderator";
    private static boolean isBalance;
    public App() {
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000,650));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JTextField[] textField = new JTextField[2];
        for(int i = 0; i < textField.length; i++) {
            this.add(textField[i] = new JTextField());
        }
        textField[0].setBounds(435,260, 200,45);
        textField[0].setFont(new Font("Arial", Font.PLAIN, 25));

        textField[1].setBounds(435,320, 200,45);
        textField[1].setFont(new Font("Arial", Font.PLAIN, 25));

        JButton button = new JButton("пополнить счет");
        button.setBounds(435, 400,200,50);
        button.setFont(new Font("Arial", Font.PLAIN, 15));
        button.setFocusable(false);
        this.add(button);

        button.addActionListener((e) -> {
            int id = Integer.parseInt(textField[0].getText());
            int balance = Integer.parseInt(textField[1].getText());
                updateUser(balance, id);
        });

        this.setBackground(Color.BLACK);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = frame.getWidth(), height = frame.getHeight();
                setBounds(0,0, width, height);
            }
        });

        frame.add(this);
        this.setLayout(null);
        frame.setVisible(true);
    }
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(new Color(192, 10, 10, 255));
        graphics.setFont(new Font("Arial", Font.PLAIN, 45));
        graphics.drawString("ID:", 360,300);
        graphics.setColor(new Color(192, 10, 10, 255));
        graphics.setFont(new Font("Arial", Font.PLAIN, 45));
        graphics.drawString("BAL:", 340,360);
        graphics.setColor(new Color(9, 255, 0, 229));
    }
    public void updateUser(Integer balance, Integer id) {
        Configuration configuration = new Configuration().addAnnotatedClass(Account.class);
        configurations(configuration);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Account account = session.find(Account.class, id);
            account.setBalance(balance);
            session.merge(account);

            transaction.commit();;
        }
    }
    public void configurations(Configuration configuration) {
        configuration.setProperty("тут указываем путь к бд", "назначаем имя бд, или ключ");
    }
    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> new App());
    }
}
