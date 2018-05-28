package piece;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class King extends GamePiece{
    King(BufferedImage img, Color color, Position position) {
      super(img, color, PieceType.KING, position);
  }

  // it needs more coding
  @Override
  public ArrayList<Position> getCanMoves() {
    PosManage posM = new PosManage(getPosition());
    ArrayList<Position> KingWay = posM.waysKingPos(color);
    return KingWay;
  }
}
