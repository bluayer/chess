package board;

import piece.GamePiece.PieceType;

/**
 * @author ChangminYi
 * class about single tile
 */
public class Tile extends Status{
  
	private boolean active;
	private boolean onPiece;
	private PieceType occupyPiece;
	
	/**
	 * @param TEAM
	 * @return index of team. black is 0, white is 1, red is 2, green is 3
	 */
	
	/*
	public static int cvtTeam(TEAM t) {
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
*/
	
	public Tile(boolean active, PieceType Piece){
		this.active = active;
		this.setOnPiece(false);
		this.occupyPiece = Piece;
	}
	public Tile() {}
	
	public void setActive(boolean tf) {
	  this.active = tf;
	}
	
	public boolean getActive() {
	  return this.active;
	}
	
  public boolean isOnPiece() {
    return this.onPiece;
  }

  public void setOnPiece(boolean onPiece) {
    this.onPiece = onPiece;
  }
  
  public void setOccupyPiece(PieceType pc) {
    this.occupyPiece = pc;
  }
  
  public PieceType getOccupyPiece() {
    return occupyPiece;
  }
}
