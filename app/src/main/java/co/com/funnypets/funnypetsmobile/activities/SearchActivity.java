package co.com.funnypets.funnypetsmobile.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import co.com.funnypets.funnypetsmobile.R;
import co.com.funnypets.funnypetsmobile.adapters.SearchAdapter;

public class SearchActivity extends AppCompatActivity {
    private String text = "";
    private DatabaseReference mDatabase;
    private ArrayList<String> lisDatos;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query ref = mDatabase.child("Post").startAt(text).limitToLast(10);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        lisDatos = new ArrayList<String>();
        for(int i = 0;i<50;i++) {
            lisDatos.add("Dato #"+i+" ");
        }
        SearchAdapter search = new SearchAdapter(lisDatos);
        recyclerView.setAdapter(search);
    }

}
