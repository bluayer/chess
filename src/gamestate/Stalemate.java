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
	TEAM team;
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
	
	public void getStalemate(TEAM team){
		this.team = team;
	}
	
	private Color teamToColor(int num) {
		Color color = null;
		
		switch(num) {
		case 0:{
			color = Color.BLACK;
			break;
			}
		case 1:{
			color = Color.WHITE;
			break;
			}
		case 2:{
			color = Color.RED;
			break;
			}
		case 3:{
			color = Color.GREEN;
			break;
			}
		default :{
			System.out.println("teamToColor:Error");
			}
		}
		
		return color;
	}
	
	public boolean isStalemate() {
		int t = Tile.cvtTeam(this.team);
		
		PieceWay p = new PieceWay(this.king[t].getPosition());
		
		//error!
		if(p.isCheck(this.king[t])) {
			
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
			
				return true;
			}
		}
	}
	
}