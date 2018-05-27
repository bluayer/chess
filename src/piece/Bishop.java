package piece;

import java.awt.image.BufferedImage;
import java.util.List;

public class Bishop extends GamePiece{
  Bishop(BufferedImage img, Color color, Position position) {
    super(img, color, PieceType.BISHOP, position);
  }

  @Override
  public List<Position> getCanMoves() {
    PosManage posM = new PosManage(getPosition());
    List<Position> BishopWay = posM.waysBishopPos(color);
    return BishopWay;
  }
}
