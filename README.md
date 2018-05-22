# KaskProjesi Bilgilendirme Dosyası

<h3>Proje İçerisindeki Klasörler</h3>
<ul>
  <li><a href="#git_firebaseModules"<b>FirebaseModules Klasörü</b></a></li>
</ul>  

<a name="git_firebaseModules"><h3>FirebaseModules Klasörü</h3></a>
<ul>
   <li><a href="#git_firebaseBilgi"<b>Başlamadan önemli bilgi!</b></a></li>
   <li><a href="#git_firebaseBagimliliklar"<b>Sınıfların Bağımlılıkları (Gradle dependencies) </b></a></li>
  <li><a href="#git_firebaseLogin"<b>FirebaseLoginModule.class</b></a></li>
  <li><a href="#git_firebaseDatabase"<b>FirebaseDatabaseModule.class</b></a></li>
</ul>  

<a name="git_firebaseBilgi"><h3>Asenkron Probleminin Çözümü</h3></a>
<b>Sınıfları incelediğinizde bütün fonksiyonların void olduğunu farkediceksiniz.Veritabanı işlemleri asenkron çalıştığı için bir fonksiyonu kullanırken size düşen (şayet geriye bir değer döndürmesini istiyorsanız) ilk olarak o fonksiyonun aldığı parametreler kısmına bu fonksiyonu çağırıcağınız sınıfın nesnesini ve referansını ilave etmeli daha sonra işlem sonlandığında ( Örneğin OnSucces() fonksiyonu çalıştığında) referansını gönderdiğimiz sınıfı kullanarak view kısmında istediğiniz değişiklikleri yapan bir fonksiyonu çağırıp ona istediğiniz geri dönüş değerini döndürebilirsiniz.(Örneğin veri çekme işlemi sonlandığında MainActivity'nin updateUI(hashMap_referans) fonksiyonunu çağırıp ona HashMap<String> ile tüm kullanıcı verilerini gönderebilirim.</b>
  
<a name="git_firebaseLogin"><h3>FirebaseModules Klasörü</h3></a>
<ul>
  <li><b style="color:blue;">getCurrentUser() fonksiyonu</b>
    <ul><li><b>Giriş yapmış olan geçerli kullanıcıyı çekebilir.UID'sini elde edebilirsiniz.</b></li></ul></li>
   <li><b>createNewUser() fonksiyonu</b>
    <ul><li><b>Email ve Şifre bilgisi göndererek yeni kullanıcı kayıt edebilirsiniz.</b></li></ul></li>
   <li><b>logIn() fonksiyonu</b>
    <ul><li><b>Email ve Şifre bilgisi göndererek üye girişi işlemi gerçekleştirebilirsiniz.</b></li></ul></li>
    <li><b>logOut() fonksiyonu</b>
    <ul><li><b>Hali hazırda bir kullanıcının giriş yapmış olduğundan eminseniz sunucu sisteminden çıkış işlemini gerçekleştirebilirsiniz.</b></li></ul></li>
</ul>  

