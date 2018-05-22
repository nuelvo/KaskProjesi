# KaskProjesi Bilgilendirme Dosyası

<h3>Proje İçerisindeki Klasörler</h3>
<ul>
  <li><a href="#git_firebaseModules"<b>FirebaseModules Klasörü</b></a></li>
</ul>  

<a name="git_firebaseModules"><h3>FirebaseModules Klasörü</h3></a>
<ul>
   <li><a href="#git_firebaseBilgi"<b>Başlamadan önemli bilgi!</b></a></li>
  <li><a href="#git_firebaseLogin"<b>FirebaseLoginModule.class</b></a></li>
  <li><a href="#git_firebaseDatabase"<b>FirebaseDatabaseModule.class</b></a></li>
</ul>  

<a name="git_firebaseBilgi"><h3>Asenkron Probleminin Çözümü</h3></a>
<b>Sınıfları incelediğinizde bütün fonksiyonların void olduğunu farkediceksiniz.Veritabanı işlemleri asenkron çalıştığı için bir fonksiyonu kullanırken size düşen (şayet geriye bir değer döndürmesini istiyorsanız) ilk olarak o fonksiyonun aldığı parametreler kısmına bu fonksiyonu çağırıcağınız sınıfın nesnesini ve referansını ilave etmeli daha sonra işlem sonlandığında ( Örneğin OnSucces() fonksiyonu çalıştığında) referansını gönderdiğimiz sınıfı kullanarak view kısmında istediğiniz değişiklikleri yapan bir fonksiyonu çağırıp ona istediğiniz geri dönüş değerini döndürebilirsiniz.(Örneğin veri çekme işlemi sonlandığında MainActivity'nin updateUI(hashMap_referans) fonksiyonunu çağırıp ona HashMap<String> ile tüm kullanıcı verilerini gönderebilirim.</b>

