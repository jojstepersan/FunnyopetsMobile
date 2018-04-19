package co.com.funnypets.funnypetsmobile.activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import co.com.funnypets.funnypetsmobile.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    FirebaseUser user ;
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
        user = FirebaseAuth.getInstance().getCurrentUser();
        String name = user.getEmail();


        TextView nombrePerfil = (TextView)findViewById(R.id.nombre_perfil);
        nombrePerfil.setText(name);


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
