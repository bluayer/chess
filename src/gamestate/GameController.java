package gamestate;

import board.Status.TEAM;
import board.ChessBoard;
import board.Tile;
import chess.ChessGui;
import gamestate.Checkmate;
import gamestate.Stalemate;
import piece.GamePiece.Color;
import piece.King;
import piece.Position;

public class GameController {
  public static int[] stalemateFlag = {0, 0, 0, 0};
  public static int[] checkmateFlag = {0, 0, 0, 0};
  //public static int[] checkFlag = {0, 0, 0, 0};

  public static void setStalemateFlag(TEAM team) {
    Stalemate s = new Stalemate(ChessGui.b.king[Tile.cvtTeam(team)]);
    if(s.isStalemate(team)) {
      stalemateFlag[Tile.cvtTeam(team)] = 1;
    }
  }
  
  public static void resetStalemateFlag(TEAM team) {
    Stalemate s = new Stalemate(ChessGui.b.king[Tile.cvtTeam(team)]);
    if(!s.isStalemate(team)) {
      stalemateFlag[Tile.cvtTeam(team)] = 0;
    }
  }
  
  public static void setCheckmateFlag(King king) {
    Checkmate c = new Checkmate(king);
    Color c1 = king.getColor();
    if(c.isCheckmate(king.getPosition(), ChessGui.b.getcBoard())) {
      checkmateFlag[Tile.cvtTeam(colorToTeam(c1))] = 1;
    }
  }
  
  public static void resetCheckmateFlag(King king) {
    Checkmate c = new Checkmate(king);
    Color c1 = king.getColor();
    if(!c.isCheckmate(king.getPosition(), ChessGui.b.getcBoard())) {
      checkmateFlag[Tile.cvtTeam(colorToTeam(c1))] = 0;
    }
  }
  /*
  public static void setCheckFlag(TEAM team) {
    Check c = new Check();
    if(c.isCheck()) {
      checkFlag[Tile.cvtTeam(team)] = 1;
    }
  }
  
  public static void resetCheckFlag(TEAM team) {
    checkFlag[Tile.cvtTeam(team)] = 0;
  }
  */
  public static Color teamToColor(int num) {
    Color color = null;
    
    switch(num) {
    case 0:
      color = Color.BLACK;
      break;
    case 1:
      color = Color.WHITE;
      break;
    case 2:
      color = Color.RED;
      break;
    case 3:
      color = Color.GREEN;
      break;
    default :
      System.out.println("teamToColor:Error");
    }
    
    return color;
  }
  
  public static TEAM colorToTeam(Color color) {
    TEAM team = null;
    
    switch(color) {
    case WHITE:
      team = TEAM.WHITE;
      break;
    case BLACK:
      team = TEAM.BLACK;
      break;
    case RED:
      team = TEAM.RED;
      break;
    case GREEN:
      team = TEAM.GREEN;
      break;
    default:
        System.out.println("coloToTeam:Error");
    }
    
    return team;
  }
  
  public int GameResult() {
    if(this.checkmateFlag[0] + this.checkmateFlag[1] == 2) {
      return 1; //Team1(Black, White) is win
    }
    else if(this.checkmateFlag[2] + this.checkmateFlag[3] == 2) {
      return 2; //Team2(Black, White) is win
    }
    else if(this.stalemateFlag[0] + this.stalemateFlag[1] == 2) {
      return -1; //draw
    }
    else if(this.stalemateFlag[2] + this.stalemateFlag[3] == 2) {
      return -1;
    }
    
    //add code here about skip some player's turn and other draw condition.
    
    else {
      return 0;
    }
  }
}
