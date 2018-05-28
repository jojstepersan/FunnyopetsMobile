package co.com.funnypets.funnypetsmobile.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import co.com.funnypets.funnypetsmobile.R;
import co.com.funnypets.funnypetsmobile.entities.Usuario;
import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtNombre, txtCorreo, txtContraseña;
    private Button btnRegistrar;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private CircleImageView foto;
    private Usuario usuario;
    private static final int GALERY_INTENT = 1;
    private Uri u;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference refStr;
    private String urlFoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Animation fromLeft = AnimationUtils.loadAnimation(this, R.anim.fromleft);
        Animation fromRight = AnimationUtils.loadAnimation(this, R.anim.fromright);
        Animation fromDown = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        Animation fromUp = AnimationUtils.loadAnimation(this, R.anim.fromtop);
        setContentView(R.layout.activity_register);
        txtNombre = (EditText) findViewById(R.id.username);
        txtNombre.setAnimation(fromUp);
        txtCorreo = (EditText) findViewById(R.id.email);
        txtCorreo.setAnimation(fromLeft);
        txtContraseña = (EditText) findViewById(R.id.password);
        txtContraseña.setAnimation(fromRight);
        usuario = new Usuario();
        btnRegistrar = (Button) findViewById(R.id.btn_register);
        btnRegistrar.setAnimation(fromDown);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        foto = findViewById(R.id.reg_foto);
        foto.setAnimation(fromRight);
        foto.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/");
                startActivityForResult(intent.createChooser(intent, "Seleccione una imagen"), GALERY_INTENT);
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String correo = txtCorreo.getText().toString();
                final String nombre = txtNombre.getText().toString();
                if (isValidEmail(correo) && validarNombre(nombre)) {
                    String contraseña = txtContraseña.getText().toString();
                    refStr = storage.getReference("foto_perfil");//imagenes_post
                    refStr.child(u.getLastPathSegment()).putFile(u).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            urlFoto=taskSnapshot.getDownloadUrl().toString();
                        }
                    });
                    mAuth.createUserWithEmailAndPassword(correo, contraseña)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        usuario.setCntFollowers(0);
                                        usuario.setCntFollowing(0);
                                        usuario.setCntFotos(0);
                                        usuario.setCorreo(correo);
                                        usuario.setUsuario(nombre);
                                        usuario.setUrlfoto(urlFoto);
                                        FirebaseUser currentUser = mAuth.getCurrentUser();
                                        DatabaseReference reference = database.getReference("Usuarios/" + currentUser.getUid());
                                        reference.setValue(usuario);
                                        finish();
                                        Toast.makeText(RegisterActivity.this, "Se registro correctamente.", Toast.LENGTH_SHORT).show();

                                        // Sign in success, update UI with the signed-in user's information

                                    } else {

                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(RegisterActivity.this, "Error al registrarse.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(RegisterActivity.this, "Validaciones funcionando.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALERY_INTENT && resultCode == -1) {
            u = data.getData();
            foto.setImageURI(u);
            /* */
        }
    }


    public boolean validarNombre(String nombre) {
        return !nombre.isEmpty();
    }
}