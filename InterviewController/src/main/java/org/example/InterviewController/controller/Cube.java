package org.example.InterviewController.controller;

import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public abstract class Cube {
    private volatile int x, y, width, height;
    public abstract void drawCube(Graphics g);
      public Cube(int x, int y, int width, int height) {
          this.x = x;
          this.y = y;
          this.width = width;
          this.height = height;
      }
      public void key(char command, DataOutputStream out, int frameWidth, int frameHeight) throws IOException {
         if(command == 'W') out.writeChar('W');
         if(command == 'S') out.writeChar('S');
         if(command == 'A') out.writeChar('A');
         if(command == 'D') out.writeChar('D');
         out.flush();
      }
      public void inputKey(int x, int y, int frameWidth, int frameHeight) {
          this.x = x;
          this.y = y;
      }
      public int getX() {
          return x;
      }
      public int getY() {
          return y;
      }
      public int getWidth() {
          return width;
      }
      public int getHeight() {
          return height;
      }
}