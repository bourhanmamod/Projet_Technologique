package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE=1;
    Bitmap bitmap;
    ImageView image;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.camera);
        image = findViewById(R.id.Ia);

        if(!Camera()){
            button.setEnabled(false);
        }

    }

    public boolean Camera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    public void launchCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            image.setImageBitmap(photo);
        }

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

                //GrayRS(bit);
                return true;

            case R.id.item3:
                Toast.makeText(this, "Histogram Equalization", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.subitem4:
                Toast.makeText(this, "Histogram Equalization Color", Toast.LENGTH_SHORT).show();

                return true;

            case R.id.subitem5:
                Toast.makeText(this, "Histogram Equalization Black & White", Toast.LENGTH_SHORT).show();

                return true;

            case R.id.item4:
                Toast.makeText(this, "Linear Dynamic Extension", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.subitem6:
                Toast.makeText(this, "Linear Dynamic ExtensionColor", Toast.LENGTH_SHORT).show();

                return true;

            case R.id.subitem7:
                Toast.makeText(this, "Linear Dynamic Extension Black & White", Toast.LENGTH_SHORT).show();

                return true;

            case R.id.item5:
                Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show();

                return true;



           case R.id.item7:
                Toast.makeText(this, "Other", Toast.LENGTH_SHORT).show();
                return true;


            case R.id.subitem10:
                Toast.makeText(this, "Colorize", Toast.LENGTH_SHORT).show();

                return true;

            case R.id.subitem11:
                Toast.makeText(this, "Only_red", Toast.LENGTH_SHORT).show();

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }








}


