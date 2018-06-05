package gamestate; 

import board.ChessBoard;
import board.Status.TEAM;
import chess.ChessGui;
import chess.MouseClick;
import gamestate.GameController;
import piece.GamePiece.Color;
import piece.Bishop;
import piece.GamePiece;
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

  protected GamePiece nowPiece;
  
  King[] king;
  Queen[] queen;
  Knight[][] knight;
  Bishop[][] bishop;
  Rook[][] rook;
  Pawn[][] pawn;
  private Position[] aw; //available way
  
  public Check(){
    this.nowPiece = MouseClick.clickedPiece;
    this.king = ChessGui.b.king;
    this.queen = ChessGui.b.queen;
    this.knight = ChessGui.b.knight;
    this.bishop = ChessGui.b.bishop;
    this.rook = ChessGui.b.rook;
    this.pawn = ChessGui.b.pawn;
  } 
  
  public void isCheck() { 
    this.nowPiece = MouseClick.clickedPiece;
    Color color = nowPiece.getColor();
    Color oppositeColor1 = null, oppositeColor2 = null, teammate = null; 
    TEAM oppositeTeam1, oppositeTeam2, playerTeam, teamT;
    int op1 = 0, op2 = 0, player = 0, team = 0;
    int flag = 0;

    switch (color) { 
    case BLACK: 
      oppositeColor1 = Color.RED; 
      oppositeColor2 = Color.GREEN;
      teammate = Color.WHITE;
      break; 
    case WHITE: 
      oppositeColor1 = Color.RED; 
      oppositeColor2 = Color.GREEN; 
      teammate = Color.BLACK;
      break; 
    case RED: 
      oppositeColor1 = Color.BLACK; 
      oppositeColor2 = Color.WHITE; 
      teammate = Color.GREEN;
      break; 
    case GREEN: 
      oppositeColor1 = Color.BLACK; 
      oppositeColor2 = Color.WHITE; 
      teammate = Color.RED;
      break; 
    default: 
    } 
    
    oppositeTeam1 = GameController.colorToTeam(oppositeColor1);
    oppositeTeam2 = GameController.colorToTeam(oppositeColor2);
    op1 = ChessBoard.cvtTeam(oppositeTeam1);
    op2 = ChessBoard.cvtTeam(oppositeTeam2);
    playerTeam = GameController.colorToTeam(color);
    player = ChessBoard.cvtTeam(playerTeam);
    teamT = GameController.colorToTeam(teammate);
    team = ChessBoard.cvtTeam(teamT);
    
    /*
     * player case
     */
    
    aw = king[player].getCanMoves();
    for(int i = 0; i < aw.length; i++) {
    	if(aw[i].getX() == king[op1].getPosition().getX() && aw[i].getY() == king[op1].getPosition().getY()) {
    		GameController.checkFlag[op1] = 1;
    		flag = 1;
    	}
    	if(aw[i].getX() == king[op2].getPosition().getX() && aw[i].getY() == king[op2].getPosition().getY()) {
    		GameController.checkFlag[op2] = 1;
    		flag = 1;
    	}
    }
    
    aw = queen[player].getCanMoves();
    for(int i = 0; i < aw.length; i++) {
    	if(aw[i].getX() == king[op1].getPosition().getX() && aw[i].getY() == king[op1].getPosition().getY()) {
    		GameController.checkFlag[op1] = 1;
    		flag = 1;
    	}
    	if(aw[i].getX() == king[op2].getPosition().getX() && aw[i].getY() == king[op2].getPosition().getY()) {
    		GameController.checkFlag[op2] = 1;
    		flag = 1;
    	}
    }
    
    for(int i = 0; i < bishop[player].length; i++) {
    	aw = bishop[player][i].getCanMoves();
    	for(int j = 0; j < aw.length; j++) {
    		if(aw[j].getX() == king[op1].getPosition().getX() && aw[j].getY() == king[op1].getPosition().getY()) {
    			GameController.checkFlag[op1] = 1;
    			flag = 1;
    		}
    		if(aw[j].getX() == king[op2].getPosition().getX() && aw[j].getY() == king[op2].getPosition().getY()) {
    			GameController.checkFlag[op1] = 1;
    			flag = 1;
    		}
    	}
    }
    
    for(int i = 0; i < rook[player].length; i++) {
    	aw = rook[player][i].getCanMoves();
    	for(int j = 0; j < aw.length; j++) {
    		if(aw[j].getX() == king[op1].getPosition().getX() && aw[j].getY() == king[op1].getPosition().getY()) {
    			GameController.checkFlag[op1] = 1;
    			flag = 1;
    		}
    		if(aw[j].getX() == king[op2].getPosition().getX() && aw[j].getY() == king[op2].getPosition().getY()) {
    			GameController.checkFlag[op1] = 1;
    			flag = 1;
    		}
    	}
    }
    
    for(int i = 0; i < knight[player].length; i++) {
    	aw = knight[player][i].getCanMoves();
    	for(int j = 0; j < aw.length; j++) {
    		if(aw[j].getX() == king[op1].getPosition().getX() && aw[j].getY() == king[op1].getPosition().getY()) {
    			GameController.checkFlag[op1] = 1;
    			flag = 1;
    		}
    		if(aw[j].getX() == king[op2].getPosition().getX() && aw[j].getY() == king[op2].getPosition().getY()) {
    			GameController.checkFlag[op1] = 1;
    			flag = 1;
    		}
    	}
    }
    
    for(int i = 0; i < pawn[player].length; i++) {
    	aw = pawn[player][i].getCanMoves();
    	for(int j = 0; j < aw.length; j++) {
    		if(aw[j].getX() == king[op1].getPosition().getX() && aw[j].getY() == king[op1].getPosition().getY()) {
    			GameController.checkFlag[op1] = 1;
    			flag = 1;
    		}
    		if(aw[j].getX() == king[op2].getPosition().getX() && aw[j].getY() == king[op2].getPosition().getY()) {
    			GameController.checkFlag[op1] = 1;
    			flag = 1;
    		}
    	}
    }
    
    /*
     * teammate case
     */
    
    aw = king[team].getCanMoves();
    for(int i = 0; i < aw.length; i++) {
    	if(aw[i].getX() == king[op1].getPosition().getX() && aw[i].getY() == king[op1].getPosition().getY()) {
    		GameController.checkFlag[op1] = 1;
    		flag = 1;
    	}
    	if(aw[i].getX() == king[op2].getPosition().getX() && aw[i].getY() == king[op2].getPosition().getY()) {
    		GameController.checkFlag[op2] = 1;
    		flag = 1;
    	}
    }
    
    aw = queen[team].getCanMoves();
    for(int i = 0; i < aw.length; i++) {
    	if(aw[i].getX() == king[op1].getPosition().getX() && aw[i].getY() == king[op1].getPosition().getY()) {
    		GameController.checkFlag[op1] = 1;
    		flag = 1;
    	}
    	if(aw[i].getX() == king[op2].getPosition().getX() && aw[i].getY() == king[op2].getPosition().getY()) {
    		GameController.checkFlag[op2] = 1;
    		flag = 1;
    	}
    }
    
    for(int i = 0; i < bishop[team].length; i++) {
    	aw = bishop[team][i].getCanMoves();
    	for(int j = 0; j < aw.length; j++) {
    		if(aw[j].getX() == king[op1].getPosition().getX() && aw[j].getY() == king[op1].getPosition().getY()) {
    			GameController.checkFlag[op1] = 1;
    			flag = 1;
    		}
    		if(aw[j].getX() == king[op2].getPosition().getX() && aw[j].getY() == king[op2].getPosition().getY()) {
    			GameController.checkFlag[op1] = 1;
    			flag = 1;
    		}
    	}
    }
    
    for(int i = 0; i < rook[team].length; i++) {
    	aw = rook[team][i].getCanMoves();
    	for(int j = 0; j < aw.length; j++) {
    		if(aw[j].getX() == king[op1].getPosition().getX() && aw[j].getY() == king[op1].getPosition().getY()) {
    			GameController.checkFlag[op1] = 1;
    			flag = 1;
    		}
    		if(aw[j].getX() == king[op2].getPosition().getX() && aw[j].getY() == king[op2].getPosition().getY()) {
    			GameController.checkFlag[op1] = 1;
    			flag = 1;
    		}
    	}
    }
    
    for(int i = 0; i < knight[team].length; i++) {
    	aw = knight[team][i].getCanMoves();
    	for(int j = 0; j < aw.length; j++) {
    		if(aw[j].getX() == king[op1].getPosition().getX() && aw[j].getY() == king[op1].getPosition().getY()) {
    			GameController.checkFlag[op1] = 1;
    			flag = 1;
    		}
    		if(aw[j].getX() == king[op2].getPosition().getX() && aw[j].getY() == king[op2].getPosition().getY()) {
    			GameController.checkFlag[op1] = 1;
    			flag = 1;
    		}
    	}
    }
    
    for(int i = 0; i < pawn[team].length; i++) {
    	aw = pawn[team][i].getCanMoves();
    	for(int j = 0; j < aw.length; j++) {
    		if(aw[j].getX() == king[op1].getPosition().getX() && aw[j].getY() == king[op1].getPosition().getY()) {
    			GameController.checkFlag[op1] = 1;
    			flag = 1;
    		}
    		if(aw[j].getX() == king[op2].getPosition().getX() && aw[j].getY() == king[op2].getPosition().getY()) {
    			GameController.checkFlag[op1] = 1;
    			flag = 1;
    		}
    	}
    }
    
    if(flag == 1) {
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