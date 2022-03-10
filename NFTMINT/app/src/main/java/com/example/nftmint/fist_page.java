package com.example.nftmint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nftmint.ui.notifications.NotificationsFragment;
import com.google.android.material.textfield.TextInputEditText;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

public class fist_page extends AppCompatActivity {
    TextInputEditText t1,t2;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fist_page);

        b1 = findViewById(R.id.button2);
        t1 = findViewById(R.id.addrs);
        t2 = findViewById(R.id.privke);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String address = t1.getText().toString();
                String prikey = t2.getText().toString();

                NotificationsFragment nf = new NotificationsFragment();

/*
                Toast.makeText(getApplicationContext(),""+m.getM_address()+" "+m.getM_private_key(),Toast.LENGTH_LONG).show();
*/

            try {
                Credentials getCredentialsFromPrivateKey = Credentials.create(prikey);
                new meta_login(address,prikey,getCredentialsFromPrivateKey);
                Intent i = new Intent(fist_page.this, MainActivity.class);
                i.putExtra("ADDRESS",address);
                i.putExtra("PRIVATEKEY",prikey);


                Web3j web3j = Web3j.build(new HttpService("https://ropsten.infura.io/v3/4096a30b2a294ec5af472034ef4d25fe"));

                final EthGetBalance balanceResponse = web3j
                        .ethGetBalance(address, DefaultBlockParameter.valueOf("latest")).sendAsync()
                        .get(3, TimeUnit.SECONDS);
                final BigInteger unscaledBalance = balanceResponse.getBalance();
                final BigDecimal scaledBalance = new BigDecimal(unscaledBalance)
                        .divide(new BigDecimal(1000000000000000000L),5, RoundingMode.HALF_UP);
                String balance = scaledBalance.toString();
                if (balance!= null)
                {
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(fist_page.this,"Enter Correct credentials",Toast.LENGTH_LONG).show();
                }



            } catch (Exception e)
            {
                Toast.makeText(getApplicationContext(),"Enter correct credentials"+e,Toast.LENGTH_LONG);
                e.printStackTrace();

            }


            }
        });

    }
}