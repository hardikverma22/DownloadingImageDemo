package com.example.android.downloadingimagedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ImageView downloadedImage;

    public void onDownloadBtnClick(View view){
        Log.i("btn clicked","yes");

        DownloadTask task = new DownloadTask();
        try {

            Bitmap map = task.execute("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSQLXyd0uMUmhAcMpITeK9Kdj8XGyM5xg3DHYJqKhApp2jspOyE").get();
            downloadedImage.setImageBitmap(map);
            Log.i("image set","task successful");

        } catch (InterruptedException e) {

            e.printStackTrace();

        } catch (ExecutionException e) {

            e.printStackTrace();

        }
    }
    private class DownloadTask extends AsyncTask<String, Void,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            URL url=null;
            HttpURLConnection con=null;
            try {
                url = new URL(urls[0]);
                con=(HttpURLConnection)url.openConnection();
                InputStream in = con.getInputStream();
                Bitmap bmp = BitmapFactory.decodeStream(in);
                return bmp;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadedImage =(ImageView)findViewById(R.id.imageViewDownloadedImage);

    }
}

