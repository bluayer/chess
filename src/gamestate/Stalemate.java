package gamestate;

import chess.ChessGui;
import piece.Position;
import piece.Bishop;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Queen;
import piece.Rook;

public class Stalemate {
  King myKing;
  
	King[] king;
	Queen[] queen;
	Knight[][] knight;
	Bishop[][] bishop;
	Rook[][] rook;
	Pawn[][] pawn;
	Position[] aw; // availableWay

	public Stalemate() {
		this.king = ChessGui.b.king;
		this.queen = ChessGui.b.queen;
		this.knight = ChessGui.b.knight;
		this.bishop = ChessGui.b.bishop;
		this.rook = ChessGui.b.rook;
		this.pawn = ChessGui.b.pawn;
	}
	
public void isStalemate() {
	for(int t = 0; t < 4; t++) {
		if(GameController.checkFlag[t] == 1) {
			return;
		}
			 
		else {
				aw = king[t].getCanMoves();
				if(aw.length != 0) {return;}
				aw = queen[t].getCanMoves();
				if(this.aw.length != 0) {return;}
			
				for(int i = 0; i < rook[t].length; i++) {
					aw = rook[t][i].getCanMoves();
					if(aw.length != 0) {return;}
				}
				for(int i = 0; i < bishop[t].length; i++) {
					aw = bishop[t][i].getCanMoves();
					if(aw.length != 0) {return;}
				}
				for(int i = 0; i < knight[t].length; i++) {
					aw = knight[t][i].getCanMoves();
					if(aw.length != 0) {return;}
				}
				for (int i = 0; i < pawn[t].length; i++) {
					aw = pawn[t][i].getCanMoves();
					if(aw.length != 0) {return;}
				}
				
				GameController.stalemateFlag[t] = 1;
				return;
			}
		}		
	}	
}