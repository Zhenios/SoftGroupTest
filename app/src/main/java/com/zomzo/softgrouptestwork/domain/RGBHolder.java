package com.zomzo.softgrouptestwork.domain;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by zomzo on 03.01.2017.
 */

public class RGBHolder {
    private int[] R,G,B;
    private final int numOfColors = 9;
    private final int colorMaxValue = 256;
    private final int multiplyOf = 51;
    public RGBHolder() {
        setRandomRGB();
    }

    public RGBHolder(int[] r, int[] g, int[] b) {
        R = r;
        G = g;
        B = b;
    }

    public int[] getR() {
        return R;
    }

    public void setR(int[] r) {
        R = r;
    }

    public int[] getG() {
        return G;
    }

    public void setG(int[] g) {
        G = g;
    }

    public int[] getB() {
        return B;
    }

    public void setB(int[] b) {
        B = b;
    }
    public int getLength(){
        return Math.min(R.length, Math.min(G.length,B.length));
    }
    public void setRandomRGB(){
        R = sort(generateRandomColor());
        G = shuffleArray(generateRandomColor());
        B = sortDesc(generateRandomColor());
    }
    //@Method
    //Random generate array of color
    public int[] generateRandomColor(){
        Random r = new Random();
        int[] arr = new int[numOfColors];
        for (int i=0;i<numOfColors;i++){
            int num = r.nextInt(colorMaxValue);
            //num % multiplyOf can not be zero
            if (num<multiplyOf){
                num +=multiplyOf;
            }
            int over = num % multiplyOf;
            if (over !=0){
                num -= over;
            }
            arr[i] = num;
        }
        return  arr;
    }

    //@Method
    //Buble sorting
    //If you do not want to use Arrays.sort(int[] arr)
    public int[] sort(int[] arr){
        int temp;
        for (int i =0; i<arr.length-1;i++){
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
    //@Method
    //Sorting in descending order
    //Buble sorting
    public static int[] sortDesc(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < (arr.length - i); j++) {
                if (arr[j - 1] < arr[j]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return  arr;
    }
    //@Method
    //Fisher–Yates shuffle
    //If you do not want to use Collections.shuffle()
    public int[] shuffleArray(int[] arr)
    {
        Random rnd = new Random();
        for (int i = arr.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = arr[index];
            arr[index] = arr[i];
            arr[i] = a;
        }
        return  arr;
    }
}
