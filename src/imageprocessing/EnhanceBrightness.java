/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;



/**
 *
 * @author malsha_h
 */
public class EnhanceBrightness {
   
    static int width;
    static int height;
    static double alpha = 2;
    static double beta = 50;
    public void enhance(){
        try{
            System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
            Mat source = Highgui.imread("images/hair.jpg",
            Highgui.CV_LOAD_IMAGE_COLOR);
            Mat destination=new Mat(source.rows(),source.cols(),
            source.type());
            source.convertTo(destination, -1, alpha, beta);
            Highgui.imwrite("bright.jpg", destination);
        }catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    } 
}
