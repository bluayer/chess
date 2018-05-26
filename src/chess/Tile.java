package chess;

public class Tile {
	boolean active;
	boolean atkPossible;
	boolean onPiece;
	
	public Tile(boolean active){
		this.active = active;
		this.atkPossible = false;
		this.onPiece = false;
	}
}
