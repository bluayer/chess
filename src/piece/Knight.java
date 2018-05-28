package piece;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Knight extends GamePiece {
  Knight(BufferedImage img, Color color, Position position) {
    super(img, color, PieceType.KNIGHT, position);
  }

  @Override
  public ArrayList<Position> getCanMoves() {
    PosManage posM = new PosManage(getPosition());
    ArrayList<Position> KnightWay = posM.waysKnightPos(color);
    return KnightWay;
  }
}
