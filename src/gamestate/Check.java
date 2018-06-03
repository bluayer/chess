package gamestate; 

import board.ChessBoard; 
import board.Tile;
import chess.ChessGui;
import piece.Bishop;
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
* @author SongJeongWoo 
* 
*/ 

public class Check { 

  private ChessBoard cboard = ChessGui.b; 
  private Tile[][] board;
  private King king;
  
  Queen[] queen;
  Knight[][] knight;
  Bishop[][] bishop;
  Rook[][] rook;
  Pawn[][] pawn;
  Position[] aw; //available way
  
  public Check(King king, Tile[][] board){
    this.king = king;
    this.board = board;
    this.queen = ChessGui.b.queen;
    this.knight = ChessGui.b.knight;
    this.bishop = ChessGui.b.bishop;
    this.rook = ChessGui.b.rook;
    this.pawn = ChessGui.b.pawn;
  } 
  
  public boolean isCheck() { 
    Color color = king.getColor(); 
    Color oppositeColor1 = null, oppositeColor2 = null; 
    int oppositeTeam1, oppositeTeam2;
    Position kingPos = king.getPosition();
    Position nowPos = new Position(0, 0);

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
    
    oppositeTeam1 = ChessBoard.cvtTeam(GameController.colorToTeam(oppositeColor1));
    oppositeTeam2 = ChessBoard.cvtTeam(GameController.colorToTeam(oppositeColor2));
    
    for(int i = 0; i < 14; i++)
      for(int j = 0; j < 14; j++)
        if(board[i][j].isOnPiece())
          if(board[i][j].getActive()) {
            nowPos.setX(i); nowPos.setY(j);
            if(board[i][j].getOccupyPiece() == PieceType.QUEEN) {
              if(queen[oppositeTeam1].getPosition() == nowPos) {
                aw = queen[oppositeTeam1].getCanMoves();
              
                for(int k = 0; k < aw.length; k++) 
                  if(aw[k] == kingPos)
                    return true;
              }
            
              else if(queen[oppositeTeam2].getPosition() == nowPos) {
                aw = queen[oppositeTeam2].getCanMoves();
              
                for(int k = 0; k < aw.length; k++)
                  if(aw[k] == kingPos)
                    return true;
              }
            }
            
            else if(board[i][j].getOccupyPiece() == PieceType.BISHOP) {
              for(int k = 0; k < bishop[oppositeTeam1].length; k++) {
                if(bishop[oppositeTeam1][k].getPosition() == nowPos) {
                  aw = bishop[oppositeTeam1][k].getCanMoves();
                  
                  for(int l = 0; l < aw.length; l++)
                    if(aw[l] == kingPos)
                      return true;
                }
                
                else if(bishop[oppositeTeam2][k].getPosition() == nowPos) {
                  aw = bishop[oppositeTeam2][k].getCanMoves();
                  
                  for(int l = 0; l < aw.length; l++)
                    if(aw[l] == kingPos)
                      return true;
                }
              }
            }
             
            else if(board[i][j].getOccupyPiece() == PieceType.ROOK) {
              for(int k = 0; k < rook[oppositeTeam1].length; k++) {
                if(rook[oppositeTeam1][k].getPosition() == nowPos) {
                  aw = rook[oppositeTeam1][k].getCanMoves();
                  
                  for(int l = 0; l < aw.length; l++)
                    if(aw[l] == kingPos)
                      return true;
                }
                
                else if(rook[oppositeTeam2][k].getPosition() == nowPos) {
                  aw = rook[oppositeTeam2][k].getCanMoves();
                  
                  for(int l = 0; l < aw.length; l++)
                    if(aw[l] == kingPos)
                      return true;
                }
              }
            }
            
            else if(board[i][j].getOccupyPiece() == PieceType.KNIGHT) {
              for(int k = 0; k < knight[oppositeTeam1].length; k++) {
                if(knight[oppositeTeam1][k].getPosition() == nowPos) {
                  aw = knight[oppositeTeam1][k].getCanMoves();
                  
                  for(int l = 0; l < aw.length; l++)
                    if(aw[l] == kingPos)
                      return true;
                }
                
                else if(knight[oppositeTeam2][k].getPosition() == nowPos) {
                  aw = knight[oppositeTeam2][k].getCanMoves();
                  
                  for(int l = 0; l < aw.length; l++)
                    if(aw[l] == kingPos)
                      return true;
                }
              }
            }
            
            else if(board[i][j].getOccupyPiece() == PieceType.PAWN) {
              for(int k = 0; k < pawn[oppositeTeam1].length; k++) {
                if(pawn[oppositeTeam1][k].getPosition() == nowPos) {
                  aw = pawn[oppositeTeam1][k].getCanMoves();
                  
                  for(int l = 0; l < aw.length; l++)
                    if(aw[l] == kingPos)
                      return true;
                }
                
                else if(pawn[oppositeTeam2][k].getPosition() == nowPos) {
                  aw = pawn[oppositeTeam2][k].getCanMoves();
                  
                  for(int l = 0; l < aw.length; l++)
                    if(aw[l] == kingPos)
                      return true;
                }
              }
            }
            
            else {
              System.out.println("Check:Error");
              return false;
            }
          }
            
    return false;
  } 
 } 