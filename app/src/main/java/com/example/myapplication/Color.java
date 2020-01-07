package com.example.myapplication;

import android.graphics.Bitmap;

import java.util.Random;

class Color {


    private static Random r;


    static void colorize(Bitmap b) {
        int height = b.getHeight();
        int width = b.getWidth();
        int[] pixels = new int[height * width];
        float random = r.nextInt(360);
        b.getPixels(pixels, 0, width, 0, 0, width, height);
        float[] hsv = new float[3];
        for (int i = 0; i < height * width; i = i + 1) {

            int color = pixels[i];
            int red = android.graphics.Color.red(color);
            int green = android.graphics.Color.red(color);
            int blue = android.graphics.Color.red(color);


            android.graphics.Color.RGBToHSV(red, green, blue, hsv);

            hsv[0] = random;

            pixels[i] = android.graphics.Color.HSVToColor(hsv);
        }

        b.setPixels(pixels, 0, width, 0, 0, width, height);

    }


    static void reset(Bitmap original, Bitmap b) {
        int height = b.getHeight();
        int width = b.getWidth();
        int[] pixels = new int[height * width];
        original.getPixels(pixels, 0, width, 0, 0, width, height);
        b.setPixels(pixels, 0, width, 0, 0, width, height);

    }


    static void only_red(Bitmap b) {
        int height = b.getHeight();
        int width = b.getWidth();
        int[] pixels = new int[height * width];
        b.getPixels(pixels, 0, width, 0, 0, width, height);
        float[] hsv = new float[3];
        for (int i = 0; i < height * width - 1; i = i + 1) {
            int color = pixels[i];
            int alpha= android.graphics.Color.alpha(color);
            int red = android.graphics.Color.red(color);
            int green = android.graphics.Color.red(color);
            int blue = android.graphics.Color.red(color);

            android.graphics.Color.RGBToHSV(red, green, blue, hsv);

            if (hsv[0] > 15 && hsv[0] < 345) {
                double grey = 0.3 * red + 0.59 * green + 0.11 * blue;
                int g = (int) grey;
                color = android.graphics.Color.argb(alpha, g, g, g);
                pixels[i] = color;
            }
        }
        b.setPixels(pixels, 0, width, 0, 0, width, height);
    }







}
