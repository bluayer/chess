package gamecstate;

import ChangminYi.Tile;

/**
 * This is the class to count turns.
 * 
 * @author Yeoilgoo
 * @since 2018-05-29
 */

public class TurnCheck {
	
	private static int mturn = 1;
	
	void nextTurn() {
		mturn ++;
		return;
	}
	/**
	 * 
	 * @return Return color that which player's turn
	 */
	
	public static int getTurn() {
		if(mturn % 4 == 1)
			return Tile.cvtTeam(Tile.TEAM.WHITE);
		else if(mturn % 4 == 2)
			return Tile.cvtTeam(Tile.TEAM.RED);
		else if(mturn % 4 == 3)
			return Tile.cvtTeam(Tile.TEAM.BLACK);
		else
			return Tile.cvtTeam(Tile.TEAM.GREEN);
	}
	
	
}
