package chess;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author ChangminYi
 * this class connects ChessGui's mouse input to MouseClick
 */
public class MClickBridge implements MouseListener{

  @Override
  public void mouseClicked(MouseEvent e) {
    MouseClick.mouseInput(e);
  }

  
  @Override
  public void mousePressed(MouseEvent e) {
    return;
  }
  @Override
  public void mouseReleased(MouseEvent e) {
    return;
  }
  @Override
  public void mouseEntered(MouseEvent e) {
    return;
  }
  @Override
  public void mouseExited(MouseEvent e) {
    return;
  }

}
