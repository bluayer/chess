package chess;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import board.ChessBoard;
import board.SearchPieceByPos;
import board.Tile;
import piece.GamePiece;
import piece.PieceWay;
import piece.Position;


public class MouseClick implements ActionListener{
  private Color clicked, backgroundBackup;
  private Color[] possMoveBGBackup;
  public static JButton firstBtn, secondBtn;
  public static Position firstPos, secondPos;
  public static JButton btn[][];
  
  private static GamePiece toMovePiece;
  private static ChessBoard board;
  private static Tile[][] tile;
  private boolean isClicked;
  private PieceWay pieceWay;
  private Position[] tileBackup;
  
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
    //toMovePiece = SearchPieceByPos.searchPiece(prev, board);
    //System.out.println(toMovePiece);
    board.updateTile(prev, togo);
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
  
  private void varsClear() {
    //clearing
    backgroundBackup = null;
    firstBtn = null;
    secondBtn = null;
    firstPos = null;
    secondPos = null;
  }
  
  private Position[] getPossMove() {
    pieceWay = new PieceWay(new Position(firstPos.getX(), firstPos.getY()));
    Position[] possPos = null, temp = null;
    GamePiece clickedPiece = SearchPieceByPos.searchPiece(firstPos, board);
    
    switch(clickedPiece.getPieceType()) {
      case PAWN:
        temp = pieceWay.waysPawnPos(clickedPiece.getColor());
        break;
      case KNIGHT:
        temp = pieceWay.waysKnightPos(clickedPiece.getColor());
        break;
      case BISHOP:
        temp = pieceWay.waysBishopPos(clickedPiece.getColor());
        break;
      case ROOK:
        temp = pieceWay.waysRookPos(clickedPiece.getColor());
        break;
      case QUEEN:
        temp = pieceWay.waysQueenPos(clickedPiece.getColor());
        break;
      case KING:
        temp = pieceWay.waysKingPos(clickedPiece.getColor());
        break;
      default:
    }
    
    //checking whether return of waysXXPos is valid
    int validSize = 0;
    while(tile[temp[validSize].getX()][temp[validSize].getY()].getActive()) {
      validSize++;
    }
    possPos = new Position[validSize];
    for(int i = 0; i < validSize; i++) {
      possPos[i] = temp[i];
    }
    
    return possPos;
  }
 
  
  private void firstClickSetup(int i,int j) {
    varsClear();
    
    //setting first button and position
    firstBtn = btn[i][j];
    firstPos = new Position(i, j);
    backgroundBackup = firstBtn.getBackground();
    firstBtn.setBackground(clicked);
    isClicked = true;
    
    Position[] possMove = getPossMove();
    tileBackup = new Position[possMove.length];
    possMoveBGBackup = new Color[possMove.length];
    for(int k = 0; k < possMove.length; k++) {
      tileBackup[k] = new Position(possMove[k].getX(), possMove[k].getY());
      possMoveBGBackup[k] = btn[possMove[k].getX()][possMove[k].getY()].getBackground();
      btn[possMove[k].getX()][possMove[k].getY()].setBackground(new Color(255, 0, 0));
    }
    
    System.out.println("First Click: " + firstPos.getX() + ", " + firstPos.getY());
  }
  
  
  private void secondClickSetup(int i, int j) {
    firstBtn.setBackground(backgroundBackup);
    secondBtn = btn[i][j];
    secondPos = new Position(i,j);
    isClicked = false;
    
    if((firstBtn != null) && (secondBtn != null) && (firstBtn != secondBtn)) {
      movePiece(secondBtn, firstBtn);
      for(int k = 0; k < tileBackup.length; k++) {
        btn[tileBackup[k].getX()][tileBackup[k].getY()].setBackground(possMoveBGBackup[k]);
        k++;
      }
    }
    
    System.out.println("Second Click: " + secondPos.getX() + ", " + secondPos.getY());
  }
  
  
  @Override
  public void actionPerformed(ActionEvent e) {
    for(int i = 0; i < 14; i++) {
      for(int j = 0; j < 14; j++) {
        
        if(e.getSource().equals(btn[i][j])) {
          if( !isClicked ) {
            firstClickSetup(i, j);
          }
          else {
            secondClickSetup(i, j);
          }
        }
        
        
      }
    }
    
    return;
  }

  
}
