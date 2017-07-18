/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
public class Flip {
    public BufferedImage flipVertical(BufferedImage src){
        
     AffineTransform tx=AffineTransform.getScaleInstance(-1.0,1.0);  //scaling
     tx.translate(-src.getWidth(),0);  //translating
     AffineTransformOp tr=new AffineTransformOp(tx,null);  //transforming
     
     return tr.filter(src, null);  //filtering
    }
    
    public BufferedImage flipHorizontal(BufferedImage src){
     AffineTransform tx=AffineTransform.getScaleInstance(1.0,-1.0);  //scaling
     tx.translate(0,-src.getHeight());  //translating
     AffineTransformOp tr=new AffineTransformOp(tx,null);  //transforming
     
     return tr.filter(src, null);  //filtering
    }
    public String vertical() throws IOException{
        Flip obj=new Flip();
     BufferedImage src = ImageIO.read(new File("images/hair.jpg"));  //reading image
     BufferedImage dest=obj.flipVertical(src);  //flipping vertically
     ImageIO.write(dest,"jpeg",new File("images/flipVer.jpg"));
     return "images/flipVer.jpg";
    }
    public String horizontal(){
        try {
            Flip obj=new Flip();
            BufferedImage src=ImageIO.read(new File("images/hair.jpg"));  //reading image  //flipping vertically
            
            BufferedImage dest=obj.flipHorizontal(src);  //flipping horizontally
            ImageIO.write(dest,"jpeg",new File("images/flipHor.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Flip.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "images/flipHor.jpg";
    }
    
//    public static void main(String[] args)throws Exception {
//     FlipImage obj=new FlipImage();
//     BufferedImage src=ImageIO.read(new File("src.jpeg"));  //reading image
//     BufferedImage dest=obj.flipVertical(src);  //flipping vertically
//     ImageIO.write(dest,"jpeg",new File("dest_flipVer.jpg"));
//     dest=obj.flipHorizontal(src);  //flipping horizontally
//     ImageIO.write(dest,"jpeg",new File("dest_flipHor.jpg"));
//     dest=obj.flipVertical(dest); //flupping both ways
//     ImageIO.write(dest,"jpeg",new File("dest_flipBoth.jpg"));
//    }
}