/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import javax.imageio.*;
/**
 *
 * @author malsha_h
 */
//public class MedianFilter {
//    public BufferedImage filter(BufferedImage image1) throws IOException{
////        File f=new File("images/hair.jpg");                               //Input Photo File
//        Color[] pixel=new Color[9];
//        int[] R=new int[9];
//        int[] B=new int[9];
//        int[] G=new int[9];
//        File output=new File("images/median.jpg");
////        BufferedImage img=ImageIO.read(f);
//        BufferedImage img =image1;
//        BufferedImage outImg =ImageIO.read(output);
//        for(int i=1;i<img.getWidth()-1;i++)
//            for(int j=1;j<img.getHeight()-1;j++)
//            {
//               pixel[0]=new Color(img.getRGB(i-1,j-1));
//               pixel[1]=new Color(img.getRGB(i-1,j));
//               pixel[2]=new Color(img.getRGB(i-1,j+1));
//               pixel[3]=new Color(img.getRGB(i,j+1));
//               pixel[4]=new Color(img.getRGB(i+1,j+1));
//               pixel[5]=new Color(img.getRGB(i+1,j));
//               pixel[6]=new Color(img.getRGB(i+1,j-1));
//               pixel[7]=new Color(img.getRGB(i,j-1));
//               pixel[8]=new Color(img.getRGB(i,j));
//               for(int k=0;k<9;k++){
//                   R[k]=pixel[k].getRed();
//                   B[k]=pixel[k].getBlue();
//                   G[k]=pixel[k].getGreen();
//               }
//               Arrays.sort(R);
//               Arrays.sort(G);
//               Arrays.sort(B);
//          
//               img.setRGB(i,j,new Color(R[4],B[4],G[4]).getRGB());
//            }
//        ImageIO.write(img,"jpg",output);
//       
//        return outImg;
//    }
//    
//    }
   
public class MedianFilter {
    public BufferedImage filter(BufferedImage image1) throws IOException{
//        File f=new File("images/hair.jpg");                               //Input Photo File
        Color[] pixel=new Color[9];
        int[] R=new int[9];
        int[] B=new int[9];
        int[] G=new int[9];
        File output=new File("images/median.jpg");
        BufferedImage outImg=ImageIO.read(output);
//        BufferedImage img=ImageIO.read(f);
        BufferedImage img = image1;
        for(int i=1;i<img.getWidth()-1;i++)
            for(int j=1;j<img.getHeight()-1;j++)
            {
               pixel[0]=new Color(img.getRGB(i-1,j-1));
               pixel[1]=new Color(img.getRGB(i-1,j));
               pixel[2]=new Color(img.getRGB(i-1,j+1));
               pixel[3]=new Color(img.getRGB(i,j+1));
               pixel[4]=new Color(img.getRGB(i+1,j+1));
               pixel[5]=new Color(img.getRGB(i+1,j));
               pixel[6]=new Color(img.getRGB(i+1,j-1));
               pixel[7]=new Color(img.getRGB(i,j-1));
               pixel[8]=new Color(img.getRGB(i,j));
               for(int k=0;k<9;k++){
                   R[k]=pixel[k].getRed();
                   B[k]=pixel[k].getBlue();
                   G[k]=pixel[k].getGreen();
               }
               Arrays.sort(R);
               Arrays.sort(G);
               Arrays.sort(B);
          
               img.setRGB(i,j,new Color(R[4],B[4],G[4]).getRGB());
            }
        ImageIO.write(img,"jpg",output);
        return outImg;
    }
    }