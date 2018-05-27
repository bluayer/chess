package piece;

import java.awt.image.BufferedImage;
import java.util.List;

public class Knight extends GamePiece {
  Knight(BufferedImage img, Color color, Position position) {
    super(img, color, PieceType.KNIGHT, position);
  }

  @Override
  public List<Position> getCanMoves() {
    PosManage posM = new PosManage(getPosition());
    List<Position> KnightWay = posM.waysKnightPos(color);
    return KnightWay;
  }
}
