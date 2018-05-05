package co.com.funnypets.funnypetsmobile.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import co.com.funnypets.funnypetsmobile.R;

public class SearchActivity extends AppCompatActivity {
    private String text = "";
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query ref = mDatabase.child("Post").startAt(text).limitToLast(10);

    }

}
