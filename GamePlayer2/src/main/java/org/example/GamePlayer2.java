package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
public class GamePlayer2 extends JPanel {
    public static Object var; // применяем любые объекты
    public static boolean[] keys = new boolean[256], keys2 = new boolean[256];
    // создаем пули первого игрока!
    private final ArrayList<Point> bullets = new ArrayList<>();
    private final ArrayList<Point> bulletsOne = new ArrayList<>();
    // создаем пули второго игрока!
    private final ArrayList<Point> bullets2 = new ArrayList<>();
    private final ArrayList<Point> bulletsTwo = new ArrayList<>();
    public static int playerX = 0, playerY = 500;
    public static int player2X = 1820, player2Y = 500;
    public static final int speed = 10, speed2 = 10;
    public static int playerXP = 255, playerXP2 = 255;
    final String[] values = {"new round"};
    public static int damage = 4;
    JLabel labelTimer, labelXP, labelXP2, winBlue, winRed;
    JFrame frame;
    GamePlayer2() throws IOException {
        frame = new JFrame("Player vs Player2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,650);
        labelTimer = new JLabel("00:00");
        labelTimer.setBounds(915,0, 125,45);
        labelTimer.setFont(new Font("00" , Font.BOLD, 32));
        labelTimer.setForeground(Color.WHITE);
        labelXP = new JLabel("XP:255");
        labelXP.setBounds(0,0, 150, 50);
        labelXP.setFont(new Font("XP: ", Font.BOLD, 32));
        labelXP.setForeground(Color.RED);
        labelXP2 = new JLabel("255:XP");
        labelXP2.setFont(new Font(":XP", Font.BOLD, 32));
        labelXP2.setBounds(1800, 0, 150,50);
        labelXP2.setForeground(Color.BLUE);
        winBlue = new JLabel("           WIN:BLUE");
        winBlue.setForeground(Color.BLUE);
        winBlue.setFont(new Font("WIN:BLUE", Font.BOLD, 15));
        winRed = new JLabel("           WIN:RED");
        winRed.setFont(new Font("WINE:RED", Font.BOLD,15));
        winRed.setForeground(Color.RED);
        frame.requestFocus();
        frame.setFocusable(true);
        frame.setVisible(true);
        requestFocusInWindow();
        setFocusable(true);
        frame.add(this);
        setLayout(null);
        add(labelTimer);
        add(labelXP);
        add(labelXP2);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keys[e.getKeyCode()] = true;
                keys2[e.getKeyCode()] = true;
            }
            @Override
            public void keyReleased(KeyEvent e) {
                keys[e.getKeyCode()] = false;
                keys2[e.getKeyCode()] = false;
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_SPACE == true) {
                    bullets.add(new Point(playerX + 80, playerY + 25));
                    bulletsOne.add(new Point(playerX + 80, playerY + 95));
                } else if (key == KeyEvent.VK_PAGE_UP) {
                    bullets2.add(new Point(player2X + 15, player2Y + 40));
                    bulletsTwo.add(new Point(player2X + 15, player2Y + 85));
                } else if (key == KeyEvent.VK_ESCAPE) {
                    int choice = JOptionPane.showOptionDialog(
                            null,
                            "                   MENU",
                            null,
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            values,
                            values[0]
                    );
                    if(choice == 0) {
                        if(playerXP < 255 && playerXP2 < 255) {
                            playerXP = 255;
                            playerXP2 = 255;
                            labelXP2.setText(playerXP2 + ":XP");
                            labelXP.setText("XP:" + playerXP);
                        }
                        bullets.clear();
                        bulletsOne.clear();
                        bullets2.clear();
                        bulletsTwo.clear();
                    } else if (choice == 1) {
                        System.out.println("menu:null");
                    }
                }
            }
        });
        // первый игрок
        Timer timer = new Timer(10, e ->  {
            updateUI();
            if(keys[KeyEvent.VK_W] && playerY >= 0) {
                playerY -= speed;
                if(playerY < 50) playerY = 50; // проверяем столкновение
            } else if(keys[KeyEvent.VK_S] && playerY >= 0) {
                   playerY += speed;
                   if(playerY >= 880) playerY = 880;
            } else if(keys[KeyEvent.VK_A] && playerX >= 0) {
                   playerX -= speed;
                if(playerX < 0) playerX = 0; // проверяем столкновение
            } else if(keys[KeyEvent.VK_D] && playerX >= 0) {
                playerX += speed;
                if (playerX >= 850) playerX = 845; // проверяем столкновение барьера
             }
        });
        repaint();
        timer.start();
        // Второй игрок 2
        Timer timer2 = new Timer(10, e2 -> {
            if(keys2[KeyEvent.VK_UP] && player2Y >= 0) {
                player2Y -= speed2;
                if(player2Y < 50) player2Y = 50;
            } else if(keys2[KeyEvent.VK_DOWN] && player2Y >= 0) {
                player2Y += speed2;
                if(player2Y >= 880) player2Y = 880; // ghj
            } else if(keys2[KeyEvent.VK_LEFT] && player2X >= 0) {
                player2X -= speed2;
                if(player2X < 0) { // проверяем столкновение
                    player2X = 0;
                } else if (player2X < 950) { // проверяем столкновение барьера
                    player2X = 950;
                }
            } else if(keys2[KeyEvent.VK_RIGHT] && player2X >= 0) {
                player2X += speed2;
                if(player2X >= 1820) player2X = 1820; // проверяем столкновение
            }
        });
        timer2.start();
        Timer timerBullet = new Timer(15, e -> {
            // пули первого игрока
            for (Point bullet : bullets) {
                bullet.x += 10;
            }
             for(Point bullet : bulletsOne) {
                bullet.x += 10;
            }
             // пули второго игрока
             for (Point bullet : bullets2) {
                 bullet.x -= 10;
             }
             for (Point bullet : bulletsTwo) {
                 bullet.x -= 10;
             }
             /// проверяем попадание.
             Rectangle player = new Rectangle(playerX, playerY, img.getWidth(this), img.getHeight(this));
             Rectangle player2 = new Rectangle(player2X, player2Y, img2.getWidth(this), img2.getHeight(this));
             // пули первого игрока
             for(Point bullet : bullets) {
               Rectangle bulletRectangle = new Rectangle(bullet.x, bullet.y, 15, 8);
               if(bulletRectangle.intersects(player2)) {
                   playerXP2 -= damage;
                   labelXP2.setText(playerXP2 +  ":XP");
                   if(playerXP2 < 0) {
                       playerXP2 = 0;
                       labelXP2.setText(playerXP2 +  ":XP");
                   }
                   bullets.remove(bullet);
                   break;
               }
             }
             for (Point bullet : bulletsOne) {
                 Rectangle bulletRectangle = new Rectangle(bullet.x,bullet.y,15,8);
                 if(bulletRectangle.intersects(player2)) {
                     playerXP2 -= damage;
                     labelXP2.setText(playerXP2 +  ":XP");
                     if(playerXP2 < 0) {
                         playerXP2 = 0;
                         labelXP2.setText(playerXP2 +  ":XP");
                         int choice = JOptionPane.showOptionDialog(
                                 null,
                                 winRed,
                                 null,
                                 JOptionPane.DEFAULT_OPTION,
                                 JOptionPane.INFORMATION_MESSAGE,
                                 null,
                                 values,
                                 values[0]
                         );
                         if(choice == 0) {
                             if(playerXP2 < 255) {
                                 playerXP2 = 255;
                                 labelXP2.setText(playerXP2 + ":XP");
                             }
                             if (playerXP < 255) {
                                 playerXP = 255;
                                 labelXP.setText("XP:" + playerXP);
                             }
                             bullets.clear();
                             bulletsOne.clear();
                             bullets2.clear();
                             bulletsTwo.clear();
                         } else if (choice == 1) {
                             System.out.println("menu:null");
                         }
                     }

                     labelXP2.setText(playerXP2 + ":XP");
                     bulletsOne.remove(bullet);
                     break;
                 }
             }
            // пули второго игрока
             for (Point bullet : bullets2) {
                 Rectangle bulletRectangle = new Rectangle(bullet.x, bullet.y, 15,8);
                 if(bulletRectangle.intersects(player)) {
                     playerXP -= damage;
                     labelXP.setText("XP:" + playerXP);
                     if(playerXP < 0) {
                         playerXP = 0;
                         labelXP.setText("XP:" + playerXP);
                         int choice = JOptionPane.showOptionDialog(
                                 null,
                                 winBlue,
                                 null,
                                 JOptionPane.DEFAULT_OPTION,
                                 JOptionPane.INFORMATION_MESSAGE,
                                 null,
                                 values,
                                 values[0]
                         );
                         if(choice == 0) {
                             if(playerXP < 255) {
                                 playerXP = 255;
                                 labelXP.setText("XP:" + playerXP);
                             }
                             if (playerXP2 < 255) {
                                 playerXP2 = 255;
                                 labelXP2.setText(playerXP2 + ":XP");
                             }
                             bullets.clear();
                             bulletsOne.clear();
                             bullets2.clear();
                             bulletsTwo.clear();
                         } else if (choice == 1) {
                             System.out.println("menu:null");
                         }
                     }
                     labelXP.setText("XP:" + playerXP);
                     bullets2.remove(bullet);
                     break;
                 }
             }
             for (Point bullet : bulletsTwo) {
                 Rectangle bulletRectangle = new Rectangle(bullet.x,bullet.y, 15,8);
                 if(bulletRectangle.intersects(player)) {
                     playerXP -= damage;
                     labelXP.setText("XP:" + playerXP);
                     if(playerXP < 0) {
                         playerXP = 0;
                         labelXP.setText("XP:" + playerXP);
                     }
                     labelXP.setText("XP:" + playerXP);
                     bulletsTwo.remove(bullet);
                     break;
                 }
             }
            repaint();
        });
        timerBullet.start();
        startGame();
    }
    // добавляем изображение
    URL url = new URL("https://files.catbox.moe/wh0kkn.gif");
    final Image img = ImageIO.read(url);
    URL url2 = new URL("https://files.catbox.moe/yxz2fz.gif");
    final Image img2 = ImageIO.read(url2);
    URL frameImg = new URL("https://cdn.pixabay.com/photo/2022/06/08/05/47/stars-7249785_1280.jpg");
    final Image GuiImg = ImageIO.read(frameImg);
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (frameImg != null) {
            g.drawImage(GuiImg, 0, 0, getWidth(), getHeight(), this);
        } else {
            var = "Ошибка запроса, фона";
        }
        g.drawImage(img, playerX, playerY, img.getWidth(this), img.getHeight(this), this);
        g.drawImage(img2, player2X, player2Y, img2.getWidth(this), img2.getHeight(this), this);
        g.setColor(Color.blue);
        g.fillRect(945, 0, 20, frame.getHeight()); // создаем барьер.
        g.setColor(Color.BLACK);
        g.fillRect(0,0, getWidth(), 50);
        // создаем пулю
        g.setColor(Color.RED);
        for (Point bullet : bullets) {
            g.fillRect(bullet.x, bullet.y, 15, 8);
        }
        g.setColor(Color.RED);
        for (Point bullet : bulletsOne) {
            g.fillRect(bullet.x, bullet.y, 15, 8);
        }
        g.setColor(Color.RED);
        for (Point bullet : bullets2) {
            g.fillRect(bullet.x, bullet.y, 15,8);
        }
        g.setColor(Color.RED);
        for (Point bullet : bulletsTwo) {
            g.fillRect(bullet.x, bullet.y, 15,8);
        }
    }
    Timer timer = new Timer(1000, null);
    int[] timers = {300};

    public void startGame() {
        timers[0] = 300;
        labelTimer.setText("05:00");

        timer.stop();
        timer = new Timer(1000, e -> {
            timers[0]--;
            int minutes = timers[0] / 60;
            int seconds = timers[0] % 60;
            labelTimer.setText(String.format("%02d:%02d", minutes, seconds));

            if (timers[0] <= 0) {
                timers[0] = 0;
                labelTimer.setText("00:00");
                timer.stop();

                int choice = JOptionPane.showOptionDialog(
                        null,
                        "                   DRAW",
                        null,
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        values,
                        values[0]
                );

                if (choice == 0) {
                    if (playerXP < 255) {
                        playerXP = 255;
                        labelXP.setText("XP:" + playerXP);
                    }
                    if (playerXP2 < 255) {
                        playerXP2 = 255;
                        labelXP2.setText(playerXP2 + ":XP");
                    }

                    bullets.clear();
                    bulletsOne.clear();
                    bullets2.clear();
                    bulletsTwo.clear();
                    startGame();
                } else if (choice == 1) {
                    System.out.println("menu:null");
                }
            }
        });
        timer.start();
    }
    public static void main(String[] args) {
        try {
            new GamePlayer2();
        } catch (IOException e) {
            e.addSuppressed(new Throwable("Error code, from image"));
        }
    }
}
