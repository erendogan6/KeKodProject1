# Hafta 1 Projesi - Switch ve BottomNavigationBar Uygulaması

 <img src="https://i.hizliresim.com/8jvow1b.png" width="200" height="200">


Bu proje, **KeKod Grubu** tarafından düzenlenen **ilk hafta proje yarışması** kapsamında geliştirilmiştir. Uygulama, **Jetpack Compose**, **Jetpack Navigation** ve **ViewBinding/DataBinding** gibi modern Android bileşenlerini kullanarak oluşturulmuş bir Android uygulamasıdır.


## Kullanım Videosu

<a href="https://www.youtube.com/shorts/CN-bCs214Kk"> <img src="https://i.hizliresim.com/g4kheua.png" alt="Seyahat Asistanım Kullanım Videosu" width="320" height="680">  </a> 

## Proje Gereksinimleri ve Hedefleri

Bu uygulama, kullanıcıların birden fazla **Switch** kontrolü ve bir alt gezinme çubuğu (**BottomNavigationBar**) ile etkileşimde bulunabileceği bir yapı sunmaktadır.

### Proje Adımları

#### 1. Switch Kontrol Mekanizması:
- Uygulama açıldığında **6 adet Switch** görüntülenir.
- **"Ego" switch’i** varsayılan olarak **açık** gelir.
- "Ego" switch’i açıkken diğer **5 switch** açılamaz.
- "Ego" switch’i kapatıldığında diğer switch’ler açılabilir.

#### 2. BottomNavigationBar:
- "Ego" switch’i **kapalı** iken **BottomNavigationBar** görünür, açıkken gizlenir.
- Her bir switch açıldığında, o switch'e ait bir ikon ve metin **BottomNavigationBar**'a eklenir.
- **BottomNavigationBar**'ın sıralaması, switch'lerin açılma sırasına göre belirlenir.

#### 3. Switch Detay Ekranları:
- **BottomNavigationBar**'a eklenen her bir öğeye tıklandığında, ilgili switch'in detay ekranına gidilir.
- Her detay ekranı, kullanıcıya belirli bir mesaj veya içerik gösterir.

#### 4. ViewBinding Kullanımı:
- Tüm ekranlarda **ViewBinding** kullanılmıştır.

## Proje Yapısı

Projede kullanılan dosya ve sınıfların yapısı aşağıdaki gibidir:

```plaintext
com.erendogan6.kekodproject1
│
├── model
│   └── SwitchModel.kt                  # Switch modellerini tanımlayan sınıf.
│
├── ui
│   ├── activity
│   │   └── MainActivity.kt             # Ana aktivite, uygulamanın başlangıç noktası.
│   │
│   ├── components
│   │   ├── GradientBackground.kt       # Arka plan geçişlerini yöneten sınıf.
│   │   └── SwitchScreen.kt             # Jetpack Compose kullanılarak oluşturulan switch ekranı bileşeni.
│   │
│   ├── fragment
│   │   ├── MainFragment.kt             # Switch'lerin ve BottomNavigationBar'ın kontrol edildiği ana ekran.
│   │   └── SwitchFragment.kt           # Her bir switch'in detay ekranını gösteren fragment.
│
├── viewmodel
│   └── MainViewModel.kt                # Uygulama genelindeki iş mantığını yöneten ViewModel.
│
├── androidTest
│   └── MainActivityTest.kt             # Ana aktivite için test sınıfı.
│
└── test
    └── MainViewModelTest.kt            # ViewModel sınıfı için test sınıfı.
```


## Kullanılan Teknolojiler ve Kütüphaneler

- **Programlama Dili:** Kotlin
- **Minimum SDK:** 26
- **Hedef SDK:** 35

### Kütüphaneler:

- **Jetpack Compose:** Modern ve duyarlı kullanıcı arayüzleri oluşturmak için kullanılan yeni nesil UI araç takımı.
- **Jetpack Navigation:** Uygulama içi ekranlar arası güvenli ve yapılandırılmış gezinme sağlamak için kullanılan kütüphane.
- **ViewBinding:** Görünümleri bağlamak için kullanılan kütüphane.
- **Lottie Compose:** Animasyon göstermek için kullanılan kütüphane.
- **Androidx:** Fragment, Core KTX, AppCompat, ConstraintLayout gibi Android bileşenleri içi.
- **Material Components:** Google’ın Material Design yönergelerine uygun kullanıcı arayüzü bileşenleri.
- **JUnit:** Java için birim testleri yazmak için kullanılan standart test kütüphanesi.
- **Espresso:** Android uygulamalarında kullanıcı arayüzü testleri yapmak için kullanılan kütüphane.
- **Mockito:** Mock nesneler oluşturup, davranışlarını simüle etmek ve test etmek için kullanılan kütüphane.
- **Core Testing:** Jetpack bileşenlerinin test edilmesi için kullanılan yardımcı test kütüphaneleri.

### Dil Desteği: ###
- İngilizce %100
- Türkçe %100
  
## Testler

Projede, uygulama mantığını ve birimler arası etkileşimi test etmek amacıyla çeşitli testler hazırlanmıştır:

- **MainActivityTest:** Ana aktivitenin tüm fonksiyonlarını test eder.
- **MainViewModelTest:** Uygulama iş mantığını ve verilerin doğruluğunu test eder.

## Kurulum ve Kullanım

Bu projeyi çalıştırmak için:

1. **Android Studio**'yu açın.
2.  Projeyi **GitHub** veya yerel bir depodan klonlayın veya indirin.
```plaintext
git clone https://github.com/erendogan6/kekodproject1.git
cd kekodproject1
```
3. `build.gradle` dosyasını senkronize edin.
4. Uygulamayı cihazınıza veya emülatöre yükleyin.
5. **Testleri** çalıştırmak için `androidTest` ve `test` dizinlerindeki test sınıflarını kullanın.

## Katkıda Bulunma

Projeye katkıda bulunmak isteyenler için katkı kuralları ve adımları **CONTRIBUTING.md** dosyasında açıklanmıştır.

## Lisans

Bu proje, [MIT Lisansı](LICENSE) ile lisanslanmıştır.
