package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Calculator extends Logic {
    JFrame jFrame;
    JLabel textLabel;
    Button clear, delete;
    Button button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9, button_minus, button_plus, button_raven, button_deleni, button_umnoj, button_tol;
    public static final int x7 = 7, x8 = 8, x9 = 9, x4 = 4, x5 = 5, x6 = 6, x1 = 1, x2 = 2,  x3 = 3, x0 = 0;
    private String string_integer = "";
    Calculator() {
        jFrame = new JFrame("Калькулятор");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(450,620);
        // JLabel вместо JTextArea — для отображения внизу справа
        textLabel = new JLabel("", SwingConstants.RIGHT); // Выравнивание по правому краю
        textLabel.setBounds(10, 20, 430, 145);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        textLabel.setBackground(Color.GRAY);
        textLabel.setForeground(Color.RED);
        textLabel.setVerticalAlignment(SwingConstants.BOTTOM); // Выравнивание вниз
        // добавляем кнопки для выполнения, цифр
        // кнопка 7
        button_7 = new Button("7");
        button_7.setBounds(25,200,80,70);
        button_7.setFont(new Font("button_7", Font.PLAIN, 30));
        button_7.setFocusable(false);
        // кнопка 8
        button_8 = new Button("8");
        button_8.setBounds(130,200, 80,70);
        button_8.setFont(new Font("button_8", Font.PLAIN, 30));
        button_8.setFocusable(false);
        // кнопка 9
        button_9 = new Button("9");
        button_9.setBounds(235,200, 80,70);
        button_9.setFont(new Font("button_9", Font.PLAIN, 30));
        button_9.setFocusable(false);
        // кнопка минус(-)
        button_minus = new Button("−");
        button_minus.setBounds(345,200,80,70);
        button_minus.setFont(new Font("button_minus", Font.PLAIN, 30));
        button_minus.setFocusable(false);
        // кнопка 4
        button_4 = new Button("4");
        button_4.setBounds(25,295,80,70);
        button_4.setFont(new Font("button_4", Font.PLAIN, 30));
        button_4.setFocusable(false);
        // кнопка 5
        button_5 = new Button("5");
        button_5.setBounds(130,295,80,70);
        button_5.setFont(new Font("button_5", Font.PLAIN, 30));
        button_5.setFocusable(false);
        // кнопка 6
        button_6 = new Button("6");
        button_6.setBounds(235,295,80,70);
        button_6.setFont(new Font("button_6", Font.PLAIN, 30));
        button_6.setFocusable(false);
        // кнопка плюс(+)
        button_plus = new Button("+");
        button_plus.setBounds(345,295, 80,70);
        button_plus.setFont(new Font("button_plus", Font.PLAIN, 30));
        button_plus.setFocusable(false);
        // кнопка 1
        button_1 = new Button("1");
        button_1.setBounds(25,390,80,70);
        button_1.setFont(new Font("button_1", Font.PLAIN, 30));
        button_1.setFocusable(false);
        // кнопка 2
        button_2 = new Button("2");
        button_2.setBounds(130,390,80,70);
        button_2.setFont(new Font("button_2", Font.PLAIN, 30));
        button_2.setFocusable(false);
        // кнопка 3
        button_3 = new Button("3");
        button_3.setBounds(235,390, 80,70);
        button_3.setFont(new Font("button_3", Font.PLAIN, 30));
        button_3.setFocusable(false);
        // кнопка деления(/)
        button_deleni = new Button("/");
        button_deleni.setBounds(345,390,80,70);
        button_deleni.setFont(new Font("button_deleni", Font.PLAIN, 30));
        button_deleni.setFocusable(false);
        // кнопка 0
        button_0 = new Button("0");
        button_0.setBounds(25,480,80,70);
        button_0.setFont(new Font("button_0", Font.PLAIN, 30));
        button_0.setFocusable(false);
        // кнопка точка(.)
        button_tol = new Button(".");
        button_tol.setBounds(130,480,80,70);
        button_tol.setFont(new Font("button_tol", Font.PLAIN, 30));
        button_tol.setFocusable(false);
        // кнопка равно(=)
        button_raven = new Button("=");
        button_raven.setBounds(235,480,80,70);
        button_raven.setFont(new Font("button_raven", Font.PLAIN, 30));
        button_raven.setFocusable(false);
        // Кнопка умножить(*)
        button_umnoj = new Button("*");
        button_umnoj.setBounds(345,480,80,70);
        button_umnoj.setFont(new Font("button_umnoj", Font.PLAIN, 30));
        button_umnoj.setFocusable(false);
        // кнопка убрать(delete)
        delete = new Button("delete");
        delete.setBounds(235,555,80,30);
        delete.setFocusable(false);
        // кнопка очистить(clear)
        clear = new Button("clear");
        clear.setBounds(345,555,80,30);
        clear.setFocusable(false);

        jFrame.getContentPane().setBackground(Color.BLACK);
        textLabel.setOpaque(true);
        jFrame.setVisible(true);
        jFrame.setLayout(null);
        jFrame.add(textLabel);
        // Добавляем кнопку во Frame
        jFrame.add(button_7);
        jFrame.add(button_8);
        jFrame.add(button_9);
        jFrame.add(button_4);
        jFrame.add(button_5);
        jFrame.add(button_6);
        // минимальные числа
        jFrame.add(button_1);
        jFrame.add(button_2);
        jFrame.add(button_3);
        jFrame.add(button_0);
        // деление, умножение, плюс, минус, точка, равно.
        jFrame.add(button_minus);
        jFrame.add(button_plus);
        jFrame.add(button_deleni);
        jFrame.add(button_tol);
        jFrame.add(button_raven);
        jFrame.add(button_umnoj);
        jFrame.add(delete);
        jFrame.add(clear);
        // Вызываем цифры.
        // Добавляем цвета кнопок и набор цветов
        button_9.setBackground(Color.DARK_GRAY);
        button_7.setBackground(Color.DARK_GRAY);
        button_8.setBackground(Color.DARK_GRAY);
        button_6.setBackground(Color.DARK_GRAY);
        button_5.setBackground(Color.DARK_GRAY);
        button_4.setBackground(Color.DARK_GRAY);
        button_3.setBackground(Color.DARK_GRAY);
        button_2.setBackground(Color.DARK_GRAY);
        button_1.setBackground(Color.DARK_GRAY);
        button_0.setBackground(Color.DARK_GRAY);
        // Окрашиваем операторы в цвет
        button_deleni.setBackground(Color.DARK_GRAY);
        button_tol.setBackground(Color.DARK_GRAY);
        button_minus.setBackground(Color.DARK_GRAY);
        button_umnoj.setBackground(Color.DARK_GRAY);
        button_plus.setBackground(Color.DARK_GRAY);
        button_raven.setBackground(Color.DARK_GRAY);
        // кнопка очистки
        delete.setBackground(Color.RED);
        clear.setBackground(Color.RED);
        // Цвет чисел
        button_9.setForeground(Color.GREEN);
        button_7.setForeground(Color.GREEN);
        button_8.setForeground(Color.GREEN);
        button_6.setForeground(Color.GREEN);
        button_5.setForeground(Color.GREEN);
        button_4.setForeground(Color.GREEN);
        button_3.setForeground(Color.GREEN);
        button_2.setForeground(Color.GREEN);
        button_1.setForeground(Color.GREEN);
        button_0.setForeground(Color.GREEN);
        // окрашиваем их также
        button_deleni.setForeground(Color.RED);
        button_tol.setForeground(Color.RED);
        button_minus.setForeground(Color.RED);
        button_umnoj.setForeground(Color.RED);
        button_plus.setForeground(Color.RED);
        button_raven.setForeground(Color.RED);
        // нопка 7
        button_7.addActionListener(e -> {
            string_integer += x7;
            textLabel.setText(string_integer);
        });
        // кнопка 8
        button_8.addActionListener(e -> {
            string_integer += x8;
            textLabel.setText(string_integer);
        });
        // кнопка 9
        button_9.addActionListener( e -> {
             string_integer += x9;
             textLabel.setText(string_integer);
        });
        // кнопка 4
        button_4.addActionListener(e -> {
            string_integer += x4;
            textLabel.setText(string_integer);
        });
        // кнопка 5
        button_5.addActionListener(e -> {
            string_integer += x5;
            textLabel.setText(string_integer);
        });
        //  кнопка 6
        button_6.addActionListener(e -> {
            string_integer += x6;
            textLabel.setText(string_integer);
        });
        // кнопка 1
        button_1.addActionListener(e-> {
           string_integer += x1;
           textLabel.setText(string_integer);
        });
        // кнопка 2
        button_2.addActionListener(e-> {
            string_integer += x2;
            textLabel.setText(string_integer);
        });
        // кнопка 3
        button_3.addActionListener(e-> {
            string_integer += x3;
            textLabel.setText(string_integer);
        });
        // кнопка 0
        button_0.addActionListener(e-> {
            string_integer += x0;
            textLabel.setText(string_integer);
        });
        // кнопка плюс(+)
        button_plus.addActionListener(e -> {
            string_integer += "+";
            textLabel.setText(string_integer);
        });
        // кнопка умножить(*)
        button_umnoj.addActionListener( e -> {
            string_integer += "*";
            textLabel.setText(string_integer);
        });
        // кнопка минус(-)
        button_minus.addActionListener( e -> {
            string_integer += "-";
            textLabel.setText(string_integer);
        });
        // кнопка деление(/)
        button_deleni.addActionListener(e -> {
            string_integer += "/";
            textLabel.setText(string_integer);
        });
        // кнопка точка(.)
        button_tol.addActionListener(e-> {
            string_integer += ".";
            textLabel.setText(string_integer);
        });
        delete.addActionListener( e-> {
          if(!string_integer.isEmpty()) {
             string_integer = string_integer.substring(0, string_integer.length() -1);
             textLabel.setText(string_integer);
          }
        });
        // Очистить текст
        clear.addActionListener(e -> {
            string_integer = "";
            textLabel.setText(string_integer);
        });
        button_raven.addActionListener(e -> {
            try {
                Number result = calculateFromStringLogic(string_integer);
                textLabel.setText(String.valueOf(result));
            } catch (Exception ex) {
               textLabel.setText("Error => " + x0);
               string_integer = "";
            }
        });
        int[] buttonsY = {200, 295, 390, 480}; // startY
        button_7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                while (buttonsY[0] > 195) {
                      buttonsY[0]--;
                }
                button_7.setLocation(button_7.getX(), buttonsY[0]);
                button_7.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                while (buttonsY[0] < 200) {
                    buttonsY[0]++;
                }
                button_7.setLocation(button_7.getX(), buttonsY[0]);
                button_7.setForeground(Color.GREEN);
            }
        });
        button_8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                while (buttonsY[0] > 195) {
                    buttonsY[0]--;
                }
                button_8.setLocation(button_8.getX(), buttonsY[0]);
                button_8.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                while (buttonsY[0] < 200) {
                    buttonsY[0]++;
                }
                button_8.setLocation(button_8.getX(), buttonsY[0]);
                button_8.setForeground(Color.GREEN);
            }
        });
        button_9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                while (buttonsY[0] > 195) {
                    buttonsY[0]--;
                }
                button_9.setLocation(button_9.getX(), buttonsY[0]);
                button_9.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                while (buttonsY[0] < 200) {
                    buttonsY[0]++;
                }
                button_9.setLocation(button_9.getX(), buttonsY[0]);
                button_9.setForeground(Color.GREEN);
            }
        });
        button_minus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                while (buttonsY[0] > 195) {
                    buttonsY[0]--;
                }
                button_minus.setLocation(button_minus.getX(), buttonsY[0]);
                button_minus.setForeground(Color.GREEN);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                while (buttonsY[0] < 200) {
                    buttonsY[0]++;
                }
                button_minus.setLocation(button_minus.getX(), buttonsY[0]);
                button_minus.setForeground(Color.RED);
            }
        });
        button_4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                while (buttonsY[1] > 290) {
                    buttonsY[1]--;
                }
                button_4.setLocation(button_4.getX(), buttonsY[1]);
                button_4.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                while (buttonsY[1] < 295) {
                    buttonsY[1]++;
                }
                button_4.setLocation(button_4.getX(), buttonsY[1]);
                button_4.setForeground(Color.GREEN);
            }
        });
        button_5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                while (buttonsY[1] > 290) {
                    buttonsY[1]--;
                }
                button_5.setLocation(button_5.getX(), buttonsY[1]);
                button_5.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                while (buttonsY[1] < 295) {
                    buttonsY[1]++;
                }
                button_5.setLocation(button_5.getX(), buttonsY[1]);
                button_5.setForeground(Color.GREEN);
            }
        });
        button_6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                while (buttonsY[1] > 290) {
                    buttonsY[1]--;
                }
                button_6.setLocation(button_6.getX(), buttonsY[1]);
                button_6.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                while (buttonsY[1] < 295) {
                    buttonsY[1]++;
                }
                button_6.setLocation(button_6.getX(), buttonsY[1]);
                button_6.setForeground(Color.GREEN);
            }
        });
        button_plus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                while (buttonsY[1] > 290) {
                    buttonsY[1]--;
                }
                button_plus.setLocation(button_plus.getX(), buttonsY[1]);
                button_plus.setForeground(Color.GREEN);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                while (buttonsY[1] < 295) {
                    buttonsY[1]++;
                }
                button_plus.setLocation(button_plus.getX(), buttonsY[1]);
                button_plus.setForeground(Color.RED);
            }
        });
        button_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                while (buttonsY[2] > 385) {
                    buttonsY[2]--;
                }
                button_1.setLocation(button_1.getX(), buttonsY[2]);
                button_1.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                while (buttonsY[2] < 390) {
                    buttonsY[2]++;
                }
                button_1.setLocation(button_1.getX(), buttonsY[2]);
                button_1.setForeground(Color.GREEN);
            }
        });
        button_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                while (buttonsY[2] > 385) {
                    buttonsY[2]--;
                }
                button_2.setLocation(button_2.getX(), buttonsY[2]);
                button_2.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                while (buttonsY[2] < 390) {
                    buttonsY[2]++;
                }
                button_2.setLocation(button_2.getX(), buttonsY[2]);
                button_2.setForeground(Color.GREEN);
            }
        });
        button_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                while (buttonsY[2] > 385) {
                    buttonsY[2]--;
                }
                button_3.setLocation(button_3.getX(), buttonsY[2]);
                button_3.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                while (buttonsY[2] < 390) {
                    buttonsY[2]++;
                }
                button_3.setLocation(button_3.getX(), buttonsY[2]);
                button_3.setForeground(Color.GREEN);
            }
        });
        button_deleni.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                while (buttonsY[2] > 385) {
                    buttonsY[2]--;
                }
                button_deleni.setLocation(button_deleni.getX(), buttonsY[2]);
                button_deleni.setForeground(Color.GREEN);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                while (buttonsY[2] < 390) {
                    buttonsY[2]++;
                }
                button_deleni.setLocation(button_deleni.getX(), buttonsY[2]);
                button_deleni.setForeground(Color.RED);
            }
        });
        button_0.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                while (buttonsY[3] > 475) {
                    buttonsY[3]--;
                }
                button_0.setLocation(button_0.getX(), buttonsY[3]);
                button_0.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                while (buttonsY[3] < 480) {
                    buttonsY[3]++;
                }
                button_0.setLocation(button_0.getX(), buttonsY[3]);
                button_0.setForeground(Color.GREEN);
            }
        });
        button_tol.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                while (buttonsY[3] > 475) {
                    buttonsY[3]--;
                }
                button_tol.setLocation(button_tol.getX(), buttonsY[3]);
                button_tol.setForeground(Color.GREEN);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                while (buttonsY[3] < 480) {
                    buttonsY[3]++;
                }
                button_tol.setLocation(button_tol.getX(), buttonsY[3]);
                button_tol.setForeground(Color.RED);
            }
        });
        button_raven.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                while (buttonsY[3] > 475) {
                    buttonsY[3]--;
                }
                button_raven.setLocation(button_raven.getX(), buttonsY[3]);
                button_raven.setForeground(Color.GREEN);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                while (buttonsY[3] < 480) {
                    buttonsY[3]++;
                }
                button_raven.setLocation(button_raven.getX(), buttonsY[3]);
                button_raven.setForeground(Color.RED);
            }
        });
        button_umnoj.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                while (buttonsY[3] > 475) {
                    buttonsY[3]--;
                }
                button_umnoj.setLocation(button_umnoj.getX(), buttonsY[3]);
                button_umnoj.setForeground(Color.GREEN);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                while (buttonsY[3] < 480) {
                    buttonsY[3]++;
                }
                button_umnoj.setLocation(button_umnoj.getX(), buttonsY[3]);
                button_umnoj.setForeground(Color.RED);
            }
        });
    }
    public static void main( String[] args ) {
        new Calculator();
    }
}
