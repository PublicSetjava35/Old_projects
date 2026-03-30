package org.example;
public class Token_Randoms {
    public static int bullet = 10;
    public static int breack_bullet = 1;
    public static boolean isEmpty = true;
    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 10; i++) {
            bullet -= breack_bullet;
            System.out.println("remove bullet: " + bullet);
            Thread.sleep(1000);
        }
        if(bullet == 0) isEmpty = true;
        System.out.println("Bullet reload!");

        if(isEmpty) {
            bullet = 10;
            System.out.println("[][][][][][][][][[][][][][][][][][][][]");
            for(int i = 0; i < 10; i++) {
                bullet -= breack_bullet;
                System.out.println("remove bullet: " + bullet);
                Thread.sleep(1000);
            }
            if(bullet == 0) System.out.println("bullet null: " + bullet);
        }
    }
}
