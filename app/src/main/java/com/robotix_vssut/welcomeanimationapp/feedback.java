package com.robotix_vssut.welcomeanimationapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
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

public class feedback extends AppCompatActivity {

    private EditText mfeedbackNameField;
    private EditText mfeedbackDescription;
    private Button   mfeedbacksSubmit;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgress;
    private DatabaseReference mDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(android.R.style.Theme_Black_NoTitleBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        mAuth=FirebaseAuth.getInstance();
        mProgress=new ProgressDialog(this);

        mfeedbackNameField=(EditText)findViewById(R.id.feedbackNameField1);
        mfeedbackDescription=(EditText)findViewById(R.id.feedbackDescription);
        mfeedbacksSubmit=(Button)findViewById(R.id.submitfeeds);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Feedback_details");


        mfeedbacksSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startfeedback_send();
            }
        });
    }

    private void startfeedback_send() {

        final String name=mfeedbackNameField.getText().toString().trim();
        String description=mfeedbackDescription.getText().toString().trim();

        if(!TextUtils.isEmpty(name)&& !TextUtils.isEmpty(description) ) {

            mProgress.setMessage("Submitting....");
            mProgress.show();

            //DatabaseReference current_user_feedback = mDatabase.push();
                String user_id=mAuth.getCurrentUser(). getUid();
                DatabaseReference current_user_feedback= mDatabase.child(user_id);
            current_user_feedback.child("feedback_name").setValue(name);
            current_user_feedback.child("feedback_description").setValue(description);

                mProgress.dismiss();

            Toast.makeText(feedback.this, "Upload Done", Toast.LENGTH_SHORT).show();
                Intent mainIntent=new Intent(feedback.this,MainActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mainIntent);


            }
        else {
            mProgress.dismiss();
            Toast.makeText(this, "some fields are missing", Toast.LENGTH_SHORT).show();
        }
    }


}
