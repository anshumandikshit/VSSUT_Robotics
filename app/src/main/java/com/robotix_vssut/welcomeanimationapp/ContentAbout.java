package com.robotix_vssut.welcomeanimationapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ContentAbout extends AppCompatActivity {
   private TextView mTextView;
    private ImageButton about_fb;
    private ImageButton about_twitter;
    private ImageButton about_youtube;
    private ImageButton about_linkedin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(android.R.style.Theme_Black_NoTitleBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_about);

        mTextView=(TextView)findViewById(R.id.text_web_addr);
        about_fb=(ImageButton)findViewById(R.id.about_fb);
        about_twitter=(ImageButton)findViewById(R.id.about_twitter);
        about_youtube=(ImageButton)findViewById(R.id.about_youtube);
        about_linkedin= (ImageButton)findViewById(R.id.about_linkedin);


        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.vssutrobotics.in"));
                startActivity(intent);
            }
        });


        about_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.facebook.com/vssutrobotics/"));
                startActivity(intent);

            }
        });

        about_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.twitter.com/societyrobotics"));
                startActivity(intent);



            }
        });


        about_youtube.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.youtube.com/channel/UCfrM26pYkyk8JtW-G0mcDNQ"));
                startActivity(intent);


            }
        });


        about_linkedin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.linkedin.com"));
                startActivity(intent);


            }
        });



    }
}
