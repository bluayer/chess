package piece;

import java.awt.image.BufferedImage;
import java.util.List;

import piece.GamePiece.Color;
import piece.GamePiece.PieceType;

public interface Piece {
  BufferedImage getSprite();
	PieceType getPieceType();
	Color getColor();
	Position getPosition();
	GamePiece move(Position goal);
	List<Position> getCanMoves();
}
