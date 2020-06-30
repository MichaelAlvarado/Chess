package Display;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DisplayScreen {

	private JFrame frame;
	private Canvas canvas;
	private String title;
	private int width, height;

	public DisplayScreen(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		createDisplay();
	}

	private void createDisplay(){
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		//       frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setBackground(Color.black);
		try {
			frame.setIconImage(ImageIO.read(new File("res/Logo/Logo.jpg"))); //Linux doesnt work
		} catch (IOException e) {
			e.printStackTrace();
		}
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setFocusable(false);
		canvas.setBackground(Color.WHITE);
		
		frame.add(canvas);
		frame.pack();

		/*
		 * Loading Screen
		 */
//		try {
//			canvas.getGraphics().drawImage(ImageIO.read(new File("res/Logo/background3.jpg")), 0, 0, canvas.getWidth(), canvas.getHeight(),null);//loading screen 
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public Canvas getCanvas(){
		return canvas;
	}

	public JFrame getFrame(){
		return frame;
	}

}
