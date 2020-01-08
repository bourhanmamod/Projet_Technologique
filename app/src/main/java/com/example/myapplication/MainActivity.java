package com.example.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.renderscript.Allocation;
import androidx.renderscript.RenderScript;

import static com.example.myapplication.Coloration.reset;
import static com.example.myapplication.HistogramEqualization.colored_histogram_Equalization_Algorithm;
import static com.example.myapplication.HistogramEqualization.hea;

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
        image.setImageBitmap(bit);


    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
                Toast.makeText(this, "Renderscript", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.subitem1:
                Toast.makeText(this, "Renderscript Gray", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.subitem2:
                Toast.makeText(this, "Renderscript Histogram Equalization", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.subitem3:
                Toast.makeText(this, "Renderscript Linear Dynamic Extension", Toast.LENGTH_SHORT);
                return true;


            case R.id.item2:
                Toast.makeText(this, "Gray", Toast.LENGTH_SHORT).show();
                //Gray.to_Grey2(bit);
                GrayRS(bit);
                return true;

            case R.id.item3:
                Toast.makeText(this, "Histogram Equalization", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.subitem4:
                Toast.makeText(this, "Histogram Equalization Color", Toast.LENGTH_SHORT).show();
                colored_histogram_Equalization_Algorithm(bit);
                return true;

            case R.id.subitem5:
                Toast.makeText(this, "Histogram Equalization Black & White", Toast.LENGTH_SHORT).show();
                hea(bit);
                return true;

            case R.id.item4:
                Toast.makeText(this, "Linear Dynamic Extension", Toast.LENGTH_SHORT).show();
                LinearDynamicExtension.lde(bit);
                return true;

            case R.id.subitem6:
                Toast.makeText(this, "Linear Dynamic ExtensionColor", Toast.LENGTH_SHORT).show();
                LinearDynamicExtension.colored_lde(bit);
                return true;

            case R.id.subitem7:
                Toast.makeText(this, "Linear Dynamic Extension Black & White", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item5:
                Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show();
                reset(original, bit);
                return true;

            case R.id.item6:
                Toast.makeText(this, "Change Image", Toast.LENGTH_SHORT).show();
                return true;


            case R.id.subitem8:
                Toast.makeText(this, "Spiderman Image selected", Toast.LENGTH_SHORT).show();
                change_toSpidy();
                return true;

            case R.id.subitem9:
                Toast.makeText(this, "Diagram Image selected", Toast.LENGTH_SHORT).show();
                change_toDiagram();
                return true;

            case R.id.item7:
                Toast.makeText(this, "Other", Toast.LENGTH_SHORT).show();
                return true;


            case R.id.subitem10:
                Toast.makeText(this, "Colorize", Toast.LENGTH_SHORT).show();
                Coloration.colorize(bit);
                return true;

            case R.id.subitem11:
                Toast.makeText(this, "Only_red", Toast.LENGTH_SHORT).show();
                Coloration.only_red(bit);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }

    void change_toSpidy(){
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.spidy);
        original = bitmap.copy(bitmap.getConfig(), true);
        bit = bitmap.copy(bitmap.getConfig(), true);
        image.setImageBitmap(bit);
        int N = bit.getWidth() * bit.getHeight();
        text = findViewById(R.id.textView);
        text.setText("Size: " + N);


    }

    void change_toDiagram(){
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.color);
        original = bitmap.copy(bitmap.getConfig(), true);
        bit = bitmap.copy(bitmap.getConfig(), true);
        image.setImageBitmap(bit);
        N = bit.getWidth() * bit.getHeight();
        text.setText("Size: " + N);


    }



    private  void  GrayRS(Bitmap  bmp) {

        RenderScript rs = RenderScript.create(this);


        Allocation input = Allocation.createFromBitmap(rs , bmp);
        Allocation output = Allocation.createTyped(rs, input.getType());
        ScriptC_gray grayScript = new  ScriptC_gray(rs);

        grayScript.forEach_toGray(input , output);
        output.copyTo(bmp);

        input.destroy ();
        output.destroy ();grayScript.destroy();
        rs.destroy();
    }


}


