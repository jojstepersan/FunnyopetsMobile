package co.com.funnypets.funnypetsmobile.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import co.com.funnypets.funnypetsmobile.R;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Animation fromBotton= AnimationUtils.loadAnimation(this,R.anim.frombottom);
        Animation fromTop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        ImageView image=findViewById(R.id.img_splash);
        //TextView text=findViewById(R.id.text_splash);
        image.setAnimation(fromTop);
       // text.setAnimation(fromBotton);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this,LoginActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}
