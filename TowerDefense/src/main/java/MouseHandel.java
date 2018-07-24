import java.awt.*;
import java.awt.event.*;

public class MouseHandel implements MouseListener, MouseMotionListener{

    public void mouseMoved(MouseEvent e){
        GameScreen.mouseEvent = new Point(e.getX() - ((Constants.sizeFrame.width - GameScreen.fieldWidth)/2), (e.getY()) - ((Constants.sizeFrame.height - (GameScreen.fieldHeight))-(Constants.sizeFrame.width - GameScreen.fieldWidth)/2));
    }

    public void mouseEntered(MouseEvent e){

    }

    public void mouseExited(MouseEvent e){

    }

    public void mouseClicked(MouseEvent e){
    }

    public void mouseDragged(MouseEvent e){
        GameScreen.mouseEvent = new Point((e.getX()) - ((Constants.sizeFrame.width - GameScreen.fieldWidth)/2), (e.getY()) - ((Constants.sizeFrame.height - (GameScreen.fieldHeight))-(Constants.sizeFrame.width - GameScreen.fieldWidth)/2));
    }

    public void mouseReleased(MouseEvent e){

    }

    public void mousePressed(MouseEvent e){
        GameScreen.store.click(e.getButton());
        GameScreen.room.click(e.getButton());
        GameScreen.information.click(e.getButton());
    }
}
