package co.com.funnypets.funnypetsmobile.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import co.com.funnypets.funnypetsmobile.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFoto;
    private RecyclerViewAdapter adapterFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        recyclerViewFoto=(RecyclerView)findViewById(R.id.recyclerview_id);
        recyclerViewFoto.setLayoutManager(new GridLayoutManager(this,3));
        adapterFoto= new RecyclerViewAdapter(obtenerFotos());
        recyclerViewFoto.setAdapter(adapterFoto);
    }

    public List<PhotoModel> obtenerFotos(){
        List<PhotoModel> foto=new ArrayList<>();
        foto.add(new PhotoModel(R.drawable.imagen1));
        foto.add(new PhotoModel(R.drawable.imagen2));
        foto.add(new PhotoModel(R.drawable.imagen1));
        foto.add(new PhotoModel(R.drawable.imagen2));
        foto.add(new PhotoModel(R.drawable.imagen1));
        foto.add(new PhotoModel(R.drawable.imagen2));
        foto.add(new PhotoModel(R.drawable.imagen1));
        foto.add(new PhotoModel(R.drawable.imagen2));
        foto.add(new PhotoModel(R.drawable.imagen1));
        foto.add(new PhotoModel(R.drawable.imagen2));
        foto.add(new PhotoModel(R.drawable.imagen1));
        foto.add(new PhotoModel(R.drawable.imagen2));

        return foto;
    }
}
