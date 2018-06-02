package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import board.ChessBoard;
import board.ImagePanel;
import board.SearchPieceByPos;
import piece.Position;

public class ChessGui {
  /**
   * macros about btn[i][j], team and piece
   */
  static final int BLACK = 0, WHITE = 1, RED = 2, GREEN = 3;
  static final int PAWN = 0, KNIGHT = 1, ROOK = 2, BISHOP = 3, QUEEN = 4, KING = 5;
  
  static JFrame mainFrame;
  static JPanel chessBoard;
  static ImagePanel btn[][];
  static Color white, gray, black;
  static MClickBridge mClkB;
  public static ChessBoard b;
  
  
  /**
   * setting initial screen
   */
 public static void setupGUI() {   
   white = new Color(255, 255, 255);   //Color: white (for buttons)
   gray = new Color(160, 160, 160);    //Color: gray (for buttons)
   black = new Color(0, 0, 0);         //Color: black (for inactive area)
   mainFrame = new JFrame("Test Frame");
   chessBoard = new JPanel();
   btn = new ImagePanel[b.getcBoard().length][b.getcBoard().length];
   
   mainFrame.setSize(1000, 1000);
   mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   mainFrame.setLayout(new BorderLayout(10, 10));
   mainFrame.setLocationRelativeTo(null);
   
   chessBoard.setLayout(new GridLayout(14, 14));
   chessBoard.setBackground(new Color(127, 127, 127));
   chessBoard.setBorder(new EmptyBorder(50, 50, 50, 50));
   
   for(int i = 0; i < b.getcBoard().length; i++) {
     for(int j = 0; j < b.getcBoard().length; j++) {
       btn[i][j] = new ImagePanel();
       btn[i][j].setOpaque(true);
       btn[i][j].addMouseListener(mClkB);
       
       if( !b.getcBoard()[i][j].getActive() ) {
         btn[i][j].setBackground(black);
       }
       else {
         btn[i][j].setBackground(((i + j) % 2 == 0)? white: gray);
         
         if(b.getcBoard()[i][j].isOnPiece()) {
           btn[i][j].setImage(SearchPieceByPos.searchPiece(new Position(i, j), b).getSprite());
         }
       }
       
       chessBoard.add(btn[i][j]);
       btn[i][j].repaint();
     }
   }
   
   mainFrame.repaint();
   mainFrame.add(chessBoard);
   mainFrame.setVisible(true);
   
   return;
 }
 

  public static void main(String[] args) {
    b = new ChessBoard();
    mClkB = new MClickBridge();
    setupGUI();
    
    printChessBoard();
    return;
  }

  public static void printChessBoard() {
    for(int i = 0; i < 14; i++) {
      for(int j = 0; j < 14; j++) {
        
        if(b.getcBoard()[i][j].getActive() == false) {
          System.out.print("  ");
        }
        else {
          if(b.getcBoard()[i][j].isOnPiece() == false) {
            System.out.print(". ");
          }
          else {
            switch(b.getcBoard()[i][j].getOccupyPiece()) {
            case PAWN:
              System.out.print("P ");
              break;
            case KNIGHT:
              System.out.print("N ");
              break;
            case BISHOP:
              System.out.print("B ");
              break;
            case ROOK:
              System.out.print("R ");
              break;
            case QUEEN:
              System.out.print("Q ");
              break;
            case KING:
              System.out.print("K ");
            }
          }
        }
      }
      
      System.out.println();
    }
    System.out.println("----------------------------------");
    return;
  }

  
}
