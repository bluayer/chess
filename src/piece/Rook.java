package piece;

import java.util.List;

public class Rook extends GamePiece{
  Rook(Color color, Position position) {
    super(color, PieceType.ROOK, position);
  }

  @Override
  public List<Position> getCanMoves() {
    PosManage posM = new PosManage(getPosition());
    List<Position> RookWay = posM.waysRookPos(color);
    return RookWay;
  }
}
