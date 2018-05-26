package com.nuelvo.team.kask_projesi.Controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseLoginModule {
    private FirebaseAuth mAuth;
    private Context context;

    public FirebaseLoginModule(Context context)
    {
        mAuth = FirebaseAuth.getInstance();
        this.context=context;
    }

    //oturum açmış kişiyi ver
    public FirebaseUser getCurrentUser()
    {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        return currentUser;
    }

    //yeni kayıt oluşturma
    public void createNewUser(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    //bilgilendirme mesajı ve ana ekrans geçiş
                    Log.d(TAG, "Register işlemi başarılı.");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                }
                else
                {
                    //hata mesajı göster
                    Log.w(TAG, "Register işlemi başarısız.", task.getException());
                    Toast.makeText(EmailPasswordActivity.this, "Kayıt Başarısız.",
                            Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });

    }

    //giriş yap
    public void logIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {

                    //bilgilendirme mesajı ver , geçiş yap, progress dialog kullanabilirsin
                    Log.i("logIn","Login işlemi başarılı.");
                }
                else
                {
                    //hata mesajı ver
                    Log.d("HATA",task.getException().getMessage());
                }



            }
        });
    }

    //çıkış yap
    public void logOut() {
        mAuth.signOut();
    }


}
