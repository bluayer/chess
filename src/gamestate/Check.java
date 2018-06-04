package gamestate; 

import board.ChessBoard;
import board.Status.TEAM;
import chess.ChessGui;
import chess.MouseClick;
import gamestate.GameController;
import piece.GamePiece.Color;
import piece.GamePiece.PieceType;
import piece.GamePiece;
import piece.King;
import piece.Position;

/** 
*  
* @author SongJeongWoo 
* 
*/ 

public class Check { 

  protected GamePiece nowPiece;
  
  King[] allKing;
  private Position[] aw; //available way
  
  public Check(){
    this.nowPiece = MouseClick.clickedPiece;
    this.allKing = ChessGui.b.king;
  } 
  
  public void isCheck() { 
    this.nowPiece = MouseClick.clickedPiece;
    Color color = nowPiece.getColor();
    Color oppositeColor1 = null, oppositeColor2 = null; 
    TEAM oppositeTeam1, oppositeTeam2;
    int op1 = 0, op2 = 0;
    int checkFlag = 0;

    switch (color) { 
    case BLACK: 
      oppositeColor1 = Color.RED; 
      oppositeColor2 = Color.GREEN; 
      break; 
    case WHITE: 
      oppositeColor1 = Color.RED; 
      oppositeColor2 = Color.GREEN; 
      break; 
    case RED: 
      oppositeColor1 = Color.BLACK; 
      oppositeColor2 = Color.WHITE; 
      break; 
    case GREEN: 
      oppositeColor1 = Color.RED; 
      oppositeColor2 = Color.WHITE; 
      break; 
    default: 
    } 
    
    oppositeTeam1 = GameController.colorToTeam(oppositeColor1);
    oppositeTeam2 = GameController.colorToTeam(oppositeColor2);
    op1 = ChessBoard.cvtTeam(oppositeTeam1);
    op2 = ChessBoard.cvtTeam(oppositeTeam2);
    aw = this.nowPiece.getCanMoves();
    
    if(this.nowPiece.getPieceType() == PieceType.KING) {
      System.out.println("Check Error: clicked Piecetype is King");
      return;
    }
    
    for(int i = 0; i < aw.length; i++) {
      if(aw[i].getX() == allKing[op1].getPosition().getX() && aw[i].getY() == allKing[op1].getPosition().getY()) {
        System.out.println("Player" + ((op1 + 1) % 4) + " is on Check");
        checkFlag = 1;
        GameController.checkFlag[op1] = 1;
      }
      
      if(aw[i].getX() == allKing[op2].getPosition().getX() && aw[i].getY() == allKing[op2].getPosition().getY()) {
        System.out.println("Player" + ((op2 + 1) % 4)+ " is on Check");
        checkFlag = 1;
        GameController.checkFlag[op2] = 1;
      }
    }
    
    if(checkFlag == 1) {
      return;
    }
    
    else {
      GameController.checkFlag[op1] = 0;
      GameController.checkFlag[op2] = 0;
    }
  }
  
  public boolean returnCheck() {
    int sum = 0;
    
   for(int i = 0; i < 4; i++) {
      if(GameController.checkFlag[i] == 1) {
        System.out.println(i + "is on Check");
      }
    }
   for(int i = 0; i < 4; i++) {
     sum += GameController.checkmateFlag[i];
   }
   
   if(sum == 0) {
     return false;
   }
   
   else {
     return true;
   }
  } 
}