package co.com.funnypets.funnypetsmobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.com.funnypets.funnypetsmobile.R;
import co.com.funnypets.funnypetsmobile.entities.Post;
import co.com.funnypets.funnypetsmobile.entities.Usuario;

public class PostDetailActivity extends AppCompatActivity {
    static List<Post> posts = new ArrayList<>();
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mFirebaseAuth;//mAuth
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private Toolbar toolbar;
    public static String userID;
    public static Usuario usuario;
    public static int i = 0;
    public static int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Gracias por adoptar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent2;
                intent2 = new Intent(PostDetailActivity.this, ChatActivity.class);
                startActivity(intent2);


            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("posts");
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        userID = user.getUid();
        DatabaseReference userRef = mFirebaseDatabase.getReference().child("Usuarios");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("root", dataSnapshot.child(userID) + "");
                usuario = new Usuario();
                usuario.setUrlfoto(dataSnapshot.child(userID).getValue(Usuario.class).getUrlfoto());
                usuario.setUsuario(dataSnapshot.child(userID).getValue(Usuario.class).getUsuario());
                usuario.setCorreo(dataSnapshot.child(userID).getValue(Usuario.class).getCorreo());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.d("root", userID);
        posts = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Bundle extra = getIntent().getExtras();
        pos = extra.getInt("pos");

    }

    private void showData(DataSnapshot ds) {
        i = 0;
        for (DataSnapshot dataSnapshot : ds.getChildren()) {
            Post post = new Post();
            if (post != null) {
                post.setName(ds.child((i) + "").getValue(Post.class).getName());
                post.setAdopcion(ds.child((i) + "").getValue(Post.class).isAdopcion());
                post.setCategoria(ds.child((i) + "").getValue(Post.class).getCategoria());
                post.setDescripcion(ds.child((i) + "").getValue(Post.class).getDescripcion());
                post.setUrlphotopost(ds.child((i) + "").getValue(Post.class).getUrlphotopost());
                post.setUsuario(ds.child((i) + "").getValue(Post.class).getUsuario());
                post.setNumOfLikes(ds.child((i) + "").getValue(Post.class).getNumOfLikes());
                post.setGenero(ds.child((i) + "").getValue(Post.class).getGenero());

                post.setEdad(ds.child((i) + "").getValue(Post.class).getEdad());
                posts.add(post);
                i++;
            }
        }
        Animation fromLeft = AnimationUtils.loadAnimation(this, R.anim.fromleft);
        Animation fromRight = AnimationUtils.loadAnimation(this, R.anim.fromright);
        Animation fromDown = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        Animation fromUp = AnimationUtils.loadAnimation(this, R.anim.fromtop);

        TextView name = findViewById(R.id.Post_name2);
        TextView raza = findViewById(R.id.Post_raza);
        TextView gen = findViewById(R.id.Post_gen);
        TextView edad = findViewById(R.id.Post_edad);
        TextView des = findViewById(R.id.Post_desc);
        ImageView foto = findViewById(R.id.backdrop);
        name.setAnimation(fromLeft);
        raza.setAnimation(fromRight);
        gen.setAnimation(fromLeft);
        edad.setAnimation(fromRight);
        des.setAnimation(fromLeft);
        foto.setAnimation(fromDown);

        name.setText(posts.get(pos).getName());
        raza.setText(posts.get(pos).getCategoria());
        gen.setText(posts.get(pos).getGenero());
        edad.setText(posts.get(pos).getEdad());
        des.setText(posts.get(pos).getDescripcion());
        Glide.with(PostDetailActivity.this).load(posts.get(pos).getUrlphotopost()).fitCenter().centerCrop().into(foto);
        toolbar.setTitle(posts.get(pos).getName());


        Log.d("post", "show data size: " + posts.size() + "");

    }


}
