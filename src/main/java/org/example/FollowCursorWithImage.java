package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

public class FollowCursorWithImage extends JPanel implements MouseMotionListener {

    private int mouseX = 0;
    private int mouseY = 0;
    private BufferedImage playerImage;

    public FollowCursorWithImage() {
        try {
            playerImage = ImageIO.read(new URL("https://devast.io//img/day-skin0.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (playerImage != null) {
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;

            // Вычисляем угол к курсору
            double angle = Math.atan2(mouseY - centerY, mouseX - centerX);

            // Создаем Graphics2D для поворота
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.translate(centerX, centerY); // переносим центр
            g2d.rotate(angle); // поворачиваем на угол
            g2d.drawImage(playerImage, -playerImage.getWidth()/2, -playerImage.getHeight()/2, null);
            g2d.dispose();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();
    }

    /*
    double scale = 200 / (200 + zNew); // чем дальше, тем меньше
    int screenX = (int) (xNew * scale + centerX);
    int screenY = (int) (y * scale + centerY);
    g.fillRect(screenX, screenY, (int)(100*scale), (int)(100*scale));
    */

    public static void main(String[] args) {
        JFrame frame = new JFrame("Персонаж следит за курсором");
        FollowCursorWithImage panel = new FollowCursorWithImage();
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
