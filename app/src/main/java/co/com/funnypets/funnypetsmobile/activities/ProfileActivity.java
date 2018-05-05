package co.com.funnypets.funnypetsmobile.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
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

public class ProfileActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private PhotosAdapter adapter;
    private List<Post> albumList;
    private Usuario usuario= new Usuario();
    private String img="IMG";
    private static final String TAG = "LogsAndroid";
    private TextView ctnp;
    private TextView ctnf;
    private TextView ctnfo;
    private TextView nombre;
    private ImageView foto;
    private Button editProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ctnp=findViewById(R.id.cntpst);
        ctnf=findViewById(R.id.cntfollowing);
        ctnfo=findViewById(R.id.cntfollowers);
        nombre=findViewById(R.id.love_music);
        foto=findViewById(R.id.backdrop);
        editProfile=findViewById(R.id.edit_profile_button);
       // img=usuariox.getUsuario();
        nombre.setText("Mario Herrera");//(usuariox.getUsuario());
        ctnp.setText(10+"");//(String.valueOf(usuariox.getCntFotos()));
        ctnf.setText(20+"");//(String.valueOf(usuariox.getCntFollowing()));
        ctnfo.setText(15+"");//(String.valueOf(usuariox.getCntFollowers()));
        Glide.with(ProfileActivity.this).load("https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2F20881853_1749211925092389_4384820394433587108_n.jpg?alt=media&token=af5e99fd-6936-4c7c-8463-0f2096560e29").fitCenter().centerCrop().into(foto);


        //   Intent intent= getIntent();
        //   Bundle b =intent.getExtras();
        //   String userid=b.getString("ID");



        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
       // prepareAlbums();
        adapter = new PhotosAdapter(this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent d = new Intent(ProfileActivity.this,EditProfile.class);
                startActivity(d);
            }
        });
        prepareAlbums();
/*
        try {
            Glide.with(this).load(R.drawable.profile2).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }


    @Override
    protected void onResume() {
        super.onResume();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
//
        //   final DatabaseReference myRef = database.getReference("Usuarios");
        //   final DatabaseReference myRefid = myRef.child(userid);
        //final DatabaseReference myRefidpost = myRefid.child("post");

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = database.getReference("Usuarios/"+user.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario usuariox = dataSnapshot.getValue(Usuario.class);
                img=usuariox.getUsuario();
                nombre.setText("Mario Herrera");//(usuariox.getUsuario());
                ctnp.setText(10+"");//(String.valueOf(usuariox.getCntFotos()));
                ctnf.setText(20+"");//(String.valueOf(usuariox.getCntFollowing()));
                ctnfo.setText(15+"");//(String.valueOf(usuariox.getCntFollowers()));
                Glide.with(ProfileActivity.this).load("https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2F20881853_1749211925092389_4384820394433587108_n.jpg?alt=media&token=af5e99fd-6936-4c7c-8463-0f2096560e29").fitCenter().centerCrop().into(foto);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });


    }
    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);

        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(img);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.post1,
                R.drawable.post3,
                R.drawable.post4
               };

        Post a = new Post("Señor bigotes", new Usuario(),"",13, "https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2Fgato-enfermo.jpg?alt=media&token=584353d8-51ad-4325-b6fd-e843d607f494");
        albumList.add(a);

        a = new Post("golden wolf", new Usuario(),"",13, "https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2Fgolden.jpg?alt=media&token=f1a6bd9d-7d0e-4f25-a771-9a13ade9c757");
        albumList.add(a);

        a = new Post("doriti", new Usuario(),"",13, "https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2Fimg_por_que_mi_gato_no_se_deja_tocar_22745_paso_0_600.jpg?alt=media&token=ae62aaea-c1bc-4e08-b253-7bb73ecfb496");
        albumList.add(a);

        a = new Post("loreta", new Usuario(),"",13, "https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2Floros-800x375.jpg?alt=media&token=b54e34ee-89fe-4ac1-af21-1aa341320847");
        albumList.add(a);

        a = new Post("ratatouille", new Usuario(),"",13, "https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2Fmaxresdefault.jpg?alt=media&token=3cedbd42-3c84-4c3e-9c96-74b087497d8b");
        albumList.add(a);

        a = new Post("firulais", new Usuario(),"",13, "https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2Fdownload.jpg?alt=media&token=563cf696-bd98-4586-8ddc-4054e56c4760");
        albumList.add(a);

        a = new Post("coco", new Usuario(),"",13, "https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2F82a78fb04d2bec18a6194762e51d2328.jpg?alt=media&token=2fd6b703-0bff-454a-bcb4-d8f1db54293e");
        albumList.add(a);

        a = new Post("viento salvaje", new Usuario(),"",13, "https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2Fcuarto-de-milla.jpg?alt=media&token=a7bbc3f5-14c8-4f16-a3fb-c16e21f96965");
        albumList.add(a);

        a = new Post("gemelos maravilla", new Usuario(),"",13, "https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2F_96315422_gettyimages-579742898.jpg?alt=media&token=35e1bd32-606f-4ad8-86e4-fa8f48d799c1");
        albumList.add(a);

        a = new Post("lamento boliviano", new Usuario(),"",13, "https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2FSLIDER-Europesecultuurvogels.jpg?alt=media&token=4570a44d-76a4-4812-854c-291a91e93076");
        albumList.add(a);

       adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
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

    public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
        usuario = dataSnapshot.getValue(Usuario.class);
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
