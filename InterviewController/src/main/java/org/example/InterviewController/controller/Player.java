package org.example.InterviewController.controller;
import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;

public class Player extends Cube {
    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    @Override
    public void key(char command, DataOutputStream out, int frameWidth, int frameHeight) throws IOException {
        super.key(command, out, frameWidth, frameHeight);
    }
    @Override
    public void inputKey(int x, int y, int frameWidth, int frameHeight) {
        super.inputKey(x, y, frameWidth, frameHeight);
    }
    @Override
    public void drawCube(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }
}