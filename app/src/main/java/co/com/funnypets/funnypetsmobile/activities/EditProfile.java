package co.com.funnypets.funnypetsmobile.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import co.com.funnypets.funnypetsmobile.R;

public class EditProfile extends AppCompatActivity {

    private LoginActivity.UserLoginTask mAuthTask = null;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Context mContext;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private AutoCompleteTextView name;
    private AutoCompleteTextView email;
    private EditText pass;
    private Button boton;
    public ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name=findViewById(R.id.name_edit);
        email=findViewById(R.id.email_edit);
        pass=findViewById(R.id.password_edit);
        boton=findViewById(R.id.save_changes_btn);
        dialog= new ProgressDialog(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference reference = database.getReference("Usuarios/"+FirebaseAuth.getInstance().getCurrentUser().getUid());


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre =name.getText().toString();
                String pasword =pass.getText().toString();
                final String correo=email.getText().toString();
                final String TAG="hols";
                dialog.setMessage("Editing your profile");
                dialog.show();
                FirebaseAuth.getInstance().getCurrentUser().updatePassword(pasword)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(Task<Void> task) {
                                if (task.isSuccessful()) {
                                    dialog.dismiss();
                                    Toast.makeText(getApplicationContext(),"your profile has benn updated",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                FirebaseAuth.getInstance().getCurrentUser().updateEmail(correo)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(Task<Void> task) {
                                if (task.isSuccessful()) {

                                }
                            }
                        });

                HashMap<String, Object> result = new HashMap<>();
                result.put("usuario",nombre);
                reference.updateChildren(result);

                HashMap<String, Object> result2 = new HashMap<>();
                result2.put("correo",correo);
                reference.updateChildren(result2);
            }
        });
    }

}
