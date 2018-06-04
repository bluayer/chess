package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import board.ChessBoard;
import board.ImagePanel;
import board.SearchPieceByPos;
import piece.Position;


/**
 * @author ChangminYi
 * class about executing program and setting initial screen
 */
public class ChessGui {
  static final int FRAME_WIDTH = 1000, FRAME_HEIGHT = 1000;
  
  private static JFrame mainFrame;
  public static JPanel chessBoard;
  static ImagePanel btn[][];
  public static Color white = Color.WHITE, gray = Color.GRAY, black = Color.black, red = Color.RED, green = Color.GREEN;
  private static MClickBridge mClkB;
  public static ChessBoard b;
  
  public static void setupStartUI() {
    mainFrame = new JFrame("Selection Mode");
    mainFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setLayout(new BorderLayout());
    mainFrame.setLocationRelativeTo(null);
    
    JPanel midPanel = new JPanel();
    midPanel.setLayout(new FlowLayout());
    midPanel.setBorder(new EmptyBorder(400, 200, 400, 200));
    midPanel.setBackground(new Color(230, 230, 230));
    midPanel.setOpaque(true);
    
    JButton oneVSone = new JButton("1 vs 1");
    JButton twoVStwo = new JButton("2 VS 2");
    class UIclick implements MouseListener{
      @Override
      public void mouseClicked(MouseEvent e) {
        mainFrame.setVisible(false);
        
        if(e.getSource().equals(oneVSone)) {
          System.out.println("We didn't make 1 vs 1 Chess!");
        }
        else if(e.getSource().equals(twoVStwo)) {
          b = new ChessBoard();
          mClkB = new MClickBridge();
          setup2vs2ChessGUI();
          printChessBoard();
        }
      }
      @Override
      public void mousePressed(MouseEvent e) {
        
      }
      @Override
      public void mouseReleased(MouseEvent e) {
        
      }
      @Override
      public void mouseEntered(MouseEvent e) {
        
      }
      @Override
      public void mouseExited(MouseEvent e) {
         
      }
    }
    
    oneVSone.setBackground(Color.GRAY);
    twoVStwo.setBackground(Color.GRAY);
    oneVSone.addMouseListener(new UIclick());
    twoVStwo.addMouseListener(new UIclick());
    
    midPanel.add(oneVSone);
    midPanel.add(twoVStwo);
    mainFrame.add(midPanel);
    mainFrame.setVisible(true);
  }
  
  /**
   * setting 2 vs 2 chess screen
   */
 public static void setup2vs2ChessGUI() {   
   mainFrame = new JFrame("2 vs 2 Chess");
   mainFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
   mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   mainFrame.setLocationRelativeTo(null);
   
   Container contentPane = mainFrame.getContentPane();
   contentPane.setLayout(new BorderLayout());
   
   chessBoard = new JPanel(); 
   chessBoard.setLayout(new GridLayout(14, 14));
   chessBoard.setBackground(white);
   chessBoard.setBorder(new EmptyBorder(50, 50, 50, 50));
   
   btn = new ImagePanel[b.getcBoard().length][b.getcBoard().length];
   
   for(int i = 0; i < b.getcBoard().length; i++) {
     for(int j = 0; j < b.getcBoard().length; j++) {
       btn[i][j] = new ImagePanel();
       btn[i][j].setOpaque(true);
       btn[i][j].addMouseListener(mClkB);
       
       if( !b.getcBoard()[i][j].getActive() ) {
         btn[i][j].setBackground(Color.DARK_GRAY);
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

   JPanel panel1 = new JPanel();
   panel1.setLayout(new BorderLayout());


   JLabel JLabel1 = new JLabel("JLabel1");
   JLabel JLabel2 = new JLabel("JLabel2");
   JLabel JLabel3 = new JLabel("JLabel3");
   JLabel JLabel4 = new JLabel("JLabel4");
   JLabel JLabel5 = new JLabel("JLabel5");

   panel1.add(JLabel1, BorderLayout.NORTH);
   panel1.add(JLabel2, BorderLayout.SOUTH);
   panel1.add(JLabel3, BorderLayout.WEST);
   panel1.add(JLabel4, BorderLayout.EAST);
   panel1.add(JLabel5, BorderLayout.CENTER);

   contentPane.add(chessBoard, BorderLayout.CENTER);
   contentPane.add(panel1, BorderLayout.LINE_END);

   mainFrame.setVisible(true);
   
   return;
 }
 
  public static void main(String[] args) {
    setupStartUI();

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
            default:
              break;
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
