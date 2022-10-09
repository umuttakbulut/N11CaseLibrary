# N11CaseLibrary
N11 için geliştirilen kütüphane yönetim prokjjesi.

Kullanılan teknolojiler;
*Backend : Spring , , Maven , Unit Tests: Mockito.
*FrontEnd : AngularJs.
*Database: MongoDB

Fonksiyonları.
-Kitap Ekle,
-Kitap Güncelle,
-Kitap Sil.

Kitap ekleme işleminde Google API "https://developers.google.com/recaptcha/docs/display" 

* Bunu çalıştırmak için önce google "https://www.google.com/recaptcha/admin#createsite" adresinden anahtar almalısınız.

Anahtarı aldıktan sonra, webapp/WEB-INF/pages/books.jsp Satırı:108'den anahtarınızı "your_public_key" alanına ayarlamalısınız.

107      <script type="text/javascript"
108            src="http://www.google.com/recaptcha/api/challenge?k=your_public_key">
109      </script>
