package voice;

import java.io.IOException;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import piece.Position;

public class Speech {

  public static Position recognition() throws IOException {
      Position pos = null;
      Configuration configuration = new Configuration();
      configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
      configuration.setDictionaryPath("src/speechDIC.dic");
      configuration.setLanguageModelPath("src/speechLM.lm");
      LiveSpeechRecognizer recognize = new LiveSpeechRecognizer(configuration);
      recognize.startRecognition(true);
      System.out.println("Recognize start");
      SpeechResult speechResult;
      
      again :
      while ((speechResult = recognize.getResult()) != null) {
        String command = speechResult.getHypothesis();
        if (countWords(command) == 2) {
          pos = change(command);
          System.out.println(pos.getX());
          System.out.println(pos.getY());
          
          if (pos.isValid() != true) {
            System.out.println("It's not valid.");
            System.out.println(command);
            continue again;
          }
        break;
        }
      }
      recognize.stopRecognition();
      System.out.println("Recognize stop");
      return pos;
  }
  
  public static int countWords(String speech)
  {
    int cnt = 0;
    for (int i = 0; i < speech.length() - 1; i++)
    {
      if (speech.charAt(i) == ' ' && speech.charAt(i+1) != ' ')
        cnt++;
    }
    cnt++;
    return cnt;
  }

  public static Position change(String speech)
  {
    String[] word = speech.split(" ");
    int[] location = new int[2];
    Position position = new Position(0, 0);
    
    
    for (int i = 0; i < 2; i++)
    {
      switch(word[i]) {
        case "ZERO":
          location[i] = 0;
          break;
        case "ONE":
          location[i] = 1;
          break;
        case "TWO":
          location[i] = 2;
          break;
        case "THREE":
          location[i] = 3;
          break;
        case "FOUR":
          location[i] = 4;
          break;
        case "FIVE":
          location[i] = 5;
          break;
        case "SIX":
          location[i] = 6;
          break;
        case "SEVEN":
          location[i] = 7;
          break;
        case "EIGHT":
          location[i] = 8;
          break;
        case "NINE":
          location[i] = 9;
          break;
        case "TEN":
          location[i] = 10;
          break;
        case "ELEVEN":
          location[i] = 11;
          break;
        case "TWELVE":
          location[i] = 12;
          break;
        case "THIRTEEN":
          location[i] = 13;
          break;
        default:
          break;
      }
    }
    
    for (int i=0; i < 2; i++) {
      System.out.println("location " + i + " : " + location[i]);
    }
    
    position.setX(location[0]);
    position.setY(location[1]);
    return position;
  }
}
