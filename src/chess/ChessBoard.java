package chess;

public class ChessBoard {
	Tile[][] board;
	
	public ChessBoard() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				//inactive tile creation
				if(((0 <= i && i <= 2) || (11 <= i && i <= 13)) && ((0 <= j && j <= 2) || (11 <= j && j <= 13))) {
					board[i][j] = new Tile(false);
				}
				else {	//active tile creation
					board[i][j] = new Tile(true);
				}
			}
		}
	}
	
}
