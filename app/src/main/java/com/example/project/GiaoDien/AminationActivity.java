package com.example.project.GiaoDien;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;

public class AminationActivity extends AppCompatActivity {
    boolean run = true;
    ImageView img, imgMatTroi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amination);
        setControl();
        setEvent();

    }

    private void setControl() {
        img = findViewById(R.id.imageView);
        imgMatTroi = findViewById(R.id.imageView3);
    }

    private void setEvent() {
        final AnimationDrawable runingcat = (AnimationDrawable) img.getDrawable();
        runingcat.start();
        Animation animation = AnimationUtils.loadAnimation(AminationActivity.this, R.anim.amination);
        imgMatTroi.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(AminationActivity.this, DanhSachCN.class);
                startActivity(intent);
            }
        }, 3000);
    }
}
