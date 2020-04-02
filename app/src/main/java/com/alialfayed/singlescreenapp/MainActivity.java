package com.alialfayed.singlescreenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import static com.alialfayed.singlescreenapp.ContactUsFragment.REQUEST_PHONE_CALL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button btnStepOne , btnStepTwo;
    public FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initialize variable
        init();
        // initialize fragment
        initFragment();
    }

    private void initFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container,new InfoFragment());
        fragmentTransaction.commit();
    }
    private void initContactUsFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,new ContactUsFragment());
        fragmentTransaction.commit();
    }
    private void initInfoFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,new InfoFragment());
        fragmentTransaction.commit();
    }

    private void init() {
        btnStepOne  = findViewById(R.id.btnStepOne);
        btnStepTwo  = findViewById(R.id.btnStepTwo);
        container  = findViewById(R.id.container);

        // onclick Button
        btnStepOne.setOnClickListener(this);
        btnStepTwo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnStepOne:
                btnStepOne.setBackground(getDrawable(R.drawable.btn_shap));
                btnStepTwo.setBackground(getDrawable(R.drawable.unselect_btn_shap));
                btnStepOne.setTextColor(Color.WHITE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    btnStepTwo.setTextColor(getColor(R.color.orange));
                }else {
                    btnStepTwo.setTextColor(Color.YELLOW);
                }
                initInfoFragment();
                break;
            case  R.id.btnStepTwo:
                btnStepOne.setBackground(getDrawable(R.drawable.unselect_btn_shap));
                btnStepTwo.setBackground(getDrawable(R.drawable.btn_shap));
                btnStepTwo.setTextColor(Color.WHITE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    btnStepOne.setTextColor(getColor(R.color.orange));
                }else {
                    btnStepOne.setTextColor(Color.YELLOW);
                }
                initContactUsFragment();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PHONE_CALL: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
                return;
            }
        }
    }
}
