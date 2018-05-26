package chess;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author ¿Ã√¢πŒ
 * class about drawing screen
 */
public class Screen {
	static JFrame frame;
	static JPanel piecePanel;
	static ChessPieceSprite cps = ChessPieceSprite.getInstace();
	static ImagePanel[][] img = new ImagePanel[14][14];
	static Tile[][] chessBoard;
	
	public Screen() {
		frame = new JFrame("Chess Board");
		piecePanel = new JPanel();
		chessBoard = ChessBoard.getInstance();
		
		for(int i = 0; i < 14; i++) {
			for(int j = 0; j < 14; j++) {
				if(chessBoard[i][j].onPiece) {
					img[i][j] = new ImagePanel();
					
					switch(chessBoard[i][j].team) {
					case BLACK:
						img[i][j].setImage(cps.getChessPiece(ChessPieceSprite.ChessPieceSpriteType.BLACK_PAWN));
						break;
					case WHITE:
						img[i][j].setImage(cps.getChessPiece(ChessPieceSprite.ChessPieceSpriteType.WHITE_PAWN));
						break;
					case RED:
						img[i][j].setImage(cps.getChessPiece(ChessPieceSprite.ChessPieceSpriteType.RED_PAWN));
						break;
					case GREEN:
						img[i][j].setImage(cps.getChessPiece(ChessPieceSprite.ChessPieceSpriteType.GREEN_PAWN));
						break;
					default:
					}
				}
				else {
					img[i][j] = null;
				}
			}
		}
	}
	
	
	/**
	 * printScreen
	 * drawing GUI on screen
	 */
	public static void printScreen() {
		frame.setSize(1200, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(20, 20));
		frame.setLocationRelativeTo(null);
		
		piecePanel.setSize(700, 700);
		piecePanel.setLayout(new GridLayout(14, 14));
		for(int i = 0; i < 14; i++) {
			for(int j = 0; j < 14; j++) {
				if(img[i][j] == null) {	//∫Û ¿⁄∏Æ ∂´ªßøÎ πˆ∆∞: ≥™¡ﬂø° ¥Ÿ∏• ªÁ¡¯¿∏∑Œ √§øˆ ≥÷æÓæﬂµ 
					piecePanel.add(new JButton("empty"));
				}
				else {
					piecePanel.add(img[i][j]);
				}
			}
		}
		
		frame.setVisible(true);
	}
}
