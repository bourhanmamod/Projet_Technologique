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
            int color = pixels[i];
            int red = (color >> 16) & 0xff;
            long result = (cumulative_histogram[red] * 255) / (width * height);
            pixels[i] = android.graphics.Color.rgb((int) result, (int) result, (int) result);
        }
        b.setPixels(pixels, 0, width, 0, 0, width, height);
    }



//A revoir
    static void colored_histogram_Equalization_Algorithm(Bitmap b) {
        int height = b.getHeight();
        int width = b.getWidth();
        int[] pixels = new int[height * width];
        b.getPixels(pixels, 0, width, 0, 0, width, height);

        float[] hsv = new float[3];
        int value=0;
        int[] histogram = new int[101];


        for (int i = 0; i < height * width; i = i + 1) {
            int color = pixels[i];
            int red = (color >> 16) & 0xff;
            int green = (color >>  8) & 0xff;
            int blue = (color      ) & 0xff;
            Color.RGBToHSV(red, green, blue, hsv);

            value = (int) (hsv[2]*100);
            histogram[value] += 1;
        }
        int[] cumulativehistogram = Other.cumulativehistogram(histogram, 101);


        for (int i = 0; i < height * width; i = i + 1) {
            int color = pixels[i];
            int red = (color >> 16) & 0xff;
            int green = (color >>  8) & 0xff;
            int blue = (color      ) & 0xff;

            Color.RGBToHSV(red, green, blue, hsv);
            value = (int) (hsv[2] * 100);
            int result = (cumulativehistogram [value]*100) / (width * height);
            hsv[2] = ((float) result) / 100;
            int color2 = Color.HSVToColor(hsv);
            pixels[i] = color2;
        }
        b.setPixels(pixels, 0, width, 0, 0, width, height);
    }


}
