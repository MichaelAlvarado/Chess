package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import Input.Images;
import Main.GameEngine;

public class MenuState implements State{
	
	private GameEngine game;
	private Rectangle start;	//start button to play
	private Rectangle twovstwo;
	private Rectangle setting;
	Color background = new Color(255,200,150); //Color use for the background
	
	public MenuState(GameEngine game) {
		this.game = game;
		twovstwo = new Rectangle((game.width/2)-150,game.height/2,300,100);
	}
	
	@Override
	public void tick() {
		if(game.mouseManager.isLeftPressed() && twovstwo.contains(game.mouseManager.getMouseX(), game.mouseManager.getMouseY()))
			game.setState(game.gameState);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(background);
		g.fillRect(0,0,game.width,game.height);
		//g.drawImage(Images.start, start.x, start.y, start.width, start.height, null);
		g.setColor(Color.BLACK);
		g.drawRect(twovstwo.x, twovstwo.y, twovstwo.width, twovstwo.height);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
		g.drawString("2 Players" , twovstwo.x+40, twovstwo.y+50);
		g.drawImage(Images.backgrounds[0], 0,0,game.width,game.height/2,null);	
	}

}
