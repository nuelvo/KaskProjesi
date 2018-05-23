package com.example.fince.erolun_isi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView user_id,name_surname,email,gender,birthday;
    private ImageView profil_image;
    private CallbackManager callbackManager;
    private FacebookCallBack facebookCallBack;
    private LoginButton loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_id=(TextView)findViewById(R.id.txt_uid);
        name_surname=(TextView)findViewById(R.id.txt_adSoyad);
        email=(TextView)findViewById(R.id.txt_email);
        gender=(TextView)findViewById(R.id.txt_cinsiyet);
        birthday=(TextView)findViewById(R.id.txt_dogumGunu);
        profil_image=(ImageView)findViewById(R.id.avatar);
        loginButton=(LoginButton)findViewById(R.id.login_button);

        facebookCallBack=new FacebookCallBack(this,this);
        //kullanıcıdan istenilecek izinler
        loginButton.setReadPermissions("public_profile","user_gender","email");
        loginButton.registerCallback(facebookCallBack.getCallbackManager(), facebookCallBack );


        // zaten girşi yapıldı ise
        if(AccessToken.getCurrentAccessToken() != null)
        {
            Log.i("Already Login","Loginned from "+AccessToken.getCurrentAccessToken().getUserId());
        }



    }

    /*void printKeyHash()
    {
        try{
            PackageInfo info=getPackageManager().getPackageInfo("com.example.fince.erolun_isi", PackageManager.GET_SIGNATURES);
            for(Signature s:info.signatures)
            {
                MessageDigest md= MessageDigest.getInstance("SHA");
                md.update(s.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebookCallBack.getCallbackManager().onActivityResult(requestCode,resultCode,data);
    }
    public void updateUI(String userId,String name,String emailS,String birthdayS,String image_link)
    {
        user_id.setText(userId);
        name_surname.setText(name);
        email.setText(emailS);
        birthday.setText(birthdayS);
        Picasso.with(this).load(image_link).into(profil_image);
    }
}
