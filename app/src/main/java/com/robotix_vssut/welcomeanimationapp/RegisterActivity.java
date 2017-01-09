package com.robotix_vssut.welcomeanimationapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.robotix_vssut.welcomeanimationapp.SetupActivity.MyPREFERENCES;

public class RegisterActivity extends AppCompatActivity {
    private EditText mNameField;
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mRegiterBtn;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgress;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

       mAuth=FirebaseAuth.getInstance();
        mProgress=new ProgressDialog(this);

        mEmailField=(EditText)findViewById(R.id.emailField);
        mNameField=(EditText)findViewById(R.id.nameField);
        mPasswordField=(EditText)findViewById(R.id.passwordField);
        mRegiterBtn=(Button)findViewById(R.id.registerBtn);



        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users");



        mRegiterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startRegister();
            }
        });
    }

    private void startRegister() {
        final String name=mNameField.getText().toString().trim();
        String email=mEmailField.getText().toString().trim();
        String password=mPasswordField.getText().toString().trim();
        if(!TextUtils.isEmpty(name)&& !TextUtils.isEmpty(email)&& !TextUtils.isEmpty(password)) {

              mProgress.setMessage("Signing up....");
            mProgress.show();
              mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful()) {

                             String user_id=mAuth.getCurrentUser(). getUid();
                         DatabaseReference current_user_db= mDatabase.child(user_id);
                          current_user_db.child("name").setValue(name);
                          current_user_db.child("image").setValue("default");
                          mProgress.dismiss();

                            storeDataOffline();
                           Intent mainIntent=new Intent(RegisterActivity.this,MainActivity.class);
                          mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                          startActivity(mainIntent);


                      }
                  }
              });
         }
        }

    private void storeDataOffline() {

        final String name=mNameField.getText().toString().trim();
        final String email=mEmailField.getText().toString().trim();

        SharedPreferences sharedPreferences_profile = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences_profile.edit();
        editor.putString("LOCAL-name",name);
        editor.putString("LOCAL-phone",email);
        editor.apply();

    }

}

