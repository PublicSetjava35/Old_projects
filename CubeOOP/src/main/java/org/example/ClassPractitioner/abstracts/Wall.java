package org.example.ClassPractitioner.abstracts;

import java.awt.*;

public abstract class Wall {
    public abstract void createWall(Graphics graphics);
    public abstract void collisionCubeAndWall(Rectangle rectangleWall, Rectangle rectangleCube, int oldX, int oldY);

}