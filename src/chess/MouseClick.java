package chess;

import java.awt.Color;
import java.awt.event.MouseEvent;

import board.ChessBoard;
import board.ImagePanel;
import board.SearchPieceByPos;
import board.Tile;
import board.UpdatePiece;
import gamestate.Check;
import gamestate.Checkmate;
import gamestate.GameController;
import gamestate.Stalemate;
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
  private static Checkmate checkmate = new Checkmate();
  private static Stalemate stalemate = new Stalemate();
  
  /**
   * constructor of mouseclick class, never used
   */
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
    
    //setting moving piece's position
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
      //error check
      System.out.println("movePiece: cannot get type of clicked piece");
      break;
    }
    return;
  }
  
  /**
   * using when first click is in.
   */
  public static void varsClear() {
    //clearing variables
    backgroundBackup = null;
    firstClk = null;
    secondClk = null;
    firstPos = null;
    secondPos = null;
    return;
  }
  
  /**
   * method that setting variables at first click
   * setting first clicked panel, piece, and flags
   * change background tile of clicked piece, and movable tile
   * @param i, j (index of imagepanel[i][j])
   */
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
    clickedPiece = SearchPieceByPos.searchPiece(firstPos, board);
    Position[] possMove = clickedPiece.getCanMoves();
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
  
  /**
   * method that sets the second click's status
   * restore tile color, and reset flags
   * see whether game state (check, checkmate or stalemate)
   * @param i, j (index of imagepanel[i][j])
   */
  public static void secondClickSetup(int i, int j) {
    firstClk.setBackground(backgroundBackup);
    secondClk = btn[i][j];
    secondPos = new Position(i,j);
    isClicked = false;
    
    if(isValidTurnClick()) {
      if((firstClk != null) && (secondClk != null) && (firstClk != secondClk)) {
        System.out.println("secondClickSetup: valid click");
        UpdatePiece.updateDead(secondPos);
        movePiece(secondClk, firstClk);
        nowTurn.nextTurn();
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
       
    skipCheck();
    
    for(int a = 0; a < 4; a++) {
    	System.out.print(GameController.checkFlag[a]);
    }
    System.out.println();
    for(int a = 0; a < 4; a++) {
    	System.out.print(GameController.checkmateFlag[a]);
    }
    System.out.println();
    for(int a = 0; a < 4; a++) {
    	System.out.print(GameController.stalemateFlag[a]);
    }
    System.out.println();
    

    return;
  }
  
  
  /**
   * skipping turn when checkmate or stalemate
   */
  private static void skipCheck() {
    //seeing the game status
    check.isCheck();
    checkmate.isCheckmate();
    stalemate.isStalemate();
    
    if(GameController.checkmateFlag[nowTurn.getter()] == 1 || GameController.stalemateFlag[nowTurn.getter()] == 1) {
      nowTurn.nextTurn();
    }
  }
  
  /**
   * checking if click is valid
   * using at secondClick
   * @return true at valid click, false at not
   */
  private static boolean isValidTurnClick() {
    if( !nowTurn.isValidTurn(nowTurn, firstPos) ) {
      return false;
    }
    else {
      for(int i = 0; i < tileBackup.length; i++) {
        if(tileBackup[i].getX() == secondPos.getX() && tileBackup[i].getY() == secondPos.getY()) {
          return true;
        }
      }
    }
    
    return false;
  }

  
  /**
   * calling firstClick and secondClick by checking isClicked flag
   * @param MouseEvent e
   */
  public static void mouseInput(MouseEvent e) {
    for(int i = 0; i < btn.length; i++) {
      for(int j = 0; j < btn.length; j++) {
        
        if(e.getSource().equals(btn[i][j])) {
          if( !isClicked ) {
            firstClickSetup(i, j);
          }
          else {
            secondClickSetup(i, j);
            ChessGui.printResultScreen();
          }
            
        }
      }
    }
    
    return;
  }


}
