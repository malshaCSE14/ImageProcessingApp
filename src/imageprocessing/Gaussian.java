/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Gaussian{
    public BufferedImage ProcessImage() throws IOException {
        BufferedImage image = ImageIO.read(new File("images/hair2.jpg"));
    int width = image.getWidth();
    int height = image.getHeight();

    int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);
    int[] changedPixels = new int[pixels.length];

    FastGaussianBlur(pixels, changedPixels, width, height, 2);

    BufferedImage newImage = new BufferedImage(width, height, image.getType());
    newImage.setRGB(0, 0, width, height, changedPixels, 0, width);
        System.out.println(newImage.getPropertyNames());
    return newImage;
}

private void FastGaussianBlur(int[] source, int[] output, int width, int height, int radius) {
    ArrayList<Integer> gaussianBoxes = CreateGausianBoxes(radius, 3);
    BoxBlur(source, output, width, height, (gaussianBoxes.get(0) - 1) / 2);
    BoxBlur(output, source, width, height, (gaussianBoxes.get(1) - 1) / 2);
    BoxBlur(source, output, width, height, (gaussianBoxes.get(2) - 1) / 2);
}

private ArrayList<Integer> CreateGausianBoxes(double sigma, int n) {
    double idealFilterWidth = Math.sqrt((12 * sigma * sigma / n) + 1);

    int filterWidth = (int) Math.floor(idealFilterWidth);

    if (filterWidth % 2 == 0) {
        filterWidth--;
    }

    int filterWidthU = filterWidth + 2;

    double mIdeal = (12 * sigma * sigma - n * filterWidth * filterWidth - 4 * n * filterWidth - 3 * n) / (-4 * filterWidth - 4);
    double m = Math.round(mIdeal);

    ArrayList<Integer> result = new ArrayList<>();

    for (int i = 0; i < n; i++) {
        result.add(i < m ? filterWidth : filterWidthU);
    }

    return result;
}

private void BoxBlur(int[] source, int[] output, int width, int height, int radius) {
    System.arraycopy(source, 0, output, 0, source.length);
    BoxBlurHorizontal(output, source, width, height, radius);
    BoxBlurVertical(source, output, width, height, radius);
}

private void BoxBlurHorizontal(int[] sourcePixels, int[] outputPixels, int width, int height, int radius) {
    int resultingColorPixel;
    float iarr = 1f / (radius + radius);
    for (int i = 0; i < height; i++) {
        int outputIndex = i * width;
        int li = outputIndex;
        int sourceIndex = outputIndex + radius;

        int fv = Byte.toUnsignedInt((byte) sourcePixels[outputIndex]);
        int lv = Byte.toUnsignedInt((byte) sourcePixels[outputIndex + width - 1]);
        float val = (radius) * fv;

        for (int j = 0; j < radius; j++) {
            val += Byte.toUnsignedInt((byte) (sourcePixels[outputIndex + j]));
        }

        for (int j = 0; j < radius; j++) {
            val += Byte.toUnsignedInt((byte) sourcePixels[sourceIndex++]) - fv;
            resultingColorPixel = Byte.toUnsignedInt(((Integer) Math.round(val * iarr)).byteValue());
            outputPixels[outputIndex++] = (0xFF << 24) | (resultingColorPixel << 16) | (resultingColorPixel << 8) | (resultingColorPixel);
        }

        for (int j = (radius + 1); j < (width - radius); j++) {
            val += Byte.toUnsignedInt((byte) sourcePixels[sourceIndex++]) - Byte.toUnsignedInt((byte) sourcePixels[li++]);
            resultingColorPixel = Byte.toUnsignedInt(((Integer) Math.round(val * iarr)).byteValue());
            outputPixels[outputIndex++] = (0xFF << 24) | (resultingColorPixel << 16) | (resultingColorPixel << 8) | (resultingColorPixel);
        }

        for (int j = (width - radius); j < width; j++) {
            val += lv - Byte.toUnsignedInt((byte) sourcePixels[li++]);
            resultingColorPixel = Byte.toUnsignedInt(((Integer) Math.round(val * iarr)).byteValue());
            outputPixels[outputIndex++] = (0xFF << 24) | (resultingColorPixel << 16) | (resultingColorPixel << 8) | (resultingColorPixel);
        }
    }
}

private void BoxBlurVertical(int[] sourcePixels, int[] outputPixels, int width, int height, int radius) {
    int resultingColorPixel;
    float iarr = 1f / (radius + radius + 1);
    for (int i = 0; i < width; i++) {
        int outputIndex = i;
        int li = outputIndex;
        int sourceIndex = outputIndex + radius * width;

        int fv = Byte.toUnsignedInt((byte) sourcePixels[outputIndex]);
        int lv = Byte.toUnsignedInt((byte) sourcePixels[outputIndex + width * (height - 1)]);
        float val = (radius + 1) * fv;

        for (int j = 0; j < radius; j++) {
            val += Byte.toUnsignedInt((byte) sourcePixels[outputIndex + j * width]);
        }
        for (int j = 0; j <= radius; j++) {
            val += Byte.toUnsignedInt((byte) sourcePixels[sourceIndex]) - fv;
            resultingColorPixel = Byte.toUnsignedInt(((Integer) Math.round(val * iarr)).byteValue());
            outputPixels[outputIndex] = (0xFF << 24) | (resultingColorPixel << 16) | (resultingColorPixel << 8) | (resultingColorPixel);
            sourceIndex += width;
            outputIndex += width;
        }
        for (int j = radius + 1; j < (height - radius); j++) {
            val += Byte.toUnsignedInt((byte) sourcePixels[sourceIndex]) - Byte.toUnsignedInt((byte) sourcePixels[li]);
            resultingColorPixel = Byte.toUnsignedInt(((Integer) Math.round(val * iarr)).byteValue());
            outputPixels[outputIndex] = (0xFF << 24) | (resultingColorPixel << 16) | (resultingColorPixel << 8) | (resultingColorPixel);
            li += width;
            sourceIndex += width;
            outputIndex += width;
        }
        for (int j = (height - radius); j < height; j++) {
            val += lv - Byte.toUnsignedInt((byte) sourcePixels[li]);
            resultingColorPixel = Byte.toUnsignedInt(((Integer) Math.round(val * iarr)).byteValue());
            outputPixels[outputIndex] = (0xFF << 24) | (resultingColorPixel << 16) | (resultingColorPixel << 8) | (resultingColorPixel);
            li += width;
            outputIndex += width;
        }
    }
}
}