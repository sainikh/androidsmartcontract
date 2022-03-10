package com.example.nftmint;


import org.web3j.crypto.Credentials;

public class meta_login
{
    String m_address;
    String m_private_key;
    Credentials m_getCredentialsFromPrivateKey;

    public meta_login(String address, String private_key, Credentials getCredentialsFromPrivateKey)
    {
        m_address = address;
       m_private_key = private_key;
       m_getCredentialsFromPrivateKey = getCredentialsFromPrivateKey;

    }

    public meta_login()
    {

    }

    public String getM_address()
    {
        return m_address;
    }
    public String getM_private_key()
    { return m_private_key; }
    public Credentials getM_credentals(){return m_getCredentialsFromPrivateKey;}


}
