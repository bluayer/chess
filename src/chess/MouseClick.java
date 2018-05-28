package chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ChangminYi.ChessBoard;
import ChangminYi.Tile;

public class MouseClick implements ActionListener{
  public static JButton firstBtn, secondBtn;
  public static JButton btn[][];

  private Tile[][] tile;
  private boolean isClicked;
  
  public MouseClick(JButton[][] btns, ChessBoard bd) {
    firstBtn = null;
    secondBtn = null;
    btn = btns;
    isClicked = false;
    tile = bd.cBoard;
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    for(int i = 0; i < 14; i++) {
      for(int j = 0; j < 14; j++) {
        
        if(e.getSource().equals(btn[i][j])) {
          if( !isClicked ) {
            firstBtn = null;
            secondBtn = null;
            firstBtn = btn[i][j];
            isClicked = true;
          }
          else {
            secondBtn = btn[i][j];
            isClicked = false;
          }
          
          if(tile[i][j].isOnPiece()) {
            System.out.println("Piece Clicked");
          }
          else {
            System.out.println("There's no piece");
          }
          
        }
      }
    }    
  }

  
}
