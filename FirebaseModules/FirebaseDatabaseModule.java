package com.nuelvo.team.kask_projesi.Controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

import id.zelory.compressor.Compressor;

public class FirebaseDatabaseModule {

    private Context context;
    private ArrayList<HashMap<String,String>> list_tanidiklar;
    private ArrayList<HashMap<String,String>> list_araclar;
    private ArrayList<HashMap<String,String>> list_ekipmanlar;
    private DatabaseReference kullanicilar_DatRef;
    private StorageReference resim_Storage;
    private DatabaseReference ark_istekleri_DatRef;
    private DatabaseReference arkadasliklar_DatRef;
    private DatabaseReference tanidiklar_DatRef;
    private DatabaseReference araclar_DatRef;
    private DatabaseReference ekipmanlar_DatRef;
    private DatabaseReference saglik_bilgileri_DatRef;
    public FirebaseDatabaseModule(Context context)
    {
        this.context=context;
        kullanicilar_DatRef=FirebaseDatabase.getInstance().getReference().child("Kullanicilar");
        ark_istekleri_DatRef=FirebaseDatabase.getInstance().getReference().child("Ark_Istekleri");
        arkadasliklar_DatRef=FirebaseDatabase.getInstance().getReference().child("Arkadasliklar");
        tanidiklar_DatRef=FirebaseDatabase.getInstance().getReference().child("Tanidiklar");
        araclar_DatRef=FirebaseDatabase.getInstance().getReference().child("Araclar");
        ekipmanlar_DatRef=FirebaseDatabase.getInstance().getReference().child("Ekipmanlar");
        saglik_bilgileri_DatRef=FirebaseDatabase.getInstance().getReference().child("Saglik_Bilgileri");
        resim_Storage= FirebaseStorage.getInstance().getReference();

        list_tanidiklar=new ArrayList<>();
        list_araclar=new ArrayList<>();
        list_ekipmanlar=new ArrayList<>();
    }

    //kullanıcı kişisel bilgilerini veritabanına kaydetme
    public void push_userInformations(String uid, String ad,String soyad,String dogum_tarihi,String cinsiyet, String resim_link, String kucuk_resim_link)
    {
        kullanicilar_DatRef=kullanicilar_DatRef.child(uid);
        kullanicilar_DatRef.keepSynced(true);
        HashMap<String,String> userMap=new HashMap<>();
        userMap.put("ad",ad);
        userMap.put("soyad",soyad);
        userMap.put("dogum_tarihi",dogum_tarihi);
        userMap.put("cinsiyet",cinsiyet);
        userMap.put("resim_link",resim_link);
        userMap.put("kucuk_resim_link",kucuk_resim_link);
        userMap.put("kisi_toplam_km","0");

        kullanicilar_DatRef.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful())
                {

                    //işlem başarılı

                }
                else
                {
                    //işlem başarısız
                }

            }
        });
        }
        //kişinin sağlık bililerini gönder
        public void push_userHealthInformations(String uid,String kan_grubu,String onemli_saglik_bilgileri)
        {
            saglik_bilgileri_DatRef=saglik_bilgileri_DatRef.child(uid);
            saglik_bilgileri_DatRef.keepSynced(true);
            HashMap<String,String> userMap=new HashMap<>();
            userMap.put("kan_grubu",kan_grubu);
            userMap.put("onemli_saglik_bilgileri",onemli_saglik_bilgileri);
            saglik_bilgileri_DatRef.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(task.isSuccessful())
                    {
                        //işlem başarılı
                    }
                    else
                    {
                        //işlem başarısız
                    }

                }
            });
        }

        //kişinin sağlık bilgilerini getir
        public void get_userHealthInformations(String uid)  {
            final HashMap<String,String> returner=new HashMap<>();
            saglik_bilgileri_DatRef=saglik_bilgileri_DatRef.child(uid);
            saglik_bilgileri_DatRef.keepSynced(true);

            ValueEventListener valueEventListener=new ValueEventListener() {
                @SuppressLint("LongLogTag")
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String kan_grubu=dataSnapshot.child("kan_grubu").getValue().toString();
                    String onemli_saglik_bilgileri=dataSnapshot.child("onemli_saglik_bilgileri").getValue().toString();
                    returner.put("kan_grubu",kan_grubu);
                    returner.put("onemli_saglik_bilgileri",onemli_saglik_bilgileri);


                    //dönen bilgilerin geçerli activitye gönderilmesini sağla
                    Log.i("get_userHealthInformations","Test başarılı mı? :"+(returner.size() > 0));




                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            saglik_bilgileri_DatRef.addValueEventListener(valueEventListener);




        }


        //veritabanından kullanıcı kişisel bilgilerini çekme
        public void get_userInformations(final String uid) {

            final HashMap<String,String> returner=new HashMap<>();
            kullanicilar_DatRef=kullanicilar_DatRef.child(uid);
            kullanicilar_DatRef.keepSynced(true);
            kullanicilar_DatRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String ad=dataSnapshot.child("ad").getValue().toString();
                    String soyad=dataSnapshot.child("soyad").getValue().toString();
                    String dogum_tarihi=dataSnapshot.child("dogum_tarihi").getValue().toString();
                    String cinsiyet=dataSnapshot.child("cinsiyet").getValue().toString();
                    String resim_link=dataSnapshot.child("resim_link").getValue().toString();
                    String kucuk_resim_link=dataSnapshot.child("kucuk_resim_link").getValue().toString();
                    String kisi_toplam_km=dataSnapshot.child("kisi_toplam_km").getValue().toString();
                    returner.put("ad",ad);
                    returner.put("soyad",soyad);
                    returner.put("dogum_tarihi",dogum_tarihi);
                    returner.put("cinsiyet",cinsiyet);
                    returner.put("resim_link",resim_link);
                    returner.put("kucuk_resim_link",kucuk_resim_link);
                    returner.put("kisi_toplam_km",kisi_toplam_km);

                    //dönen bilgilerin geçerli activitye gönderilmesini sağla
                    Log.i("get_userInformations","Test başarılı mı? :"+(returner.size() > 0));



                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        //kişiye ait yakınların bilgilerinin girilmesi
        public void push_familyInformations(String kisi_uid,String ad,String soyad,String gsm,String email)
        {
            HashMap<String,String> bilgiler=new HashMap<>();
            bilgiler.put("ad",ad);
            bilgiler.put("soyad",soyad);
            bilgiler.put("gsm",gsm);
            bilgiler.put("email",email);

            tanidiklar_DatRef.child(kisi_uid).push().setValue(bilgiler).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    //İşlem başarılı
                    Log.i("push_familyInformations","İşlem başarılı.");

                }
            });

        }

        //kişinin tüm yakınlarının bilgilerinin çekilmesi
    public void get_allFamilyInformations(String uid)
    {
        list_tanidiklar.clear();

        tanidiklar_DatRef=tanidiklar_DatRef.child(uid);
        tanidiklar_DatRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    HashMap<String,String> tanidik=new HashMap<>();
                    String ad=ds.child("ad").getValue().toString();
                    String soyad=ds.child("soyad").getValue().toString();
                    String gsm=ds.child("gsm").getValue().toString();
                    String email=ds.child("email").getValue().toString();

                    tanidik.put("ad",ad);
                    tanidik.put("soyad",soyad);
                    tanidik.put("gsm",gsm);
                    tanidik.put("email",email);

                    list_tanidiklar.add(tanidik);
                }
                //işlem başarılı ilgili sınıfa gönder
                Log.i("get_AllFamilyInformations","İşlem başarılı mı?: "+(list_tanidiklar.size() >0));



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    //kişiye ait araçların bilgilerinin girilmesi
    public void push_vehiclesInformations(String kisi_uid,String marka,String model,String arac_toplam_km,String secili_arac)
    {
        HashMap<String,String> bilgiler=new HashMap<>();
        bilgiler.put("marka",marka);
        bilgiler.put("model",model);
        bilgiler.put("arac_toplam_km",arac_toplam_km);
        bilgiler.put("secili_arac",secili_arac);

        araclar_DatRef.child(kisi_uid).push().setValue(bilgiler).addOnSuccessListener(new OnSuccessListener<Void>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onSuccess(Void aVoid) {

                //İşlem başarılı
                Log.i("push_vehiclesInformations","İşlem başarılı.");

            }
        });

    }
    //kişinin tüm araçlarının bilgilerinin çekilmesi
    public void get_allVehiclesInformations(String uid)
    {
        list_araclar.clear();
        araclar_DatRef=araclar_DatRef.child(uid);
        araclar_DatRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    HashMap<String,String> arac=new HashMap<>();
                    String marka=ds.child("marka").getValue().toString();
                    String model=ds.child("model").getValue().toString();
                    String arac_toplam_km=ds.child("arac_toplam_km").getValue().toString();
                    String secili_arac=ds.child("secili_arac").getValue().toString();

                    arac.put("marka",marka);
                    arac.put("model",model);
                    arac.put("arac_toplam_km",arac_toplam_km);
                    arac.put("secili_arac",secili_arac);

                    list_araclar.add(arac);
                }
                //işlem başarılı ilgili sınıfa gönder
                Log.i("get_AllFamilyInformations","İşlem başarılı mı?: "+(list_araclar.size() >0));





            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    //kişiye ait ekipman bilgilerinin girilmesi
    public void push_equipmentInformations(String kisi_uid,String marka,String model,String ekipman_tur)
    {
        HashMap<String,String> bilgiler=new HashMap<>();
        bilgiler.put("marka",marka);
        bilgiler.put("model",model);
        bilgiler.put("ekipman_tur",ekipman_tur);


        ekipmanlar_DatRef.child(kisi_uid).push().setValue(bilgiler).addOnSuccessListener(new OnSuccessListener<Void>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onSuccess(Void aVoid) {

                //İşlem başarılı
                Log.i("push_vehiclesInformations","İşlem başarılı.");

            }
        });

    }
    //kişinin tüm ekipman bilgilerinin çekilmesi
    public void get_allEquipmentInformations(String uid)
    {
        list_ekipmanlar.clear();
        ekipmanlar_DatRef=ekipmanlar_DatRef.child(uid);
        ekipmanlar_DatRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    HashMap<String,String> ekipman=new HashMap<>();
                    String marka=ds.child("ekipman_tur").getValue().toString();
                    String model=ds.child("marka").getValue().toString();
                    String arac_toplam_km=ds.child("model").getValue().toString();


                    ekipman.put("marka",marka);
                    ekipman.put("model",model);
                    ekipman.put("arac_toplam_km",arac_toplam_km);


                    list_ekipmanlar.add(ekipman);
                }
                //işlem başarılı ilgili sınıfa gönder
                Log.i("get_AllEquipmentInformations","İşlem başarılı mı?: "+(list_ekipmanlar.size() >0));





            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

        //kişinin km bilgisini güncelleme
        public void update_KmValueForUser(String uid, String km)
        {
            kullanicilar_DatRef=kullanicilar_DatRef.child(uid);
            kullanicilar_DatRef.child("kisi_toplam_km").setValue(km).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        //işlem başarılı
                        Log.i("update_KmValueForUser","İşlem başarılı.");
                    }
                    else
                    {
                        //işlem başarısız
                        Log.i("update_KmValueForUser","İşlem başarısız.");
                    }

                }
            });
        }

        //kişinin profil resminin yüklenmesi
        public void upload_profilePicture(final String uid, Uri resultUri)
        {
            //thum image
            File thumb_file=new File(resultUri.getPath());
            Bitmap thumb_bitmap=new Compressor(context)
                    .setMaxWidth(200)
                    .setMaxHeight(200)
                    .setQuality(75)
                    .compressToBitmap(thumb_file);
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            thumb_bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
            final byte[] thum_byte=baos.toByteArray();
            final StorageReference thumb_filepath=resim_Storage.child("profile_images").child("thumbs").child(uid+".jpg");
            //normal image
            StorageReference filepath=resim_Storage.child("profile_images").child(uid+".jpg");
            filepath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if(task.isSuccessful())
                    {
                        final String download_url=task.getResult().getDownloadUrl().toString();
                        //thumb
                        UploadTask uploadTask=thumb_filepath.putBytes(thum_byte);
                        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> thum_task) {
                                if(thum_task.isSuccessful())
                                {
                                    //normal
                                    String thumb_downloadUrl=thum_task.getResult().getDownloadUrl().toString();
                                    Map update_hashMap=new HashMap();
                                    update_hashMap.put("image",download_url);
                                    update_hashMap.put("thumb_image",thumb_downloadUrl);

                                    kullanicilar_DatRef=kullanicilar_DatRef.child(uid);
                                    kullanicilar_DatRef.updateChildren(update_hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {
                                               //işlem başarılı
                                                Log.i("upload_ProfilePicture","İşlem Başarılı.");
                                            }
                                            else
                                            {
                                                //işlem başarısız
                                                Log.i("upload_ProfilePicture","İşlem Başarısız.");

                                            }
                                        }
                                    });
                                }
                                else
                                {
                                    //işlem başarısız
                                    Log.i("upload_ProfilePicture","İşlem Başarısız.");
                                }

                            }
                        });


                    }
                    else
                    {
                        //işlem başarısız
                        Log.i("upload_ProfilePicture","İşlem Başarısız.");

                    }
                }
            });



        }

        //arkadaşlık isteği gönderme
        public void push_friend_request(final String gonderen_kisi_uid,final String gonderilen_kisi_uid)
        {

            ark_istekleri_DatRef.child(gonderen_kisi_uid).child(gonderilen_kisi_uid).child("istek_durumu").setValue("Gonderildi").
                    addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {

                                ark_istekleri_DatRef.child(gonderilen_kisi_uid).child(gonderen_kisi_uid).child("istek_durumu").setValue("Alındı").
                                        addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {

                                                //send_notification(gonderilen_uid,"request"); sunucu hazır olunca
                                                Log.i("push_friend_request","İşlem başarılı.");


                                            }
                                        });
                            }
                            else
                            {
                                Log.i("push_friend_request","Hata: "+task.getException().toString());
                            }
                        }
                    });
        }
        //arkadaşlık isteğini iptal etme
        public void cancel_friend_request(final String gonderen_kisi_uid, final String gonderilen_kisi_uid)
        {

            ark_istekleri_DatRef.child(gonderen_kisi_uid).child(gonderilen_kisi_uid).removeValue()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            ark_istekleri_DatRef.child(gonderilen_kisi_uid).child(gonderen_kisi_uid)
                                    .removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    //işlem başarılı
                                    Log.i("calcel_friend_request","İşlem başarılı.");

                                }
                            });

                        }
                    });
        }

        //arkdaşlık isteğini kabul etme
        public void accept_friend_request(final String gonderen_kisi_uid, final String gonderilen_kisi_uid)
        {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            final String current_date=dateFormat.format(new Date());
            arkadasliklar_DatRef.child(gonderen_kisi_uid).child(gonderilen_kisi_uid).setValue(current_date).
                    addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            arkadasliklar_DatRef.child(gonderilen_kisi_uid).child(gonderen_kisi_uid).setValue(current_date)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                            ark_istekleri_DatRef.child(gonderen_kisi_uid).child(gonderilen_kisi_uid).removeValue()
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            ark_istekleri_DatRef.child(gonderilen_kisi_uid).child(gonderen_kisi_uid)
                                                                    .removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    //İşlem başarılı
                                                                    Log.i("accept_friend_request","İşlem Başarılı");
                                                                }
                                                            });

                                                        }
                                                    });

                                        }
                                    });

                        }
                    });
        }

        //kişilerin arkadaşlık açısından durumlarını getir( İstek Gitti,İstek Geldi,Zaten Arkadaşlar)
        public void get_request_feature(final String gonderen_kisi_uid, final String gonderilen_kisi_uid) {
            //get request feature
            ark_istekleri_DatRef.child(gonderen_kisi_uid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild(gonderilen_kisi_uid)) {
                        String req_type = dataSnapshot.child(gonderilen_kisi_uid).child("istek_durumu").getValue().toString();
                        //istek durumunu ilgili sınıfa gönder (Gönderildi/Alındı)
                        Log.i("get_request_feature", "İstek durumu çekildi: " + req_type);
                    } else {
                        //hali hazırda zaten arkadaşlar ise
                        arkadasliklar_DatRef.child(gonderen_kisi_uid).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild(gonderilen_kisi_uid)) {
                                    String req_type = "Arkadas";
                                    //istek durumunu ilgili sınıfa gönder (Arkadas)
                                    Log.i("get_request_feature", "Kişiler Arkadaşlar.");


                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


        //toplam kullanıcı sayısını çekme
        public void get_count_of_childrens_from_users()
        {


            kullanicilar_DatRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    //ilgili sınıfa gönder
                    long count=dataSnapshot.getChildrenCount();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

}
