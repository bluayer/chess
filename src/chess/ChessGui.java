package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import board.ChessBoard;
import board.ImagePanel;
import board.SearchPieceByPos;
import board.Tile;
import gamestate.GameController;
import gamestate.TurnCheck;
import piece.Position;
import voice.Speech;


/**
 * @author Yi Changmin
 * class about executing program and setting initial screen
 */
public class ChessGui {
  static final int FRAME_WIDTH = 1050, FRAME_HEIGHT = 1000;
  
  private static JButton recog;
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
  static Position wholeFirstPos = null;
  private static JPanel statusPanels = null;
  
  
  /**
   * starting screen
   */
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
    gameStatus = new JLabel();
    underBar.add(currentTeam, BorderLayout.WEST);
    underBar.add(gameStatus, BorderLayout.EAST);
   
    
    //creating Voice Recognition Button
    if(vRecFlag == true) {
      forVoice = new JPanel();
      forVoice.setLayout(new FlowLayout());
      forVoice.setBorder(new EmptyBorder(0, 50, 0, 50));
      JLabel firstLabel = new JLabel("first Position   ");
      JLabel secondLabel = new JLabel("Second Position");
      recog = new JButton("Rec.");
      recog.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          System.out.println("Record Button Clicked");
          int flag = 0;
          
          if( !MouseClick.isClicked ) {
            first:
            while (true) {
              Position firstPos = null;
              try {
                firstPos = Speech.recognition();
              } catch (IOException e1) {
                e1.printStackTrace();
              }
              Tile firstTile = b.getcBoard()[firstPos.getX()][firstPos.getY()];
              
              if(firstTile.isOnPiece() == true) {
                if (SearchPieceByPos.searchPiece(firstPos, b) != null) {
                  if(SearchPieceByPos.searchPiece(firstPos, b).getCanMoves().length !=0) {
                    if (SearchPieceByPos.searchPiece(firstPos, b).getColor() == TurnCheck.getTurn()) {
                      MouseClick.firstClickSetup(firstPos.getX(), firstPos.getY());
                      System.out.println("Piece is" + SearchPieceByPos.searchPiece(firstPos, b).getPieceType());
                      System.out.println("X : " +  firstPos.getX() + " Y : " + firstPos.getY());
                      wholeFirstPos = firstPos;
                      break;
                    }
                    else {
                      gameStatus.setText("It's not your Piece! Try again.");
                      continue first;
                    }
                  }
                  else {
                    gameStatus.setText("This Piece can't move anyway! Try again.");
                    continue first;
                  }
                } 
                else {
                  gameStatus.setText("There is no Piece in firstPos and it's null");
                  continue first;
                }
              }
              else {
                gameStatus.setText("There is no Piece in firstPos");
                continue first;
              }
            }
            
            firstLabel.setText("first Position : " + "(" + wholeFirstPos.getX() + "," + wholeFirstPos.getY() + ")" ); 
            firstLabel.repaint();
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
               
              
              
              if(flag == 0) {
                if(SearchPieceByPos.searchPiece(wholeFirstPos, b).getCanMoves().length !=0 ) {
                  for (int i =0; i < SearchPieceByPos.searchPiece(wholeFirstPos, b).getCanMoves().length; i++) {
                    if (SearchPieceByPos.searchPiece(wholeFirstPos, b).getCanMoves()[i].getX() == secondPos.getX() &&
                        SearchPieceByPos.searchPiece(wholeFirstPos, b).getCanMoves()[i].getY() == secondPos.getY()) {
                      MouseClick.secondClickSetup(secondPos.getX(), secondPos.getY());
                      System.out.println("�씪-�븯 ");
                      secondLabel.setText("second Position : " + "(" + secondPos.getX() + "," + secondPos.getY() + ")" );
                      break second;
                    }
                  }
                }  
                flag = 1;
              }
              if(flag == 1) {
                 flag = 0; 
                 System.out.println("You can't move that Position. Try again");
                 continue second;
              }
            } 
          }  
        }
      });
     
      forVoice.add(firstLabel);
      forVoice.add(secondLabel);
      forVoice.add(recog);
  
      underBar.add(forVoice, BorderLayout.CENTER);
    }
   
    statusPanels = new JPanel();

    statusPanels.setLayout(new GridLayout(5, 1));
    statusPanels.setBorder(new EmptyBorder(0, 30, 0, 30));

    turnCount = new JLabel("Turn 1");
    statusPanels.add(turnCount);
   
    for(int i = 0; i < checkLabel.length; i++) {
      checkLabel[i] = new JLabel("");
      checkLabel[i].setOpaque(true);
      checkLabel[i].setForeground(Color.black);
      statusPanels.add(checkLabel[i]);
    }

    contentPane.add(chessBoard, BorderLayout.CENTER);
    contentPane.add(statusPanels, BorderLayout.LINE_END);
    contentPane.add(underBar, BorderLayout.SOUTH);

    mainFrame.setVisible(true);
   
    return;
  }
  
  
  /**
   * showing win/lose screen
   */
  public static void printResultScreen() {
    //checkmate
    if(GameController.checkmateFlag[0] == 1 && GameController.checkmateFlag[2] == 1) {
      JFrame endGame = new JFrame("Checkmate!");
      endGame.setSize(FRAME_WIDTH / 2, FRAME_HEIGHT / 2);
      endGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      endGame.setLocationRelativeTo(mainFrame);
      endGame.setLayout(new BorderLayout(0, 0));
      
      JPanel cont = new JPanel();
      cont.setOpaque(true);
      cont.setLayout(new BorderLayout(0, 0));
      cont.setBorder(new EmptyBorder(150, 150, 150, 150));
      
      JPanel center = new JPanel();
      center.setOpaque(true);
      center.setLayout(new BorderLayout(20, 20));
      center.setBorder(new EmptyBorder(20, 20, 20, 20));
      center.add(new JLabel("Team " + playerName[1] + " and " + playerName[3] + " win!"), BorderLayout.NORTH);
      JButton exit = new JButton("Exit");
      exit.setOpaque(true);
      exit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
         System.exit(0); 
        }
      });
      center.add(exit);
      
      cont.add(center);
      endGame.add(cont);
      endGame.setVisible(true);
    }
    //checkmate
    else if(GameController.checkmateFlag[1] == 1 && GameController.checkmateFlag[3] == 1) { 
      JFrame endGame = new JFrame("Checkmate!");
      endGame.setSize(FRAME_WIDTH / 2, FRAME_HEIGHT / 2);
      endGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      endGame.setLocationRelativeTo(mainFrame);
      endGame.setLayout(new BorderLayout(0, 0));
      
      JPanel cont = new JPanel();
      cont.setOpaque(true);
      cont.setLayout(new BorderLayout(0, 0));
      cont.setBorder(new EmptyBorder(150, 150, 150, 150));
      
      JPanel center = new JPanel();
      center.setOpaque(true);
      center.setLayout(new BorderLayout(20, 20));
      center.setBorder(new EmptyBorder(20, 20, 20, 20));
      center.add(new JLabel("Team " + playerName[0] + " and " + playerName[2] + " win!"), BorderLayout.NORTH);
      JButton exit = new JButton("Exit");
      exit.setOpaque(true);
      exit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
         System.exit(0); 
        }
      });
      center.add(exit);
      
      cont.add(center);
      endGame.add(cont);
      endGame.setVisible(true);
    }
    //stalemate
    else if((GameController.stalemateFlag[0] == 1 && GameController.stalemateFlag[2] == 1) || (GameController.stalemateFlag[1] == 1 && GameController.stalemateFlag[3] == 1)) {
     JFrame endGame = new JFrame("Stalemate!");
     endGame.setSize(FRAME_WIDTH / 2, FRAME_HEIGHT / 2);
     endGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     endGame.setLocationRelativeTo(mainFrame);
     endGame.setLayout(new BorderLayout(0, 0));
     
     JPanel cont = new JPanel();
     cont.setOpaque(true);
     cont.setLayout(new BorderLayout(0, 0));
     cont.setBorder(new EmptyBorder(150, 150, 150, 150));
     
     JPanel center = new JPanel();
     center.setOpaque(true);
     center.setLayout(new BorderLayout(20, 20));
     center.setBorder(new EmptyBorder(20, 20, 20, 20));
     center.add(new JLabel("It is stalemate, so draw!"), BorderLayout.NORTH);
     JButton exit = new JButton("Exit");
     exit.setOpaque(true);
     exit.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent arg0) {
        System.exit(0); 
       }
     });
     center.add(exit);
     
     cont.add(center);
     endGame.add(cont);
     endGame.setVisible(true);
    }
    else {
      //not yet any status (including stalemate-checkmate)
      return;
    }
  }
  
  
  /**
   * updating status gui at right side of chess screen
   */
  public static void printStatusBar() {
    //printing check status
    for(int i = 0; i < 4; i++) {
      if(GameController.checkFlag[i] == 1) {
        checkLabel[i].setText(playerName[i] + ": Check!");
      }
      else {
        checkLabel[i].setText(null);
      }
      checkLabel[i].repaint();
    }
    //printing checkmate status
    for(int i = 0; i < 4; i++) {
      if(GameController.checkmateFlag[i] == 1) {
        checkLabel[i].setText(playerName[i] + ": Checkmate!");
      }
      else {
        checkLabel[i].setText(null);
      }
      checkLabel[i].repaint();
    }
    //printing stalemate status
    for(int i = 0; i < 4; i++) {
      if(GameController.stalemateFlag[i] == 1) {
        checkLabel[i].setText(playerName[i] + ": Stalemate!");
      }
      else {
        checkLabel[i].setText(null);
      }
      checkLabel[i].repaint();
    }
  }
  
  
  /**
   * just main method, calls setupStartUI
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    setupStartUI();
    return;
  }
  
  
}
