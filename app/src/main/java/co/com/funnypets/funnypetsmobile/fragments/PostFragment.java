package co.com.funnypets.funnypetsmobile.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.com.funnypets.funnypetsmobile.R;
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
    List<Post>  posts=new ArrayList<>();

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
        // Inflate the layout for this fragment
        Log.d("home","inflate");
        View view=inflater.inflate(R.layout.fragment_post, container, false);
        recyclerView=view.findViewById(R.id.recycler_view_post);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Usuario usuario=new Usuario();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("posts");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                posts= (List<Post>) dataSnapshot.getValue();
                Log.d("post","#post");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
     /*   usuario.setUrlfoto("https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2F20881853_1749211925092389_4384820394433587108_n.jpg?alt=media&token=af5e99fd-6936-4c7c-8463-0f2096560e29");
        usuario.setUsuario("Mario Herrera");
        Post post;
        post=new Post(""
                ,usuario,"Mi perrito ya no puedo vivir mas con nosotros ya que nos vamos del pais, asi que con el dolor del alma tendremos que dejarlo en adopcion, el es super tierno",20,"https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2Fgolden.jpg?alt=media&token=f1a6bd9d-7d0e-4f25-a771-9a13ade9c757");
        posts.add(post);
        post=new Post("el gato lendo",usuario,"El es ratatouille, es un super buen compañero, pero ya no puede estar mas conmigo ya que mi gato se lo quiere comer :'(",20,"https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2Fmaxresdefault.jpg?alt=media&token=3cedbd42-3c84-4c3e-9c96-74b087497d8b");
        posts.add(post);
        usuario=new Usuario();
        usuario.setUrlfoto("https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2F21687944_1674164502595781_6556764894954520245_n.jpg?alt=media&token=cc9db31a-855e-4896-a85d-2fdb7eb328c0");
        usuario.setUsuario("Juan Felipe");
        post=new Post("el perico lendo",usuario,"Ella es loreta, la queremos mandar a una finca para que tenga mas liberta, algun interesado porvafor comuniquese",20,"https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2Floros-800x375.jpg?alt=media&token=b54e34ee-89fe-4ac1-af21-1aa341320847");
        posts.add(post);
        post=new Post("la perra esa",usuario,"Ella es dolores, acaba de dar a luz y estamos regalando sus crias, interesados por inbox",20,"https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2Fimg_por_que_mi_gato_no_se_deja_tocar_22745_paso_0_600.jpg?alt=media&token=ae62aaea-c1bc-4e08-b253-7bb73ecfb496");
        posts.add(post);
        usuario=new Usuario();
        usuario.setUrlfoto("https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2FDSCN8293.JPG?alt=media&token=a15a4445-08cf-45d2-8b05-66c084309df0");
        usuario.setUsuario("Stiven Perdomo");
        post=new Post("el tiger",usuario,"mi bigotes esta muy enfermo alguen recomienda un buen lugar para que me lo revisen",20,"https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2Fgato-enfermo.jpg?alt=media&token=584353d8-51ad-4325-b6fd-e843d607f494");
        posts.add(post);
       // ref.setValue(posts);
       */
        adapter=new PostAdapter(getContext(),posts);
        recyclerView.setAdapter(adapter);
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
