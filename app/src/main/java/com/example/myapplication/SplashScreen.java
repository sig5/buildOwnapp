package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
Handler handler;
ImageView img;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        handler=new Handler();
        img=findViewById(R.id.logo);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ActivityOptionsCompat activityOptionsCompat = (ActivityOptionsCompat) ActivityOptionsCompat.makeSceneTransitionAnimation(SplashScreen.this,(View)img,"zoomout");
                startActivity(new Intent(getApplicationContext(),Loginpage.class),activityOptionsCompat.toBundle());
            }
        },2000);

    }
}
