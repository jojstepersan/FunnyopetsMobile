package co.com.funnypets.funnypetsmobile.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import co.com.funnypets.funnypetsmobile.activities.ChatActivity;
import co.com.funnypets.funnypetsmobile.entities.Post;
import co.com.funnypets.funnypetsmobile.entities.Usuario;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PostDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PostDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
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
    private TextView name;
    private TextView raza;
    private TextView gen;
    private TextView edad;
    private TextView des;
    private ImageView foto;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PostDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PostDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostDetailsFragment newInstance(String param1, String param2) {
        PostDetailsFragment fragment = new PostDetailsFragment();
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
        View view = inflater.inflate(R.layout.fragment_post_details, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        //  view.setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Gracias por adoptar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent2;
                intent2 = new Intent(getContext(), ChatActivity.class);
                startActivity(intent2);


            }
        });

        name = view.findViewById(R.id.Post_name2);
        raza = view.findViewById(R.id.Post_raza);
        gen = view.findViewById(R.id.Post_gen);
        edad = view.findViewById(R.id.Post_edad);
        des = view.findViewById(R.id.Post_desc);
        foto = view.findViewById(R.id.backdrop);
        return view;
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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


        pos = 0;

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
        name.setText(posts.get(pos).getName());
        raza.setText(posts.get(pos).getCategoria());
        gen.setText(posts.get(pos).getGenero());
        edad.setText(posts.get(pos).getEdad());
        des.setText(posts.get(pos).getDescripcion());
        Glide.with(getContext()).load(posts.get(pos).getUrlphotopost()).fitCenter().centerCrop().into(foto);
        toolbar.setTitle(posts.get(pos).getName());

    }


}
