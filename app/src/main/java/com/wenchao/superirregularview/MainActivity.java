package com.wenchao.superirregularview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * @author wenchao
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.irv_1).setOnClickListener(this);
        findViewById(R.id.irv_2).setOnClickListener(this);
        findViewById(R.id.irv_3).setOnClickListener(this);
        findViewById(R.id.irv_4).setOnClickListener(this);
        findViewById(R.id.idv_1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (null != mToast) {
            mToast.cancel();
        }
        switch (v.getId()) {
            case R.id.irv_1:
                mToast = Toast.makeText(this, "red", Toast.LENGTH_SHORT);
                break;
            case R.id.irv_2:
                mToast = Toast.makeText(this, "yellow", Toast.LENGTH_SHORT);
                break;
            case R.id.irv_3:
                mToast = Toast.makeText(this, "green", Toast.LENGTH_SHORT);
                break;
            case R.id.irv_4:
                mToast = Toast.makeText(this, "blue", Toast.LENGTH_SHORT);
                break;
            case R.id.idv_1:
                mToast = Toast.makeText(this, v.getTag(v.getId()).toString(), Toast.LENGTH_SHORT);
                break;
            default:
                break;
        }
        mToast.show();
    }
}
