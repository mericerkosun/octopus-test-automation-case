# Octopus Test Otomasyon Projesi

Bu proje, sağlanan teknik vaka (case) dokümanı doğrultusunda farklı web siteleri (SauceDemo, ParaBank, DemoBlaze, HerokuApp, UITestingPlayground) için geliştirilmiş, sürdürülebilir ve paralel koşuma uygun bir Selenium test otomasyon framework'üdür.

## 🚀 Proje Hakkında

Proje, modern yazılım geliştirme prensiplerine (Clean Code, SOLID) sadık kalınarak, yüksek test bağımsızlığı ve genişletilebilirlik hedeflenerek tasarlanmıştır. Tüm testler birbirlerinden tamamen bağımsızdır ve aynı anda paralel olarak koşturulabilirler.

## 🛠 Kullanılan Teknolojiler

- **Dil:** Java 21
- **Otomasyon:** Selenium WebDriver (4.27.0)
- **Test Framework:** TestNG
- **Bağımlılık Yönetimi:** Maven
- **Raporlama:** Allure Report
- **Mimari:** Page Object Model (POM)
- **Veri Üretimi:** DataFaker
- **Yapılandırma:** AEONBITS Owner (Singleton Pattern)
- **CI/CD:** GitHub Actions
- **Konteynerizasyon:** Docker & Docker Compose

## 🏗 Mimari Yaklaşım ve Tasarım Desenleri

### 1. Page Object Model (POM)
Web sayfalarındaki öğeler ve bu öğelerle yapılan etkileşimler, test kodundan ayrıştırılmıştır. Her web sayfası/modül için ayrı bir `Page` sınıfı oluşturularak `BasePage` sınıfından türetilmiştir.

### 2. Merkezi Driver Yönetimi (ThreadLocal)
`DriverManager` sınıfı, `ThreadLocal<WebDriver>` kullanarak her test thread'i için izole bir driver instance'ı sağlar. Bu sayede paralel execution sırasında oturum çakışmaları tamamen önlenmiştir.

### 3. Base Sınıflar
- **BasePage:** Bekleme stratejileri (Explicit Wait), element etkileşimleri ve ortak metodları barındırır.
- **BaseTest:** Her test öncesi driver kurulumunu ve sonrası temizlik (quit) işlemlerini yönetir.

### 4. Konfigürasyon Yönetimi
Owner kütüphanesi kullanılarak `config.properties` dosyası üzerinden çevre değişkenleri yönetilir. `ConfigManager` bir Singleton olarak tasarlanmıştır.

## 📦 Kurulum ve Çalıştırma

### Gereksinimler
- Java 21
- Maven
- Google Chrome (Yerel koşum için)

### Yerel Çalıştırma
Projeyi klonladıktan sonra bağımlılıkları yükleyin:
```bash
mvn clean install
```

Tüm testleri varsayılan ayarlarla (Headful) koşturmak için:
```bash
mvn test
```

Testleri **Headless** (arayüzsüz) modda koşturmak için:
```bash
mvn test -Dheadless=true
```

Sadece belirli bir süiti (örn: SauceDemo) koşturmak için:
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng-saucedemo.xml
```

### Docker ile Çalıştırma
Docker yüklü olan sistemlerde hiçbir bağımlılığa (Java, Chrome vb.) ihtiyaç duymadan testleri koşturabilirsiniz:
```bash
docker-compose up --build
```

## 📊 Raporlama

Test sonuçlarını görsel olarak incelemek için Allure Report entegrasyonu mevcuttio.

1. Testleri koşturun.
2. Raporu oluşturun ve tarayıcıda açın:
```bash
mvn allure:serve
```

## 🤖 CI Entegrasyonu

Proje, GitHub Actions ile entegre edilmiştir. `.github/workflows/maven.yml` dosyası sayesinde her commit sonrası testler GitHub sunucularında otomatik olarak koşar ve rapor sonuçları artifakt olarak saklanır.

## 🛡 Test Stratejisi ve Varsayımlar

- **Bağımsızlık:** Testler "Atomik" düzeydedir. Hiçbir test bir diğerinin sonucuna veya verisine ihtiyaç duymaz.
- **Senaryo Kapsamı:** Hem pozitif hem de negatif (geçersiz login, boş form vb.) senaryolar kapsanmıştır.
- **Wait Stratejisi:** `Implicit wait` yerine, stabiliteyi artırmak için ilgili elemente özel `Explicit Wait` stratejileri tercih edilmiştir.
- **Dinamik Veri:** Kayıt olma (registration) gibi senaryolarda `Faker` kütüphanesi ile her seferinde benzersiz kullanıcılar oluşturulur.
- **ParaBank Notu:** ParaBank sitesindeki session yönetimi kaynaklı kısıtlar nedeniyle, pozitif ve negatif testler `BaseTest` içindeki `registerUniqueUser` yardımcı metoduyla tamamen izole edilmiştir.

---
**Geliştiren:** Meriç Emre ERKOŞUN
