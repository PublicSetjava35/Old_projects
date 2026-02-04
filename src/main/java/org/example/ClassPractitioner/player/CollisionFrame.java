package org.example.ClassPractitioner.player;

public class CollisionFrame extends org.example.ClassPractitioner.abstracts.CollisionFrame {
     private final KeyBoardCube keyBoardCube;
     public CollisionFrame(KeyBoardCube keyBoardCube) {
         this.keyBoardCube = keyBoardCube;
     }
    @Override
    public void collisionFrame(int width, int height) {
         int x = keyBoardCube.keyX(), y = keyBoardCube.keyY();
         if(x <= 0) keyBoardCube.frameX(0);
         if(x >= width) keyBoardCube.frameX(width);
         if(y <= 0) keyBoardCube.frameY(0);
         if(y >= height) keyBoardCube.frameY(height);
    }
}