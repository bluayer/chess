package piece;

import java.util.List;

public abstract class GamePiece implements Piece{

  public enum Color {
    WHITE,
  	  BLACK,
  	  RED,
  	  GREEN
  }
	
  public enum PieceType {
    PAWN,
    ROOK,
    KNIGHT,
    BISHOP,
    QUEEN,
    KING
  }
	
  protected Color color;
  protected PieceType piece;
  private Position mposition;
	
  public GamePiece(Color color, PieceType piece, Position position) {
    this.color = color;
    this.piece = piece;
    this.mposition = position;
  }
  
  boolean isWhite() {
    if(Color.WHITE == color) {
    	  return true;
    }
    else {
      return false;
    }
  }

  boolean isBlack() {
    if(Color.BLACK == color) {
        return true;
    }
    else {
      return false;
    }
  }
  
  boolean isRed() {
    if(Color.RED == color) {
        return true;
    }
    else {
      return false;
    }
  }
  
  boolean isGreen() {
    if(Color.GREEN == color) {
        return true;
    }
    else {
      return false;
    }
  }
  
  @Override
  public Position getPosition() {
    return this.mposition;
  }
  
  @Override
  public abstract List<Position> getCanMoves();
  
  @Override
  public Color getColor() {
    return color;
  }
  
  @Override
  public PieceType getPieceType() {
    return piece;
  }
      
}
