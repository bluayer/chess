package gamestate;

import board.ChessBoard;
import board.SearchPieceByPos;
import chess.ChessGui;
import piece.GamePiece;
import piece.GamePiece.Color;
import piece.Position;

/**
 * This is the class to count turns.
 * 
 * @see MouseClick
 * @author Yeoilgoo
 * @since 2018-05-29
 */

public class TurnCheck {
	
	private static int mturn;
	private ChessBoard board = ChessGui.b;
	GamePiece nowPiece;
	private Color nowTurn;
	
	public TurnCheck(){
	  mturn = 0;
	}
	
	public int getter() {
		return (mturn % 4);
	}
	
	public void nextTurn() {
		mturn++;
		
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
	public static Color getTurn() {
		if((mturn % 4) == 0) {
			return Color.WHITE;
		}
		else if((mturn % 4) == 1) {
			return Color.RED;
		}
		else if((mturn % 4) == 2) {
			return Color.BLACK;
		}
		else {
			return Color.GREEN;
		}
	}
	
	public boolean isValidTurn(TurnCheck nowTurn, Position pos) {
	  
	  if(ChessGui.b.getcBoard()[pos.getX()][pos.getY()].isOnPiece()) {
	    nowPiece = SearchPieceByPos.searchPiece(pos, board);
	  }
	  
	  Color nowColor = nowPiece.getColor();
	  System.out.println("isValidTurn: get color " + nowColor);
	  
	  switch(nowColor) {
  	  case WHITE :
  	    this.nowTurn = Color.WHITE;
  	    break;
  	  case BLACK :
  	    this.nowTurn = Color.BLACK;
  	    break;
  	  case RED :
  	    this.nowTurn = Color.RED;
  	    break;
  	  case GREEN :
  	    this.nowTurn = Color.GREEN;
  	    break;
	  }
	  
	  if(this.nowTurn == nowTurn.getTurn()) {
	    return true;
	  }
	  else {
	    System.out.println("isValidTurn: It's " + nowTurn.getTurn() + "'s turn");
	    return false;
	  }
	}

}