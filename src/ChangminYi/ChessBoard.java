package ChangminYi;

import ChangminYi.ChessPieceSprite.ChessPieceSpriteType;
import piece.*;
import piece.GamePiece.Color;

/**
 * @author ¿Ã√¢πŒ
 * Class about chess board, and moving pieces
 */
public class ChessBoard extends Tile{
	/**
	 * Board is two-dimensional Tile Object array
	 */
	Tile[][] cBoard;
	
	/**
	 * arrays about initial pieces
	 * can access by using cvtTeam()
	 * Pawn is not built yet
	 * king, queen is only one per team, so one-dimensional
	 * pawn is 5-column
	 * else are 2-column
	 */
	//static Pawn[][] pawn = new Pawn[4][5];
	static Knight[][] knight = new Knight[4][2];
	static Bishop[][] bishop = new Bishop[4][2];
	static Rook[][] rook = new Rook[4][2];
	static Queen[] queen = new Queen[4];
	static King[] king = new King[4];
	
	/**
	 * black team: top side, 0
	 * white team: bottom side, 1
	 * red team: left side, 2
	 * green team: right side, 3
	 */
	public ChessBoard() {
		//initializing pieces
		/*
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 5; j++) {
				pawn[i][j] = new Pawn();
			}
		}*/
		for(int i = 0; i < 4; i++) {
			switch(i) {
			case 0:	//black
				for(int j = 0; j < 2; j++) {
					bishop[i][j] = new Bishop(Color.BLACK, new Position(5 + 3 * j, 0),  ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_BISHOP));
					knight[i][j] = new Knight(Color.BLACK, new Position(4 + 5 * j, 0),  ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_KNIGHT));
					rook[i][j] = new Rook(Color.BLACK, new Position(3 + 7 * j, 0),  ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_LOOK));
				}
				queen[i] = new Queen(Color.BLACK, new Position(7, 0),  ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_QUEEN));
				king[i] = new King(/*parameters*/);
				break;
			case 1:	//white
				for(int j = 0; j < 2; j++) {
					bishop[i][j] = new Bishop(Color.WHITE, new Position(5 + 3 * j, 13),  ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_BISHOP));
					knight[i][j] = new Knight(Color.WHITE, new Position(4 + 5 * j, 13),  ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_KNIGHT));
					rook[i][j] = new Rook(Color.WHITE, new Position(3 + 7 * j, 13),  ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_LOOK));
				}
				queen[i] = new Queen(Color.WHITE, new Position(6, 13),  ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_QUEEN));
				king[i] = new King(/*parameters*/);
				break;
			case 2:	//red
				for(int j = 0; j < 2; j++) {
					bishop[i][j] = new Bishop(Color.RED, new Position(0, 5 + 3 * j),  ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_BISHOP));
					knight[i][j] = new Knight(Color.RED, new Position(0, 4 + 5 * j),  ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_KNIGHT));
					rook[i][j] = new Rook(Color.RED, new Position(0, 3 + 7 * j),  ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_LOOK));
				}
				queen[i] = new Queen(Color.RED, new Position(0, 7),  ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_QUEEN));
				king[i] = new King(/*parameters*/);
				break;
			case 3:	//green
				for(int j = 0; j < 2; j++) {
					bishop[i][j] = new Bishop(Color.GREEN, new Position(13, 5 + 3 * j),  ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_BISHOP));
					knight[i][j] = new Knight(Color.GREEN, new Position(13, 4 + 5 * j),  ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_KNIGHT));
					rook[i][j] = new Rook(Color.GREEN, new Position(13, 3 + 7 * j),  ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_LOOK));
				}
				queen[i] = new Queen(Color.GREEN, new Position(13, 6),  ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_QUEEN));
				king[i] = new King(/*parameters*/);
			}
		}
		
		
		for(int i = 0; i < cBoard.length; i++) {
			for(int j = 0; j < cBoard.length; j++) {
				//inactive tile creation
				if(((0 <= i && i <= 2) || (11 <= i && i <= 13)) && ((0 <= j && j <= 2) || (11 <= j && j <= 13))) {
					cBoard[i][j] = new Tile(false);
				}
				else {	//active tile creation
					cBoard[i][j] = new Tile(true);
				}
			}
		}
		
		//initializing BLACK, RED team's onPiece
		for(int i = 0; i <= 1; i++) {
			for(int j = 3; j <= 10; j++) {
				cBoard[i][j].onPiece = true;
				cBoard[j][i].onPiece = true;
			}
		}
		//initializing WHITE, GREEN team's onPiece
		for(int i = 12; i <= 13; i++) {
			for(int j = 3; j <= 10; j++) {
				cBoard[i][j].onPiece = true;
				cBoard[j][i].onPiece = true;
			}
		}
	}
	
	public Tile[][] getInstance() {
		return this.cBoard;
	}
}
