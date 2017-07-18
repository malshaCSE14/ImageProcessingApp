/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author malsha_h
 */
public class Sharpen {
    public void sharpen(){
        try{
            System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
            Mat source = Highgui.imread("digital_image_processing.jpg",
            Highgui.CV_LOAD_IMAGE_COLOR);
            Mat destination = new
            Mat(source.rows(),source.cols(),source.type());
            Imgproc.GaussianBlur(source, destination, new Size(0,0), 10);
            Core.addWeighted(source, 1.5, destination, -0.5, 0, destination);
            Highgui.imwrite("sharp.jpg", destination);
        }catch (Exception e) {
 }

    }
    
}

