package org.example;
import org.example.course.Base;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
public class Hibernate extends JPanel {
    public static Configuration configuration;
    public static SessionFactory sessionFactory;
    public static Session session;
    public JFrame frame;
    public JTextField[] text;
    public JButton button;
    public static Parameters parent;
    public AnnotationConfigApplicationContext context;
    public Hibernate() {
        frame = new JFrame("Hibernate Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,650);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        this.setLayout(null);
        this.setBackground(Color.GRAY);

        text = new JTextField[2];
        for(int i = 0; i < text.length; i++) {
            this.add(text[i] = new JTextField());
            text[i].setFont(new Font("Arial", Font.PLAIN, 22));
        }
        text[0].setBounds(350,250,300,50);
        text[1].setBounds(350,350,300,50);

        button = new JButton("LOGIN");
        button.setBounds(text[0].getX(),450, text[0].getWidth(), text[0].getHeight());
        button.setFocusable(false);

        context = new AnnotationConfigApplicationContext(Configurations.class);
        parent = context.getBean(Parameters.class);
        button.addActionListener(e -> {
            try {
                String name = text[0].getText(), password = text[1].getText();
                if (name.isEmpty() || password.isEmpty()) {
                    System.out.println("Filed null");
                } else {
                    int parser = Integer.parseInt(password);
                    _register_(name, parser, random_keys(), level());
                }
            } catch (Exception exception) {
                System.out.println("exception null: " + exception.getMessage());
            }
        });
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                setBounds(0,0, frame.getWidth(), frame.getHeight());
            }
        });
        frame.add(this);
        this.add(button);
        frame.setVisible(true);
    }
    public void _register_(String name, int age, String keys, int level) {
        configuration = new Configuration().addAnnotatedClass(Base.class);
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Base base = new Base(name, age, keys, level);
            session.persist(base);
            session.getTransaction().commit();
        } finally {
          session.close();
        }
    }
    public String random_keys() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 10; i++) builder.append(parent.getKeys().charAt((int)Math.floor(Math.random() * parent.getKeys().length())));
        return builder.toString();
    }
    public int level() {
        int level = (int)Math.floor(1 * Math.random() * parent.getLevel());
        if(level == parent.getLevel()) level -= (int)(1 * Math.random() * parent.getLevel());
        return level;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Hibernate().setVisible(true));
    }
}