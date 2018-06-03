package gamestate;

import board.Status.TEAM;
import board.Tile;
import chess.ChessGui;
import piece.Position;
import piece.Bishop;
import piece.GamePiece.Color;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.PieceWay;
import piece.Queen;
import piece.Rook;

public class Stalemate {
  King myKing;
  Tile[][] board;
  
	TEAM team;
	King[] king;
	Queen[] queen;
	Knight[][] knight;
	Bishop[][] bishop;
	Rook[][] rook;
	Pawn[][] pawn;
	Position[] aw; // availableWay

	public Stalemate(King king) {
		this.king = ChessGui.b.king;
		this.queen = ChessGui.b.queen;
		this.knight = ChessGui.b.knight;
		this.bishop = ChessGui.b.bishop;
		this.rook = ChessGui.b.rook;
		this.pawn = ChessGui.b.pawn;
		this.myKing = king;
		this.board = ChessGui.b.getcBoard();
	}
	
	public void getStalemate(TEAM team){
		this.team = team;
	}

	public boolean isStalemate(TEAM team) {
		int t = Tile.cvtTeam(team);
		
		Check p = new Check();
	
		if(p.isCheck()) {
			return false;
		}
		 
		else {
			if(t == Tile.cvtTeam(TEAM.BLACK)) {
				this.aw = king[t].getCanMoves();
				if(this.aw.length != 0) {return false;}
				this.aw = queen[t].getCanMoves();
				if(this.aw.length != 0) {return false;}
			
				for(int i = 0; i < rook.length; i++) {
					this.aw = rook[t][i].getCanMoves();
					if(this.aw.length != 0) {return false;}
				}
				for(int i = 0; i < bishop.length; i++) {
					this.aw = bishop[t][i].getCanMoves();
					if(this.aw.length != 0) {return false;}
				}
				for(int i = 0; i < knight.length; i++) {
					this.aw = knight[t][i].getCanMoves();
					if(this.aw.length != 0) {return false;}
				}
				for (int i = 0; i < pawn.length; i++) {
					this.aw = pawn[t][i].getCanMoves();
					if(this.aw.length != 0) {return false;}
				}
			
				System.out.println("Stalemate");
				return true;
			}
		
			else if(t == Tile.cvtTeam(TEAM.WHITE)) {
				this.aw = king[t].getCanMoves();
				if(this.aw.length != 0) {return false;}
				this.aw = queen[t].getCanMoves();
				if(this.aw.length != 0) {return false;}
			
				for(int i = 0; i < rook.length; i++) {
					this.aw = rook[t][i].getCanMoves();
					if(this.aw.length != 0) {return false;}
				}
				for(int i = 0; i < bishop.length; i++) {
					this.aw = bishop[t][i].getCanMoves();
					if(this.aw.length != 0) {return false;}
				}
				for(int i = 0; i < knight.length; i++) {
					this.aw = knight[t][i].getCanMoves();
					if(this.aw.length != 0) {return false;}
				}
				for (int i = 0; i < pawn.length; i++) {
					this.aw = pawn[t][i].getCanMoves();
					if(this.aw.length != 0) {return false;}
				}
			
				System.out.println("Stalemate");
				return true;
			}
		
			else if(t == Tile.cvtTeam(TEAM.RED)) {
				this.aw = king[t].getCanMoves();
				if(this.aw.length != 0) {return false;}
				this.aw = queen[t].getCanMoves();
				if(this.aw.length != 0) {return false;}
			
				for(int i = 0; i < rook.length; i++) {
					this.aw = rook[t][i].getCanMoves();
					if(this.aw.length != 0) {return false;}
				}
				for(int i = 0; i < bishop.length; i++) {
					this.aw = bishop[t][i].getCanMoves();
					if(this.aw.length != 0) {return false;}
				}
				for(int i = 0; i < knight.length; i++) {
					this.aw = knight[t][i].getCanMoves();
					if(this.aw.length != 0) {return false;}
				}
				for (int i = 0; i < pawn.length; i++) {
					this.aw = pawn[t][i].getCanMoves();
					if(this.aw.length != 0) {return false;}
				}
			
				System.out.println("Stalemate");
				return true;
			}
			
			else {
				this.aw = king[t].getCanMoves();
				if(this.aw.length != 0) {return false;}
				this.aw = queen[t].getCanMoves();
				if(this.aw.length != 0) {return false;}
			
				for(int i = 0; i < rook.length; i++) {
					this.aw = rook[t][i].getCanMoves();
					if(this.aw.length != 0) {return false;}
				}
				for(int i = 0; i < bishop.length; i++) {
					this.aw = bishop[t][i].getCanMoves();
					if(this.aw.length != 0) {return false;}
				}
				for(int i = 0; i < knight.length; i++) {
					this.aw = knight[t][i].getCanMoves();
					if(this.aw.length != 0) {return false;}
				}
				for (int i = 0; i < pawn.length; i++) {
					this.aw = pawn[t][i].getCanMoves();
					if(this.aw.length != 0) {return false;}
				}
			
				System.out.println("Stalemate");
				return true;
			}
		}
	}
	
}