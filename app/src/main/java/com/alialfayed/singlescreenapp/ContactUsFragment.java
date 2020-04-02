package com.alialfayed.singlescreenapp;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ContactUsFragment extends Fragment {

    static final int REQUEST_PHONE_CALL = 1;
    private static final int HANDLER_TIME = 3000;

    public ContactUsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_contact_us, container, false);

        TextView txtPhone = (TextView) view.findViewById(R.id.txtPhone);
        TextView txtEmail = (TextView) view.findViewById(R.id.txtEmail);
        TextView txtThread = (TextView) view.findViewById(R.id.txtThread);

        visibleThread(txtThread);

        txtPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String number = "+201014775215";

                if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
                } else {
                    makeCall(number);
                }

            }
        });

        txtEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        return view;
    }

    // Handel To Visible Gone Text
    private void visibleThread(final TextView txtThread) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                txtThread.setVisibility(View.GONE);
            }
        },HANDLER_TIME);

    }


    // Move Intent To Create Call
    private void makeCall(String number) {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" +number));//change the number
            startActivity(callIntent);
        } catch (ActivityNotFoundException activityException) {
            Log.e("Calling a Phone Number", "Call failed", activityException);
        }
    }

    // Move Intent To Send Message
    private void sendMessage(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, "alialfayed1@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Buy Mango");
        intent.putExtra(Intent.EXTRA_TEXT, "I'm create order now ..");
        startActivity(Intent.createChooser(intent, "Send Email"));
    }

}
