package co.com.funnypets.funnypetsmobile.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

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
import co.com.funnypets.funnypetsmobile.activities.PostDetailActivity;
import co.com.funnypets.funnypetsmobile.adapters.PostAdapter;
import co.com.funnypets.funnypetsmobile.entities.Post;
import co.com.funnypets.funnypetsmobile.entities.Usuario;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PostFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private PostAdapter adapter;
    private RecyclerView recyclerView;
    List<Post> posts = new ArrayList<>();
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mFirebaseAuth;//mAuth
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    public static String userID;
    public static Usuario usuario;
    public static int i = 0;
    private BottomNavigationView mDrawer;


    public PostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostFragment newInstance(String param1, String param2) {
        PostFragment fragment = new PostFragment();
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
        //traer la base de datos
        //mDrawer=getActivity().findViewById(R.id.bottomNavViewBar);
       // mDrawer.(Gravity.LEFT);
        //mDrawer.(Gravity.LEFT);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("posts");
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        userID = user.getUid();
        DatabaseReference userRef = mFirebaseDatabase.getReference().child("Usuarios");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usuario=new Usuario();
                usuario.setUrlfoto(dataSnapshot.child(userID).getValue(Usuario.class).getUrlfoto());
                usuario.setUsuario(dataSnapshot.child(userID).getValue(Usuario.class).getUsuario());
                usuario.setCorreo(dataSnapshot.child(userID).getValue(Usuario.class).getCorreo());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_post);
        recyclerView.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fromleft));
        LinearLayoutManager layoutLinear = new LinearLayoutManager(getContext());
        layoutLinear.setReverseLayout(true);
        layoutLinear.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutLinear);
        recyclerView.setAdapter(adapter);
        return view;
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
            posts.add(post);
            i++;
            adapter = new PostAdapter(getContext(), posts);
            recyclerView.setAdapter(adapter);
        }

        Log.d("post", "show data size: " + posts.size());

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
//            throw new RuntimeException(context.toString()
            //                  + " must implement OnFragmentInteractionListener");
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
}