package chess;

/**
 * @author ¿Ã√¢πŒ
 * class about single tile
 */
public class Tile {
	/**
	 * enum about team and piece
	 * if team is BLACK or White, pawn moves vertically
	 * if team is RED or GREEN, pawn moves horizontally
	 */
	public static enum TEAM{
		BLACK, WHITE, RED, GREEN
	}
	public static enum PIECE{
		PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING
	}
	boolean active;
	boolean onPiece;
	TEAM team;
	PIECE piece;
	
	public Tile(boolean active){
		this.active = active;
		this.onPiece = false;
	}
	public Tile() {}
}
