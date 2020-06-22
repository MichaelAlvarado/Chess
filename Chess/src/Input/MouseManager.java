package Input;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener,MouseMotionListener{

    private static boolean leftPressed;
	private static boolean rightPressed;
    private static int mouseX;
	private static int mouseY;

    public MouseManager(){

    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
    
    public static boolean rectPressed(Rectangle rec) {
    	return rec.contains(mouseX, mouseY) && leftPressed;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }


    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            leftPressed=true;
        }
        else if(e.getButton()==MouseEvent.BUTTON3){
            rightPressed=true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            leftPressed=false;
        }
        else if(e.getButton()==MouseEvent.BUTTON3){
            rightPressed=false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
    
}
