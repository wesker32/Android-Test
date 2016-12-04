package com.example.wi.cadastro;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    mAuth = FirebaseAuth.getInstance();

    private Button btn_Registrar;
    private EditText edt_email;
    private EditText edt_pass;
    private TextView txtSign;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuthListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        }

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        btn_Registrar = (Button)findViewById(R.id.btn_Registrar);
        edt_email = (EditText)findViewById(R.id.edt_email);
        edt_pass = (EditText)findViewById(R.id.edt_pass);
        txtSign = (TextView) findViewById(R.id.txtSign);

        btn_Registrar.setOnClickListener(this);
        txtSign.setOnClickListener(this);
    }

    private void registrerUser(){
        String email = edt_email.getText().toString().trim();
        String password = edt_pass.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter Your Email",Toast.LENGTH_SHORT).show();
           //Para a a execuçao da funcao
            return;

        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
            //Para a funcao do codigo
            return;

        }
        //Para a validacao press ok
        progressDialog.setMessage("Registered User....");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                 .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()) {

                             Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                         }else{
                             Toast.makeText(MainActivity.this, "Could not registered .. please  try again", Toast.LENGTH_SHORT).show();

                         }
                     }
                 });
    }

    @Override
    public void onClick(View view) {
        if (view == btn_Registrar){
            registrerUser();
        }
        if (view == txtSign){

        }

    }
}
