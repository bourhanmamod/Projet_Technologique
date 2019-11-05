package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView image= (ImageView) findViewById(R.id.spidy);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.spidy);
        final Bitmap original=bitmap.copy(bitmap.getConfig(),true);
        final Bitmap bit=bitmap.copy(bitmap.getConfig(),true);

        Button btn1= (Button) findViewById(R.id.only_red);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset(original,bit);

            }
        });

        Button btn2= (Button) findViewById(R.id.contrast);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorize(bit);

            }
        });


        Button btn3= (Button) findViewById(R.id.to_grey2);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to_Grey2(bit);

            }
        });


        Button btn4= (Button) findViewById(R.id.to_grey);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to_Grey1(bit);

            }
        });

        image.setImageBitmap(bit);


    }



    void to_Grey1(Bitmap b){
        int height= b.getHeight();
        int width= b.getWidth();

        for (int j=0; j<height; j=j+1){
            for (int i=0; i<width; i=i+1){
                int color = b.getPixel(i, j);
                int alpha = (color >> 24) & 0xff;
                int red = (color >> 16) & 0xff;
                int green = (color >>  8) & 0xff;
                int blue = (color      ) & 0xff;
                double grey= 0.3*red + 0.59*green + 0.11*blue;
                int g= (int) grey;
                b.setPixel(i,j, Color.argb(alpha,g,g,g));
            }
        }
    }

    void to_Grey2(Bitmap b){
        int height= b.getHeight();
        int width= b.getWidth();
        int[] pixels= new int[height*width];
        b.getPixels(pixels,0,width,0,0,width,height);

        for(int i=0; i<height*width-1; i=i+1) {
            int color = pixels[i];
            int alpha = (color >> 24) & 0xff;
            int red = (color >> 16) & 0xff;
            int green = (color >> 8) & 0xff;
            int blue = (color) & 0xff;
            double grey = 0.3 * red + 0.59 * green + 0.11 * blue;
            int g = (int) grey;
            color = Color.argb(alpha, g, g, g);
            pixels[i]=color;
        }
            b.setPixels(pixels,0,width,0,0,width,height);
        }

    void only_red(Bitmap b){
        int height= b.getHeight();
        int width= b.getWidth();
        int[] pixels= new int[height*width];
        b.getPixels(pixels,0,width,0,0,width,height);
        float[] hsv= new float[3];
        for(int i=0; i<height*width-1; i=i+1) {
            int color = pixels[i];
            int alpha = (color >> 24) & 0xff;
            int red = (color >> 16) & 0xff;
            int green = (color >> 8) & 0xff;
            int blue = (color) & 0xff;
            Color.RGBToHSV(red, green, blue, hsv);

            if (hsv[0] > 15 && hsv[0]<345) {
                double grey = 0.3 * red + 0.59 * green + 0.11 * blue;
                int g = (int) grey;
                color = Color.argb(alpha, g, g, g);
                pixels[i] = color;
            }
        }
        b.setPixels(pixels,0,width,0,0,width,height);
        }

        void colorize (Bitmap b){
            int height= b.getHeight();
            int width= b.getWidth();
            int[] pixels= new int[height*width];
            b.getPixels(pixels,0,width,0,0,width,height);
            float[] hsv= new float[3];
            for(int i=0; i<height*width-1; i=i+1) {
                int color = pixels[i];
                int alpha = (color >> 24) & 0xff;
                int red = (color >> 16) & 0xff;
                int green = (color >> 8) & 0xff;
                int blue = (color) & 0xff;

                Color.RGBToHSV(red,green,  blue, hsv);

                double randomDouble = Math.random();
                randomDouble = randomDouble * 50 + 1;
                int randomInt = (int) randomDouble;
                hsv[0]= randomInt;
                pixels[i]=Color.HSVToColor(hsv);
            }

                b.setPixels(pixels,0,width,0,0,width,height);

        }
        //Linear Dynamic Extention
        void lde(Bitmap b) {
            int height = b.getHeight();
            int width = b.getWidth();
            int[] pixels = new int[height * width];
            b.getPixels(pixels, 0, width, 0, 0, width, height);
            float[] hsv = new float[3];
            int[] histogram = new int[256];

            for (int i = 0; i < height * width-1; i = i + 1) {
                int color = pixels[i];
                int alpha = (color >> 24) & 0xff;
                int red = (color >> 16) & 0xff;
                int green = (color >> 8) & 0xff;
                int blue = (color) & 0xff;
                Color.RGBToHSV(red,green,  blue, hsv);
                //histogram[]=histogram[hsv[0]]+1;

            }
        }

        void reset (Bitmap original, Bitmap b){
            int height = b.getHeight();
            int width = b.getWidth();
            int[] pixels = new int[height * width];
            original.getPixels(pixels,0,width,0,0,width,height);
            b.setPixels(pixels,0,width,0,0,width,height);

    }



}

