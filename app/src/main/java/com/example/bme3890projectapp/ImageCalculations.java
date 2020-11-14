package com.example.bme3890projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ImageCalculations extends AppCompatActivity {

    private ImageView fullImage;
    private GraphView rgbChart;
    private int bitmapWidth, bitmapHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_calculations);


        fullImage = (ImageView) findViewById(R.id.iv_fullImage);
        rgbChart = (GraphView) findViewById(R.id.gv_graph);

        Intent imageCalculations = getIntent();
        String currentPhotoPath = imageCalculations.getStringExtra(TakePhoto.NAME_EXTRA);

        Bitmap imageBitmap = BitmapFactory.decodeFile(currentPhotoPath);
        fullImage.setImageBitmap(imageBitmap);
        bitmapWidth = imageBitmap.getWidth();
        bitmapHeight = imageBitmap.getHeight();



    }

    public void makeGraph(View view) {

        Intent imageCalculations = getIntent();
        String currentPhotoPath = imageCalculations.getStringExtra(TakePhoto.NAME_EXTRA);
        Bitmap imageBitmap = BitmapFactory.decodeFile(currentPhotoPath);


      //  int length = (bitmapHeight*bitmapWidth)/6;

        //graphing 3 random pixels
        DataPoint[] red = new DataPoint[3];
        int redValue = Color.red(imageBitmap.getPixel(0,0));
        red[0] = new DataPoint(0, redValue);
        redValue = Color.red(imageBitmap.getPixel(bitmapWidth/2,bitmapHeight/2));
        red[1] = new DataPoint(1,redValue);
        redValue = Color.red(imageBitmap.getPixel(bitmapWidth-1,bitmapHeight-1));
        red[2] = new DataPoint(2,redValue);
       // DataPoint[] blue = new DataPoint[length];
       // DataPoint[] green = new DataPoint[length];


       /* for (int k = 0; k<= length; k++){
            for (int i = 0; i <= bitmapHeight/6; i++) {
                for (int j = 0; j <= bitmapWidth/6; j++) {
                    redValue = Color.red(imageBitmap.getPixel(j,i));
                    red[k] = new DataPoint(k, redValue);
                  //  blue[k] = new DataPoint(k, Color.blue(imageBitmap.getPixel(j,i)));
                  //  green[k] = new DataPoint(k, Color.green(imageBitmap.getPixel(j,i)));
                }
            }
        } */

        LineGraphSeries<DataPoint> redSeries = new LineGraphSeries<>(red);
      //  LineGraphSeries<DataPoint> blueSeries = new LineGraphSeries<>(blue);
      //  LineGraphSeries<DataPoint> greenSeries = new LineGraphSeries<>(green);

        rgbChart.addSeries(redSeries);
        rgbChart.setTitle("Red Values of the Pixels");
        rgbChart.getGridLabelRenderer().setVerticalAxisTitle("Red Value");
        rgbChart.getGridLabelRenderer().setHorizontalAxisTitle("Pixel Number");
      //  rgbChart.addSeries(blueSeries);
        // rgbChart.addSeries(greenSeries);
    }
}