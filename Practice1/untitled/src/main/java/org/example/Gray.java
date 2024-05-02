package org.example;


import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

public class Gray {

    public static String[] labels = { "getRGB", "processing", "setRGB" };

    /**
     * Serial program to convert color image to grayscale.
     * Returns a Timer object with timing data collected during its execution.
     *
     * @param image
     * @param newImage
     * @return
     */
    public static Timer gray_SS(BufferedImage image, BufferedImage newImage) {
        Timer time = new Timer(labels);
        ColorModel colorModel = ColorModel.getRGBdefault();
        int w = image.getWidth();
        int h = image.getHeight();
        time.now();
        int[] sourcePixelArray = image.getRGB(0, 0, w, h, new int[w * h], 0, w);
        time.now();
        int[] grayPixelArray = new int[w * h];
        for (int i = 0; i < sourcePixelArray.length; i++) {
            int pixel = sourcePixelArray[i];
            int grayVal = (int) ((colorModel.getRed(pixel) * .299) + (colorModel.getGreen(pixel) * .587)
                    + (colorModel.getBlue(pixel) * .114));
            grayPixelArray[i] = Utils.makeRGBPixel(grayVal, grayVal, grayVal);
        }
        time.now();
        newImage.setRGB(0, 0, w, h, grayPixelArray, 0, w);
        time.now();
        return time;
    }

    public static Timer gray_SS_FJ(FJBufferedImage image, FJBufferedImage newImage) {
        Timer time = new Timer(labels);
        /**Implement this function with serial processing and parallel setRGB and getRGB */

        ColorModel colorModel = ColorModel.getRGBdefault();
        int w = image.getWidth();
        int h = image.getHeight();
        time.now();
        int[] sourcePixelArray = image.getRGB(0, 0, w, h, new int[w * h], 0, w);

        time.now();
        int[] grayPixelArray = new int[w * h];
        for (int i = 0; i < sourcePixelArray.length; i++) {
            int pixel = sourcePixelArray[i];
            int grayVal = (int) ((colorModel.getRed(pixel) * .299) + (colorModel.getGreen(pixel) * .587)
                    + (colorModel.getBlue(pixel) * .114));
            grayPixelArray[i] = Utils.makeRGBPixel(grayVal, grayVal, grayVal);
        }
        time.now();
        newImage.setRGB(0, 0, w, h, grayPixelArray, 0, w);

        time.now();

        return time;
    }

    public static Timer gray_PS_FJ(FJBufferedImage image, FJBufferedImage newImage) {
        Timer time = new Timer(labels);
        /**this function is implemented with parallel processing and parallel setRGB and getRGB */
        ColorModel colorModel = ColorModel.getRGBdefault();
        int w = image.getWidth();
        int h = image.getHeight();
        time.now();
        int[] sourcePixelArray = image.getRGB(0, 0, w, h, new int[w * h], 0, w);

        time.now();
        int[] grayPixelArray = new int[w * h];
        // Aquí puedes implementar el procesamiento paralelo
        for (int i = 0; i < sourcePixelArray.length; i++) {
            int pixel = sourcePixelArray[i];
            int grayVal = (int) ((colorModel.getRed(pixel) * .299) + (colorModel.getGreen(pixel) * .587)
                    + (colorModel.getBlue(pixel) * .114));
            grayPixelArray[i] = Utils.makeRGBPixel(grayVal, grayVal, grayVal);
        }
        time.now();
        newImage.setRGB(0, 0, w, h, grayPixelArray, 0, w);
        time.now();

        return time;
    }

    public static Timer gray_PS(BufferedImage image, BufferedImage newImage) {
        Timer time = new Timer(labels);
        ColorModel colorModel = ColorModel.getRGBdefault();
        int w = image.getWidth();
        int h = image.getHeight();
        time.now();
        int[] sourcePixelArray = image.getRGB(0, 0, w, h, new int[w * h], 0, w);
        time.now();
        int[] grayPixelArray = new int[w * h];
        // Aquí puedes implementar el procesamiento paralelo
        for (int i = 0; i < sourcePixelArray.length; i++) {
            int pixel = sourcePixelArray[i];
            int grayVal = (int) ((colorModel.getRed(pixel) * .299) + (colorModel.getGreen(pixel) * .587)
                    + (colorModel.getBlue(pixel) * .114));
            grayPixelArray[i] = Utils.makeRGBPixel(grayVal, grayVal, grayVal);
        }
        time.now();
        newImage.setRGB(0, 0, w, h, grayPixelArray, 0, w);
        time.now();
        return time;
    }
}