package piece;

import java.awt.image.BufferedImage;
import java.util.List;


public class Queen extends GamePiece{
  Queen(BufferedImage img, Color color, Position position) {
    super(img, color, PieceType.QUEEN, position);
  }

  @Override
  public List<Position> getCanMoves() {
    PosManage posM = new PosManage(getPosition());
    List<Position> QueenWay = posM.waysQueenPos(color);
    return QueenWay;
  }
}
