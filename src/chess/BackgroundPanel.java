package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class BackgroundPanel extends JPanel{
  private static final long serialVersionUID = -7792037863415649086L;
  private BufferedImage img;
  
  public BackgroundPanel() {
    setOpaque(true);
    
    try {
      img = ImageIO.read(new File("mainBackground.jpg"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
  
  public void setGUI() {
    JLabel initialString = new JLabel(/*"Chess Game"*/);
    setLayout(new BorderLayout(10, 10));
    setBorder(new EmptyBorder(100 , 100, 100, 100));
    
    JPanel buttonPanel = new JPanel();
    buttonPanel.setOpaque(false);
    buttonPanel.setLayout(new GridLayout(3, 1));
    buttonPanel.add(initialString);
    
    JPanel voiceBtnPanel = new JPanel();
    voiceBtnPanel.setOpaque(false);
    voiceBtnPanel.setLayout(new BorderLayout());
    voiceBtnPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
    
    JPanel twoBtnPanel = new JPanel();
    twoBtnPanel.setOpaque(false);
    twoBtnPanel.setLayout(new BorderLayout());
    twoBtnPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
    
    JButton voiceRec= new JButton("Voice Recongnition");
    JButton twoVStwo = new JButton("2 VS 2 Chess");
    class UIclick implements MouseListener{
      @Override
      public void mouseClicked(MouseEvent e) {
        ChessGui.mainFrame.setVisible(false);
        
        if(e.getSource().equals(voiceRec)) {
          ChessGui.setupNameInputGUI();
          ChessGui.vRecFlag = true;
        }
        else if(e.getSource().equals(twoVStwo)) {
          ChessGui.setupNameInputGUI();
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
    

    Color pink = new Color(255,160,154);
    Color red = new Color(165, 8, 33);
    
    voiceRec.setBackground(pink);
    twoVStwo.setBackground(pink);
    voiceRec.setForeground(Color.WHITE);
    twoVStwo.setForeground(Color.WHITE);
    voiceRec.setBorder(new LineBorder(red));
    twoVStwo.setBorder(new LineBorder(red));
    
    voiceRec.addMouseListener(new UIclick());
    twoVStwo.addMouseListener(new UIclick());
    
    voiceRec.setOpaque(true);
    twoVStwo.setOpaque(true);
    voiceBtnPanel.add(voiceRec);
    twoBtnPanel.add(twoVStwo);
    
    buttonPanel.add(voiceBtnPanel, BorderLayout.NORTH);
    buttonPanel.add(twoBtnPanel, BorderLayout.SOUTH);
    
    add(buttonPanel);
  }
  
  public BufferedImage getBackgroundImage() {
    return img;
  }
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(img, 0, 0, this);
  }
}
