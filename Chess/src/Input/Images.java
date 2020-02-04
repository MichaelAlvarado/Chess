package Input;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Images {

    public static BufferedImage[] backgrounds;
    public static BufferedImage Title;
    public static BufferedImage PieceSprite;
    
    //White Pieces
    public static BufferedImage WPawn;
    public static BufferedImage WRook;
    public static BufferedImage WBishop;
    public static BufferedImage WKnight;
    public static BufferedImage WQueen;
    public static BufferedImage WKing;
    //Black Pieces
    public static BufferedImage BPawn;
    public static BufferedImage BRook;
    public static BufferedImage BBishop;
    public static BufferedImage BKnight;
    public static BufferedImage BQueen;
    public static BufferedImage BKing;
    
    public Images() {

        backgrounds = new BufferedImage[1];
       
        try {
        
            
            //backgrounds[0] = ImageIO.read(getClass().getResourceAsStream("res/Pieces/Test.png")); //not working
            backgrounds[0] = ImageIO.read(new File("res/Logo/Logo.jpg"));
            PieceSprite = ImageIO.read((new File("res/Pieces/ChessPieces.png")));
           
            //White Pieces
            WRook = PieceSprite.getSubimage(0, 0, 132, 132);
            WKnight = PieceSprite.getSubimage(132, 0, 132, 132);
            WBishop = PieceSprite.getSubimage(264, 0, 132, 132);
            WQueen = PieceSprite.getSubimage(396, 0, 132, 132);
            WKing = PieceSprite.getSubimage(528, 0, 132, 132);
            WPawn = PieceSprite.getSubimage(660, 0, 132, 132);
            //Black Pieces
            BRook = PieceSprite.getSubimage(0, 132, 132, 132);
            BKnight = PieceSprite.getSubimage(132, 132, 132, 132);
            BBishop = PieceSprite.getSubimage(264, 132, 132, 132);
            BQueen = PieceSprite.getSubimage(396, 132, 132, 132);
            BKing = PieceSprite.getSubimage(528, 132, 132, 132);
            BPawn = PieceSprite.getSubimage(660, 132, 132, 132);
        }catch (IOException e) {
        e.printStackTrace();
    }


    }

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Images.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }   
    
    public static BufferedImage tint(BufferedImage src, float r, float g, float b) {

        // Copy image ( who made that so complicated :< )
        BufferedImage newImage = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TRANSLUCENT);
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(src, 0, 0, null);
        graphics.dispose();

        // Color image
        for (int i = 0; i < newImage.getWidth(); i++) {
            for (int j = 0; j < newImage.getHeight(); j++) {
                int ax = newImage.getColorModel().getAlpha(newImage.getRaster().getDataElements(i, j, null));
                int rx = newImage.getColorModel().getRed(newImage.getRaster().getDataElements(i, j, null));
                int gx = newImage.getColorModel().getGreen(newImage.getRaster().getDataElements(i, j, null));
                int bx = newImage.getColorModel().getBlue(newImage.getRaster().getDataElements(i, j, null));
                rx *= r;
                gx *= g;
                bx *= b;
                newImage.setRGB(i, j, (ax << 24) | (rx << 16) | (gx << 8) | (bx << 0));
            }
        }
        return newImage;
    }

}
