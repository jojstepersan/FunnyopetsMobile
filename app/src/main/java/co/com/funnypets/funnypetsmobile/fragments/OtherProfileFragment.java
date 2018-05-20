package co.com.funnypets.funnypetsmobile.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
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
import co.com.funnypets.funnypetsmobile.adapters.PhotosAdapter;
import co.com.funnypets.funnypetsmobile.entities.Post;
import co.com.funnypets.funnypetsmobile.entities.Usuario;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OtherProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OtherProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OtherProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static Usuario usuario;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private PhotosAdapter adapter;
    private List<Post> posts;
    private TextView username;
    private ImageView foto;
    private ImageView portada;
    private TextView ctnp;
    int i = 0;
    private String Correo;
    private OnFragmentInteractionListener mListener;

    public OtherProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OtherProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OtherProfileFragment newInstance(String param1, String param2) {
        OtherProfileFragment fragment = new OtherProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_other_profile, container, false);
        username = view.findViewById(R.id.love_music);
        foto = view.findViewById(R.id.backdrop);
        portada = view.findViewById(R.id.portada);
        ctnp = view.findViewById(R.id.cntpst);
        Correo = usuario.getCorreo();
        String nombre = usuario.getUsuario();
        String fotou = usuario.getUrlfoto();
        if (fotou == null) {
            foto.setImageResource(R.drawable.sinperfil);
        } else {
            Glide.with(this).load(fotou).into(foto);
        }
        username.setText(nombre);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        posts = new ArrayList<>();
        Post a = new Post("Señor bigotes", new Usuario(), "", 13, "https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2Fgato-enfermo.jpg?alt=media&token=584353d8-51ad-4325-b6fd-e843d607f494");
        posts.add(a);
        a = new Post("golden wolf", new Usuario(), "", 13, "https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2Fgolden.jpg?alt=media&token=f1a6bd9d-7d0e-4f25-a771-9a13ade9c757");
        posts.add(a);

        a = new Post("doriti", new Usuario(), "", 13, "https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2Fimg_por_que_mi_gato_no_se_deja_tocar_22745_paso_0_600.jpg?alt=media&token=ae62aaea-c1bc-4e08-b253-7bb73ecfb496");
        posts.add(a);

        a = new Post("loreta", new Usuario(), "", 13, "https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2Floros-800x375.jpg?alt=media&token=b54e34ee-89fe-4ac1-af21-1aa341320847");
        posts.add(a);

        a = new Post("ratatouille", new Usuario(), "", 13, "https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2Fmaxresdefault.jpg?alt=media&token=3cedbd42-3c84-4c3e-9c96-74b087497d8b");
        posts.add(a);

         a = new Post("firulais", new Usuario(),"",13, "https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2Fdownload.jpg?alt=media&token=563cf696-bd98-4586-8ddc-4054e56c4760");
        posts.add(a);

        a = new Post("coco", new Usuario(),"",13, "https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2F82a78fb04d2bec18a6194762e51d2328.jpg?alt=media&token=2fd6b703-0bff-454a-bcb4-d8f1db54293e");
        posts.add(a);

         a = new Post("viento salvaje", new Usuario(),"",13, "https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2Fcuarto-de-milla.jpg?alt=media&token=a7bbc3f5-14c8-4f16-a3fb-c16e21f96965");
        posts.add(a);

          a = new Post("gemelos maravilla", new Usuario(),"",13, "https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2F_96315422_gettyimages-579742898.jpg?alt=media&token=35e1bd32-606f-4ad8-86e4-fa8f48d799c1");
        posts.add(a);

          a = new Post("lamento boliviano", new Usuario(),"",13, "https://firebasestorage.googleapis.com/v0/b/funnypetsandroid.appspot.com/o/foto_perfil%2FSLIDER-Europesecultuurvogels.jpg?alt=media&token=4570a44d-76a4-4812-854c-291a91e93076");
        posts.add(a);
        adapter = new PhotosAdapter(getContext(), posts);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
     //   prepareAlbums();
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

    private void prepareAlbums() {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("posts");
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
            prepareAlbums();
        }

        Log.d("post", "show data size: " + posts.size());
        ctnp.setText(String.valueOf(posts.size()));
        if (posts.size() > 0) {
            Glide.with(getContext()).load(posts.get(0).getUrlphotopost()).fitCenter().centerCrop().into(portada);
        }

    }
}
