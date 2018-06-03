package gamestate; 

import board.ChessBoard;
import board.Status.TEAM;
import chess.ChessGui;
import chess.MouseClick;
import gamestate.GameController;
import piece.GamePiece.Color; 
import piece.GamePiece;
import piece.King;
import piece.Position;


/** 
*  
* @author SongJeongWoo 
* 
*/ 

public class Check { 

  private GamePiece nowPiece;
  
  private King[] allKing;
  private Position[] aw; //available way
  
  public Check(){
    this.nowPiece = MouseClick.clickedPiece;
    this.allKing = ChessGui.b.king;
  } 
  
  public boolean isCheck() { 
    Color color = nowPiece.getColor(); 
    Color oppositeColor1 = null, oppositeColor2 = null; 
    TEAM oppositeTeam1, oppositeTeam2;
    int op1 = 0, op2 = 0;

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
    
    for(int i = 0; i < aw.length; i++) {
      if(aw[i] == allKing[op1].getPosition()) {
        GameController.setCheckFlag(oppositeTeam1);
      }
      
      if(aw[i] == allKing[op2].getPosition()) {
        GameController.setCheckFlag(oppositeTeam2);
      }
    }
    
    for(int i = 0; i < 4; i++) {
      if(GameController.checkFlag[i] == 1) {
        System.out.println("Player" + (i + 1) + " is on Check");
        return true;
      }
    }
    
    return false;
  }
 } 