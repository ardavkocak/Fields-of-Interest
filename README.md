# Fields of Interest (İlgi Alanı Çıkarımı)

Twitter kullanıcılarının attığı tweet'lerden, kullanıcıların **ilgi alanlarını** çıkartan bir Java uygulaması. Veri yapıları (ArrayList, HashMap, LinkedList, Graph) **sıfırdan** yazılmış olup, kullanıcı–takipçi ilişkileri bir graf yapısı üzerinde modellenmiştir.

## Özellikler

- **Kendi yazılmış veri yapıları**:
  - `ArrayListim<T>` — dinamik dizi
  - `HashMapim<K, V>` — açık hash tablosu (chaining)
  - `LinkedListim<T>` — tek yönlü bağlı liste
  - `MyGraph<T>` — komşuluk listesi tabanlı graf
- **Tweet temizleme**: noktalama, sayı ve stopword çıkarımı
- **Kelime frekans analizi**: her kullanıcı için en çok kullanılan kelimeler → ilgi alanı tahmini
- **Sosyal graf**: takipçi/takip edilen ilişkileri graph üzerinde tutulur
- **Java Swing arayüzü**: `NewJFrame1`, `NewJFrame2` formları ile kullanıcı arama, ilgi alanı listeleme

## Veri Kaynakları

Aşağıdaki JSON dosyaları repoda bulunmaz; bağlantılardan indirilip projeye eklenmelidir:

- **StopWords.json** — https://drive.google.com/file/d/14DIqaO0g3mH_3wW6x6bjly2UMTgny9Pv/view?usp=sharing
- **twitter_data_en_30K.json** — https://drive.google.com/file/d/1o1yS8eksp649b9j3JP7G5g2ZMu7jmNdL/view?usp=sharing

## Bağımlılıklar

- **Java 8+**
- **Gson** — JSON parse etme

## Çalıştırma

```bash
javac -cp "lib/gson.jar" -d out src/*.java
java -cp "out;lib/gson.jar" Main          # Windows
java -cp "out:lib/gson.jar" Main          # Linux/macOS
```

Veya NetBeans/IntelliJ üzerinde projeyi açıp Gson'u classpath'e ekleyin.

## Dosya Yapısı

```
src/
├── Main.java              # Giriş noktası, JSON okuma, akış kontrolü
├── User.java              # Kullanıcı, tweet, takipçi, frekans, ilgi alanı
├── MyGraph.java           # Generic graph (komşuluk listesi)
├── ArrayListim.java       # Custom ArrayList
├── HashMapim.java         # Custom HashMap (chaining)
├── LinkedListim.java      # Custom LinkedList
├── NewJFrame1.java/.form  # Swing arayüz
├── NewJFrame2.java/.form
└── graf.txt               # Çıktı graf dosyası
```

## Bağlam

Veri Yapıları ve Algoritmalar dersi proje ödevi. Standart Java koleksiyon sınıflarını **kullanmadan** kendi veri yapılarını yazmak hedeftir.
