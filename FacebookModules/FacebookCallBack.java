package com.example.fince.erolun_isi;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;


public class FacebookCallBack implements FacebookCallback<LoginResult> {
    private ProgressDialog mDialog;
    private Context con;

    public CallbackManager getCallbackManager() {
        return callbackManager;
    }

    private CallbackManager callbackManager;
    //Asenkron iş yapıalcağı için bu sınıfı kullanacak MainActivity'i göndermem lazım
    private MainActivity mainActivity;


    public FacebookCallBack(Context con,MainActivity mainActivity) {
        this.con = con;
        this.mainActivity=mainActivity;
        callbackManager= CallbackManager.Factory.create();
    }



    @Override
    public void onSuccess(LoginResult loginResult) {
        mDialog=new ProgressDialog(con);
        mDialog.setMessage("Bilgiler Alınıyor...");
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
        GraphRequest request=GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                mDialog.dismiss();
                Log.d("Response",response.toString());
                getData(object);

            }


        });
        //Request Graph API
        Bundle parameters=new Bundle();
        parameters.putString("fields", "id,name,email,birthday,gender");
        request.setParameters(parameters);
        request.executeAsync();





    }

    private void getData(JSONObject object) {
        try
        {

            URL profile_picture=new URL("https://graph.facebook.com/"+object.getString("id")+"/picture?width=250&height=250");

            String email=object.getString("email");
            String birthday=object.getString("birthday");
            String name=object.getString("name");
            String user_id=object.getString("id");

            mainActivity.updateUI(user_id,name,email,birthday,profile_picture.toString());






        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void onCancel() {

    }

    @Override
    public void onError(FacebookException error) {

    }
}
