package co.com.funnypets.funnypetsmobile.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import co.com.funnypets.funnypetsmobile.R;
import co.com.funnypets.funnypetsmobile.fragments.PostFragment;
import co.com.funnypets.funnypetsmobile.fragments.ProfileFragment;
import co.com.funnypets.funnypetsmobile.fragments.SearchFragment;
import co.com.funnypets.funnypetsmobile.fragments.subirFotoFragment;

public class MainActivity extends AppCompatActivity {


    private TextView mTextMessage;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.ic_house:
                    transaction.replace(R.id.main_fragment, new PostFragment()).commit();
                    return true;
                case R.id.ic_circle:
                    transaction.replace(R.id.main_fragment, new subirFotoFragment()).commit();
                    return true;
                case R.id.ic_android:
                    transaction.replace(R.id.main_fragment, new ProfileFragment()).commit();
                    return true;
                case R.id.ic_search:
                    transaction.replace(R.id.main_fragment, new SearchFragment()).commit();
                    return true;
                case R.id.ic_alert:
                    Transition t=new Slide(Gravity.END);
                    t.setDuration(1000);
                    t.setInterpolator(new DecelerateInterpolator());
                    getWindow().setExitTransition(t);
                    Intent intent2;
                    intent2 = new Intent(MainActivity.this, ChatActivity.class);
                    startActivity(intent2, ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView) findViewById(R.id.message);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_fragment, new PostFragment()).commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.fromleft);
        navigation.setAnimation(anim);
    }

}