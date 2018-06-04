package gamestate;

import board.ChessBoard;
import chess.ChessGui;
import chess.MouseClick;
import gamestate.GameController;
import piece.Bishop;
import piece.GamePiece;
import piece.GamePiece.Color;
import piece.GamePiece.PieceType;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Position;
import piece.Queen;
import piece.Rook;

/**
 * 
 * @author Yeoilgoo
 *
 * @since 2018-06-02
 */
public class Checkmate {

  private King myKing;
  private GamePiece target;
  Check check;
  
  Queen[] queen;
  Knight[][] knight;
  Bishop[][] bishop;
  Rook[][] rook;
  Pawn[][] pawn;
  Position[] aw; // availableWay
  
  public Checkmate() { 
    this.queen = ChessGui.b.queen;
    this.knight = ChessGui.b.knight;
    this.bishop = ChessGui.b.bishop;
    this.rook = ChessGui.b.rook;
    this.pawn = ChessGui.b.pawn;
  }
  
  public void isCheckmate() {
    Check check = MouseClick.check;
    Color teamColor1 = null, teamColor2 = null;
    int targetX, targetY;
    int kingX;
    int kingY;
    int directionX, directionY;
    int teamNum; 
    target = check.nowPiece;
    int checkmateFlag = 0;
    
    if(target.getColor() == Color.WHITE || target.getColor() == Color.BLACK) {
      teamColor1 = Color.RED;
      teamColor2 = Color.GREEN;
    }
    
    else {
      teamColor1 = Color.WHITE;
      teamColor2 = Color.BLACK;
    }

    /*
     * Case teamColor1
     */

    
    teamNum = ChessBoard.cvtTeam((GameController.colorToTeam(teamColor1)));
    this.myKing = check.allKing[teamNum];
    kingX = this.myKing.getPosition().getX();
    kingY = this.myKing.getPosition().getY();
    targetX = target.getPosition().getX();
    targetY = target.getPosition().getY();
    
    if(GameController.checkFlag[teamNum] == 0 || this.myKing.getCanMoves().length != 0) {
      GameController.checkmateFlag[teamNum] = 0;
      checkmateFlag = 0;
    }
    
    else {      
      /*
       * if target piece type is pawn or knight, player must delete target.
       */
      
      if(target.getPieceType() == PieceType.PAWN) {
        for(int i = 0; i < pawn[teamNum].length; i++) {
          aw = pawn[teamNum][i].getCanMoves();
        
          for(int j = 0; j < aw.length; j++) {
            if(aw[j].getX() == targetX && aw[j].getY() == targetY) {
              GameController.checkmateFlag[teamNum] = 0;;
              checkmateFlag = 0;
            }
          }
        }
      }
      
      else if(target.getPieceType() == PieceType.KNIGHT) {
        for(int i = 0; i < knight[teamNum].length; i++) {
          aw = knight[teamNum][i].getCanMoves();
          
          for(int j = 0; j < aw.length; j++) {
            if(aw[j].getX() == targetX && aw[j].getY() == targetY) {
              GameController.checkmateFlag[teamNum] = 0;;
              checkmateFlag = 0;
            }
          }
        }
      }
      
      else if(target.getPieceType() == PieceType.QUEEN) {
        directionX = targetX - kingX;
        directionY = targetY - kingY;
        
        if(directionX == 0) {
          for(int i = 0; i < Math.abs(directionY); i++) {
            aw = queen[teamNum].getCanMoves();
            
            for(int j = 0; j < aw.length; j++) {
              if(aw[j].getX() == kingX && aw[j].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                GameController.checkmateFlag[teamNum] = 0;;
                checkmateFlag = 0;
              }
            }
            
            for(int j = 0; j < bishop[teamNum].length; j++) {
              aw = bishop[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < rook[teamNum].length; j++) {
              aw = rook[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < knight[teamNum].length; j++) {
              aw = knight[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < pawn[teamNum].length; j++) {
              aw = pawn[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;;
                  checkmateFlag = 0;
                }
              }
            }
          }
        }
        
        else if(directionY == 0) {
          for(int i = 0; i < Math.abs(directionX); i++) {
            aw = queen[teamNum].getCanMoves();
            
            for(int j = 0; j < aw.length; j++) {
              if(aw[j].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[j].getY() == kingY) {
                GameController.checkmateFlag[teamNum] = 0;;
                checkmateFlag = 0;
              }
            }
            
            for(int j = 0; j < bishop[teamNum].length; j++) {
              aw = bishop[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  GameController.checkmateFlag[teamNum] = 0;;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < rook[teamNum].length; j++) {
              aw = rook[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  GameController.checkmateFlag[teamNum] = 0;;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < knight[teamNum].length; j++) {
              aw = knight[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  GameController.checkmateFlag[teamNum] = 0;;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < pawn[teamNum].length; j++) {
              aw = pawn[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  GameController.checkmateFlag[teamNum] = 0;;
                  checkmateFlag = 0;
                }
              }
            }
          }
        }
        
        else {
          for(int i = 0; i < Math.abs(directionX); i++) {
            aw = queen[teamNum].getCanMoves();
            
            for(int j = 0; j < aw.length; j++) {
              if(aw[j].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[j].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                GameController.checkmateFlag[teamNum] = 0;;
                checkmateFlag = 0;
              }
            }
            
            for(int j = 0; j < bishop[teamNum].length; j++) {
              aw = bishop[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < rook[teamNum].length; j++) {
              aw = rook[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < knight[teamNum].length; j++) {
              aw = knight[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < pawn[teamNum].length; j++) {
              aw = pawn[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
          } 
        }
      }
      
      else if(target.getPieceType() == PieceType.BISHOP) {
        directionX = targetX - kingX;
        directionY = targetY - kingY;
        
        for(int i = 0; i < Math.abs(directionX); i++) {
          for(int j = 0; j < bishop[teamNum].length; j++) {
            aw = bishop[teamNum][j].getCanMoves();
          }
          
          for(int j = 0; j < aw.length; j++) {
            if(aw[j].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[j].getY() == kingY + i * (directionY / Math.abs(directionY))) {
              GameController.checkmateFlag[teamNum] = 0;
              checkmateFlag = 0;
            }
          }
          
          for(int j = 0; j < bishop[teamNum].length; j++) {
            aw = bishop[teamNum][j].getCanMoves();
            
            for(int k = 0; k < aw.length; k++) {
              if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                GameController.checkmateFlag[teamNum] = 0;
                checkmateFlag = 0;
              }
            }
          }
          
          for(int j = 0; j < rook[teamNum].length; j++) {
            aw = rook[teamNum][j].getCanMoves();
            
            for(int k = 0; k < aw.length; k++) {
              if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                GameController.checkmateFlag[teamNum] = 0;
                checkmateFlag = 0;
              }
            }
          }
          
          for(int j = 0; j < knight[teamNum].length; j++) {
            aw = knight[teamNum][j].getCanMoves();
            
            for(int k = 0; k < aw.length; k++) {
              if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                GameController.checkmateFlag[teamNum] = 0;
                checkmateFlag = 0;
              }
            }
          }
          
          for(int j = 0; j < pawn[teamNum].length; j++) {
            aw = pawn[teamNum][j].getCanMoves();
            
            for(int k = 0; k < aw.length; k++) {
              if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                GameController.checkmateFlag[teamNum] = 0;
                checkmateFlag = 0;
              }
            }
          }
        }
      }
      
      else if(target.getPieceType() == PieceType.ROOK) {
        directionX = targetX - kingX;
        directionY = targetY - kingY;
        
        if(directionX == 0) {
          for(int i = 0; i < Math.abs(directionY); i++) {
            for(int j = 0; j < rook[teamNum].length; j++) {
              aw = queen[teamNum].getCanMoves();
            }
            
            for(int j = 0; j < aw.length; j++) {
              if(aw[j].getX() == kingX && aw[j].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                GameController.checkmateFlag[teamNum] = 0;
                checkmateFlag = 0;
              }
            }
            
            for(int j = 0; j < bishop[teamNum].length; j++) {
              aw = bishop[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < rook[teamNum].length; j++) {
              aw = rook[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < knight[teamNum].length; j++) {
              aw = knight[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < pawn[teamNum].length; j++) {
              aw = pawn[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
          }
        }
        
        else{
          for(int i = 0; i < Math.abs(directionX); i++) {
            for(int j = 0; j < rook[teamNum].length; j++) {
              aw = queen[teamNum].getCanMoves();
            }
            
            for(int j = 0; j < aw.length; j++) {
              if(aw[j].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[j].getY() == kingY) {
                GameController.checkmateFlag[teamNum] = 0;
                checkmateFlag = 0;
              }
            }
            
            for(int j = 0; j < bishop[teamNum].length; j++) {
              aw = bishop[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < rook[teamNum].length; j++) {
              aw = rook[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < knight[teamNum].length; j++) {
              aw = knight[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < pawn[teamNum].length; j++) {
              aw = pawn[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
          }
        }
      }
      
      if(checkmateFlag == 1) {
        System.out.println("op1 Checkmate");
        GameController.checkmateFlag[teamNum] = 1;
      }
    }
    
    /*
     * Case teamColor2
     */
    
    checkmateFlag = 1;
    teamNum = ChessBoard.cvtTeam((GameController.colorToTeam(teamColor2)));
    this.myKing = check.allKing[teamNum];
    kingX = this.myKing.getPosition().getX();
    kingY = this.myKing.getPosition().getY();
    targetX = target.getPosition().getX();
    targetY = target.getPosition().getY();
    
    if(GameController.checkmateFlag[teamNum] == 0 || this.myKing.getCanMoves().length != 0) {
      GameController.checkmateFlag[teamNum] = 0;
      checkmateFlag = 0;
    }
    
    else {      
      /*
       * if target piece type is pawn or knight, player must delete target.
       */
      
      if(target.getPieceType() == PieceType.PAWN) {
        for(int i = 0; i < pawn[teamNum].length; i++) {
          aw = pawn[teamNum][i].getCanMoves();
        
          for(int j = 0; j < aw.length; j++) {
            if(aw[j].getX() == targetX && aw[j].getY() == targetY) {
              GameController.checkmateFlag[teamNum] = 0;
              checkmateFlag = 0;
            }
          }
        }
      }
      
      else if(target.getPieceType() == PieceType.KNIGHT) {
        for(int i = 0; i < knight[teamNum].length; i++) {
          aw = knight[teamNum][i].getCanMoves();
          
          for(int j = 0; j < aw.length; j++) {
            if(aw[j].getX() == targetX && aw[j].getY() == targetY) {
              GameController.checkmateFlag[teamNum] = 0;
              checkmateFlag = 0;
            }
          }
        }
      }
      
      else if(target.getPieceType() == PieceType.QUEEN) {
        directionX = targetX - kingX;
        directionY = targetY - kingY;
        
        if(directionX == 0) {
          for(int i = 0; i < Math.abs(directionY); i++) {
            aw = queen[teamNum].getCanMoves();
            
            for(int j = 0; j < aw.length; j++) {
              if(aw[j].getX() == kingX && aw[j].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                GameController.checkmateFlag[teamNum] = 0;
                checkmateFlag = 0;
              }
            }
            
            for(int j = 0; j < bishop[teamNum].length; j++) {
              aw = bishop[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < rook[teamNum].length; j++) {
              aw = rook[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < knight[teamNum].length; j++) {
              aw = knight[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < pawn[teamNum].length; j++) {
              aw = pawn[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
          }
        }
        
        else if(directionY == 0) {
          for(int i = 0; i < Math.abs(directionX); i++) {
            aw = queen[teamNum].getCanMoves();
            
            for(int j = 0; j < aw.length; j++) {
              if(aw[j].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[j].getY() == kingY) {
                GameController.checkmateFlag[teamNum] = 0;
                checkmateFlag = 0;
              }
            }
            
            for(int j = 0; j < bishop[teamNum].length; j++) {
              aw = bishop[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < rook[teamNum].length; j++) {
              aw = rook[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < knight[teamNum].length; j++) {
              aw = knight[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < pawn[teamNum].length; j++) {
              aw = pawn[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
          }
        }
        
        else {
          for(int i = 0; i < Math.abs(directionX); i++) {
            aw = queen[teamNum].getCanMoves();
            
            for(int j = 0; j < aw.length; j++) {
              if(aw[j].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[j].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                GameController.checkmateFlag[teamNum] = 0;
                checkmateFlag = 0;
              }
            }
            
            for(int j = 0; j < bishop[teamNum].length; j++) {
              aw = bishop[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < rook[teamNum].length; j++) {
              aw = rook[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < knight[teamNum].length; j++) {
              aw = knight[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < pawn[teamNum].length; j++) {
              aw = pawn[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
          } 
        }
      }
      
      else if(target.getPieceType() == PieceType.BISHOP) {
        directionX = targetX - kingX;
        directionY = targetY - kingY;
        
        for(int i = 0; i < Math.abs(directionX); i++) {
          for(int j = 0; j < bishop[teamNum].length; j++) {
            aw = bishop[teamNum][j].getCanMoves();
          }
          
          for(int j = 0; j < aw.length; j++) {
            if(aw[j].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[j].getY() == kingY + i * (directionY / Math.abs(directionY))) {
              GameController.checkmateFlag[teamNum] = 0;
              checkmateFlag = 0;
            }
          }
          
          for(int j = 0; j < bishop[teamNum].length; j++) {
            aw = bishop[teamNum][j].getCanMoves();
            
            for(int k = 0; k < aw.length; k++) {
              if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                GameController.checkmateFlag[teamNum] = 0;
                checkmateFlag = 0;
              }
            }
          }
          
          for(int j = 0; j < rook[teamNum].length; j++) {
            aw = rook[teamNum][j].getCanMoves();
            
            for(int k = 0; k < aw.length; k++) {
              if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                GameController.checkmateFlag[teamNum] = 0;
                checkmateFlag = 0;
              }
            }
          }
          
          for(int j = 0; j < knight[teamNum].length; j++) {
            aw = knight[teamNum][j].getCanMoves();
            
            for(int k = 0; k < aw.length; k++) {
              if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                GameController.checkmateFlag[teamNum] = 0;
                checkmateFlag = 0;
              }
            }
          }
          
          for(int j = 0; j < pawn[teamNum].length; j++) {
            aw = pawn[teamNum][j].getCanMoves();
            
            for(int k = 0; k < aw.length; k++) {
              if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                GameController.checkmateFlag[teamNum] = 0;
                checkmateFlag = 0;
              }
            }
          }
        }
      }
      
      else if(target.getPieceType() == PieceType.ROOK) {
        directionX = targetX - kingX;
        directionY = targetY - kingY;
        
        if(directionX == 0) {
          for(int i = 0; i < Math.abs(directionY); i++) {
            for(int j = 0; j < rook[teamNum].length; j++) {
              aw = queen[teamNum].getCanMoves();
            }
            
            for(int j = 0; j < aw.length; j++) {
              if(aw[j].getX() == kingX && aw[j].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                GameController.checkmateFlag[teamNum] = 0;
                checkmateFlag = 0;
              }
            }
            
            for(int j = 0; j < bishop[teamNum].length; j++) {
              aw = bishop[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < rook[teamNum].length; j++) {
              aw = rook[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < knight[teamNum].length; j++) {
              aw = knight[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < pawn[teamNum].length; j++) {
              aw = pawn[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
          }
        }
        
        else{
          for(int i = 0; i < Math.abs(directionX); i++) {
            for(int j = 0; j < rook[teamNum].length; j++) {
              aw = queen[teamNum].getCanMoves();
            }
            
            for(int j = 0; j < aw.length; j++) {
              if(aw[j].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[j].getY() == kingY) {
                GameController.checkmateFlag[teamNum] = 0;
                checkmateFlag = 0;
              }
            }
            
            for(int j = 0; j < bishop[teamNum].length; j++) {
              aw = bishop[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < rook[teamNum].length; j++) {
              aw = rook[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < knight[teamNum].length; j++) {
              aw = knight[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
            
            for(int j = 0; j < pawn[teamNum].length; j++) {
              aw = pawn[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  GameController.checkmateFlag[teamNum] = 0;
                  checkmateFlag = 0;
                }
              }
            }
          }
        }
      }
      
      if(checkmateFlag == 1) {
        System.out.println("op2 Checkmate");
        GameController.checkmateFlag[teamNum] = 1;
      }
    }
  }
  
  public boolean returnCheckmate() {
    int sum = 0;
    
    for(int i = 0; i < 4; i++) {
      if(GameController.checkmateFlag[i] == 1) {
        System.out.println(i + "is on Checkmate");
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
