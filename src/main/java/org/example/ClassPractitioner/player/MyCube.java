package org.example.ClassPractitioner.player;

import org.example.ClassPractitioner.abstracts.Cube;

import java.awt.*;

public class MyCube extends Cube {
   private final KeyBoardCube keyBoardCube;
   public MyCube(KeyBoardCube keyBoardCube) {
      this.keyBoardCube = keyBoardCube;
   }
    public void createCube(Graphics graphics) {
        graphics.setColor(new Color(0, 35, 235, 255));
        graphics.fillRect(keyBoardCube.keyX(), keyBoardCube.keyY(), 100,100);
    }
}