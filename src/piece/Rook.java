package piece;

import java.awt.image.BufferedImage;
import java.util.List;


public class Rook extends GamePiece{
  public Rook(Color color, Position position, BufferedImage img) {
    super(color, PieceType.ROOK, position, img);
  }

  @Override
  public List<Position> getCanMoves() {
    PosManage posM = new PosManage(getPosition());
    List<Position> RookWay = posM.waysRookPos(color);
    return RookWay;
  }
}
