package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.Color;

public class Convolution {

    static boolean border(int pos, int width, int height, int size){
        int delta= (size-1)/2;
        if(pos%width < delta || pos%width+delta > width-1 || pos%height < delta || pos%height+delta >height -1){
            return true;
        }
        return false;
    }


static int[] get_matrix(int[] pixels, int pos, int size, int width){
        int delta= (size-1)/2;
        int[] tab = new int[size*size];
        for (int i = 0 ; i< size*size; i=i+1){
            tab[i]= pixels[pos- width*delta -delta + i];
            }
    return tab;
    }


static int produit(int[] matrix, int[] tab, int size){
        int sum=0;
        for(int i = 0 ; i<size*size ; i=i+1){
            sum += tab[i] * matrix[size*size-1-i];
        }
        return sum;
}


static int sum_mask(int[] matrix, int size){
        int sum=0;
        for(int i= 0 ; i<size*size; i=i+1){
            sum += matrix[i];
        }
        return sum;

}


    static void convolution(int[] matrix, int matrix_size, Bitmap b){
        int height = b.getHeight();
        int width = b.getWidth();
        int[] pixels = new int[height * width];
        int result;
        b.getPixels(pixels, 0, width, 0, 0, width, height);

        for(int i= 0 ; i< width * height ; i=i+1){
            if(border (i, width, height, matrix_size)){
                pixels[i]= Color.rgb(0,0,0);
            }
            else{
                int produit= produit(matrix, get_matrix(pixels,i, matrix_size, width), matrix_size);
                int sum= sum_mask(matrix, matrix_size);
                if(sum==0){
                    return;
                }
                result= produit / sum;
                pixels[i]= result;
            }
        }
        b.setPixels(pixels, 0, width, 0, 0, width, height);

    }
}