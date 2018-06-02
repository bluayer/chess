package chess;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
