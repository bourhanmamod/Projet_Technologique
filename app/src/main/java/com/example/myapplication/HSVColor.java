package com.example.myapplication;

public class HSVColor {

    static int HSVToColor(float[] hsv) {
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


    //A MODIFIER car ne fonctionne pas
    static void RGBToHSV(int red, int green, int blue, float[] hsv) {

        int min = Math.min(red, Math.min(green, blue));
        int max = Math.max(red, Math.max(green, blue));
        if (max == min) {
            hsv[0] = 0;
        } else if (max == red) {
            hsv[0] = ((60 * ((float) (green - blue)) / ((float)(max - min)) + 360) % 360);
        } else if (max == green) {
            hsv[0] =  ((60 * ((float) (blue - red)) / ((float) (max - min))) + 120);
        } else if (max == blue) {
            hsv[0] =  ((60 * ((float) (red - green)) / ((float) (max - min))) + 240);

        }

        if (max == 0) {
            hsv[1] = 0;
        } else {
            hsv[1] = 1 - ((float) min / (float) max);
        }
        hsv[2] = max;
    }
}

