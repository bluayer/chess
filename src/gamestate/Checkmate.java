package gamestate;

import board.ChessBoard;
import board.Tile;
import chess.ChessGui;
import gamestate.GameController;
import piece.Bishop;
import piece.GamePiece.Color;
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

  private Tile[][] nowTile; 
  King myKing;
  
  King[] king;
  Queen[] queen;
  Knight[][] knight;
  Bishop[][] bishop;
  Rook[][] rook;
  Pawn[][] pawn;
  Position[] aw; // availableWay
  
  public Checkmate(King k) {
    this.myKing = k;
    this.nowTile = ChessBoard.getcBoard();
    this.king = ChessGui.b.king;
    this.queen = ChessGui.b.queen;
    this.knight = ChessGui.b.knight;
    this.bishop = ChessGui.b.bishop;
    this.rook = ChessGui.b.rook;
    this.pawn = ChessGui.b.pawn;
  }
  
  public boolean isCheckmate(Position kingPos, Tile[][] tile) {
    Check c = new Check();
    Tile[][] bufferTile;
    Color teamColor = myKing.getColor();
    int team = ChessBoard.cvtTeam(GameController.colorToTeam(teamColor));
    
    if(!c.isCheck(this.myKing))
      return false;
    
    else {
      if(myKing.getColor() == Color.BLACK) {
        aw = king[0].getCanMoves();
        for(int i = 0; i < aw.length; i++) {
          if(!isCheckmate(aw[i], tile))
            return false;
        }
        
        aw = queen[0].getCanMoves();
        for(int i = 0; i < aw.length; i++) {
          ChessBoard.updateTile(queen[0].getPosition(), aw[i]);
          bufferTile = ChessBoard.getcBoard();
          if(!isCheckmate(kingPos, bufferTile)) {
            bufferTile = this.nowTile;
            return false;
          }
        }
        
        for(int i = 0; i < knight.length; i++) {
          aw = knight[0][i].getCanMoves();
          ChessBoard.updateTile(knight[0][i].getPosition(), aw[i]);
          bufferTile = ChessBoard.getcBoard();
          if(!isCheckmate(kingPos, bufferTile)) {
            bufferTile = this.nowTile;
            return false;
          }
        }
        
        for(int i = 0; i < bishop.length; i++) {
          aw = bishop[0][i].getCanMoves();
          ChessBoard.updateTile(bishop[0][i].getPosition(), aw[i]);
          bufferTile = ChessBoard.getcBoard();
          if(!isCheckmate(kingPos, bufferTile)) {
            bufferTile = this.nowTile;
            return false;
          }
        }
        
        for(int i = 0; i < rook.length; i++) {
          aw = rook[0][i].getCanMoves();
          ChessBoard.updateTile(rook[0][i].getPosition(), aw[i]);
          bufferTile = ChessBoard.getcBoard();
          if(!isCheckmate(kingPos, bufferTile)) {
            bufferTile = this.nowTile;
            return false;
          }
        }
        
        for(int i = 0; i < pawn.length; i++) {
          aw = pawn[0][i].getCanMoves();
          ChessBoard.updateTile(pawn[0][i].getPosition(), aw[i]);
          bufferTile = ChessBoard.getcBoard();
          if(!isCheckmate(kingPos, bufferTile)) {
            bufferTile = this.nowTile;
            return false;
          }
        }    
      }    
    }
    
    return true;
  }
  
}