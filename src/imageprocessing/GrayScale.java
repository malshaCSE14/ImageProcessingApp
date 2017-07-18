/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imageprocessing;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;


/**
 *
 * @author malsha_h
 */
public class GrayScale {
    BufferedImage image;
    private int width;
    private int height;
    public GrayScale(){
    }

    public String gray() {
        String url = "images/grayscale.jpg";
        try {
//            File input = new File("aaa.jpg");
            File input = new File("images/hair.jpg");
            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();
            for(int i=0; i<height; i++){
                for(int j=0; j<width; j++){
                    Color c = new Color(image.getRGB(j, i));
                    int red = (int)(c.getRed() * 0.299);
                    int green = (int)(c.getGreen() * 0.587);
                    int blue = (int)(c.getBlue() *0.114);
                    Color newColor = new Color(red+green+blue,red+green+blue,red+green+blue);
                    image.setRGB(j,i,newColor.getRGB());
                }
            }          
            File ouptut = new File(url);
            ImageIO.write(image, "jpg", ouptut);
        } catch (Exception e) {}
        
        return url;
    }
}
