package piece;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import piece.GamePiece.*;

/**
 * A Interface for basic Piece
 * 
 * @see GamePiece
 * @see BufferedImage
 * @see ArrayList
 * @author SongJeongWoo
 * @since 2018-05-26
 */

public interface Piece {
  BufferedImage getSprite();
	PieceType getPieceType();
	Color getColor();
	Position getPosition();
	GamePiece move(Position goal);
	Position[] getCanMoves();
	
}
