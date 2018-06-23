package com.nuelvo.team.kask_projesi;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nuelvo.team.kask_projesi.Controllers.FirebaseDatabaseModule;
import com.nuelvo.team.kask_projesi.Controllers.FirebaseLoginModule;

import java.lang.reflect.Method;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity  {

    FirebaseLoginModule firebaseLoginModule;

    private EditText editText;
    private EditText editText2;
    private FirebaseDatabaseModule firebaseDatabaseModule;
    private Button btnLogin;
    private Button btnRegister;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        btnLogin = (Button) findViewById(R.id.btnlogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        firebaseLoginModule=new FirebaseLoginModule(this);
        firebaseDatabaseModule=new FirebaseDatabaseModule(this);

        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_PHONE_STATE)!=
        PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_PHONE_STATE))
            {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_PHONE_STATE},1);
            }
            else
            {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_PHONE_STATE},1);
            }
        }
        else
        {

        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){

            if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ANSWER_PHONE_CALLS)!=
                    PackageManager.PERMISSION_GRANTED)
            {
                if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.ANSWER_PHONE_CALLS))
                {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ANSWER_PHONE_CALLS},1);
                }
                else
                {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ANSWER_PHONE_CALLS},1);
                }
            }
            else
            {

            }

        } else{
            // do something for phones running an SDK before lollipop
        }







        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               /* String email = editText.getText().toString().trim();
                String password = editText2.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getBaseContext(), "Lütfen e-mail giriniz.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getBaseContext(), "Lütfen şifre giriniz.", Toast.LENGTH_SHORT).show();
                    return;


                }
                firebaseLoginModule.logIn(email , password);*/

            }
        });
        btnRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Activity değişecek.Kayıtol sayfm.


            }
        });






    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
     switch(requestCode)
     {
         case 1:
         {
             if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
             {
                if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_PHONE_STATE) ==
                        PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this,"Aramalara erişim izni verildi.",Toast.LENGTH_SHORT).show();

                }

             }
             else
             {
                 Toast.makeText(this,"Aramalara erişim izni verilmedi.",Toast.LENGTH_SHORT).show();
             }
             return;
         }
     }
    }
}