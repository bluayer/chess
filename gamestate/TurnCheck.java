package gamestate;

import board.Status.TEAM;

/**
 * This is the class to count turns.
 * 
 * @author Yeoilgoo
 * @since 2018-05-29
 */

public class TurnCheck {
	
	private int mturn;
	
	public TurnCheck(){
	  this.mturn = 0;
	}
	
	private int getter() {
		return (mturn % 4);
	}
	
	public void nextTurn() {
		this.mturn++;
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

}