package com.example.hctpfpoly.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hctpfpoly.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.jetbrains.annotations.NotNull;


public class ph13373_Fragment_LoginFB extends Fragment {

    private LoginButton btnFrgLoginfb;
    CallbackManager callbackManager;



    public ph13373_Fragment_LoginFB() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view=inflater.inflate(R.layout.fragment_ph13373___login_f_b, container, false);
        btnFrgLoginfb = (LoginButton) view.findViewById(R.id.btn_frg_Loginfb);
        callbackManager=CallbackManager.Factory.create();//tao doi tuong lang nghe
        FacebookSdk.setApplicationId("686179786115185");
        FacebookSdk.sdkInitialize(getContext());





        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable  Bundle savedInstanceState) {
        btnFrgLoginfb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getContext(),loginResult.toString(),Toast.LENGTH_SHORT).show();;
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ////cac phan xu li sau login
    }
}