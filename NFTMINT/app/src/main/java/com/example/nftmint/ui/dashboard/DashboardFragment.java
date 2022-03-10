package com.example.nftmint.ui.dashboard;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.nftmint.R;
import com.example.nftmint.databinding.FragmentDashboardBinding;
import com.example.nftmint.meta_login;
import com.google.android.material.textfield.TextInputEditText;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    TextInputEditText address,prikey,sendadd,amount;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        address = (TextInputEditText) root.findViewById(R.id.myaddress);
        prikey = (TextInputEditText) root.findViewById(R.id.privatekey);
        sendadd = (TextInputEditText) root.findViewById(R.id.sendig_address);
        amount = (TextInputEditText) root.findViewById(R.id.amount);


        String adder =  getActivity().getIntent().getExtras().getString("ADDRESS");
        String privkey =  getActivity().getIntent().getExtras().getString("PRIVATEKEY");

        address.setText(adder);
        prikey.setText(privkey);

        Button b1 = root.findViewById(R.id.button);
        Button b2 = root.findViewById(R.id.make_trans);
        TextView t1 = root.findViewById(R.id.textView1);
   /*         mt = (TextInputEditText)root.findViewById(R.id.myaddress);
            String m = mt.getText().toString();*/
        /*  t1.setText(m);*/
     /*
        String ADDRESS = "0xd24741f5Fa5580deb817423ec211ffd2fdf08203";
        EthGetBalance balanceResponse = null;
        try {
            Web3j web3j = Web3j.build(new HttpService("https://ropsten.infura.io/v3/4096a30b2a294ec5af472034ef4d25fe"));

            balanceResponse = web3j
                    .ethGetBalance(ADDRESS, DefaultBlockParameter.valueOf("latest")).sendAsync()
                    .get(3, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        final BigInteger unscaledBalance = balanceResponse.getBalance();
        final BigDecimal scaledBalance = new BigDecimal(unscaledBalance)
                .divide(new BigDecimal(1000000000000000000L), 5, RoundingMode.HALF_UP);
        String balance = scaledBalance.toString();
        t1.setText(balance);*/


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address = (TextInputEditText) root.findViewById(R.id.myaddress);
                String m = address.getText().toString();
                String address = m;


                String CONTRACT_ADDRESS = "0x993fa3acbddc7af2895a6fb97281fe72652e96e8";


                String PRIVATE_KEY = privkey;
                BigInteger GAS_LIMIT = BigInteger.valueOf(3000000);
                BigInteger GAS_PRICE = BigInteger.valueOf(4_100_000_000L);
                String RECEPENT = "0x79e13d3dC1A0B252DF92D4966dB26D90c6E3e2A7";

                try {
                    Web3j web3j = Web3j.build(new HttpService("https://ropsten.infura.io/v3/4096a30b2a294ec5af472034ef4d25fe"));

                    final EthGetBalance balanceResponse = web3j
                            .ethGetBalance(address, DefaultBlockParameter.valueOf("latest")).sendAsync()
                            .get(3, TimeUnit.SECONDS);
                    final BigInteger unscaledBalance = balanceResponse.getBalance();
                    final BigDecimal scaledBalance = new BigDecimal(unscaledBalance)
                            .divide(new BigDecimal(1000000000000000000L),5, RoundingMode.HALF_UP);
                    String balance = scaledBalance.toString();
                    t1.setText(balance);

                /*    String address = t1.getText().toString();
                    BigDecimal ethvalue = BigDecimal.valueOf(Integer.parseInt(t2.getText().toString()));*/

                    /* Credentals using private key*/
                    Credentials getCredentialsFromPrivateKey = Credentials.create(PRIVATE_KEY);

                  /* Transaction


                    CompletableFuture<TransactionReceipt> transactionReceipt = null;
                    try {
                        try {
                            transactionReceipt = Transfer.sendFunds(web3j
                                    ,getCredentialsFromPrivateKey
                                    ,address
                                    , BigDecimal.valueOf(0.001)
                                    , Convert.Unit.ETHER)
                                    .sendAsync();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (TransactionException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getApplicationContext(),"receipt "+transactionReceipt,Toast.LENGTH_LONG).show();

*/


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String PRIVATE_KEY = privkey;
                String ADDRESS = adder ;

                String senderaddress = sendadd.getText().toString();
                String samount = amount.getText().toString();
                Double d = Double.parseDouble(samount);
                BigDecimal amout = BigDecimal.valueOf(d);




                Web3j web3j = Web3j.build(new HttpService("https://ropsten.infura.io/v3/4096a30b2a294ec5af472034ef4d25fe"));
                Credentials getCredentialsFromPrivateKey = Credentials.create(PRIVATE_KEY);


                TransactionReceipt transactionReceipt = null;

                    try {
                        transactionReceipt = Transfer.sendFunds(web3j
                                , getCredentialsFromPrivateKey
                                , senderaddress
                                , amout
                                , Convert.Unit.ETHER)
                                .sendAsync().get(5, TimeUnit.SECONDS);;
                        Toast.makeText(root.getContext(),""+transactionReceipt,Toast.LENGTH_LONG).show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (TransactionException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
                Toast.makeText(root.getContext(), "receipt " + transactionReceipt, Toast.LENGTH_LONG).show();


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