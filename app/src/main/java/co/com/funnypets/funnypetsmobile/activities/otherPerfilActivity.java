package co.com.funnypets.funnypetsmobile.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.com.funnypets.funnypetsmobile.R;
import co.com.funnypets.funnypetsmobile.adapters.PhotosAdapter;
import co.com.funnypets.funnypetsmobile.entities.Usuario;
import co.com.funnypets.funnypetsmobile.entities.Post;
import co.com.funnypets.funnypetsmobile.fragments.ProfileFragment;


public class otherPerfilActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PhotosAdapter adapter;
    private List<Post> posts;
    private Usuario usuario;
    private TextView username;
    private ImageView foto;
    private ImageView portada;
    private TextView  ctnp;
    int i =0;
    private String Correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otherperfil);
        username=findViewById(R.id.love_music);
        foto=findViewById(R.id.backdrop);
        portada=findViewById(R.id.portada);
        ctnp=findViewById(R.id.cntpst);
        Correo=getIntent().getStringExtra("correo");
        String nombre= getIntent().getStringExtra("name");
        String fotou= getIntent().getStringExtra("foto");
        if(fotou==null){
            foto.setImageResource(R.drawable.sinperfil);
        }else{
            Glide.with(this).load(fotou).into(foto);
        }
        username.setText(nombre);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        posts = new ArrayList<>();
        adapter = new PhotosAdapter(this, posts);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);







    }
    private void showData(DataSnapshot ds) {
        i=0;

        for (DataSnapshot dataSnapshot : ds.getChildren()) {

            Post post = new Post();
            post.setAdopcion(ds.child((i) + "").getValue(Post.class).isAdopcion());
            post.setCategoria(ds.child((i) + "").getValue(Post.class).getCategoria());
            post.setDescripcion(ds.child((i) + "").getValue(Post.class).getDescripcion());
            post.setUrlphotopost(ds.child((i) + "").getValue(Post.class).getUrlphotopost());
            post.setUsuario(ds.child((i) + "").getValue(Post.class).getUsuario());
            post.setNumOfLikes(ds.child((i) + "").getValue(Post.class).getNumOfLikes());
            post.setName(ds.child((i) + "").getValue(Post.class).getName());
            post.setEdad(ds.child((i) + "").getValue(Post.class).getEdad());
         if(post.getUsuario().getCorreo().equals(getIntent().getStringExtra("correo"))){
                posts.add(post);
         }
            i++;
            adapter = new PhotosAdapter(this, posts);
            recyclerView.setAdapter(adapter);
        }

        Log.d("post", "show data size: " + posts.size());
        ctnp.setText(String.valueOf(posts.size()));
        if(posts.size()>0) {
            Glide.with(this).load(posts.get(0).getUrlphotopost()).fitCenter().centerCrop().into(portada);
        }

    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    @Override
    public void onResume(){
        super.onResume();
        DatabaseReference myRef= FirebaseDatabase.getInstance().getReference("posts");

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



    }
    public String correo(){
        String correo=getIntent().getStringExtra("correo");
        return correo;
    }
}
