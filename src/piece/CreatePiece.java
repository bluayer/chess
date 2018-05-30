package piece;

import java.awt.image.BufferedImage;

import board.ChessPieceSprite;
import board.ChessPieceSprite.ChessPieceSpriteType;
import piece.GamePiece.Color;

public class CreatePiece {
  public static Piece NOPE(Position position) {
    return new Nope(position);
  }
  
  public static GamePiece WPawn(Position position) {
    BufferedImage Wpawn = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_PAWN);
    return new Pawn(Wpawn, Color.WHITE, position);
  }
  
  public static GamePiece WRook(Position position) {
    BufferedImage Wrook = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_LOOK);
    return new Rook(Wrook, Color.WHITE, position);
  }
  
  public static GamePiece WKnight(Position position) {
    BufferedImage Wknight = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_KNIGHT);
    return new Knight(Wknight, Color.WHITE, position);
  }
  
  public static GamePiece WBishop(Position position) {
    BufferedImage Wbishop = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_BISHOP);
    return new Bishop(Wbishop, Color.WHITE, position);
  }
  
  public static GamePiece WQueen(Position position) {
    BufferedImage Wqueen = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_QUEEN);
    return new Queen(Wqueen, Color.WHITE, position);
  }
  
  public static GamePiece WKing(Position position) {
    BufferedImage Wking = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.WHITE_KING);
    return new King(Wking, Color.WHITE, position);
  }
  
  public static GamePiece BPawn(Position position) {
    BufferedImage Bpawn = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_PAWN);
    return new Pawn(Bpawn, Color.BLACK, position);
  }
  
  public static GamePiece BRook(Position position) {
    BufferedImage Brook = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_LOOK);
    return new Rook(Brook, Color.BLACK, position);
  }
  
  public static GamePiece BBishop(Position position) {
    BufferedImage Bbishop = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_BISHOP);
    return new Bishop(Bbishop, Color.BLACK, position);
  }
  
  public static GamePiece BKnight(Position position) {
    BufferedImage Bknight = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_KNIGHT);
    return new Knight(Bknight, Color.BLACK, position);
  }
  
  public static GamePiece BQueen(Position position) {
    BufferedImage Bqueen = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_QUEEN);
    return new Queen(Bqueen, Color.BLACK, position);
  }
  
  public static GamePiece BKing(Position position) {
    BufferedImage Bking = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.BLACK_KING);
    return new King(Bking, Color.BLACK, position);
  }
  
  public static GamePiece RPawn(Position position) {
    BufferedImage Rpawn = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_PAWN);
    return new Pawn(Rpawn, Color.RED, position);
  }
  
  public static GamePiece RRook(Position position) {
    BufferedImage Rrook = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_LOOK);
    return new Rook(Rrook, Color.RED, position);
  }
  
  public static GamePiece RKnight(Position position) {
    BufferedImage Rknight = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_KNIGHT);
    return new Knight(Rknight, Color.RED, position);
  }
  
  public static GamePiece RBishop(Position position) {
    BufferedImage Rbishop = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_BISHOP);
    return new Bishop(Rbishop, Color.RED, position);
  }
  
  public static GamePiece RQueen(Position position) {
    BufferedImage Rqueen = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_QUEEN);
    return new Queen(Rqueen, Color.RED, position);
  }
  
  public static GamePiece RKing(Position position) {
    BufferedImage Rking = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.RED_KING);
    return new King(Rking, Color.RED, position);
  }
  
  public static GamePiece GPawn(Position position) {
    BufferedImage Gpawn = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_PAWN);
    return new Pawn(Gpawn, Color.GREEN, position);
  }
  
  public static GamePiece GRook(Position position) {
    BufferedImage Grook = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_LOOK);
    return new Rook(Grook, Color.GREEN, position);
  }
  
  public static GamePiece GKnight(Position position) {
    BufferedImage Gknight = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_KNIGHT);
    return new Knight(Gknight, Color.GREEN, position);
  }
  
  public static GamePiece GBishop(Position position) {
    BufferedImage Gbishop = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_BISHOP);
    return new Bishop(Gbishop, Color.GREEN, position);
  }
  
  public static GamePiece GQueen(Position position) {
    BufferedImage Gqueen = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_QUEEN);
    return new Queen(Gqueen, Color.GREEN, position);
  }
  
  public static GamePiece GKing(Position position) {
    BufferedImage Gking = ChessPieceSprite.getInstace().getChessPiece(ChessPieceSpriteType.GREEN_KING);
    return new King(Gking, Color.GREEN, position);
  }
  
}
