package States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Input.Images;
import Main.GameEngine;

public class MenuState implements State{
	
	private GameEngine game;
	private Rectangle start;	//start button to play
	Color background = new Color(255,200,150); //Color use for the background
	
	public MenuState(GameEngine game) {
		this.game = game;
		start = new Rectangle((game.width/2)-200,game.height/2,400,400);
	}
	
	@Override
	public void tick() {
		if(game.mouseManager.isLeftPressed() && start.contains(game.mouseManager.getMouseX(), game.mouseManager.getMouseY()))
			game.setState(game.gameState);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(background);
		g.fillRect(0,0,game.width,game.height);
		g.drawImage(Images.start, start.x, start.y, start.width, start.height, null);
		g.drawImage(Images.backgrounds[0], 0,0,game.width,game.height/2,null);	
	}

}
