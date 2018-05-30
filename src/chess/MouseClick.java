package chess;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import board.ChessBoard;
import board.SearchPieceByPos;
import board.Tile;
import piece.GamePiece;
import piece.Position;


public class MouseClick implements ActionListener{
  private Color clicked, backgroundBackup;
  public static JButton firstBtn, secondBtn;
  public static Position firstPos, secondPos;
  public static JButton btn[][];
  
  private static GamePiece toMovePiece;
  private static ChessBoard board;
  private static Tile[][] tile;
  private boolean isClicked;
  
  public MouseClick(JButton[][] btns, ChessBoard bd) {
    this.firstBtn = null;
    this.secondBtn = null;
    this.firstPos = null;
    this.secondPos = null;
    this.btn = btns;
    this.isClicked = false;
    this.board = bd;
    tile = this.board.cBoard;
    this.clicked = new Color(255, 255, 0);
    this.backgroundBackup = null;
    this.toMovePiece = null;
  }
  
  public static GamePiece getMovePiece() {
    return toMovePiece;
  }
  
  private static void movePieceAtTile(Position togo, Position prev) {
    toMovePiece = SearchPieceByPos.searchPiece(prev, board);
    //System.out.println(toMovePiece);
    ChessBoard.updateTile(prev, togo);
  }
  
  /**
   * move ImageIcon from prev to togo
   * @param togo, prev
   */
  private void movePiece(JButton togo, JButton prev) {
    togo.setIcon(prev.getIcon());
    prev.setIcon(null);
    movePieceAtTile(secondPos, firstPos);
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    for(int i = 0; i < 14; i++) {
      for(int j = 0; j < 14; j++) {
        
        if(e.getSource().equals(btn[i][j])) {
          if( !isClicked ) {
            //clearing
            backgroundBackup = null;
            firstBtn = null;
            secondBtn = null;
            firstPos = null;
            secondPos = null;
            
            //setting first button and position
            firstBtn = btn[i][j];
            firstPos = new Position(i, j);
            backgroundBackup = firstBtn.getBackground();
            firstBtn.setBackground(clicked);
            isClicked = true;
            
            System.out.println(firstPos.getX() + ", " + firstPos.getY());
          }
          else {
            firstBtn.setBackground(backgroundBackup);
            secondBtn = btn[i][j];
            secondPos = new Position(i,j);
            isClicked = false;
            
            if((firstBtn != null) && (secondBtn != null) && (firstBtn != secondBtn)) {
              movePiece(secondBtn, firstBtn);
            }
            
            System.out.println(secondPos.getX() + ", " + secondPos.getY());
          }
          
          
        }
      }
    }    
  }

  
}
