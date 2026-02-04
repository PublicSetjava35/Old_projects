package org.example.ClassPractitioner.player;
import org.example.ClassPractitioner.abstracts.Wall;

import java.awt.*;

public class MyWall extends Wall {
    private final CollisionWall collisionWall;
    private final KeyBoardCube keyBoardCube;
    public MyWall(CollisionWall collisionWall, KeyBoardCube keyBoardCube) {
        this.collisionWall = collisionWall;
        this.keyBoardCube = keyBoardCube;
    }
    @Override
    public void createWall(Graphics graphics) {
        graphics.setColor(new Color(255, 0, 0, 255));
        graphics.fillRect(collisionWall.wallX(), collisionWall.wallY(),100,   650);
    }

    @Override
    public void collisionCubeAndWall(Rectangle rectangleWall, Rectangle rectangleCube, int oldX, int oldY) {
        if(rectangleCube.intersects(rectangleWall)) {
             keyBoardCube.oldX(oldX);
             keyBoardCube.oldY(oldY);
        }
    }
}