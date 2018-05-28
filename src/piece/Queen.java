package piece;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Queen extends GamePiece{
  Queen(BufferedImage img, Color color, Position position) {
    super(img, color, PieceType.QUEEN, position);
  }

  @Override
  public ArrayList<Position> getCanMoves() {
    PosManage posM = new PosManage(getPosition());
    ArrayList<Position> QueenWay = posM.waysQueenPos(color);
    return QueenWay;
  }
}
