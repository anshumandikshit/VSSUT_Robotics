package com.robotix_vssut.welcomeanimationapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.baoyz.widget.PullRefreshLayout;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.robotix_vssut.welcomeanimationapp.activities.MainActivity2;
import com.pushbots.push.Pushbots;
import com.robotix_vssut.welcomeanimationapp.activities.FoldableListActivity;
import com.robotix_vssut.welcomeanimationapp.activities.MainActivity2;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mBlogList;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToogle;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    NavigationView navigationView;
    private boolean mProcessLike=false;
    private DatabaseReference mDatabaseLike;
    private DatabaseReference mDatabaseUsers;



ImageButton imageButton;

private Toolbar mToolbar1;
private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_navigate);

        setSupportActionBar(mToolbar1);
















        Pushbots.sharedInstance().registerForRemoteNotifications();
      //////////////////////////////////////////////////////////////////////////
           mAuth=FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser()==null){

                    Intent loginintent=new Intent(MainActivity.this,LoginActivity.class);
                    loginintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(loginintent);
                }

            }
        };


 ////////////////////////////////////////////////////////////////////////////////
        mDatabase=FirebaseDatabase.getInstance().getReference().child("Blog");
        mDatabaseUsers=FirebaseDatabase.getInstance().getReference().child("Users");
        mDatabaseLike=FirebaseDatabase.getInstance().getReference().child("Likes");
        mDatabaseUsers.keepSynced(true);
        mDatabaseLike.keepSynced(true);
        mDatabase.keepSynced(true);
     /////////////////////////////////////////////////////////////////////
        mBlogList=(RecyclerView)findViewById(R.id.Blog_list);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

        /////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////
        mToolbar1 = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar1);
        mToolbar1.setTitle("VSSUT ROBOTICS");
        //////////////////////////////////////////////////////////////

       ///////////////////////////////////////////////////////////////////////////
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToogle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToogle);
        mToogle.syncState();
        navigationView=(NavigationView)findViewById(R.id.navigation_View);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                 switch(item.getItemId())
                 {
                     case (R.id.nav_accoount):
                         startActivity(new Intent(MainActivity.this,SetupActivity.class));
                         mDrawerLayout.closeDrawers();
                      break;
                     case (R.id.gallery):
                         mDrawerLayout.closeDrawers();
                         /*Intent intent=new Intent();
                         intent.setAction(Intent.ACTION_VIEW);
                         intent.addCategory(Intent.CATEGORY_BROWSABLE);
                         intent.setData(Uri.parse("http://vssutrobotics.in/gallery"));
                         startActivity(intent);
                         */
                         startActivity(new Intent(MainActivity.this,activity_gallery.class));
                         break;



                     case (R.id.about):

                         startActivity(new Intent(MainActivity.this,ContentAbout.class));
                         mDrawerLayout.closeDrawers();
                         break;
                     case(R.id.feedback):
                         startActivity(new Intent(MainActivity.this,feedback.class));
                         mDrawerLayout.closeDrawers();
                         break;
                     case(R.id.share):
                         mDrawerLayout.closeDrawers();
                         Intent myIntent=new Intent(Intent.ACTION_SEND);
                         myIntent.setType("text/plain");
                         String shareBody="your body here";
                         String shareSub="your sub here";
                         myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                         myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                         startActivity(Intent.createChooser(myIntent,"Share Using"));
                         break;
                     case(R.id.achivements):
                         mDrawerLayout.closeDrawers();
                         startActivity(new Intent(MainActivity.this,Activity_Achievement.class));
                         break;
                     case(R.id.notifications):
                         mDrawerLayout.closeDrawers();
                         startActivity(new Intent(MainActivity.this,Notification_Activity.class));
                         break;
                     case (R.id.projects):
                         mDrawerLayout.closeDrawers();
                         startActivity(new Intent(MainActivity.this,FoldableListActivity.class));
                         break;


                 }
                return false;
            }
        });



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // ///////////////////////////////////////////////////////////////////////////////////


    }








    ////////////////////////////////////////////////////////////////////////////////////EXIT POPUP MESSAGE
    @Override
    public void onBackPressed() {

        final AlertDialog.Builder builder1= new AlertDialog.Builder(MainActivity.this);
        builder1.setIcon(android.R.drawable.ic_dialog_alert);
        builder1.setTitle("Quit??");
        builder1.setMessage("Do you want to close this application?? ");
        builder1.setCancelable(true);


        builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });
        builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog=builder1.create();
        alertDialog.show();
    }

    /////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
        FirebaseRecyclerAdapter<Blog,BlogViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(


                Blog.class,
                R.layout.blog_row,
                BlogViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

                final String post_key=getRef(position).getKey();


                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getApplicationContext(),model.getImage());
                viewHolder.set_likeBtn(post_key);
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, post_key, Toast.LENGTH_SHORT).show();
                    }
                });
               viewHolder.mLikeBtn.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {


                              mProcessLike=true;
                                 mDatabaseLike.addValueEventListener(new ValueEventListener() {
                                     @Override
                                     public void onDataChange(DataSnapshot dataSnapshot) {

                                         if(mProcessLike) {
                                             if (dataSnapshot.child(post_key).hasChild(mAuth.getCurrentUser().getUid())) {
                                                 mDatabaseLike.child(post_key).child(mAuth.getCurrentUser().getUid()).removeValue();
                                                 mProcessLike = false;
                                             } else {
                                                 mDatabaseLike.child(post_key).child(mAuth.getCurrentUser().getUid()).setValue("Random value");
                                                 mProcessLike = false;
                                             }
                                         }
                                     }

                                     @Override
                                     public void onCancelled(DatabaseError databaseError) {

                                     }
                                 });


                   }
               });
            }
        };

        mBlogList.setAdapter(firebaseRecyclerAdapter);
    }













    ////////////////////////////////////////////////////////creating ADD POST icon

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);


        return super.onCreateOptionsMenu(menu);
    }


    //////////////////////////////
    private void logout() {
        mAuth.signOut();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


            if(mToogle.onOptionsItemSelected(item)){
                return true;
        }
        if (item.getItemId()==R.id.action_add){
            startActivity(new Intent(MainActivity.this,PostActivity.class));
        }

            if(item.getItemId()==R.id.action_logout){

                logout();

            }


         return super.onOptionsItemSelected(item);
    }



    public static class BlogViewHolder extends RecyclerView.ViewHolder{

        View mView;
        ImageButton mLikeBtn;
        DatabaseReference mDatabaseLike;
        FirebaseAuth mAuth;


        public BlogViewHolder(View itemView) {
            super(itemView);
            mView=itemView;

            mLikeBtn=(ImageButton)mView.findViewById(R.id.like_btn);
            mDatabaseLike=FirebaseDatabase.getInstance().getReference().child("Likes");
            mAuth=FirebaseAuth.getInstance();
            mDatabaseLike.keepSynced(true);

        }



         public void set_likeBtn(final String post_key){

              mDatabaseLike.addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(DataSnapshot dataSnapshot) {

                      if (mAuth.getInstance().getCurrentUser() !=null) {

                          if (dataSnapshot.child(post_key).hasChild(mAuth.getCurrentUser().getUid())) {
                              mLikeBtn.setImageResource(R.mipmap.ic_blue);
                          } else {
                              mLikeBtn.setImageResource(R.mipmap.ic_thumb_up_white_24dp);


                          }
                      }
                  }

                  @Override
                  public void onCancelled(DatabaseError databaseError) {

                  }
              });


         }




        public void setTitle(String title){
            TextView post_title=(TextView) mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }
        public void setDesc(String desc){
            TextView post_desc=(TextView)mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }

        public void setImage(Context ctx,String image){

            ImageView post_image=(ImageView)mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(post_image);
        }









    }














}
