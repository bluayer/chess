package piece;

import java.awt.image.BufferedImage;
import java.util.List;


public class Queen extends GamePiece{
  Queen(Color color, Position position, BufferedImage img) {
    super(color, PieceType.QUEEN, position, img);
  }

  @Override
  public List<Position> getCanMoves() {
    PosManage posM = new PosManage(getPosition());
    List<Position> QueenWay = posM.waysQueenPos(color);
    return QueenWay;
  }
}
