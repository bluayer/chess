package gamestate;

import chess.ChessGui;
import chess.MouseClick;
import piece.Bishop;
import piece.GamePiece;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.GamePiece.PieceType;
import piece.Position;
import piece.Queen;
import piece.Rook;

/**
 * Class to decide which player is on stalemate.
 * @see Check
 * @see GamePiece
 * @author Yeoilgoo
 * @since 2018-06-06
 */

public class Stalemate {
  King myKing;
    
  King[] king;
  Queen[] queen;
  Knight[][] knight;
  Bishop[][] bishop;
  Rook[][] rook;
  Pawn[][] pawn;
  Position[] aw; // availableWay
  
  /*
   * get information of all pieces on board.
   */
  public Stalemate() {
    this.king = ChessGui.b.king;
    this.queen = ChessGui.b.queen;
    this.knight = ChessGui.b.knight;
    this.bishop = ChessGui.b.bishop;
    this.rook = ChessGui.b.rook;
    this.pawn = ChessGui.b.pawn;
  }
  
  public void isStalemate() {
    for(int t = 0; t < 4; t++) { //check whole players.
      
      /*
       * if player is on check state, it isn't on stalemate.
       */
      if(GameController.checkFlag[t] == 1) {
        GameController.stalemateFlag[t] = 0;
      }
       
      /*
       * if whole pieces of player don't have possible way, it is stalemate state.
       */
      else {
        GameController.stalemateFlag[t] = 1;
        Position nowPos = null;
        
        aw = king[t].getCanMoves();
        if(aw.length != 0) {
          GameController.stalemateFlag[t] = 0;
        }
        
        if(queen[t].isAlive()) {
          aw = queen[t].getCanMoves();
          nowPos = queen[t].getPosition();
          
          if(this.aw.length != 0) {				  
            nowPos = queen[t].getPosition();
            for(int i = 0; i < this.aw.length; i++) {
              boolean onPieceTemp = ChessGui.b.getcBoard()[this.aw[i].getX()][this.aw[i].getY()].isOnPiece();
              PieceType temp = ChessGui.b.getcBoard()[this.aw[i].getX()][this.aw[i].getY()].getOccupyPiece();
              queen[t].move(this.aw[i]);
              ChessGui.b.getcBoard()[this.aw[i].getX()][this.aw[i].getY()].setOccupyPiece(ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].getOccupyPiece());
              ChessGui.b.getcBoard()[this.aw[i].getX()][this.aw[i].getY()].setOnPiece(true);
              ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].setOnPiece(false);
              ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].setOccupyPiece(PieceType.NOPE);
              MouseClick.check.isCheck();
              ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].setOccupyPiece(ChessGui.b.getcBoard()[this.aw[i].getX()][this.aw[i].getY()].getOccupyPiece());
              ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].setOnPiece(true);
              ChessGui.b.getcBoard()[this.aw[i].getX()][this.aw[i].getY()].setOnPiece(onPieceTemp);
              ChessGui.b.getcBoard()[this.aw[i].getX()][this.aw[i].getY()].setOccupyPiece(temp);
              queen[t].move(nowPos);
              
              if(GameController.checkFlag[t] == 1) {
                GameController.stalemateFlag[t] = 0;
                break;
              }
              else {
                continue;
              }
            }
          }
          
          GameController.stalemateFlag[t] = 0;
          break;
        }
        			
        for(int i = 0; i < rook[t].length; i++) {
          if(!rook[t][i].isAlive()) {
            continue;
          }
          	
          aw = rook[t][i].getCanMoves();
          if(this.aw.length != 0) {
            nowPos = rook[t][i].getPosition();
            for(int k = 0; k < this.aw.length; k++) {
              boolean onPieceTemp = ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].isOnPiece();
              PieceType temp = ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].getOccupyPiece();
              rook[t][i].move(this.aw[k]);
              ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].setOccupyPiece(ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].getOccupyPiece());
              ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].setOnPiece(true);
              ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].setOnPiece(false);
              ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].setOccupyPiece(PieceType.NOPE);
              MouseClick.check.isCheck();
              ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].setOccupyPiece(ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].getOccupyPiece());
              ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].setOnPiece(true);
              ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].setOnPiece(onPieceTemp);
              ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].setOccupyPiece(temp);
              rook[t][i].move(nowPos);
                
              if(GameController.checkFlag[t] == 1) {
                GameController.stalemateFlag[t] = 0;
                break;
              }
              else {
                continue;
              }
            }
            
            GameController.stalemateFlag[t] = 0;
          }
        } 
        				
        for(int r = 0; r < bishop[t].length; r++) {
          if(!bishop[t][r].isAlive()) {
            continue;
          }
          
          aw = bishop[t][r].getCanMoves();
          if(aw.length != 0) {
            nowPos = bishop[t][r].getPosition();
            for(int k = 0; k < this.aw.length; k++) {
              boolean onPieceTemp = ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].isOnPiece();
              PieceType temp = ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].getOccupyPiece();
              bishop[t][r].move(this.aw[k]);
              ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].setOccupyPiece(ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].getOccupyPiece());
              ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].setOnPiece(true);
              ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].setOnPiece(false);
              ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].setOccupyPiece(PieceType.NOPE);
              MouseClick.check.isCheck();
              ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].setOccupyPiece(ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].getOccupyPiece());
              ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].setOnPiece(true);
              ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].setOnPiece(onPieceTemp);
              ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].setOccupyPiece(temp);
              bishop[t][r].move(nowPos);
                
              if(GameController.checkFlag[t] == 1) {
                GameController.stalemateFlag[t] = 0;
                break;
              }
              else {
                continue;
              }
            }
            GameController.stalemateFlag[t] = 0;
          }
        }
        
        for(int r = 0; r < knight[t].length; r++) {
          if(!knight[t][r].isAlive()) {
            continue;
          }
          
          aw = knight[t][r].getCanMoves();
          if(aw.length != 0) {
            nowPos = knight[t][r].getPosition();
            for(int k = 0; k < this.aw.length; k++) {
              boolean onPieceTemp = ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].isOnPiece();
              PieceType temp = ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].getOccupyPiece();
              knight[t][r].move(this.aw[k]);
              ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].setOccupyPiece(ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].getOccupyPiece());
              ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].setOnPiece(true);
              ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].setOnPiece(false);
              ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].setOccupyPiece(PieceType.NOPE);
              MouseClick.check.isCheck();
              ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].setOccupyPiece(ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].getOccupyPiece());
              ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].setOnPiece(true);
              ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].setOnPiece(onPieceTemp);
              ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].setOccupyPiece(temp);
              knight[t][r].move(nowPos);
              
              if(GameController.checkFlag[t] == 1) {
                GameController.stalemateFlag[t] = 0;
                break;
              }
              else {
                continue;
              }
            }
            GameController.stalemateFlag[t] = 0;
          }
        }
        
        for (int r = 0; r < pawn[t].length; r++) {
          if(!pawn[t][r].isAlive()) {
            continue;
          }
          
          aw = pawn[t][r].getCanMoves();
          if(aw.length != 0) { 
            nowPos = pawn[t][r].getPosition();
            for(int k = 0; k < this.aw.length; k++) {
              boolean onPieceTemp = ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].isOnPiece();
              PieceType temp = ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].getOccupyPiece();
              pawn[t][r].move(this.aw[k]);
              ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].setOccupyPiece(ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].getOccupyPiece());
              ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].setOnPiece(true);
              ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].setOnPiece(false);
              ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].setOccupyPiece(PieceType.NOPE);
              MouseClick.check.isCheck();
              ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].setOccupyPiece(ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].getOccupyPiece());
              ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()].setOnPiece(true);
              ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].setOnPiece(onPieceTemp);
              ChessGui.b.getcBoard()[this.aw[k].getX()][this.aw[k].getY()].setOccupyPiece(temp);
              pawn[t][r].move(nowPos);
              
              if(GameController.checkFlag[t] == 1) {
                GameController.stalemateFlag[t] = 0;
                break;
              }
              else {
                continue;
              }
            }
            GameController.stalemateFlag[t] = 0;
          }
        }
      }
    }		
  }	
}