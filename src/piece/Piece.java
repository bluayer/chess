package piece;

import java.util.List;

import piece.GamePiece.Color;
import piece.GamePiece.PieceType;

public interface Piece {
	PieceType getPieceType();
	Color getColor();
	Position getPosition();
	GamePiece move(Position goal);
	List<Position> getCanMoves();
}
