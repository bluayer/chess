package piece;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Rook extends GamePiece{
  Rook(BufferedImage img, Color color, Position position) {
    super(img, color, PieceType.ROOK, position);
  }

  @Override
  public ArrayList<Position> getCanMoves() {
    PosManage posM = new PosManage(getPosition());
    ArrayList<Position> RookWay = posM.waysRookPos(color);
    return RookWay;
  }
}