# KaskProjesi Bilgilendirme Dosyası

<h3>Proje İçerisindeki Klasörler</h3>
<ul>
  <li><a href="#git_firebaseModules"<b>FirebaseModules Klasörü</b></a></li>
  <li><a href="#git_facebookModules"<b>FacebookModules Klasörü</b></a></li>
</ul>  

<a name="git_firebaseModules"><h3>FirebaseModules Klasörü</h3></a>
<ul>
   <li><a href="#git_firebaseBilgi"<b>Başlamadan önemli bilgi!</b></a></li>
   <li><a href="#git_firebaseBagimliliklar"<b>Sınıfların Bağımlılıkları (Gradle dependencies) </b></a></li>
  <li><a href="#git_firebaseLogin"<b>FirebaseLoginModule.class</b></a></li>
  <li><a href="#git_firebaseDatabase"<b>FirebaseDatabaseModule.class</b></a></li>
</ul>  

<a name="git_firebaseBilgi"><h3>Asenkron Probleminin Çözümü</h3></a>
<b>Sınıfları incelediğinizde bütün fonksiyonların void olduğunu farkedeceksiniz.Veritabanı işlemleri asenkron çalıştığı için bir fonksiyonu kullanırken size düşen (şayet geriye bir değer döndürmesini istiyorsanız) ilk olarak o fonksiyonun aldığı parametreler kısmına bu fonksiyonu çağırıcağınız sınıfın nesnesini ve referansını ilave etmeli daha sonra işlem sonlandığında ( Örneğin OnSucces() fonksiyonu çalıştığında) referansını gönderdiğimiz sınıfı kullanarak view kısmında istediğiniz değişiklikleri yapan bir fonksiyonu çağırıp ona istediğiniz geri dönüş değerini döndürebilirsiniz.(Örneğin veri çekme işlemi sonlandığında MainActivity'nin updateUI(hashMap_referans) fonksiyonunu çağırıp ona HashMap<String> ile tüm kullanıcı verilerini gönderebilirim.</b>

<a name="git_firebaseBagimliliklar"><h3>FirebaseModules Klasörü Sınıflarının Bağımlılıkları</h3></a>
<ol>
  <li>implementation 'com.google.firebase:firebase-database:11.8.0'</li>
  <li>implementation 'com.google.firebase:firebase-storage:11.8.0'</li>
  <li>implementation 'com.google.firebase:firebase-auth:11.8.0'</li>
  <li>implementation 'com.google.firebase:firebase-messaging:11.8.0'</li>
  <li> implementation 'id.zelory:compressor:2.0.0'</li>
  <li>apply plugin: 'com.google.gms.google-services'</li>
  <li>classpath 'com.google.gms:google-services:3.1.1' ( Gradle Project )</li>
</ol> 
  
<a name="git_firebaseLogin"><h3>FirebaseLoginModule.class Fonksiyonlarının Bilgileri</h3></a>
<ul>
  <li><b>getCurrentUser() fonksiyonu</b>
    <ul><li><b>Giriş yapmış olan geçerli kullanıcıyı çekebilir.UID'sini elde edebilirsiniz.</b></li></ul></li>
   <li><b>createNewUser() fonksiyonu</b>
    <ul><li><b>Email ve Şifre bilgisi göndererek yeni kullanıcı kayıt edebilirsiniz.</b></li></ul></li>
   <li><b>logIn() fonksiyonu</b>
    <ul><li><b>Email ve Şifre bilgisi göndererek üye girişi işlemi gerçekleştirebilirsiniz.</b></li></ul></li>
    <li><b>logOut() fonksiyonu</b>
    <ul><li><b>Hali hazırda bir kullanıcının giriş yapmış olduğundan eminseniz sunucu sisteminden çıkış işlemini gerçekleştirebilirsiniz.</b></li></ul></li>
</ul>  

<a name="git_firebaseDatabase"><h3>FirebaseDatabaseModule.class Fonksiyonlarının Bilgileri</h3></a>
<ul>
  <li><b>push_userInformations() fonksiyonu</b>
    <ul><li><b>Kullanıcının kişisel bilgilerini veritabanında ilgili alana kaydeder.Aldığı parametreler: uid,ad,soyad,dogum_tarihi,cinsiyet,resim_link,kucuk_resim_link olmak üzere hepsi "String" idir.</b></li></ul></li>
   <li><b> push_userHealthInformations() fonksiyonu</b>
    <ul><li><b>Kişinin sağlık bilgilerini veritabanında ilgili alana kaydeder.Aldığı parametreler: uid,kan_grubu,onemli_saglik_bilgileri olmak üzere hepsi String idir.</b></li></ul></li>
   <li><b>get_userHealthInformations() fonksiyonu</b>
    <ul><li><b>Kişinin sağlık bilgilerini veritabanından çeker.Aldığı parametreler:uid ( kişinin benzersiz user id değeri) Dikkat bu fonksiyon geriye değer döndürmesi kesinlikle gerektiğinden ilk baştaki </b><a href="#git_firebaseBilgi"<b>"Asenkron Probleminin Çözümü"</b></a><b> incelemeniz gerekebilir.</b></li></ul></li>
   <li><b>get_userInformations() fonksiyonu</b>
    <ul><li><b>Kişinin kişisel bilgilerini veritabanından çeker.Aldığı parametreler:uid ( kişinin benzersiz user id değeri) Dikkat bu fonksiyon geriye değer döndürmesi kesinlikle gerektiğinden ilk baştaki </b><a href="#git_firebaseBilgi"<b>"Asenkron Probleminin Çözümü"</b></a><b> incelemeniz gerekebilir.</b></li></ul></li>
 <li><b> push_familyInformations() fonksiyonu</b>
    <ul><li><b>Kişinin yakınlarına ait bilgileri veritabanında ilgili alana kaydeder.Aldığı parametreler: uid,ad,soyad,gsm,email olmak üzere hepsi String idir.</b></li></ul></li>
<li><b>get_allFamilyInformations() fonksiyonu</b>
    <ul><li><b>Kişinin tüm yakınlarına ait bilgileri veritabanından çeker.Aldığı parametreler:uid ( kişinin benzersiz user id değeri) Dikkat bu fonksiyon geriye değer döndürmesi kesinlikle gerektiğinden ilk baştaki </b><a href="#git_firebaseBilgi"<b>"Asenkron Probleminin Çözümü"</b></a><b> incelemeniz gerekebilir.</b></li></ul></li>
    <li><b> push_vehiclesInformations() fonksiyonu</b>
    <ul><li><b>Kişinin araçlarına ait bilgileri veritabanında ilgili alana kaydeder.Aldığı parametreler: uid,marka,model,arac_toplam_km,secili_arac olmak üzere hepsi String idir.</b></li></ul></li>
    <li><b>get_allVehiclesInformations() fonksiyonu</b>
    <ul><li><b>Kişinin tüm araçlarına ait bilgileri veritabanından çeker.Aldığı parametreler:uid ( kişinin benzersiz user id değeri) Dikkat bu fonksiyon geriye değer döndürmesi kesinlikle gerektiğinden ilk baştaki </b><a href="#git_firebaseBilgi"<b>"Asenkron Probleminin Çözümü"</b></a><b> incelemeniz gerekebilir.</b></li></ul></li>
    <li><b> push_equipmentInformations() fonksiyonu</b>
    <ul><li><b>Kişinin ekipmanlarına ait bilgileri veritabanında ilgili alana kaydeder.Aldığı parametreler: uid,marka,model,ekipman_tur olmak üzere hepsi String idir.</b></li></ul></li>
    <li><b> get_allEquipmentInformations() fonksiyonu</b>
    <ul><li><b>Kişinin tüm ekipmanlarına ait bilgileri veritabanından çeker.Aldığı parametreler:uid ( kişinin benzersiz user id değeri) Dikkat bu fonksiyon geriye değer döndürmesi kesinlikle gerektiğinden ilk baştaki </b><a href="#git_firebaseBilgi"<b>"Asenkron Probleminin Çözümü"</b></a><b> incelemeniz gerekebilir.</b></li></ul></li>
     <li><b> update_KmValueForUser() fonksiyonu</b>
    <ul><li><b>Kişiye ait toplam yapılmış KM değerini veritabanında ilgili alanda günceler.Aldığı parametreler: uid,km olmak üzere hepsi String idir.</b></li></ul></li>
    <li><b> upload_profilePicture() fonksiyonu</b>
    <ul><li><b>Kişiye ait resimi Storage kısmına yükler ve veritabanında kişinin ilgili alanlarına resim linklerini günceller.Aldığı parametreler: String olarak uid ve Uri nesnesi olarak resultUri idir.</b></li></ul></li>
    <li><b>push_friend_request() fonksiyonu</b>
    <ul><li><b>Arkadaşlık isteği işlemi tetiklendiğinde veritabanının ilgili alanlarını günceller/oluşturur.Aldığı parametreler:
      gonderen_kisi_uid ve gonderilen_kisi_uid olmak üzere hepsi String idir.</b></li></ul></li>
     <li><b>cancel_friend_request() fonksiyonu</b>
    <ul><li><b>Arkadaşlık isteği işlemi iptal edildiğinde veritabanının ilgili alanlarını siler.Aldığı parametreler:
      gonderen_kisi_uid ve gonderilen_kisi_uid olmak üzere hepsi String idir.</b></li></ul></li>
      <li><b>accept_friend_request() fonksiyonu</b>
    <ul><li><b>Arkadaşlık isteği işlemi kabul edildiğinde veritabanının ilgili alanlarında gerekli işlemleri yapar.</b><a href="https://drive.google.com/open?id=1G623sNkfz6zT_ehYAmAtICOb4JFyeLry"<b>(Detaylı bilgi için veritabanı yapısını gösteren pdf dosyasını inceleyiniz)</b></a><b>Aldığı parametreler:
      gonderen_kisi_uid ve gonderilen_kisi_uid olmak üzere hepsi String idir.</b></li></ul></li>
      <li><b> get_request_feature() fonksiyonu</b>
    <ul><li><b>Kişilerin arkadaşlıklarına ait bilgileri veritabanından çeker(İstek gitti,İstek geldi veya Arkadaşlar).Aldığı parametreler:gonderen_kisi_uid,gonderilen_kisi_uid olmak üzere hepsi String idir.Dikkat bu fonksiyon geriye değer döndürmesi kesinlikle gerektiğinden ilk baştaki </b><a href="#git_firebaseBilgi"<b>"Asenkron Probleminin Çözümü"</b></a><b> incelemeniz gerekebilir.</b></li></ul></li>
     <li><b> get_count_of_childrens_from_users() fonksiyonu</b>
    <ul><li><b>Sunucudaki tüm kullanıcı sayısını döndürür.Hiç parametre almaz.Dikkat bu fonksiyon geriye değer döndürmesi kesinlikle gerektiğinden ilk baştaki </b><a href="#git_firebaseBilgi"<b>"Asenkron Probleminin Çözümü"</b></a><b> incelemeniz gerekebilir.</b></li></ul></li>
    
</ul>
<a name="git_facebookModules"><h3>FacebookModules Klasörü</h3></a>
<ul>
   <li><a href="#git_facebookBagimliliklar"<b>Sınıfların Bağımlılıkları (Gradle dependencies) </b></a></li>
  <li><a href="#git_facebokCallBack"<b>FacebookCallBack.class</b></a></li>
  <li><a href="#git_facebookMainActivity"<b>MainActivity.class</b></a></li>
</ul>


<a name="git_facebookBagimliliklar"><h3>FacebookModules Klasörü Sınıflarının Bağımlılıkları</h3></a>
<ol>
  <li>implementation 'com.facebook.android:facebook-android-sdk:4.28.0'</li>
  <li>implementation 'com.squareup.picasso:picasso:2.5.2'</li>
  <li>classpath 'com.google.gms:google-services:3.1.1' ( Gradle Project )</li>
</ol> 

<a name="git_facebokCallBack"><h3>FacebookCallBackManager.class Sınıfı</h3></a>
<b>Gerekli bilgileri çeken sınıf FacebookCallBack sınıfından implement edilerek hazırlanmıştır.MainActivity örneğinde olduğu gibi bu sınıf Facebook LoginButton nesnesine registerCallBack() fonksiyonu ile eklenmelidir.</b>

<a name="git_facebookMainActivity"><h3>MainActivity.class Sınıfı</h3></a>
<b>Gerekli View işlemlerinin yapıldığı sınıftır.Bilgiler CallBack sınıfı ile çekilir.Gerekli bilgiler facebook sunucularından çekildikten sonra kendi yazdığımız updateUI() fonksiyonu yardımı ile View'ımıza aktarılır.Not: onActivityResult() kısmı çok önemlidir her aktivitinin hazır fonksiyonudur onu ezerek ( Override ) örnekteki bir satır kodu yazmanız gerekmektedir. </b>


