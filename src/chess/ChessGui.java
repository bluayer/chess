package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import board.ChessBoard;
import board.ImagePanel;
import board.SearchPieceByPos;
import board.Tile;
import piece.Position;
import voice.Speech;


/**
 * @author ChangminYi
 * class about executing program and setting initial screen
 */
public class ChessGui {
  static final int FRAME_WIDTH = 1050, FRAME_HEIGHT = 1000;
  
  private static JButton recog, reset;
  static JFrame mainFrame;
  public static JPanel chessBoard, underBar, forVoice;
  public static JLabel currentTeam, gameStatus, turnCount;
  public static JLabel[] checkLabel = new JLabel[4];
  static ImagePanel btn[][];
  public static Color white = Color.WHITE, gray = Color.GRAY, black = Color.black, red = Color.RED, green = Color.GREEN;
  private static MClickBridge mClkB;
  public static ChessBoard b;
  public static String[] playerName = new String[4];
  static boolean vRecFlag = false;
  
  public static void setupStartUI(){
    mainFrame = new JFrame("Mode Selection");
    mainFrame.setSize(FRAME_WIDTH / 2, FRAME_HEIGHT / 2);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setLayout(new BorderLayout(10, 10));
    mainFrame.setLocationRelativeTo(null);
    
    BackgroundPanel midPanel = new BackgroundPanel();
    midPanel.setGUI();
    
    mainFrame.add(midPanel);
    mainFrame.setVisible(true);
  }
  
  /**
   * set GUI receiving player's name
   */
  public static void setupNameInputGUI() {
    mainFrame = new JFrame("Name Input");
    mainFrame.setSize(FRAME_WIDTH / 2, FRAME_HEIGHT / 2);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JPanel bigPanel = new JPanel();
    bigPanel.setLayout(new GridLayout(5, 1));
    JPanel[] textPanel = new JPanel[playerName.length + 1];
    for(int i = 0; i < textPanel.length; i++) {
      textPanel[i] = new JPanel();
      textPanel[i].setLayout(new GridLayout(1, 2));
      textPanel[i].setBorder(new EmptyBorder(25, 20, 25, 20));
      bigPanel.add(textPanel[i]);
    }
    JTextField[] inputName = new JTextField[playerName.length];
    for(int i = 0; i < inputName.length; i++) {
      inputName[i] = new JTextField();
      switch(i) {
      case 0:
        textPanel[i].add(new JLabel("Player White's name"));
        break;
      case 1:
        textPanel[i].add(new JLabel("Player Red's name"));
        break;
      case 2:
        textPanel[i].add(new JLabel("Player Black's name"));
        break;
      case 3:
        textPanel[i].add(new JLabel("Player Green's name"));
      }
      textPanel[i].add(inputName[i]);
    }
    
    JButton inputEnter = new JButton("Start");
    inputEnter.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        mainFrame.setVisible(false);
        
        for(int i = 0; i < inputName.length; i++) {
          playerName[i] = inputName[i].getText();
          if(playerName[i].length() == 0) {
            switch(i) {
            case 0:
              playerName[i] = new String("White");
              break;
            case 1:
              playerName[i] = new String("Red");
              break;
            case 2:
              playerName[i] = new String("Black");
              break;
            case 3:
              playerName[i] = new String("Green");
              break;
            }
          }
          System.out.println(playerName[i]);
        }
        
        b = new ChessBoard();
        mClkB = new MClickBridge();
        setup2vs2ChessGUI();
        printChessBoard();
      }
    });
    textPanel[4].add(inputEnter);
    
    mainFrame.add(bigPanel);
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
   contentPane.setLayout(new BorderLayout(10, 10));
   
   chessBoard = new JPanel(); 
   chessBoard.setLayout(new GridLayout(14, 14));
   chessBoard.setBackground(white);
   chessBoard.setBorder(new EmptyBorder(30, 30, 30, 30));
   
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

   underBar = new JPanel();
   underBar.setLayout(new BorderLayout(10, 10));
   underBar.setBorder(new EmptyBorder(20, 150, 20, 150));
   currentTeam = new JLabel(playerName[0] + "'s turn");
   gameStatus = new JLabel("Test gameStatus");
   underBar.add(currentTeam, BorderLayout.WEST);
   underBar.add(gameStatus, BorderLayout.EAST);
   
   if(vRecFlag == true) {
     forVoice = new JPanel();
     forVoice.setLayout(new FlowLayout());
     forVoice.setBorder(new EmptyBorder(0, 50, 0, 50));
     JLabel firstLabel = new JLabel("first Position");
     JLabel secondLabel = new JLabel("Second Position");
     recog = new JButton("Rec.");
     recog.addActionListener(new ActionListener() {
      @Override
      //for voice recognition: not completely written
      public void actionPerformed(ActionEvent e) {
        int flag = 0;
        Position wholeFirstPos = null;
        
        if(MouseClick.isClicked) {
          System.out.println("Record Button Clicked");
          
          first:
          while (true) {
            Position firstPos = null;
            
            try {
              firstPos = Speech.recognition();
            } catch (IOException e1) {
              e1.printStackTrace();
            }
            
            if (SearchPieceByPos.searchPiece(firstPos, b) != null) {
              MouseClick.firstClickSetup(firstPos.getX(), firstPos.getY());
              System.out.println("Piece is" + SearchPieceByPos.searchPiece(firstPos, b).getPieceType());
              System.out.println("X : " +  firstPos.getX() + " Y : " + firstPos.getY());
              wholeFirstPos = firstPos;
              break;
            } 
            else {
              System.out.println("voice is unvalid");
              continue first;
            }
          }
          
          firstLabel.setText("first Position : " + "(" + wholeFirstPos.getX() + "," + wholeFirstPos.getY() + ")" ); 
          firstLabel.repaint();
          flag = 0; //  flag媛� 0 �씠硫� break�븯怨� �걹�굹嫄곕굹 searchPiece�븯怨� 寃곌낵 媛� �븞�굹�솕�쓣 �븣 flag 1濡� 珥덇린�솕�븯怨�, flag瑜� 0�쑝濡� 珥덇린�솕�븯硫� continue
        }
        else {
          second:
          while(true) {
            Position secondPos = null;
            
            try {
              secondPos = Speech.recognition();
            } catch (IOException e1) {
              e1.printStackTrace();
            }
            
            System.out.println("secondPos is Okay");
            System.out.println("secondPos X : " + secondPos.getX() + " secondPos Y : " + secondPos.getY());
            
            Tile secondTile = b.getcBoard()[secondPos.getX()][secondPos.getY()];
            
            System.out.println(secondTile.isOnPiece());
            
            if (secondTile.isOnPiece() == false) {
              if(flag == 0) {
                for (int i =0; i < SearchPieceByPos.searchPiece(wholeFirstPos, b).getCanMoves().length; i++) {
                  if (SearchPieceByPos.searchPiece(wholeFirstPos, b).getCanMoves()[i].getX() == secondPos.getX() &&
                      SearchPieceByPos.searchPiece(wholeFirstPos, b).getCanMoves()[i].getY() == secondPos.getY()) {
                    MouseClick.secondClickSetup(secondPos.getX(), secondPos.getY());
                    System.out.println("Il-Hi");
                    secondLabel.setText("second Position : " + "(" + secondPos.getX() + "," + secondPos.getY() + ")" );
                    break second;
                  }
               }
               flag = 1;
             }
             if(flag == 1) {
                flag = 0; 
                continue second;
              }
            } 
            else {
              System.out.println("Second voice is unvalid");
              continue second;
            }
          }
        }  
      }
    });
     reset = new JButton("Reset");
     reset.addActionListener(new ActionListener() {
      @Override
      //for voice reset
      public void actionPerformed(ActionEvent e) {
        System.out.println("Reset Button Clicked");
        MouseClick.varsClear();
      }
    });
     
     forVoice.add(firstLabel);
     forVoice.add(secondLabel);
     forVoice.add(recog);
     forVoice.add(reset);
  
     underBar.add(forVoice, BorderLayout.CENTER);
   }
   
   JPanel panel1 = new JPanel();
   panel1.setLayout(new GridLayout(0, 1));
   panel1.setBorder(new EmptyBorder(0, 20, 0, 20));

   turnCount = new JLabel("Turn: 1");
   panel1.add(turnCount);
   
   for(int i = 0; i < checkLabel.length; i++) {
     checkLabel[i] = new JLabel();
     panel1.add(checkLabel[i]);
   }

   contentPane.add(chessBoard, BorderLayout.CENTER);
   contentPane.add(panel1, BorderLayout.LINE_END);
   contentPane.add(underBar, BorderLayout.SOUTH);

   mainFrame.setVisible(true);
   
   return;
 }
 
  public static void main(String[] args) throws IOException {
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
