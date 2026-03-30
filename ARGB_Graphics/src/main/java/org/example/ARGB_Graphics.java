package org.example;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
public class ARGB_Graphics extends JPanel {
    ARGB rgb = new ARGB(0,0, 110, 900, 110*2, 0,110*3, 900, 110*4,0, 110*5, 900, 110*6, 0, 110*7, 900, 900, 0);
    ARGB_Graphics() throws IOException {
        JFrame frame = new JFrame("Block.ARGB");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,650);
        frame.add(this);
        frame.setVisible(true);
        new Timer(10, e -> {
            updateUI();
            Animals();
                // первый блок
                if (rgb.blockY > this.getHeight() - 100) {
                    rgb.isARGB = true;
                    rgb.animations = -Math.abs(rgb.animations); // меняем направление вверх
                } else if (rgb.blockY < 0) {
                       rgb.isARGB = false;
                       rgb.animations = Math.abs(rgb.animations);  // меняем направление вниз
                }
                // второй блок
                if (rgb.blockY2 > this.getHeight() - 100) {
                    rgb.isARGB2 = false;
                    rgb.getAnimations = -Math.abs(rgb.getAnimations);
                } else if (rgb.blockY2 < 0) {
                       rgb.isARGB2 = true;
                       rgb.getAnimations = Math.abs(rgb.getAnimations);
                }
                // третий блок
                if (rgb.blockY3 > this.getHeight() - 100) {
                       rgb.isARGB3 = true;
                       rgb.animations3 = -Math.abs(rgb.animations3); // меняем направление вверх
                } else if (rgb.blockY3 < 0) {
                       rgb.isARGB3 = false;
                       rgb.animations3 = Math.abs(rgb.animations3);  // меняем направление вниз
                }
                // четвертый блок
                if(rgb.blockY4 > this.getHeight() - 100) {
                    rgb.isARGB4 = true;
                    rgb.animations4 = -Math.abs(rgb.animations4);
                } else if (rgb.blockY4 < 0) {
                    rgb.isARGB4 = false;
                    rgb.animations4 = Math.abs(rgb.animations4);
                }
                if(rgb.blockY5 > this.getHeight() - 100) {
                    rgb.isARGB5 = true;
                    rgb.animations5 = -Math.abs(rgb.animations5);
                } else if (rgb.blockY5 < 0) {
                    rgb.isARGB5 = false;
                    rgb.animations5 = Math.abs(rgb.animations5);
                }
                // пятый блок
                if(rgb.blockY6 > this.getHeight() - 100) {
                    rgb.isARGB6 = true;
                    rgb.animations6 = -Math.abs(rgb.animations6);
                } else if (rgb.blockY6 < 0) {
                    rgb.isARGB6 = false;
                    rgb.animations6 = Math.abs(rgb.animations6);
                }
                if(rgb.blockY7 > this.getHeight() - 100) {
                rgb.isARGB7 = true;
                rgb.animations7 = -Math.abs(rgb.animations7);
               } else if (rgb.blockY7 < 0) {
                  rgb.isARGB7 = false;
                  rgb.animations7 = Math.abs(rgb.animations7);
               }
               if(rgb.blockY8 > this.getHeight() - 100) {
                   rgb.isARGB8 = true;
                   rgb.animations8 = -Math.abs(rgb.animations8);
               } else if (rgb.blockY8 < 0) {
                    rgb.isARGB8 = false;
                    rgb.animations8 = Math.abs(rgb.animations8);
               }
               if(rgb.blockY9 > this.getHeight() - 100) {
                   rgb.isARGB9 = true;
                   rgb.animations9 = -Math.abs(rgb.animations9);
               } else if (rgb.blockY9 < 0) {
                    rgb.isARGB9 = false;
                    rgb.animations9 = Math.abs(rgb.animations9);
               }
            repaint();
             }).start();
         }
        URL url = new URL("https://files.catbox.moe/i2mfee.jpg");
        Image img = ImageIO.read(url);
        public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(img, 0,0,this.getWidth(), this.getHeight(), null);
        if(!rgb.isARGB) {
            g2.setColor(Color.BLUE);
        } else {
            g2.setColor(Color.RED);
        }
        g2.fillRect(rgb.blockX, rgb.blockY, 100,100);
        if(!rgb.isARGB2) {
            g2.setColor(Color.CYAN);
        } else {
            g2.setColor(Color.GREEN);
        }
        g2.fillRect(rgb.blockX2, rgb.blockY2, 100,100);
        if(!rgb.isARGB3) {
            g2.setColor(Color.MAGENTA);
        } else {
            g2.setColor(Color.YELLOW);
        }
        g2.fillRect(rgb.blockX3, rgb.blockY3,100,100);
        if(!rgb.isARGB4) {
            g2.setColor(Color.BLUE);
        } else {
            g2.setColor(Color.GRAY);
        }
        g2.fillRect(rgb.blockX4, rgb.blockY4, 100,100);
        if(!rgb.isARGB5) {
            g2.setColor(Color.BLACK);
        } else {
            g2.setColor(Color.CYAN);
        }
        g2.fillRect(rgb.blockX5, rgb.blockY5, 100,100);
        if(!rgb.isARGB6) {
            g2.setColor(Color.CYAN);
        } else {
            g2.setColor(Color.BLACK);
        }
        g2.fillRect(rgb.blockX6, rgb.blockY6, 100,100);
        if(!rgb.isARGB7) {
            g2.setColor(Color.RED);
        } else {
            g2.setColor(Color.YELLOW);
        }
        g2.fillRect(rgb.blockX7, rgb.blockY7, 100,100);
        if(!rgb.isARGB8) {
            g2.setColor(Color.MAGENTA);
        } else {
            g2.setColor(Color.BLUE);
        }
        g2.fillRect(rgb.blockX8, rgb.blockY8, 100,100);
        if(!rgb.isARGB9) {
            g2.setColor(Color.YELLOW);
        } else {
            g2.setColor(Color.BLUE);
        }
        g2.fillRect(rgb.blockX9, rgb.blockY9, 100,100);
    }
    public void Animals() {
        rgb.blockY += (int) rgb.animations;
        rgb.blockY2 += (int) rgb.getAnimations;
        rgb.blockY3 += (int) rgb.animations3;
        rgb.blockY4 += (int) rgb.animations4;
        rgb.blockY5 += (int) rgb.animations5;
        rgb.blockY6 += (int) rgb.animations6;
        rgb.blockY7 += (int) rgb.animations7;
        rgb.blockY8 += (int) rgb.animations8;
        rgb.blockY9 += (int) rgb.animations9;
    }
    public static void main( String[] args ) throws IOException {
        new ARGB_Graphics();
    }
}
