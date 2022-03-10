package com.example.nftmint.ui.notifications;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.nftmint.MainActivity2;
import com.example.nftmint.R;
import com.example.nftmint.databinding.FragmentNotificationsBinding;
import com.example.nftmint.meta_login;
import com.example.nftmint.upload;
import com.google.android.material.textfield.TextInputEditText;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    String address;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button b1,b2,b3;
        TextInputEditText add;
        address =  getActivity().getIntent().getExtras().getString("ADDRESS");



        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        add = (TextInputEditText)root.findViewById(R.id.url);
        b1 = (Button)root.findViewById(R.id.view_transition);
                add.setText(address);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                EditText add = (EditText)root.findViewById(R.id.url);
                String address = add.getText().toString();

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ropsten.etherscan.io/address/"+address));
                startActivity(intent);
            }
        });

        b2= (Button)root.findViewById(R.id.opean_pinita);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://app.pinata.cloud/pinmanager"));
                    startActivity(intent);
            }
        });

        b3= (Button)root.findViewById(R.id.create_file);
        b3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(root.getContext(), upload.class);
                startActivity(i);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}