package piece;

import java.awt.image.BufferedImage;
import java.util.List;

public class Pawn extends GamePiece{
    Pawn(BufferedImage img, Color color, Position position) {
      super(img, color, PieceType.PAWN, position);
  }

  @Override
  public List<Position> getCanMoves() {
    PosManage posM = new PosManage(getPosition());
    List<Position> PawnWay = posM.waysPawnPos(color);
    return PawnWay;
  }
}
