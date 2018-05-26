package piece;

import java.util.ArrayList;
import java.util.List;

import piece.GamePiece.Color;
import piece.Position.Direction;

public class PosManage {
  private Position mpos;
  
  public PosManage(Position mpos) {
    this.mpos = mpos;
  }
  
  public List<Position> waysRookPos(Color color) {
    ArrayList<Position> RookPos = new ArrayList<Position>();
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WRook = Direction.WRookD();
      for (int i=0; i< WRook.length; i++) {
        RookPos.addAll(mpos.findPos(WRook[i]));
      }
      return RookPos;
    }
    else {
      Direction[] RRook = Direction.RRookD();
      for (int i=0; i< RRook.length; i++) {
        RookPos.addAll(mpos.findPos(RRook[i]));
      }
    }
      return RookPos;
  }
  
  
}
