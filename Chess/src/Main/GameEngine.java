package Main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import Display.DisplayScreen;
import Input.KeyManager;
import Input.MouseManager;
import Input.MusicManager;
import States.GameState;
import States.MenuState;
import States.State;
import Input.Images;

public class GameEngine implements Runnable {
	//Display
	public static DisplayScreen display;
	public String title;
	public int width, height;
	//Running engine
	private boolean running = false;
	private Thread thread;
	public static boolean threadB;
	//Render
	private BufferStrategy bs;
	private Graphics g;
	//Managers
	public KeyManager keyManager;
	public MouseManager mouseManager;
	public MusicManager musicHandler;
	//State
	public State currentState; //State currently at
	public State gameState;
	public State menuState;

	public GameEngine(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		threadB=false;

		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		musicHandler = new MusicManager();

		gameState = new GameState(this);
		menuState = new MenuState(this);
		currentState = menuState;

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
		currentState = menuState;
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
	public void setState(State state) {
		currentState = state;
	}

	private void tick(){
		//checks for key types and manages them
		keyManager.tick();

		if(musicHandler.ended()){
			//musicHandler.restartBackground();
		}

		//game states are the menus
		if(currentState != null)
			currentState.tick();
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

		if(currentState != null)
			currentState.render(g);

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


