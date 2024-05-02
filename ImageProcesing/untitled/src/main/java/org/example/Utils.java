package org.example;


import java.awt.image.BufferedImage;

public class Utils {

    /**
     * Pack the three color and alpha components into an int variable.
     * Precondition: each component in [0,256).
     * Violation of the precondition will lead to corrupted results.
     *
     * @param red
     * @param green
     * @param blue
     * @param alpha
     * @return the other four parameters packed into an int.
     */


    //Generate a hash of the colors and alpha
    static int makeRGBPixel(int red, int green, int blue, int alpha) {
        int pixel = ((alpha & 0xFF) << 24) /* alpha component */
                | ((red & 0xFF) << 16) /* red component */
                | ((green & 0xFF) << 8) /* green component */
                | ((blue & 0xFF) << 0) /* blue component */;
        return pixel;
    }

    public static void main(String[] args) {
        int pixelRojo = makeRGBPixel(255, 0, 0, 255); // 0xFF000000 (rojo opaco)
        int pixelVerde = makeRGBPixel(0, 255, 0, 128); // 0x80008000 (verde semi-transparente)
        int pixelAzul = makeRGBPixel(0, 0, 255, 64); // 0x404000FF (azul muy transparente)

        System.out.println("Pixel Rojo: " + pixelRojo);
        System.out.println("Pixel Verde: " + pixelVerde);
        System.out.println("Pixel Azul: " + pixelAzul);
    }

    /**
     * Pack the three color into in int. The alpha component is the default
     * value of 255.
     *
     * @param red
     * @param green
     * @param blue
     * @param alpha
     * @return the other four parameters packed into an int.
     */

    static int makeRGBPixel(int red, int green, int blue) {
        return makeRGBPixel(red, green, blue, 255);
    }

    /**
     * Compare two images for equality.
     * @param image0
     * @param image1
     * @return
     */

    static boolean equals(BufferedImage image0, BufferedImage image1){
        int w0 = image0.getWidth();
        int w1 = image1.getWidth();
        if (w0 != w1) return false;
        int h0 = image0.getHeight();
        int h1 = image1.getHeight();
        if (h0 != h1) return false;

        int nPixels = w0*h0;
        int[] pixels0 = new int[nPixels];
        image0.getRGB(0, 0, w0, h0, pixels0, 0, w0);

        int[] pixels1 = new int[nPixels];
        //System.out.println("ff");
        image1.getRGB(0, 0, w1, h1, pixels1, 0, w1);
        for(int i=0; i<nPixels; i++){
            if (pixels0[i] != pixels1[i]) return false;
        }
        return true;
    }
}