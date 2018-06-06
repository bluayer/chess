package gamestate; 

import chess.ChessGui;
import chess.MouseClick;
import gamestate.GameController;
import piece.Bishop;
import piece.GamePiece;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Position;
import piece.Queen;
import piece.Rook;

/** 
* This class decide opposite team kings are on check 
* @see GamePiece
* @see MouseClick
* @author Yeoilgoo
* @since 2018-05-28
*/ 

public class Check { 

  King[] king;
  Queen[] queen;
  Knight[][] knight;
  Bishop[][] bishop;
  Rook[][] rook;
  Pawn[][] pawn;
  Position[] aw; //available way
  
  /*
   * get information of all pieces on board.
   */
  public Check(){
    this.king = ChessGui.b.king;
    this.queen = ChessGui.b.queen;
    this.knight = ChessGui.b.knight;
    this.bishop = ChessGui.b.bishop;
    this.rook = ChessGui.b.rook;
    this.pawn = ChessGui.b.pawn;
  } 
  
  public void isCheck() { 
  	for(int i = 0; i < 4; i++) { //initialize whole flags.
  		GameController.checkFlag[i] = 0;
  	}
  	
  	for(int i = 0; i < 4; i++) { //check all players.
  		aw = null;
  		int op1 = 0, op2 = 0;
  		int king1X = 0, king1Y = 0, king2X = 0, king2Y = 0;
  		
  		switch(i) { //set op1, op2.
  		case 0:
  			op1 = 2;
  			op2 = 3;
  			break;
  		case 1:
  			op1 = 2;
  			op2 = 3;
  			break;
  		case 2:
  			op1 = 0;
  			op2 = 1;
  			break;
  		case 3:
  			op1 = 0;
  			op2 = 1;
  			break;
  		}
  		
  		/*
  		 * king1X, king1Y is op1 king's position. king2X, king1Y is op2 king's position.
  		 * if one of player piece possible way's position is equal to opposite king position, set check flag.
  		 */
  		king1X = king[op1].getPosition().getX(); king1Y = king[op1].getPosition().getY();
  		king2X = king[op2].getPosition().getX(); king2Y = king[op2].getPosition().getY();
  		
  		aw = queen[i].getCanMoves();
  		for(int j = 0; j < aw.length; j++) {
  			if(!queen[i].isAlive()) {
  				break;
  			}
  			if(aw[j].getX() == king1X && aw[j].getY() == king1Y) {
  				GameController.checkFlag[op1] = 1;
  			}
  			if(aw[j].getX() == king2X && aw[j].getY() == king2Y) {
  				GameController.checkFlag[op2] = 1;
  			}
  		}
  		
  		for(int j = 0; j < bishop[i].length; j++) {
  			aw = bishop[i][j].getCanMoves();
  			for(int k = 0; k < aw.length; k++) {
  				if(!bishop[i][j].isAlive()) {
  					break;
  				}
  				if(aw[k].getX() == king1X && aw[k].getY() == king1Y) {
  					GameController.checkFlag[op1] = 1;
  				}
  				if(aw[k].getX() == king2X && aw[k].getY() == king2Y) {
  					GameController.checkFlag[op2] = 1;
  				}
  			}
  		}
  		
  		for(int j = 0; j < rook[i].length; j++) {
  			aw = rook[i][j].getCanMoves();
  			for(int k = 0; k < aw.length; k++) {
  				if(!rook[i][j].isAlive()) {
  					break;
  				}
  				if(aw[k].getX() == king1X && aw[k].getY() == king1Y) {
  					GameController.checkFlag[op1] = 1;
  				}
  				if(aw[k].getX() == king2X && aw[k].getY() == king2Y) {
  					GameController.checkFlag[op2] = 1;
  				}
  			}
  		}
  		
  		for(int j = 0; j < knight[i].length; j++) {
  			aw = knight[i][j].getCanMoves();
  			for(int k = 0; k < aw.length; k++) {
  				if(!knight[i][j].isAlive()) {
  					break;
  				}
  				if(aw[k].getX() == king1X && aw[k].getY() == king1Y) {
  					GameController.checkFlag[op1] = 1;
  				}
  				if(aw[k].getX() == king2X && aw[k].getY() == king2Y) {
  					GameController.checkFlag[op2] = 1;
  				}
  			}
  		}
  		
  		for(int j = 0; j < pawn[i].length; j++) {
  			aw = pawn[i][j].getCanMoves();
  			for(int k = 0; k < aw.length; k++) {
  				if(!pawn[i][j].isAlive()) {
  					break;
  				}
  				if(aw[k].getX() == king1X && aw[k].getY() == king1Y) {
  					GameController.checkFlag[op1] = 1;
  				}
  				if(aw[k].getX() == king2X && aw[k].getY() == king2Y) {
  					GameController.checkFlag[op2] = 1;
  				}
  			}
  		}
  	}
  }
}