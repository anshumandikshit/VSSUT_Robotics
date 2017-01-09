package com.robotix_vssut.welcomeanimationapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class SetupActivity extends AppCompatActivity {
    private ImageButton mSetupImageBtn;
    private EditText  mName;
    private EditText mPhone;
    private Button mSubmitBtn;
    private Uri mImageUri=null;
    private static final int GALLERY_REQUEST=1;
    private DatabaseReference mDatabaseUsers;
    private FirebaseAuth mAuth;
    private StorageReference mStoragereImage;
    private ProgressDialog mProgress;
    public static final String  MyPREFERENCES="OfflineDatabase";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        mProgress=new ProgressDialog(this);
       mStoragereImage= FirebaseStorage.getInstance().getReference().child("profile images");
        mAuth=FirebaseAuth.getInstance();
       mDatabaseUsers= FirebaseDatabase.getInstance().getReference().child("User details");
        mSetupImageBtn=(ImageButton)findViewById(R.id.setupImageBtn);
        mName=(EditText)findViewById(R.id.setupNameField);
        mPhone=(EditText)findViewById(R.id.setupPhone);
        mSubmitBtn=(Button)findViewById(R.id.setupSubmitBtn);

           mSubmitBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   startSetupAcct();
               }
           });

     mSetupImageBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             Intent galleryintent=new Intent();
             galleryintent.setAction(Intent.ACTION_GET_CONTENT);
             galleryintent.setType("image/*");
             startActivityForResult(galleryintent,GALLERY_REQUEST);


         }
     });

    getDetailsFromFirebase();

    }

    UserDetails userDetails;

    private void getDetailsFromFirebase() {
        final String user_id = mAuth.getCurrentUser().getUid();
        DatabaseReference current_user=mDatabaseUsers.child(user_id);

        current_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userDetails = dataSnapshot.getValue(UserDetails.class);
                storeDataOffline(userDetails.getName(),userDetails.getMobile());
                populateFields();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void populateFields() {
        SharedPreferences sharedPreferences = getSharedPreferences(SetupActivity.MyPREFERENCES,Context.MODE_PRIVATE);
        String data_name = sharedPreferences.getString("LOCAL-name", " ");
        String data_number = sharedPreferences.getString("LOCAL-phone"," ");
        mName.setText(data_name);
        mPhone.setText(data_number);
    }

    private void startSetupAcct() {


        final String name=mName.getText().toString().trim();
        final String phone=mPhone.getText().toString().trim();

        final String user_id=mAuth.getCurrentUser().getUid();
       if(!TextUtils.isEmpty(name)&& !TextUtils.isEmpty(phone)&& mImageUri!=null){
           mProgress.setMessage("Uploading.....");
           mProgress.show();

           StorageReference filepath=mStoragereImage.child(mImageUri.getLastPathSegment());
              filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                  @Override
                  public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                       String DownloadUri=taskSnapshot.getDownloadUrl().toString();
                      DatabaseReference current_user=mDatabaseUsers.child(user_id);
                      current_user.child("name").setValue(name);
                      current_user.child("mobile").setValue(phone);
                      current_user.child("image").setValue(DownloadUri);
                       mProgress.dismiss();
                      Toast.makeText(SetupActivity.this, "Upload Done", Toast.LENGTH_SHORT).show();

                      storeDataOffline(name,phone);



                  }
              });




       }
       else{

           Toast.makeText(SetupActivity.this,"Some fields are still missing",Toast.LENGTH_LONG).show();;
       }




    }

    private void storeDataOffline(String name, String phone) {


        SharedPreferences sharedPreferences_profile = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences_profile.edit();
        editor.putString("LOCAL-name",name);
        editor.putString("LOCAL-phone",phone);
        editor.apply();

    }

    private void storeImageUri(Uri imageUri) {


        SharedPreferences sharedPreferences_profile = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences_profile.edit();
        editor.putString("LOCAL-imageUri",imageUri.toString());
        editor.apply();

    }

    private String getImageUriLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(SetupActivity.MyPREFERENCES,Context.MODE_PRIVATE);
        String data_uri = sharedPreferences.getString("LOCAL-imageUri", " ");
        return data_uri;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("nImageUri", "resultCode= " + resultCode + "&&" + "requestCode = " + requestCode);

        Uri imageUri =null;
        try {
            imageUri = data.getData();
            if(requestCode == 1 && resultCode == -1){
                storeImageUri(imageUri);
            }

        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

        if(requestCode == 203 && resultCode == -1){
            imageUri = Uri.parse(getImageUriLocal());
        }

        if (imageUri != null) {
            if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
                CropImage.activity(imageUri).setAspectRatio(1, 1)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);
            }
//            new cropImage().execute();

            Log.e("Uri", "resultCode= " + resultCode + "&&" + "requestCode = " + requestCode + "imageUri= " + imageUri);

            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    mImageUri = result.getUri();
                    Log.e("nImageUri",mImageUri.toString());
                    mSetupImageBtn.setImageURI(mImageUri);
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }
            }
        }
    }
/*
    class cropImage extends AsyncTask<Object, Object, Uri> {

        protected void onPreExecute(){

        }

        @Override
        protected Uri doInBackground(Object... params) {

        }


        protected void onPostExecute(Uri mImageUri){
            mSetupImageBtn.setImageURI(mImageUri);
        }
    }*/
}
