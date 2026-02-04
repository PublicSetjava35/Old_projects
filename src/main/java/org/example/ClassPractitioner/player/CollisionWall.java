package org.example.ClassPractitioner.player;

import org.example.ClassPractitioner.abstracts.Collision;

import java.awt.*;

public class CollisionWall extends Collision {
    private final KeyBoardCube keyBoardCube;
    public CollisionWall(KeyBoardCube keyBoardCube) {
        this.keyBoardCube = keyBoardCube;
    }
    private final int x = 650;
    private final int y = 0;

    @Override
    public int wallX() {
        return x;
    }
    @Override
    public int wallY() {
        return y;
    }

    @Override
    public Rectangle rectangleWall() {
        return new Rectangle(x, y, 100,650);
    }

    @Override
    public Rectangle rectangleCube() {
        return new Rectangle(keyBoardCube.keyX(), keyBoardCube.keyY(), 100,100);
    }
}