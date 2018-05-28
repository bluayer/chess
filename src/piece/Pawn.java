package piece;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Pawn extends GamePiece{
    Pawn(BufferedImage img, Color color, Position position) {
      super(img, color, PieceType.PAWN, position);
  }

  @Override
  public ArrayList<Position> getCanMoves() {
    PosManage posM = new PosManage(getPosition());
    ArrayList<Position> PawnWay = posM.waysPawnPos(color);
    return PawnWay;
  }
}
