package com.example.assignment3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.example.assignment3.views.CustomView;

public class MainActivity extends AppCompatActivity {

    private CustomView mCustomView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCustomView = (CustomView) findViewById(R.id.CustomView);
        findViewById(R.id.btn_move_left).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCustomView.moveLeft();
            }
        });

        mCustomView = (CustomView) findViewById(R.id.CustomView);
        findViewById(R.id.btn_move_right).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCustomView.moveRight();
            }
        });

        mCustomView = (CustomView) findViewById(R.id.CustomView);
        findViewById(R.id.btn_move_down).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCustomView.moveDown();
            }
        });

        mCustomView = (CustomView) findViewById(R.id.CustomView);
        findViewById(R.id.btn_rotate).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCustomView.Rotate();
            }
        });
    }

    //Sets keyboard keys to function like the buttons
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_DOWN:
                mCustomView.moveDown();
                return true;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                mCustomView.moveLeft();
                return true;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                mCustomView.moveRight();
                return true;
            case KeyEvent.KEYCODE_R:
                mCustomView.Rotate();
            default:
                return super.onKeyUp(keyCode, event);
        }
    }
}

