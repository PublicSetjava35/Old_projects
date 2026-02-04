package org.example.ClassPractitioner;

import org.example.ClassPractitioner.player.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SpringBootApplication
public class ClassPractitionerApplication extends JPanel {
	private final JFrame frame;
	private final KeyBoardCube keyBoardCube = new KeyBoardCube();
	private final CollisionWall collisionWall = new CollisionWall(keyBoardCube);
	private final MyWall myWall = new MyWall(collisionWall, keyBoardCube);
	private final MyCube cube = new MyCube(keyBoardCube);
	private final CollisionFrame collisionFrame = new CollisionFrame(keyBoardCube);
	private final boolean[] keys = new boolean[256];
	public ClassPractitionerApplication() {
		frame = new JFrame("Cube");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(1000,650));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		frame.setFocusable(true);
		frame.requestFocusInWindow();
        this.setFocusable(true);
		this.requestFocusInWindow();
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keys[e.getKeyCode()] = true;
			}

			@Override
			public void keyReleased(KeyEvent e) {
				keys[e.getKeyCode()] = false;
			}
		});
		new Timer(10, e -> {
			updateUI();
			int x = keyBoardCube.keyX();
			int y = keyBoardCube.keyY();
			if(keys[KeyEvent.VK_W]) keyBoardCube.W();
			if(collisionWall.rectangleCube().intersects(collisionWall.rectangleWall())) keyBoardCube.oldY(y);
			if(keys[KeyEvent.VK_S]) keyBoardCube.S();
			if(collisionWall.rectangleCube().intersects(collisionWall.rectangleWall())) keyBoardCube.oldY(y);
			if(keys[KeyEvent.VK_A]) keyBoardCube.A();
			if(collisionWall.rectangleCube().intersects(collisionWall.rectangleWall())) keyBoardCube.oldX(x);
			if(keys[KeyEvent.VK_D]) keyBoardCube.D();
			if(collisionWall.rectangleCube().intersects(collisionWall.rectangleWall())) keyBoardCube.oldX(x);
			collisionFrame.collisionFrame(this.getWidth() - 100, this.getHeight() - 100);
			myWall.collisionCubeAndWall(collisionWall.rectangleCube(), collisionWall.rectangleWall(), x, y);
		}).start();

		frame.add(this);
	}
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		cube.createCube(graphics);
		myWall.createWall(graphics);
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new ClassPractitionerApplication().repaint());
	}
}

