package board;

import piece.GamePiece.PieceType;

/**
 * @author ¿Ã√¢πŒ
 * interface about moving on tile
 */
interface Moving {
  public void setActive(boolean tf);
  public void setOnPiece(boolean onPiece);
  public void setOccupyPiece(PieceType pc);
}
