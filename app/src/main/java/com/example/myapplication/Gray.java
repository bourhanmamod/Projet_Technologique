package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.Color;

class Gray {

    static void to_Grey1(Bitmap b) {
        int height = b.getHeight();
        int width = b.getWidth();

        for (int j = 0; j < height; j = j + 1) {
            for (int i = 0; i < width; i = i + 1) {
                int color = b.getPixel(i, j);
                int alpha = (color >> 24) & 0xff;
                int red = (color >> 16) & 0xff;
                int green = (color >> 8) & 0xff;
                int blue = (color) & 0xff;
                double grey = 0.3 * red + 0.59 * green + 0.11 * blue;
                int g = (int) grey;
                b.setPixel(i, j, Color.argb(alpha, g, g, g));
            }
        }
    }

    void to_Grey2(Bitmap b) {
        int height = b.getHeight();
        int width = b.getWidth();
        int[] pixels = new int[height * width];
        b.getPixels(pixels, 0, width, 0, 0, width, height);

        for (int i = 0; i < height * width - 1; i = i + 1) {
            int color = pixels[i];
            int alpha = (color >> 24) & 0xff;
            int red = (color >> 16) & 0xff;
            int green = (color >> 8) & 0xff;
            int blue = (color) & 0xff;
            double grey = 0.3 * red + 0.59 * green + 0.11 * blue;
            int g = (int) grey;
            color = Color.argb(alpha, g, g, g);
            pixels[i] = color;
        }
        b.setPixels(pixels, 0, width, 0, 0, width, height);
    }



}
