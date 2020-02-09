package ChessPieces;

import java.awt.Graphics;

public interface Piece {
	enum side {White, Black};
	void move(int x, int y);
	int xPos();
	int yPos();
	void tick();
	void render(Graphics g);
	void deselect();
}
