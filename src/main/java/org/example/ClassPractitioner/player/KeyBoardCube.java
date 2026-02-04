package org.example.ClassPractitioner.player;

import org.example.ClassPractitioner.abstracts.KeyBoard;

public class KeyBoardCube extends KeyBoard {
    private int x = 500, y = 500;
    private final int speed = 10;
    private int oldX, oldY;
    @Override
    public void A() {
        x -= speed;
    }

    @Override
    public void D() {
        x += speed;
    }

    @Override
    public void S() {
        y += speed;
    }

    @Override
    public void W() {
        y -= speed;
    }


    @Override
    public int keyY() {
        return y;
    }

    @Override
    public int keyX() {
        return x;
    }

    @Override
    public void oldX(int oldX) {
       this.x = oldX;
    }

    @Override
    public void oldY(int oldY) {
       this.y = oldY;
    }

    @Override
    public void frameX(int width) {
         this.x = width;
    }

    @Override
    public void frameY(int height) {
         this.y = height;
    }

}