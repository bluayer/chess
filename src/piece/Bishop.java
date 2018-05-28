package piece;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Bishop extends GamePiece{
  Bishop(BufferedImage img, Color color, Position position) {
    super(img, color, PieceType.BISHOP, position);
  }

  @Override
  public ArrayList<Position> getCanMoves() {
    PosManage posM = new PosManage(getPosition());
    ArrayList<Position> BishopWay = posM.waysBishopPos(color);
    return BishopWay;
  }
}
