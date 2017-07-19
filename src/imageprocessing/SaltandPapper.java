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
public class SaltandPapper {
    public BufferedImage filter(BufferedImage img) {
        double salt = 0.05;
        double pepper =0.05;
        BufferedImage noise_img = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                if (Math.random() < pepper) {
                    noise_img.setRGB(i, j, 0);
                    continue;
                }
                if (Math.random() < salt) {
                    int gray = 255;
                    int rgb = ((gray * 256) + gray) * 256 + gray;
                    noise_img.setRGB(i, j, rgb);
                    continue;
                }
                noise_img.setRGB(i, j, img.getRGB(i, j));
            }
        }
        return noise_img;
    }
}
