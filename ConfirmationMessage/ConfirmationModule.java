package com.nuelvo.team.kask_projesi.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//Confirmation Telefon Numarası Activity
public class ConfirmationPhoneNumberActivity{

    private Intent intent;
    EditText editText;
    String telNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        intent = new Intent(this,ConfirmationPasswordActivity.class);
        editText = findViewById(R.id.edtTelNo);
    }
    public void clickDevamEt(View view){
        
        startActivity(intent);
        telNo = editText.getText() + "" ;
        Log.i("telNo","Telefon NO:" + telNo );
    }

}
//Confirmation Dogrulama Kodu Activity
public class ConfirmationNumberActivity  {

    private Intent intent;
    EditText editText;
    String sifre, gelen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_password);
        intent = new Intent(this,MainActivity.class);

        editText = findViewById(R.id.edtSifre);
        //temsili
        sifre = "0000";
    }
    public void clickDevamEt2(View view){
        
        gelen = editText.getText() + "";
        if (gelen.equals(sifre)) {
            Log.i("sifre", "Sifre:" + sifre);
            startActivity(intent);
        }
        else{
            Log.i("sifre", "Sifre:" + sifre);
            Toast.makeText(getApplicationContext(),"Doğrulama Kodu Yanlış", Toast.LENGTH_SHORT).show();
        }
        Log.i("sifre", "Sifre:" + sifre);
    }
}