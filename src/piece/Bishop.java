package piece;

import java.awt.image.BufferedImage;
import java.util.List;

public class Bishop extends GamePiece{
  public Bishop(Color color, Position position, BufferedImage img) {
    super(color, PieceType.BISHOP, position, img);
  }

  @Override
  public List<Position> getCanMoves() {
    PosManage posM = new PosManage(getPosition());
    List<Position> BishopWay = posM.waysBishopPos(color);
    return BishopWay;
  }
}
