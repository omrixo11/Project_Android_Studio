package com.esprit.lunar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class FirstPage extends AppCompatActivity {

    private Button button2;
    private Button button1;
    VideoView video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);



        button2 = (Button) findViewById(R.id.button2);
        video = (VideoView) findViewById(R.id.videoVeiw);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.anim;
        Uri uri = Uri.parse(videoPath);
        video.setVideoURI(uri);
        video.start();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUp();
            }
        });

        button1 = (Button) findViewById(R.id.signup);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignIn();
            }
        });
    }

    private void openSignIn() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openSignUp() {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}