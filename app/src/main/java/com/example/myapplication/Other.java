package com.example.myapplication;

import android.graphics.Color;

class Other {

    static int[] histograms(int[] pixels, int height, int width){
        int[] histogram = new int[256];
        for (int i = 0; i < height * width; i = i + 1) {
            int color = pixels[i];
            int red = Color.red(color);
            histogram[red] = histogram[red] + 1;
        }
        return histogram;
    }

    static int[] cumulativehistogram(int[] histogram, int size){
        int[] h= new int[size];
        h[0]= histogram[0];
        for(int i = 1 ; i<size ; i=i+1){
            h[i]= h[i-1] + histogram[i];
        }
        return h;
    }

    static int[] lut(int[] pixels, int height, int width, int max, int min){
        int[] lut= new int[256];
        for (int i = 0; i < height * width; i = i + 1) {

            int color = pixels[i];
            int red = (color >> 16) & 0xff;
            if (min > red) {
                min = red;
            }
            if (max < red) {
                max = red;
            }

        }
        for (int ng = 0; ng < 256; ng++) {
            if (max!=min) {
                lut[ng] = ((255 * (ng - min)) / (max - min));
            }
        }
        return lut;
    }




}
