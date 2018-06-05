package chess;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;

import board.ChessBoard;
import board.ImagePanel;
import board.SearchPieceByPos;
import board.Status.TEAM;
import board.Tile;
import board.UpdatePiece;
import gamestate.Check;
import gamestate.Checkmate;
import gamestate.GameController;
import gamestate.TurnCheck;
import piece.GamePiece;
import piece.GamePiece.PieceType;
import piece.Position;

/**
 * @author ChangminYi
 * class about overall reaction to mouse click
 */
public class MouseClick{
  private static Color clicked = new Color(0x41bdb5);
  private static Color backgroundBackup;
  private static Color[] possMoveBGBackup;
  public static ImagePanel firstClk, secondClk;
  public static Position firstPos, secondPos;
  public static ImagePanel[][] btn = ChessGui.btn;
  
  private static GamePiece toMovePiece;
  public static ChessBoard board = ChessGui.b;
  private static Tile[][] cBoard = ChessGui.b.getcBoard();
  static boolean isClicked;
  private static Position[] tileBackup;
  public static GamePiece clickedPiece;
  private static TurnCheck nowTurn = new TurnCheck();
  private static Check check = new Check();
  private static Checkmate checkMate = new Checkmate();
  
  public MouseClick() {
    firstClk = null;
    secondClk = null;
    firstPos = null;
    secondPos = null;
    btn = ChessGui.btn;
    isClicked = false;
    board = ChessGui.b;
    cBoard = ChessGui.b.getcBoard();
    clicked = new Color(255, 255, 0);
    backgroundBackup = null;
    toMovePiece = null;
    clickedPiece = null;
  }
  
  public static GamePiece getMovePiece() {
    return toMovePiece;
  }
  
  
  /**
   * move ImageIcon and Piece in ChessBoard from prev to togo
   * @param togo, prev
   */
  private static void movePiece(ImagePanel togo, ImagePanel prev) {
    togo.setImage(prev.getImage());
    prev.setImage(null);
     
    cBoard[firstPos.getX()][firstPos.getY()].setOnPiece(false);
    cBoard[secondPos.getX()][secondPos.getY()].setOccupyPiece(cBoard[firstPos.getX()][firstPos.getY()].getOccupyPiece());
    cBoard[firstPos.getX()][firstPos.getY()].setOccupyPiece(PieceType.NOPE);
    cBoard[secondPos.getX()][secondPos.getY()].setOnPiece(true);
    
    //setting attacked piece dead

    
    switch(clickedPiece.getPieceType()) {
    case PAWN:
      UpdatePiece.updatePawn(firstPos, secondPos);
      break;
    case KNIGHT:
      UpdatePiece.updateKnight(firstPos, secondPos);
      break;
    case BISHOP:
      UpdatePiece.updateBishop(firstPos, secondPos);
      break;
    case ROOK:
      UpdatePiece.updateRook(firstPos, secondPos);
      break;
    case QUEEN:
      UpdatePiece.updateQueen(firstPos, secondPos);
      break;
    case KING:
      UpdatePiece.updateKing(firstPos, secondPos);
      break;
    default:
      System.out.println("movePiece: cannot get type of clicked piece");
      break;
    }
    return;
  }
  
  public static void varsClear() {
    //clearing variables
    backgroundBackup = null;
    firstClk = null;
    secondClk = null;
    firstPos = null;
    secondPos = null;
    return;
  }
  
  private static Position[] getPossMove() {
    //set clickPiece for whether is check
    clickedPiece = SearchPieceByPos.searchPiece(firstPos, board);
    
    //return possible moves
    return clickedPiece.getCanMoves();
  }
 
  
  public static void firstClickSetup(int i,int j) {
    varsClear();
    if(btn[i][j].getImage() == null) {
      return;
    }
    
    //setting first button and position
    firstClk = btn[i][j];
    firstPos = new Position(i, j);  
    
    //backup and change background of clicked tile
    backgroundBackup = firstClk.getBackground();
    firstClk.setBackground(clicked);
    firstClk.repaint();
    isClicked = true;
    
    //get movable area and backup the areas' tile status
    Position[] possMove = getPossMove();
    tileBackup = new Position[possMove.length];
    possMoveBGBackup = new Color[possMove.length];
    for(int k = 0; k < possMove.length; k++) {
      tileBackup[k] = new Position(possMove[k].getX(), possMove[k].getY());
      possMoveBGBackup[k] = btn[possMove[k].getX()][possMove[k].getY()].getBackground();
      btn[possMove[k].getX()][possMove[k].getY()].setBackground(new Color(0xf6c467));
      btn[possMove[k].getX()][possMove[k].getY()].repaint();
    }
    
    return;
  }
  
  
  public static void secondClickSetup(int i, int j) {
    firstClk.setBackground(backgroundBackup);
    secondClk = btn[i][j];
    secondPos = new Position(i,j);
    isClicked = false;
    
    if(isValidTurnClick()) {
      if((firstClk != null) && (secondClk != null) && (firstClk != secondClk)) {
        UpdatePiece.updateDead(secondPos);
        movePiece(secondClk, firstClk);
      }
    }

    //rollback the tile's background
    for(int k = 0; k < tileBackup.length; k++) {
      btn[tileBackup[k].getX()][tileBackup[k].getY()].setBackground(possMoveBGBackup[k]);
      btn[tileBackup[k].getX()][tileBackup[k].getY()].repaint();
    }
    
    //Cleaning ChessGui's chess state message
    for(int k = 0; k < ChessGui.checkLabel.length; k++) {
      ChessGui.checkLabel[k].setText(null);
    }
    //...and see it's check or checkmate
    
    check.isCheck();
    checkMate.isCheckmate();
    for(int a = 0; a < 4; a++) {
    	System.out.print(GameController.checkFlag[a]);
    }
    for(int a = 0; a < 4; a++) {
    	System.out.print(GameController.checkmateFlag[a]);
    }
    System.out.println();
    

    return;
  }
  
  private static boolean isValidTurnClick() {
    
    if( !nowTurn.isValidTurn(nowTurn, firstPos) ) {
      return false;
    }
 
    for(int i = 0; i < tileBackup.length; i++) {
      if(tileBackup[i].getX() == secondPos.getX() && tileBackup[i].getY() == secondPos.getY()) {
        nowTurn.nextTurn();
        return true;
      }
    }
    return false;
  }


  public static void mouseInput(MouseEvent e) {
    for(int i = 0; i < btn.length; i++) {
      for(int j = 0; j < btn.length; j++) {
        
        if(e.getSource().equals(btn[i][j])) {
          if( !isClicked ) {
            firstClickSetup(i, j);
          }
          else {
            secondClickSetup(i, j);
            ChessGui.printChessBoard();
          }
            
        }
      }
    }
    
    return;
  }


}
