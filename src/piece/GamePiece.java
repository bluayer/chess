package piece;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ChangminYi.ChessBoard;

/**
 * A abstract class to make piece in Game, so its name is GamePiece
 * 
 * @see Piece(Interface)
 * @see BufferedImage
 * @see ArrayList
 * @author SongJeongWoo
 * @since 2018-05-26
 */

public abstract class GamePiece implements Piece{

  public enum Color {
    WHITE, BLACK, RED, GREEN
  }
	
  public enum PieceType {
    PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING
  }
	
  protected BufferedImage img;
  protected Color color;
  protected PieceType piece;
  private Position mposition;
 
  
  public GamePiece(BufferedImage img, Color color, PieceType piece, Position position) {
    this.img = img;
    this.color = color;
    this.piece = piece;
    this.mposition = position;
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
  
  public abstract Position[] getCanMoves();
  
  @Override
  public GamePiece move(Position goal) {
    ChessBoard.updateTile(this.mposition, goal);
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
  public BufferedImage getSprite() {
    return img;
  }
      
}
