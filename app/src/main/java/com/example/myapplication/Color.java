package com.example.myapplication;

import android.graphics.Bitmap;

import java.util.Random;

public class Color {


    private static Random r;
    static void colorize(Bitmap b) {
        int height = b.getHeight();
        int width = b.getWidth();
        int[] pixels = new int[height * width];
        float random = r.nextInt(360);
        b.getPixels(pixels, 0, width, 0, 0, width, height);
        float[] hsv = new float[3];
        for (int i = 0; i < height * width - 1; i = i + 1) {
            int color = pixels[i];
            int red = (color >> 16) & 0xff;
            int green = (color >> 8) & 0xff;
            int blue = (color) & 0xff;
            android.graphics.Color.RGBToHSV(red, green, blue, hsv);
            hsv[0] = random;
            pixels[i] = android.graphics.Color.HSVToColor(hsv);
        }

        b.setPixels(pixels, 0, width, 0, 0, width, height);

    }


    //Linear Dynamic Extention
    static void lde(Bitmap b) {
        int max = 0;
        int min = 255;
        int height = b.getHeight();
        int width = b.getWidth();
        int[] pixels = new int[height * width];
        b.getPixels(pixels, 0, width, 0, 0, width, height);
        int[] lut = new int[256];

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
            lut[ng] = ((255 * (ng - min)) / (max - min));
        }
        for (int i = 0; i < width * height; i++) {
            int color = pixels[i];
            int red = (color >> 16) & 0xff;
            int c = lut[red];
            pixels[i] = android.graphics.Color.rgb(c, c, c);
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
            pixels[i] = android.graphics.Color.rgb(r, g, bl);
        }
        b.setPixels(pixels, 0, width, 0, 0, width, height);
    }


    //histogramm egalisation algorithm

    static void hea(Bitmap b) {
        int height = b.getHeight();
        int width = b.getWidth();
        int[] pixels = new int[height * width];
        int C;
        b.getPixels(pixels, 0, width, 0, 0, width, height);
        int[] histo = new int[256];
        for (int i = 0; i < height * width; i = i + 1) {
            int color = pixels[i];
            int red = (color >> 16) & 0xff;
            histo[red] = histo[red] + 1;
        }
        for (int i = 0; i < height * width; i = i + 1) {
            C = 0;
            int color = pixels[i];
            int red = (color >> 16) & 0xff;

            for (int j = 0; j < red; j = j + 1) {
                C = histo[j] + C;
            }

            long result = (C * 255) / (width * height);
            pixels[i] = android.graphics.Color.rgb((int) result, (int) result, (int) result);
        }
        b.setPixels(pixels, 0, width, 0, 0, width, height);
    }

    static void colored_histogram_Egalization_Algorithm(Bitmap b) {
        int height = b.getHeight();
        int width = b.getWidth();
        int[] pixels = new int[height * width];
        int C_r;
        int C_g;
        int C_b;
        b.getPixels(pixels, 0, width, 0, 0, width, height);
        int[] hist_r = new int[256];
        int[] hist_g = new int[256];
        int[] hist_b = new int[256];
        for (int i = 0; i < height * width; i = i + 1) {
            int color = pixels[i];
            int red = (color >> 16) & 0xff;
            int green = (color >> 8) & 0xff;
            int blue = (color) & 0xff;
            hist_r[red] = hist_r[red] + 1;
            hist_g[green] = hist_g[green] + 1;
            hist_b[blue] = hist_b[blue] + 1;
        }
        for (int i = 0; i < height * width; i = i + 1) {
            C_r = 0;
            C_g = 0;
            C_b = 0;
            int color = pixels[i];
            int red = (color >> 16) & 0xff;
            int green = (color >> 8) & 0xff;
            int blue = (color) & 0xff;
            for (int j = 0; j < red; j = j + 1) {
                C_r = hist_r[j] + C_r;
            }
            for (int j = 0; j < green; j = j + 1) {
                C_g = hist_g[j] + C_g;
            }
            for (int j = 0; j < blue; j = j + 1) {
                C_b = hist_b[j] + C_b;
            }

            long result_r = (C_r * 255) / (width * height);
            long result_g = (C_g * 255) / (width * height);
            long result_b = (C_b * 255) / (width * height);
            pixels[i] = android.graphics.Color.rgb((int) result_r, (int) result_g, (int) result_b);
        }
        b.setPixels(pixels, 0, width, 0, 0, width, height);
    }

    int HSVToColor(float[] hsv) {
        int v = (int) hsv[2];
        int t = (int) ((hsv[0] / 60) % 6);
        int f = (int) ((hsv[0] / 60) - t);
        int l = (int) (v * (1 - hsv[1]));
        int m = (int) (v * (1 - (f - hsv[1])));
        int n = (int) (v * (1 - (1 - f) * hsv[1]));

        if (t == 0) {
            return android.graphics.Color.rgb(v, n, l);
        } else if (t == 1) {
            return android.graphics.Color.rgb(m, v, l);
        } else if (t == 2) {
            return android.graphics.Color.rgb(l, v, n);
        } else if (t == 3) {
            return android.graphics.Color.rgb(l, m, v);
        } else if (t == 4) {
            return android.graphics.Color.rgb(n, l, v);
        }
        return android.graphics.Color.rgb(v, l, m);
    }

    static void only_red(Bitmap b) {
        int height = b.getHeight();
        int width = b.getWidth();
        int[] pixels = new int[height * width];
        b.getPixels(pixels, 0, width, 0, 0, width, height);
        float[] hsv = new float[3];
        for (int i = 0; i < height * width - 1; i = i + 1) {
            int color = pixels[i];
            int alpha = (color >> 24) & 0xff;
            int red = (color >> 16) & 0xff;
            int green = (color >> 8) & 0xff;
            int blue = (color) & 0xff;
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


    //A MODIFIER
    void RGBToHSV(int red, int green, int blue, float[] hsv) {

        float min = Math.min(red, Math.min(green, blue));
        float max = Math.max(red, Math.max(green, blue));
        if (max == min) {
            hsv[0] = 0;
        } else if (max == red) {
            hsv[0] = ((60 * (green - blue) / (max - min)) + 360) % 360;
        } else if (max == green) {
            hsv[0] = (60 * (blue - red) / (max - min)) + 120;
        } else if (max == blue) {
            hsv[0] = (60 * (red - green) / (max - min)) + 240;

        }

        if (max == 0) {
            hsv[1] = 0;
        } else {
            hsv[1] = 1 - (min / max);
        }
        hsv[2] = max;
    }


}
