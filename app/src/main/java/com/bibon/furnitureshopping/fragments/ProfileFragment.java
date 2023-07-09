package com.bibon.furnitureshopping.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.bibon.furnitureshopping.R;
import com.bibon.furnitureshopping.activities.AddressShippingActivity;
import com.bibon.furnitureshopping.activities.LoginActivity;
import com.bibon.furnitureshopping.activities.ShowProfileActivity;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileFragment extends Fragment {

    FirebaseAuth mAuth;

    ImageView img;
    TextView tvUsername, tvEmail;



//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        try {
//            FirebaseUser currentUser = mAuth.getCurrentUser();
//            if (currentUser == null) {
//                Intent intent = new Intent(getActivity(), LoginActivity.class);
//                startActivity(intent);
//            } else {
//                System.out.println(currentUser + "kkkkk");
//            }
//        } catch (NullPointerException e) {
//            System.out.println(e);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        img = (ImageView) getView().findViewById(R.id.img_avatar);
        tvUsername = (TextView) getView().findViewById(R.id.tv_username);
        tvEmail = (TextView) getView().findViewById(R.id.tv_email);

        AppCompatButton btn_log_out = getView().findViewById(R.id.btn_logout);
        LinearLayout linearLayoutAddresses = getView().findViewById(R.id.linearLayout_addresses);
        LinearLayout linearLayoutProfile = getView().findViewById(R.id.linearLayout_my_profile);
        btn_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutAddresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddressShippingActivity.class);
                startActivity(intent);
            }
        });
        linearLayoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ShowProfileActivity.class);
                startActivity(intent);
            }
        });

        showUserInfor();

    }

    private void showUserInfor() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }
        String username = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();

        if (username == null) {
            tvUsername.setVisibility(View.GONE);
        } else {
            tvUsername.setVisibility(View.VISIBLE);
        }
        tvUsername.setText(username);
        tvEmail.setText(email);
        Glide.with(this).load(photoUrl).error(R.drawable.avatar).into(img);
    }


}