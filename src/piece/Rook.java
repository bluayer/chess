package piece;

import java.awt.image.BufferedImage;
import java.util.List;


public class Rook extends GamePiece{
  Rook(BufferedImage img, Color color, Position position) {
    super(img, color, PieceType.ROOK, position);
  }

  @Override
  public List<Position> getCanMoves() {
    PosManage posM = new PosManage(getPosition());
    List<Position> RookWay = posM.waysRookPos(color);
    return RookWay;
  }
}