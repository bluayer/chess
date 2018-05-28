package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ChangminYi.ChessBoard;
import ChangminYi.ChessPieceSprite;
import ChangminYi.ChessPieceSprite.ChessPieceSpriteType;
import ChangminYi.ImagePanel;

public class ChessGui {
  /**
   * macros about img[i][j], team and piece
   */
  static final int BLACK = 0, WHITE = 1, RED = 2, GREEN = 3;
  static final int PAWN = 0, KNIGHT = 1, ROOK = 2, BISHOP = 3, QUEEN = 4, KING = 5;
  
  static JFrame mainFrame;
  static JPanel chessBoard;
  static ImagePanel img[][];
  static JButton btn[][];
  static Color white, gray, black;
  
  static ChessBoard b = new ChessBoard();
  
  /**
   * allocating image to img[i][j]
   */
  public static void setupImg() {
    img[BLACK][PAWN].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_PAWN));
    img[BLACK][KNIGHT].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_KNIGHT));
    img[BLACK][ROOK].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_LOOK));
    img[BLACK][BISHOP].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_BISHOP));
    img[BLACK][QUEEN].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_QUEEN));
    img[BLACK][KING].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_KING));

    img[WHITE][PAWN].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_PAWN));
    img[WHITE][KNIGHT].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_KNIGHT));
    img[WHITE][ROOK].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_LOOK));
    img[WHITE][BISHOP].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_BISHOP));
    img[WHITE][QUEEN].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_QUEEN));
    img[WHITE][KING].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_KING));
    
    img[RED][PAWN].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_PAWN));
    img[RED][KNIGHT].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_KNIGHT));
    img[RED][ROOK].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_LOOK));
    img[RED][BISHOP].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_BISHOP));
    img[RED][QUEEN].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_QUEEN));
    img[RED][KING].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_KING));

    img[GREEN][PAWN].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_PAWN));
    img[GREEN][KNIGHT].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_KNIGHT));
    img[GREEN][ROOK].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_LOOK));
    img[GREEN][BISHOP].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_BISHOP));
    img[GREEN][QUEEN].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_QUEEN));
    img[GREEN][KING].setImage(ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_KING));
    
    return;
  }
  
  /**
   * setting button's icon. size converted.
   */
  public static void setButtonIcon() {
    BufferedImage bufimg = null;
    Image dimg;
    ImageIcon imgicn;
    for(int i = 0; i < 14; i++) {
      for(int j = 0; j < 14; j++) {
        //pawn setting
        if(i == 1 && (j < 11 && j > 2)) {
          bufimg = img[BLACK][PAWN].getImage();
        }
        else if(i == 12 && (j < 11 && j > 2)) {
          bufimg = img[WHITE][PAWN].getImage();
        }
        else if(j == 1 && (i < 11 && i > 2)) {
          bufimg = img[RED][PAWN].getImage();
        }
        else if(j == 12 && (i < 11 && i > 2)){
          bufimg = img[GREEN][PAWN].getImage();
        }

        //bishop setting
        else if(i == 0 && (j == 5 || j == 8)) {
          bufimg = img[BLACK][BISHOP].getImage();
        }
        else if(i == 13 && (j == 5 || j == 8)) {
          bufimg = img[WHITE][BISHOP].getImage();
        }
        else if(j == 0 && (i == 5 || i == 8)) {
          bufimg = img[RED][BISHOP].getImage();
        }
        else if(j == 13 && (i == 5 || i == 8)) {
          bufimg = img[GREEN][BISHOP].getImage();
        }
        
        //kinght setting
        else if(i == 0 && (j == 4 || j == 9)) {
          bufimg = img[BLACK][KNIGHT].getImage();
        }
        else if(i == 13 && (j == 4 || j == 9)) {
          bufimg = img[WHITE][KNIGHT].getImage();
        }
        else if(j == 0 && (i == 4 || i == 9)) {
          bufimg = img[RED][KNIGHT].getImage();
        }
        else if(j == 13 && (i == 4 || i == 9)) {
          bufimg = img[GREEN][KNIGHT].getImage();
        }
        
        //rook setting
        else if(i == 0 && (j == 3 || j == 10)) {
          bufimg = img[BLACK][ROOK].getImage();
        }
        else if(i == 13 && (j == 3 || j == 10)) {
          bufimg = img[WHITE][ROOK].getImage();
        }
        else if(j == 0 && (i == 3 || i == 10)) {
          bufimg = img[RED][ROOK].getImage();
        }
        else if(j == 13 && (i == 3 || i == 10)) {
          bufimg = img[GREEN][ROOK].getImage();
        }
        
        //queen setting
        else if(i == 0 && j == 7) {
          bufimg = img[BLACK][QUEEN].getImage();
        }
        else if(i == 13 && j == 6) {
          bufimg = img[WHITE][QUEEN].getImage();
        }
        else if(j == 0 && i == 7) {
          bufimg = img[RED][QUEEN].getImage();
        }
        else if(j == 13 && i == 6) {
          bufimg = img[GREEN][QUEEN].getImage();
        }
        
        //king setting
        else if(i == 0 && j == 6) {
          bufimg = img[BLACK][KING].getImage();
        }
        else if(i == 13 && j == 7) {
          bufimg = img[WHITE][KING].getImage();
        }
        else if(j == 0 && i == 6) {
          bufimg = img[RED][KING].getImage();
        }
        else if(j == 13 && i == 7) {
          bufimg = img[GREEN][KING].getImage();
        }
        
        //empty grid
        else {
          continue;
        }
        
        dimg = bufimg.getScaledInstance(btn[0][0].getWidth(), btn[0][0].getHeight(), Image.SCALE_SMOOTH);
        imgicn = new ImageIcon(dimg);
        btn[i][j].setIcon(imgicn);
      }
    }
  }
  
  /**
   * setting initial screen
   */
 public static void setupGUI() {   
   white = new Color(255, 255, 255);   //Color: white (for buttons)
   gray = new Color(160, 160, 160);    //Color: gray (for buttons)
   black = new Color(0, 0, 0);         //Color: black (for inactive area)
   mainFrame = new JFrame("Test Frame");
   chessBoard = new JPanel();
   img = new ImagePanel[4][6];
   for(int i = 0; i < 4; i++) {
     for(int j = 0; j < 6; j++) {
       img[i][j] = new ImagePanel();
     }
   }
   setupImg();
   
   //creating and setting Buttons
   btn = new JButton[14][14];
   MouseClick clk = new MouseClick(btn, b);
   for(int i = 0; i < 14; i++) {
     for(int j = 0; j < 14; j++) {
       btn[i][j] = new JButton();
       btn[i][j].setBorderPainted(false);
       
       if((i < 3 && j < 3) || (i < 3 && j > 10) || (i > 10 && j < 3) || (i > 10 && j > 10)) {    //inactive area
         btn[i][j].setBackground(black);
       }
       else {    //setting ochre pattern and adding event handler
         if((i % 2 == 1 && j % 2 == 0) || (i % 2 == 0 && j % 2 == 1)) {
           btn[i][j].setBackground(white);
         }
         else {
           btn[i][j].setBackground(gray);
         }
         
         btn[i][j].addActionListener(clk);
       }
     }
   }
   chessBoard.setLayout(new GridLayout(14, 14));
   chessBoard.setBackground(new Color(127, 127, 127));
   chessBoard.setBorder(new EmptyBorder(50, 50, 50, 50));
   
   mainFrame.setSize(1000, 1000);
   mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   mainFrame.setLayout(new BorderLayout(10, 10));
   mainFrame.setLocationRelativeTo(null);
   
   for(int i = 0; i < 14; i++) {
     for(int j = 0; j < 14; j++) {
       chessBoard.add(btn[i][j]);
     }
   }
   mainFrame.add(chessBoard);
   mainFrame.setVisible(true);
   
   setButtonIcon();
   
   return;
 }
 

  public static void main(String[] args) {
    setupGUI();
  }

}
