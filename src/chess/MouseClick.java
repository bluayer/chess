package chess;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import board.ChessBoard;
import board.SearchPieceByPos;
import board.Tile;
import board.UpdatePiece;
import piece.GamePiece;
import piece.PieceWay;
import piece.Position;
import piece.GamePiece.PieceType;


public class MouseClick implements ActionListener{
  private Color clicked, backgroundBackup;
  private Color[] possMoveBGBackup;
  public static JButton firstBtn, secondBtn;
  public static Position firstPos, secondPos;
  public static JButton btn[][];
  
  private static GamePiece toMovePiece;
  public static ChessBoard board;
  private static Tile[][] cBoard;
  private boolean isClicked;
  private PieceWay pieceWay;
  private Position[] tileBackup;
  private GamePiece clickedPiece;
  
  public MouseClick(JButton[][] btns, ChessBoard bd) {
    this.firstBtn = null;
    this.secondBtn = null;
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
  
  public static GamePiece getMovePiece() {
    return toMovePiece;
  }
  
  
  /**
   * move ImageIcon and Piece in ChessBoard from prev to togo
   * @param togo, prev
   */
  private void movePiece(JButton togo, JButton prev) {
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
      UpdatePiece.updateRook(firstPos, secondPos);
      break;
    case KING:
      UpdatePiece.updateKing(firstPos, secondPos);
      break;
    default:
    }
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
    Position[] possPos = null, temp = null;
    clickedPiece = SearchPieceByPos.searchPiece(firstPos, board);
    
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
      default:
    }
    
    //checking whether return of waysXXPos is valid

    return temp;
  }
 
  
  private Position[] possRemake(Position[] originalPoss) {
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
  
  private void firstClickSetup(int i,int j) {
    varsClear();
    
    //setting first button and position
    firstBtn = btn[i][j];
    firstPos = new Position(i, j);
    backgroundBackup = firstBtn.getBackground();
    firstBtn.setBackground(clicked);
    isClicked = true;
    
    Position[] possMove = possRemake(getPossMove());
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
    }

    //rollback the tile's background
    for(int k = 0; k < tileBackup.length; k++) {
      btn[tileBackup[k].getX()][tileBackup[k].getY()].setBackground(possMoveBGBackup[k]);
      k++;
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
