package gamestate;

import board.ChessBoard;
import board.SearchPieceByPos;
import board.Tile;
import chess.ChessGui;
import gamestate.GameController;
import piece.Bishop;
import piece.GamePiece;
import piece.GamePiece.Color;
import piece.GamePiece.PieceType;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.PieceWay;
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
  
  public boolean isCheckmate() {
    Color teamColor = this.myKing.getColor();
    Check check = new Check();
    int targetX, targetY;
    int kingX = this.myKing.getPosition().getX();
    int kingY = this.myKing.getPosition().getY();
    int directionX, directionY;
    int teamNum = ChessBoard.cvtTeam((GameController.colorToTeam(teamColor)));
    
    if(!check.isCheck() || this.myKing.getCanMoves().length != 0) {
      return false;
    }
    
    else {
      target = check.nowPiece;
      targetX = target.getPosition().getX();
      targetY = target.getPosition().getY();
      
      aw = queen[teamNum].getCanMoves();
      for(int i = 0; i < aw.length; i++) {
        if(aw[i].getX() == targetX && aw[i].getY() == targetY) {
          return false;
        }
      }
      
      for(int i = 0; i < rook[teamNum].length; i++) {
        aw = rook[teamNum][i].getCanMoves();
        for(int j = 0; j < aw.length; j++) {
          if(aw[j].getX() == targetX && aw[j].getY() == targetY)
            return false;
        }
      }
      
      for(int i = 0; i < bishop[teamNum].length; i++) {
        aw = bishop[teamNum][i].getCanMoves();
        for(int j = 0; j < aw.length; j++) {
          if(aw[j].getX() == targetX && aw[j].getY() == targetY)
            return false;
        }
      }
      
      for(int i = 0; i < knight[teamNum].length; i++) {
        aw = knight[teamNum][i].getCanMoves();
        for(int j = 0; j < aw.length; j++) {
          if(aw[j].getX() == targetX && aw[j].getY() == targetY)
            return false;
        }
      }
      
      for(int i = 0; i < pawn[teamNum].length; i++) {
        aw = pawn[teamNum][i].getCanMoves();
        for(int j = 0; j < aw.length; j++) {
          if(aw[j].getX() == targetX && aw[j].getY() == targetY)
            return false;
        }
      }
      
      /*
       * if target piece type is pawn or knight, player must delete target.
       */
      
      if(target.getPieceType() == PieceType.PAWN || target.getPieceType() == PieceType.KNIGHT) {
        System.out.println("Checkmate");
        return true;
      }
      
      else if(target.getPieceType() == PieceType.QUEEN) {
        directionX = targetX - kingX;
        directionY = targetY - kingY;
        
        if(directionX == 0) {
          for(int i = 0; i < Math.abs(directionY); i++) {
            aw = queen[teamNum].getCanMoves();
            
            for(int j = 0; j < aw.length; j++) {
              if(aw[j].getX() == kingX && aw[j].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                return false;
              }
            }
            
            for(int j = 0; j < bishop.length; j++) {
              aw = bishop[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  return false;
                }
              }
            }
            
            for(int j = 0; j < rook.length; j++) {
              aw = rook[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  return false;
                }
              }
            }
            
            for(int j = 0; j < knight.length; j++) {
              aw = knight[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  return false;
                }
              }
            }
            
            for(int j = 0; j < pawn.length; j++) {
              aw = pawn[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  return false;
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
                return false;
              }
            }
            
            for(int j = 0; j < bishop.length; j++) {
              aw = bishop[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  return false;
                }
              }
            }
            
            for(int j = 0; j < rook.length; j++) {
              aw = rook[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  return false;
                }
              }
            }
            
            for(int j = 0; j < knight.length; j++) {
              aw = knight[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  return false;
                }
              }
            }
            
            for(int j = 0; j < pawn.length; j++) {
              aw = pawn[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  return false;
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
                return false;
              }
            }
            
            for(int j = 0; j < bishop.length; j++) {
              aw = bishop[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  return false;
                }
              }
            }
            
            for(int j = 0; j < rook.length; j++) {
              aw = rook[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  return false;
                }
              }
            }
            
            for(int j = 0; j < knight.length; j++) {
              aw = knight[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  return false;
                }
              }
            }
            
            for(int j = 0; j < pawn.length; j++) {
              aw = pawn[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  return false;
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
              return false;
            }
          }
          
          for(int j = 0; j < bishop.length; j++) {
            aw = bishop[teamNum][j].getCanMoves();
            
            for(int k = 0; k < aw.length; k++) {
              if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                return false;
              }
            }
          }
          
          for(int j = 0; j < rook.length; j++) {
            aw = rook[teamNum][j].getCanMoves();
            
            for(int k = 0; k < aw.length; k++) {
              if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                return false;
              }
            }
          }
          
          for(int j = 0; j < knight.length; j++) {
            aw = knight[teamNum][j].getCanMoves();
            
            for(int k = 0; k < aw.length; k++) {
              if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                return false;
              }
            }
          }
          
          for(int j = 0; j < pawn.length; j++) {
            aw = pawn[teamNum][j].getCanMoves();
            
            for(int k = 0; k < aw.length; k++) {
              if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                return false;
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
                return false;
              }
            }
            
            for(int j = 0; j < bishop.length; j++) {
              aw = bishop[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  return false;
                }
              }
            }
            
            for(int j = 0; j < rook.length; j++) {
              aw = rook[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  return false;
                }
              }
            }
            
            for(int j = 0; j < knight.length; j++) {
              aw = knight[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  return false;
                }
              }
            }
            
            for(int j = 0; j < pawn.length; j++) {
              aw = pawn[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX && aw[k].getY() == kingY + i * (directionY / Math.abs(directionY))) {
                  return false;
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
                return false;
              }
            }
            
            for(int j = 0; j < bishop.length; j++) {
              aw = bishop[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  return false;
                }
              }
            }
            
            for(int j = 0; j < rook.length; j++) {
              aw = rook[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  return false;
                }
              }
            }
            
            for(int j = 0; j < knight.length; j++) {
              aw = knight[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  return false;
                }
              }
            }
            
            for(int j = 0; j < pawn.length; j++) {
              aw = pawn[teamNum][j].getCanMoves();
              
              for(int k = 0; k < aw.length; k++) {
                if(aw[k].getX() == kingX + i * (directionX / Math.abs(directionX)) && aw[k].getY() == kingY) {
                  return false;
                }
              }
            }
          }
        }
      }
      
      else {
        System.out.println("Checkmate");
        return true;
      }
    }
  }
}
