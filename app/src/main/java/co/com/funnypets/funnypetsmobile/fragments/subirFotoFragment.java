package co.com.funnypets.funnypetsmobile.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import co.com.funnypets.funnypetsmobile.R;
import co.com.funnypets.funnypetsmobile.activities.MainActivity;
import co.com.funnypets.funnypetsmobile.entities.Post;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link subirFotoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link subirFotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class subirFotoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mFirebaseAuth;//mAuth
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private String userID;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference refStr;
    private static final int GALERY_INTENT = 1;
    private ImageView image;
    private Uri u;
    private Post post;

    public subirFotoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment subirFotoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static subirFotoFragment newInstance(String param1, String param2) {
        subirFotoFragment fragment = new subirFotoFragment();
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
        View view = inflater.inflate(R.layout.fragment_subir_foto, container, false);
        image = view.findViewById(R.id.image_subir_foto);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Image_touch", "imagen....");
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/");
                startActivityForResult(intent.createChooser(intent, "Seleccione una imagen"), GALERY_INTENT);

            }
        });
        final Spinner spinner = (Spinner) view.findViewById(R.id.categorias_spinner);
        final Spinner spinnerG = (Spinner) view.findViewById(R.id.categorias_spinner2);
        final EditText descripcion = view.findViewById(R.id.editText);
        final EditText name = view.findViewById(R.id.name_Edit);
        final EditText edad = view.findViewById(R.id.edad_Edit);
        final Switch adopcion=view.findViewById(R.id.esta_adoptar);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.seso, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerG.setAdapter(adapter2);
        Button btnSubirFoto = view.findViewById(R.id.btn_subir_foto);
        btnSubirFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refStr = storage.getReference("imagenes_posts");//imagenes_post
                final StorageReference fotoReferencia = refStr.child(u.getLastPathSegment());
                fotoReferencia.putFile(u).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        post = new Post();
                        adopcion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                post.setAdopcion(buttonView.isChecked());
                            }
                        });
                        post.setDescripcion(descripcion.getText().toString());
                        post.setName(name.getText().toString());
                        post.setEdad(edad.getText().toString());
                        post.setNumOfLikes(0);
                        post.setUsuario(PostFragment.usuario);
                        post.setCategoria(spinner.getSelectedItem().toString());
                        post.setGenero(spinnerG.getSelectedItem().toString());
                        post.setUrlphotopost(taskSnapshot.getDownloadUrl().toString());
                        Toast.makeText(getContext(), "Se subio la foto exitosamente ", Toast.LENGTH_SHORT).show();
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("posts/" + PostFragment.i);
                        ref.setValue(post);
                        Intent intent2= new Intent(getActivity(), MainActivity.class);
                        getActivity().startActivity(intent2);
                    }
                });
                //post.setUsuario(FirebaseAuth.getInstance().getCurrentUser());
            }
        });
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALERY_INTENT && resultCode == -1) {
            u = data.getData();
            image.setImageURI(u);
            /* */
        }
    }
}
