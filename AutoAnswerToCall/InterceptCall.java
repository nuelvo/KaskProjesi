package com.nuelvo.team.kask_projesi;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;

public class InterceptCall extends BroadcastReceiver {
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            //arama var ise
            if (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)) {
                String incomingPhoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                Toast.makeText(context, "Ringing from: " + incomingPhoneNumber, Toast.LENGTH_SHORT).show();
                //arayan numara müşteri hizmeti ise aç
                String musteriHizmetiNo = "05326414959";
                if (incomingPhoneNumber.equals(musteriHizmetiNo)) {
                    Log.i("Test", "Girdi");
                    TelecomManager tm = (TelecomManager) context.getSystemService(Context.TELECOM_SERVICE);

                    if (tm == null) {
                        // whether you want to handle this is up to you really
                        throw new NullPointerException("tm == null");
                    }

                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ANSWER_PHONE_CALLS) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    tm.acceptRingingCall();

                }
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}
