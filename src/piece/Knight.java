package piece;

import java.awt.image.BufferedImage;
import java.util.List;

public class Knight extends GamePiece {
  public Knight(Color color, Position position, BufferedImage img) {
    super(color, PieceType.KNIGHT, position, img);
  }

  @Override
  public List<Position> getCanMoves() {
    PosManage posM = new PosManage(getPosition());
    List<Position> KnightWay = posM.waysKnightPos(color);
    return KnightWay;
  }
}
