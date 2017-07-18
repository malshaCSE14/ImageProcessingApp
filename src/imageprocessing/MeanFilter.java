/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author malsha_h
 */
public class MeanFilter {
    public String filter() throws IOException{
       
        BufferedImage img = ImageIO.read(new File("images/hair.jpg"));

//get dimensions
        int maxHeight = img.getHeight();
        int maxWidth = img.getWidth();

//create 2D Array for new picture
int pictureFile[][] = new int [maxHeight][maxWidth];
for( int i = 0; i < maxHeight; i++ ){
    for( int j = 0; j < maxWidth; j++ ){
        pictureFile[i][j] = img.getRGB( j, i );
    }
}

//Apply Mean Filter
for (int v=1; v<=maxHeight-2; v++) {
    for (int u=1; u<=maxWidth-2; u++) {
        //compute filter result for position (u,v)

        int sum = 0;
        for (int j=-1; j<=1; j++) {
            for (int i=-1; i<=1; i++) {
                try{
                    int p = pictureFile[u+i][v+j];
                    sum = sum + p;
                }catch(ArrayIndexOutOfBoundsException e){
                }
                
                

            }
        }

        int q = (int) (sum / 9);
        try{pictureFile[u][v] = q;}catch(ArrayIndexOutOfBoundsException e){}
        
    }
}

//Turn the 2D array back into an image
BufferedImage theImage = new BufferedImage(
    maxHeight, 
    maxWidth, 
    BufferedImage.TYPE_BYTE_GRAY);
int value;
for(int y = 0; y<maxHeight; y++){
    for(int x = 0; x<maxWidth; x++){

        value = pictureFile[y][x] ;
        try{theImage.setRGB(x, y, value);}catch(ArrayIndexOutOfBoundsException e){}
        
    }
}

File outputfile = new File("saved.jpg");
ImageIO.write(theImage, "png", outputfile);
        return "saved.jpg";
  
}
}
    
