package co.com.funnypets.funnypetsmobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
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

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
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

                    Intent intent2;
                    intent2 = new Intent(MainActivity.this, ChatActivity.class);
                    startActivity(intent2);
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
        Log.d("idUsuario", user.getUid());
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_fragment, new PostFragment()).commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
