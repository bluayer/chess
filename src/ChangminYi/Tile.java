package ChangminYi;

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
	public enum TEAM{
		BLACK, WHITE, RED, GREEN
	}
	public enum PIECE{
		PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING
	}
	boolean active;
	boolean onPiece;
	
	/**
	 * @param TEAM
	 * @return index of team. black is 0, white is 1, red is 2, green is 3
	 */
	public int cvtTeam(TEAM t) {
		switch(t) {
		case BLACK:
			return 0;
		case WHITE:
			return 1;
		case RED:
			return 2;
		case GREEN:
			return 3;
		}
		return -1;
	}

	
	public Tile(boolean active){
		this.active = active;
		this.onPiece = false;
	}
	public Tile() {}
}
