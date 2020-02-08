package States;

import java.awt.Graphics;

public interface State {
	void tick();
	void render(Graphics g);
}
