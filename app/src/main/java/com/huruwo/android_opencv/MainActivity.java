package com.huruwo.android_opencv;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;

import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import static com.huruwo.android_opencv.CalculateDistance.bitmapToMat;
import static com.huruwo.android_opencv.CalculateDistance.matToBitmap;

public class MainActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OpenCVLoader.initDebug();
        webview = (WebView) findViewById(R.id.webview);
        webview.loadUrl("https://ssl.captcha.qq.com/template/wireless_mqq_captcha.html");
        //slide();
    }

    private void slide(){
        Bitmap  bg_bmp = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        Bitmap  slide_bmp = BitmapFactory.decodeResource(getResources(), R.drawable.slide);
        try {
            double d = CalculateDistance.calculateDistance(bg_bmp,slide_bmp);
            Log.e("MainActivity",d +" ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}