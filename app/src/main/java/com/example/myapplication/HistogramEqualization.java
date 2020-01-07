package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.Color;

class HistogramEqualization {


    //histograms equalisation algorithm

    static void hea(Bitmap b) {
        int height = b.getHeight();
        int width = b.getWidth();
        int[] pixels = new int[height * width];
        b.getPixels(pixels, 0, width, 0, 0, width, height);
        int[] histogram = Other.histograms(pixels, height, width);
        int[] cumulative_histogram = Other.cumulativehistogram(histogram, 256);

        for(int i =0 ; i<height*width ; i++){
            int color = Color.red(pixels[i]);
            long result = (cumulative_histogram[color] * 255) / (width * height);
            pixels[i] = android.graphics.Color.rgb((int) result, (int) result, (int) result);
        }
        b.setPixels(pixels, 0, width, 0, 0, width, height);
    }




    static void colored_histogram_Equalization_Algorithm(Bitmap b) {
        int height = b.getHeight();
        int width = b.getWidth();
        int[] pixels = new int[height * width];
        b.getPixels(pixels, 0, width, 0, 0, width, height);

        float[] hsv = new float[3];
        int value;
        int[] histogram = new int[100];
        int[] histogram_cumule = new int[101];
        for (int i = 0; i < height * width - 1; i = i + 1) {
            int color = pixels[i];
            int red = android.graphics.Color.red(color);
            int green = android.graphics.Color.green(color);
            int blue = android.graphics.Color.blue(color);
            android.graphics.Color.RGBToHSV(red, green, blue, hsv);

            value = (int) (hsv[2]*100);
            histogram[value-1] += 1;
        }
        histogram_cumule[0] = histogram[0];
        for (int j = 1; j < 100; j = j + 1) {
            histogram_cumule[j] = histogram[j - 1] + histogram[j];}

        for (int i = 0; i < height * width - 1; i = i + 1) {
            int color = pixels[i];
            int red = android.graphics.Color.red(color);
            int green = android.graphics.Color.green(color);
            int blue = android.graphics.Color.blue(color);
            android.graphics.Color.RGBToHSV(red, green, blue, hsv);
            value = (int) (hsv[2] * 100);
            long result = (histogram_cumule[value-1] * 100) / (width * height);
            hsv[2] = ((float) result) / 100;
            int color2 = android.graphics.Color.HSVToColor(hsv);
            pixels[i] = color2;
        }
        b.setPixels(pixels, 0, width, 0, 0, width, height);
    }


}
