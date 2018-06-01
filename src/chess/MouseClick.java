package chess;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import board.ChessBoard;
import board.SearchPieceByPos;
import board.Status.TEAM;
import board.Tile;
import board.UpdatePiece;
import gamestate.TurnCheck;
import piece.GamePiece;
import piece.GamePiece.PieceType;
import piece.PieceWay;
import piece.Position;

public class MouseClick{
  private static Color clicked;
  private static Color backgroundBackup;
  private static Color[] possMoveBGBackup;
  public static JLabel firstClk, secondClk;
  public static Position firstPos, secondPos;
  public static JLabel btn[][];
  
  private static GamePiece toMovePiece;
  public static ChessBoard board;
  private static Tile[][] cBoard;
  private static boolean isClicked;
  private static PieceWay pieceWay;
  private static Position[] tileBackup;
  private static GamePiece clickedPiece;
  
  //TurnCheck nowTurn = new TurnCheck();
  
  public MouseClick(JLabel[][] btns, ChessBoard bd) {
    this.firstClk = null;
    this.secondClk = null;
    this.firstPos = null;
    this.secondPos = null;
    this.btn = btns;
    this.isClicked = false;
    this.board = bd;
    cBoard = this.board.getcBoard();
    this.clicked = new Color(255, 255, 0);
    this.backgroundBackup = null;
    this.toMovePiece = null;
    this.clickedPiece = null;
  }
  
  private static GamePiece.Color teamToColor(TEAM t) {
    GamePiece.Color color = null;
    
    switch(t) {
    case BLACK:{
      color = GamePiece.Color.BLACK;
      break;
      }
    case WHITE:{
      color = GamePiece.Color.WHITE;
      break;
      }
    case RED:{
      color = GamePiece.Color.RED;
      break;
      }
    case GREEN:{
      color = GamePiece.Color.GREEN;
      break;
      }
    default :{
      System.out.println("teamToColor:Error");
      }
    }
    
    return color;
  }
  
  public static GamePiece getMovePiece() {
    return toMovePiece;
  }
  
  
  /**
   * move ImageIcon and Piece in ChessBoard from prev to togo
   * @param togo, prev
   */
  private static void movePiece(JLabel togo, JLabel prev) {
    togo.setIcon(prev.getIcon());
    prev.setIcon(null);
     
    cBoard[firstPos.getX()][firstPos.getY()].setOnPiece(false);
    cBoard[secondPos.getX()][secondPos.getY()].setOccupyPiece(cBoard[firstPos.getX()][firstPos.getY()].getOccupyPiece());
    cBoard[firstPos.getX()][firstPos.getY()].setOccupyPiece(PieceType.NOPE);
    cBoard[secondPos.getX()][secondPos.getY()].setOnPiece(true);
    
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
    }
    return;
  }
  
  private static void varsClear() {
    //clearing
    backgroundBackup = null;
    firstClk = null;
    secondClk = null;
    firstPos = null;
    secondPos = null;
    return;
  }
  
  private static Position[] getPossMove() {
    Position[] possPos = null, temp = null;
    GamePiece clickedPiece = SearchPieceByPos.searchPiece(firstPos, board);
    
    switch(clickedPiece.getPieceType()) {
      case PAWN:
        temp = clickedPiece.getCanMoves();
        break;
      case KNIGHT:
        temp = clickedPiece.getCanMoves();
        break;
      case BISHOP:
        temp = clickedPiece.getCanMoves();
        break;
      case ROOK:
        temp = clickedPiece.getCanMoves();
        break;
      case QUEEN:
        temp = clickedPiece.getCanMoves();
        break;
      case KING:
        temp = clickedPiece.getCanMoves();
        break;
    }
    
    //checking whether return of waysXXPos is valid
    return temp;
  }
 
  
  private static Position[] possRemake(Position[] originalPoss) {
    Position[] temp = null;
    int leng = 0;
    
    for(int i = 0; i < originalPoss.length; i++) {
      if(cBoard[originalPoss[i].getX()][originalPoss[i].getY()].getActive()) {
        leng++;
      }
    }
    
    temp = new Position[leng];
    leng = 0;
    for(int i = 0; i < originalPoss.length; i++) {
      if(cBoard[originalPoss[i].getX()][originalPoss[i].getY()].getActive()) {
        temp[leng++] = originalPoss[i];
      }      
    }
    
    return temp;
  }
  
  private static void firstClickSetup(int i,int j) {
    varsClear();
    
    //setting first button and position
    firstClk = btn[i][j];
    firstPos = new Position(i, j);
    backgroundBackup = firstClk.getBackground();
    isClicked = true;
    
    Position[] possMove = possRemake(getPossMove());
    tileBackup = new Position[possMove.length];
    possMoveBGBackup = new Color[possMove.length];
    for(int k = 0; k < possMove.length; k++) {
      tileBackup[k] = new Position(possMove[k].getX(), possMove[k].getY());
      possMoveBGBackup[k] = btn[possMove[k].getX()][possMove[k].getY()].getBackground();
      btn[possMove[k].getX()][possMove[k].getY()].setBackground(new Color(255, 0, 0));
    }
    
    firstClk.setBackground(clicked);
    return;
  }
  
  
  private static void secondClickSetup(int i, int j) {
    firstClk.setBackground(backgroundBackup);
    secondClk = btn[i][j];
    secondPos = new Position(i,j);
    isClicked = false;
    
    if(isValidMove()) {
      if((firstClk != null) && (secondClk != null) && (firstClk != secondClk)) {
        movePiece(secondClk, firstClk);
      }
    }

    //rollback the tile's background
    for(int k = 0; k < tileBackup.length; k++) {
      btn[tileBackup[k].getX()][tileBackup[k].getY()].setBackground(possMoveBGBackup[k]);
    }
    return;
  }
  
  private static boolean isValidMove() {
    /*if(!nowTurn.isValidTurn(nowTurn, firstPos)) {
      System.out.println("Not your turn!");
      return false;
    }*/
      
    for(int i = 0; i < tileBackup.length; i++) {
      if(tileBackup[i].getX()==secondPos.getX() && tileBackup[i].getY()==secondPos.getY()) {
        //nowTurn.nextTurn();
        return true;
      }
    }
    return false;
  }


  public static void mouseInput(MouseEvent e) {
    // TODO Auto-generated method stub
    for(int i = 0; i < 14; i++) {
      for(int j = 0; j < 14; j++) {
        
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
