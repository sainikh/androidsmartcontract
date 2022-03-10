package com.example.nftmint.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nftmint.R;
import com.example.nftmint.databinding.FragmentHomeBinding;
import com.example.nftmint.display_NFT;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class HomeFragment extends Fragment {
RecyclerView recyclerView;
Adapter adapter;
FloatingActionButton fab;
Button b3;
TextInputEditText t1;
ArrayList<String> items,desc;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        /*binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();*/
        fab = view.findViewById(R.id.floatingActionButton2);
        b3 = view.findViewById(R.id.button3);
        t1 =view.findViewById(R.id.image_url);


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String imgjson = t1.getText().toString();
                String finalurl = "https://gateway.pinata.cloud/ipfs/"+imgjson;

                BigInteger GAS_LIMIT = BigInteger.valueOf(3000000);
                BigInteger GAS_PRICE = BigInteger.valueOf(4_100_000_000L);
                String ADDRESS =  getActivity().getIntent().getExtras().getString("ADDRESS");
                String PRIVATE_KEY =  getActivity().getIntent().getExtras().getString("PRIVATEKEY");
                String CONTRACT_ADDRESS = "0x7b6a1d23cB74F5ED1fbB21B2B539BFa2345B1A8f";
                Web3j web3j = Web3j.build(new HttpService("https://ropsten.infura.io/v3/4096a30b2a294ec5af472034ef4d25fe"));
                Credentials getCredentialsFromPrivateKey = Credentials.create(PRIVATE_KEY);
                Nft_minter contract = Nft_minter.load(CONTRACT_ADDRESS, web3j, getCredentialsFromPrivateKey, GAS_PRICE, GAS_LIMIT);
              /*  Toast.makeText(getActivity(),""+finalurl,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(finalurl));
                startActivity(intent);*/
                try {
                    TransactionReceipt transactionReceipt = contract
                            .createToken(finalurl)
                            .sendAsync()
                            .get(20, TimeUnit.SECONDS);
                    Toast.makeText(view.getContext(),""+transactionReceipt,Toast.LENGTH_LONG).show();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
         /* items = new ArrayList<>();
          items.add("Sham");
          items.add("Hi sai");


            desc = new ArrayList<>();
            desc.add("Sham");
            desc.add("Hi sai");
          recyclerView = view.findViewById(R.id.myrecycle);
          recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
          adapter = new Adapter(view.getContext(),items,desc);
          recyclerView.setAdapter(adapter);*/

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),display_NFT.class);
                startActivity(i);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}