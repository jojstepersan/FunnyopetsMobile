package co.com.funnypets.funnypetsmobile.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
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
import co.com.funnypets.funnypetsmobile.activities.EditProfile;
import co.com.funnypets.funnypetsmobile.activities.LoginActivity;
import co.com.funnypets.funnypetsmobile.activities.MainActivity;
import co.com.funnypets.funnypetsmobile.activities.PieActivity;
import co.com.funnypets.funnypetsmobile.adapters.PhotosAdapter;
import co.com.funnypets.funnypetsmobile.entities.Post;
import co.com.funnypets.funnypetsmobile.entities.Usuario;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private PhotosAdapter adapter;
    private List<Post> posts;
    private Usuario usuario = new Usuario();
    private String img = "IMG";
    private static final String TAG = "LogsAndroid";
    private TextView ctnp;
    private TextView ctnf;
    private TextView ctnfo;
    private TextView nombre;
    private ImageView foto;
    private ImageView portada;
    private Button editProfile;
    private Button signout;
    private Button dashboard;
    int i = 0;


    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Animation fromLeft = AnimationUtils.loadAnimation(getContext(), R.anim.fromleft);
        Animation fromRight = AnimationUtils.loadAnimation(getContext(), R.anim.fromright);
        Animation fromDown = AnimationUtils.loadAnimation(getContext(), R.anim.frombottom);
        Animation fromUp = AnimationUtils.loadAnimation(getContext(), R.anim.fromtop);
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setAnimation(fromUp);
        ctnp = view.findViewById(R.id.cntpst);
        ctnp.setAnimation(fromUp);
        ctnf = view.findViewById(R.id.cntfollowing);
        ctnf.setAnimation(fromUp);
        ctnfo = view.findViewById(R.id.cntfollowers);
        ctnfo.setAnimation(fromUp);
        nombre = view.findViewById(R.id.love_music);
        nombre.setAnimation(fromLeft);
        foto = view.findViewById(R.id.backdrop);
        foto.setAnimation(fromUp);
        editProfile = view.findViewById(R.id.edit_profile_button);
        editProfile.setAnimation(fromLeft);
        portada = view.findViewById(R.id.portada);
        portada.setAnimation(fromDown);
        nombre.setText("Mario Herrera");//(usuariox.getUsuario());
        ctnp.setText(10 + "");//(String.valueOf(usuariox.getCntFotos()));
        ctnf.setText(20 + "");//(String.valueOf(usuariox.getCntFollowing()));
        ctnfo.setText(15 + "");//(String.valueOf(usuariox.getCntFollowers()));
        signout = view.findViewById(R.id.profile_signout);
        signout.setAnimation(fromRight);
        ctnp.setText(10 + "");//(String.valueOf(usuariox.getCntFotos()));
        ctnf.setText(20 + "");//(String.valueOf(usuariox.getCntFollowing()));
        ctnfo.setText(15 + "");//(String.valueOf(usuariox.getCntFollowers()));
        signout = view.findViewById(R.id.profile_signout);
        dashboard = view.findViewById(R.id.settings_button);
        dashboard.setAnimation(fromRight);
        dashboard.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Transition t=new Explode();
                t.setDuration(1000);
                t.setInterpolator(new DecelerateInterpolator());
                getActivity().getWindow().setExitTransition(t);
                startActivity(new Intent(getContext(), PieActivity.class), ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity()).toBundle());

            }
        });


        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Transition t=new Fade();
                t.setDuration(1000);
                t.setInterpolator(new DecelerateInterpolator());
                getActivity().getWindow().setExitTransition(t);
                startActivity(new Intent(getContext(), LoginActivity.class) ,ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity()).toBundle());


            }
        });
        Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2F20881853_1749211925092389_4384820394433587108_n.jpg?alt=media&token=af5e99fd-6936-4c7c-8463-0f2096560e29").fitCenter().centerCrop().into(foto);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setAnimation(fromDown);
        posts = new ArrayList<>();
        adapter = new PhotosAdapter(getContext(), posts);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent d = new Intent(getContext(), EditProfile.class);
                startActivity(d);
            }
        });
        prepareAlbums();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("Usuarios/" + user.getUid());


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario ux = dataSnapshot.getValue(Usuario.class);
                nombre.setText(ux.getUsuario());//(usuariox.getUsuario());
                //ctnp.setText(String.valueOf(ux.getCntFotos()));//(String.valueOf(usuariox.getCntFotos()));
                ctnf.setText(String.valueOf(ux.getCntFollowing()));//(String.valueOf(usuariox.getCntFollowing()));
                ctnfo.setText(String.valueOf(ux.getCntFollowers()));
                if (ux.getUrlfoto() == null) {
                    foto.setImageResource(R.drawable.sinperfil);
                } else {
                    Glide.with(getContext()).load(ux.getUrlfoto()).fitCenter().centerCrop().into(foto);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);


        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void prepareAlbums() {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("posts");

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


        adapter.notifyDataSetChanged();
    }

    private void showData(DataSnapshot ds) {
        i = 0;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
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
            try {
                if (post.getUsuario().getCorreo().equals(user.getEmail())) {
                    posts.add(post);
                }
            } catch (Exception e) {

            }
            i++;
            adapter = new PhotosAdapter(getContext(), posts);
            recyclerView.setAdapter(adapter);
        }

        Log.d("post", "show data size: " + posts.size());
        ctnp.setText(String.valueOf(posts.size()));
        if (posts.size() > 0) {
            Glide.with(getContext()).load(posts.get(0).getUrlphotopost()).fitCenter().centerCrop().into(portada);
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
}
