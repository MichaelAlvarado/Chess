package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import Display.DisplayScreen;
import Input.KeyManager;
import Input.MouseManager;
import Input.MusicManager;
import Input.Images;

public class GameEngine implements Runnable {

	public static DisplayScreen display;
	public String title;
	private int width, height;
	private boolean running = false;
	private Thread thread;
	public static boolean threadB;
	private BufferStrategy bs;
	private Graphics g;
	public KeyManager keyManager;
	public MouseManager mouseManager;
	public MusicManager musicHandler;

	public GameEngine(String title) {
		this.title = title;
		width = 1000;
		height = 1000;
		threadB=false;

		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		musicHandler = new MusicManager();
	}

	private void init(){
		display = new DisplayScreen(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);

		Images img = new Images();
	}

	public void reStart(){
	}

	public synchronized void start(){
		if(running)
			return;
		running = true;
		//this runs the run method in this  class
		thread = new Thread(this);
		thread.start();
	}

	public void run(){

		//initiallizes everything in order to run without breaking
		init();

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while(running){
			//makes sure the games runs smoothly at 60 FPS
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if(delta >= 1){
				//re-renders and ticks the game around 60 times per second
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000){
				ticks = 0;
				timer = 0;
			}
		}

		stop();

	}

	private void tick(){
		//checks for key types and manages them
		keyManager.tick();

		if(musicHandler.ended()){
			//musicHandler.restartBackground();
		}

		//game states are the menus
		//			if(State.getState() != null)
		//				State.getState().tick();


	}


	private void render(){
		bs = display.getCanvas().getBufferStrategy();

		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0,  width, height);

		//Draw Here!
		Graphics2D g2 = (Graphics2D) g.create();
		
		//White Piece Test
		g.drawImage(Images.WKing,0,0,132,132,null);
		g.drawImage(Images.WPawn,150,0,132,132,null);
		g.drawImage(Images.WBishop,300,0,132,132,null);
		g.drawImage(Images.WQueen,450,0,132,132,null);
		g.drawImage(Images.WRook,600,0,132,132,null);
		g.drawImage(Images.WKnight,750,0,132,132,null);
		//Black Piece Test
		g.drawImage(Images.BKing,0,132,132,132,null);
		g.drawImage(Images.BPawn,150,132,132,132,null);
		g.drawImage(Images.BBishop,300,132,132,132,null);
		g.drawImage(Images.BQueen,450,132,132,132,null);
		g.drawImage(Images.BRook,600,132,132,132,null);
		g.drawImage(Images.BKnight,750,132,132,132,null);
		




		//			if(State.getState() != null)
		//				State.getState().render(g);

		//End Drawing!
		bs.show();
		g.dispose();
	}



	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public KeyManager getKeyManager(){
		return keyManager;
	}

	public MusicManager getMusicHandler() {
		return musicHandler;
	}


	public MouseManager getMouseManager(){
		return mouseManager;
	}


	public static DisplayScreen getDisplay() {
		return display;
	}

}


