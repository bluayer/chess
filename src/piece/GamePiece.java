package piece;

import java.awt.image.BufferedImage;

/**
 * A abstract class for piece in Game, so its name is GamePiece
 * 
 * @see Piece
 * @see BufferedImage
 * @author SongJeongWoo
 * @since 2018-05-26
 */

public abstract class GamePiece extends Piece{

  public static enum Color {
    WHITE, RED, BLACK, GREEN
  }
	
  public static enum PieceType {
    PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING, NOPE
  }
	
  protected BufferedImage img;
  protected Color color;
  protected PieceType piece;
  protected boolean alive;
  private Position mposition;
 
  
  public GamePiece(BufferedImage img, Color color, PieceType piece, Position position, boolean alive) {
    this.img = img;
    this.color = color;
    this.piece = piece;
    this.mposition = position;
    this.alive = alive;
  }
  
  public GamePiece(PieceType piece, Position position, boolean alive) {
    this.img = null;
    this.color = null;
    this.piece = piece;
    this.mposition = position;
    this.alive = false;
  }
  
  public boolean isWhite() {
    if(Color.WHITE == color) {
    	  return true;
    }
    else {
      return false;
    }
  }

  public boolean isBlack() {
    if(Color.BLACK == color) {
        return true;
    }
    else {
      return false;
    }
  }
  
  public boolean isRed() {
    if(Color.RED == color) {
        return true;
    }
    else {
      return false;
    }
  }
  
  public boolean isGreen() {
    if(Color.GREEN == color) {
        return true;
    }
    else {
      return false;
    }
  }
  
  @Override
  public abstract Position[] getCanMovesForKing();
  
  @Override
  public abstract Position[] getCanMoves();
  
  @Override
  public GamePiece move(Position goal) {
    this.mposition = null;
    this.mposition = goal;
    return this;
  }
  
  @Override
  public Position getPosition() {
    return this.mposition;
  }
  
  @Override
  public Color getColor() {
    return color;
  }
  
  @Override
  public PieceType getPieceType() {
    return piece;
  }
  
  @Override
  public boolean isAlive() {
    return alive;
  }
  
  public void setAlive(boolean alive) {
    this.alive = alive;
  }
  
  @Override
  public BufferedImage getSprite() {
    return img;
  }
      
}
