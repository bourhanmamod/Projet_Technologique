package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.Color;

class LinearDynamicExtension {

    //Linear Dynamic Extension
    static void lde(Bitmap b) {
        int max = 0;
        int min = 255;
        int height = b.getHeight();
        int width = b.getWidth();
        int[] pixels = new int[height * width];
        b.getPixels(pixels, 0, width, 0, 0, width, height);
        int[] lut = Other.lut(pixels, height, width, max, min);


        for (int i = 0; i < width * height; i++) {
            int color = pixels[i];
            int red = Color.red(color);
            int c = lut[red];
            pixels[i] = Color.rgb(c, c, c);
        }
        b.setPixels(pixels, 0, width, 0, 0, width, height);
    }

    static void colored_lde(Bitmap b) {
        int height = b.getHeight();
        int width = b.getWidth();
        int[] pixels = new int[height * width];
        b.getPixels(pixels, 0, width, 0, 0, width, height);
        int[] lut_r = new int[256];
        int[] lut_g = new int[256];
        int[] lut_b = new int[256];
        int min_r = 255;
        int min_g = 255;
        int min_b = 255;
        int max_r = 0;
        int max_g = 0;
        int max_b = 0;
        for (int i = 0; i < height * width; i = i + 1) {

            int color = pixels[i];
            int red = (color >> 16) & 0xff;
            int green = (color >> 8) & 0xff;
            int blue = (color) & 0xff;

            if (min_r > red) {
                min_r = red;
            }
            if (max_r < red) {
                max_r = red;
            }

            if (min_g > green) {
                min_g = green;
            }
            if (max_g < green) {
                max_g = green;
            }
            if (min_b > blue) {
                min_b = blue;
            }
            if (max_b < blue) {
                max_b = blue;
            }
        }
        for (int ng = 0; ng < 256; ng++) {
            lut_r[ng] = ((255 * (ng - min_r)) / (max_r - min_r));
            lut_g[ng] = ((255 * (ng - min_g)) / (max_g - min_g));
            lut_b[ng] = ((255 * (ng - min_b)) / (max_b - min_b));
        }

        for (int i = 0; i < width * height; i++) {
            int color = pixels[i];
            int red = (color >> 16) & 0xff;
            int green = (color >> 8) & 0xff;
            int blue = (color) & 0xff;
            int r = lut_r[red];
            int g = lut_g[green];
            int bl = lut_b[blue];
            pixels[i] = Color.rgb(r, g, bl);
        }
        b.setPixels(pixels, 0, width, 0, 0, width, height);
    }

}
