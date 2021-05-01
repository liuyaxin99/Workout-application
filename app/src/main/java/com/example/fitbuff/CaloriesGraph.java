package com.example.fitbuff;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CaloriesGraph extends AppCompatActivity
{

    private int toLoseOneLb;
    private int toGainOneLb;
    private int dailyCaloricIntake;
    private ImageView plotImage;
    private TextView caloriesText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view);

        Bundle calories = getIntent().getExtras();

        dailyCaloricIntake = Integer.parseInt(calories.getString("DailyCaloricIntake")); //get the daily caloric intake from the class who called this intent
        toLoseOneLb = dailyCaloricIntake - 500;
        toGainOneLb = dailyCaloricIntake + 500;

        caloriesText = (TextView) findViewById(R.id.CaloriesTextView);

        if (calories != null) {
            caloriesText.setText("Daily Caloric intake to maintain weight is " + dailyCaloricIntake + ". To lose one pound per week, the Daily Caloric Intake needs to be adjusted to " +
                    toLoseOneLb + ". To gain one pound per week, the Daily Caloric Intake must be adjusted to " + toGainOneLb + ".");
        }

        plotImage = (ImageView) findViewById(R.id.plotImageView);
        Bitmap plotBitMap = Bitmap.createBitmap(900,900, Bitmap.Config.ARGB_8888);
        final Canvas plotCanvas = new Canvas(plotBitMap);
        plotCanvas.drawColor(Color.GRAY);//set background of plot to cyan
        plotImage.setImageBitmap(plotBitMap);

        //draw on image view
        Paint plotPaint = new Paint();
        plotPaint.setColor(Color.WHITE);
        plotPaint.setStrokeWidth(10);
        plotPaint.setTextSize(40);
        
        int[] values = {toLoseOneLb, dailyCaloricIntake, toGainOneLb};
        int maxValue = Math.max(toLoseOneLb, Math.max(dailyCaloricIntake, toGainOneLb));
        int minValue = Math.min(toLoseOneLb, Math.min(dailyCaloricIntake, toGainOneLb));

        //compute yti
        double[] yt= {0, 0, 0};
        for (int i=0; i<yt.length; i++) {
            yt[i] = 900 - (850 * (values[i] - minValue) / (maxValue - minValue));

            //draw line segment
            try {
                plotCanvas.drawLine((i-1) * 100, (float) yt[i - 1], i * 100, (float) yt[i], plotPaint);
            } catch (ArrayIndexOutOfBoundsException e) {}

            plotCanvas.drawCircle(i * 100, (float) yt[i], 13, plotPaint);
            plotCanvas.drawText(Integer.toString(values[i]), (i + 1) * 100 - 80, (float) yt[i] - 15, plotPaint);

        }
    }

}
