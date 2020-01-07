package com.example.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Bitmap bitmap, original, bit;
    ImageView image;
    TextView text;
    int N;
    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.color);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.color);
        original = bitmap.copy(bitmap.getConfig(), true);
        bit = bitmap.copy(bitmap.getConfig(), true);
        N = bit.getWidth() * bit.getHeight();
        text = findViewById(R.id.textView);
        text.setText("Size: " + N);


        Button btn = findViewById(R.id.change_toSpidy);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View v) {
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.spidy);
                        original = bitmap.copy(bitmap.getConfig(), true);
                        bit = bitmap.copy(bitmap.getConfig(), true);
                        image.setImageBitmap(bit);
                        int N = bit.getWidth() * bit.getHeight();
                        text = findViewById(R.id.textView);
                        text.setText("Size: " + N);


                    }
                });

        Button btn0 = findViewById(R.id.change_toDiagram);
        btn0.setOnClickListener(
                new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View v) {
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.color);
                        original = bitmap.copy(bitmap.getConfig(), true);
                        bit = bitmap.copy(bitmap.getConfig(), true);
                        image.setImageBitmap(bit);
                        N = bit.getWidth() * bit.getHeight();
                        text.setText("Size: " + N);


                    }
                });


        Button btn1 = findViewById(R.id.only_red);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Color.only_red(bit);

            }
        });

        Button btn2 = findViewById(R.id.colorize);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Color.colorize(bit);

            }
        });


        Button btn3 = findViewById(R.id.to_grey2);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to_Grey2(bit);

            }
        });


        Button btn4 = findViewById(R.id.to_grey);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gray.to_Grey1(bit);

            }
        });

        Button btn5 = findViewById(R.id.reset);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Color.reset(original, bit);

            }
        });

        Button btn6 = findViewById(R.id.lde);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Color.lde(bit);

            }
        });

        Button btn7 = findViewById(R.id.clde);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Color.colored_lde(bit);

            }
        });

        Button btn8 = findViewById(R.id.hea);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Color.hea(bit);

            }
        });

        Button btn9 = findViewById(R.id.chea);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Color.colored_histogram_Egalization_Algorithm(bit);

            }
        });



        image.setImageBitmap(bit);


    }






}


