package gamestate;

import board.ChessBoard;
import board.SearchPieceByPos;
import board.Status.TEAM;
import chess.ChessGui;
import piece.GamePiece;
import piece.GamePiece.Color;
import piece.Position;

/**
 * This is the class to count turns.
 * 
 * @author Yeoilgoo
 * @since 2018-05-29
 * 
 */

public class TurnCheck {
	
	private static int mturn;
	private ChessBoard board = ChessGui.b;
	GamePiece nowPiece;
	
	public TurnCheck(){
	  this.mturn = 0;
	}
	
	public int getter() {
		return (mturn % 4);
	}
	
	public void nextTurn() {
		this.mturn++;
		
		ChessGui.currentTeam.setText(ChessGui.playerName[this.getter()] + "'s turn");
		ChessGui.turnCount.setText("Turn: " + (mturn + 1));
    if(this.getter() == 0) {
      ChessGui.chessBoard.setBackground(ChessGui.white);
    }
    else if(this.getter() == 1){
      ChessGui.chessBoard.setBackground(ChessGui.red);
    }
    else if(this.getter() == 2){
      ChessGui.chessBoard.setBackground(ChessGui.black);
    }
    else {
      ChessGui.chessBoard.setBackground(ChessGui.green);
    }
		
		return;
	}
	
	/**
	 * 
	 * @return Return color that which player's turn
	 */
	public TEAM getTurn() {
		if(this.getter() == 0)
			return TEAM.WHITE;
		else if(this.getter() == 1)
			return TEAM.RED;
		else if(this.getter() == 2)
			return TEAM.BLACK;
		else
			return TEAM.GREEN;
	}
	
	public static Color getTurnColor() {
	   if((mturn % 4) == 0)
	      return Color.WHITE;
	    else if((mturn % 4) == 1)
	      return Color.RED;
	    else if((mturn % 4) == 2)
	      return Color.BLACK;
	    else
	      return Color.GREEN;
	}
	
	public boolean isValidTurn(TurnCheck nowTurn, Position pos) {
	  
	  if(ChessGui.b.getcBoard()[pos.getX()][pos.getY()].isOnPiece()) {
	    nowPiece = SearchPieceByPos.searchPiece(pos, board);
	  }
	  
	  Color nowColor = nowPiece.getColor();
	  TEAM nowTEAM = null;
	  
	  switch(nowColor) {
	  case WHITE :
	    nowTEAM = TEAM.WHITE;
	    break;
	  case BLACK :
	    nowTEAM = TEAM.BLACK;
	    break;
	  case RED :
	    nowTEAM = TEAM.RED;
	    break;
	  case GREEN :
	    nowTEAM = TEAM.GREEN;
	    break;
	  }
	  
	  if(nowTEAM == nowTurn.getTurn()) {
	    return true;
	  }
	  else {
	    System.out.println("isValidTurn: It's " + nowTurn.getTurn() + "'s turn");
	    return false;
	  }
	}

}