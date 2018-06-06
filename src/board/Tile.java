package board;

import piece.GamePiece.PieceType;

/**
 * @author ChangminYi
 * class about single tile
 */
public class Tile implements Status{
  
	private boolean active;
	private boolean onPiece;
	private PieceType occupyPiece;
	

	public Tile(boolean active, PieceType Piece){
		this.active = active;
		this.setOnPiece(false);
		this.occupyPiece = Piece;
	}
	
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
