import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;



public class Main {

    //StopWords isimli json dosyasından kelimeleri tek tek okuyup bu arrayListe atacağız.
    public static ArrayListim<String> stopWordsList = new ArrayListim<>();
    public static User mainkullanici = null;

    private static String removePunctuation(String input) {
        // Define a regular expression pattern for punctuation
        String regex = "\\p{Punct}";

        // Use the pattern to replace all occurrences of punctuation with an empty string
        return input.replaceAll(regex, "");
    }

    public static ArrayListim<User> kullanicilarYeniListem = new ArrayListim<>();


    public static void main(String[] args) {


        //Json dosyasındaki oluşturulmuş tüm kullanıcıları bir ArrayList'e atama işlemi

        String userName = null;
        String name = null;
        int followersCount = 0;
        int followingCount = 0;
        String language = null;
        String region = null;

        //Kullancıyıa dair bütün işlemler bittikten sonra kullanıcı aşağıdaki hashMap'te tutulmalıdır.
        HashMapim<String , User> allUsers = new HashMapim<>();


        try {
            Gson gson = new Gson();

            // StopWords isimli json dosyasındaki veriyi JsonArray'e at.
            //JsonArray stopWordsJsonArray = gson.fromJson(new FileReader("C:\\Users\\alief\\IdeaProjects\\GSON\\src\\StopWords.json"), JsonArray.class);
            JsonArray stopWordsJsonArray = gson.fromJson(new FileReader("C:\\Users\\arda\\Desktop\\Proje3\\Proje3\\src\\StopWords.json"), JsonArray.class);

            // JsonArray'deki her bir öğeyi String tipinde ArrayList'e ekle
            for (JsonElement element : stopWordsJsonArray) {
                stopWordsList.add(element.getAsString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        //Dosyanın adını alıyoruz
        //String dosyaAdi = "C:\\Users\\alief\\IdeaProjects\\GSON\\src\\twitter_data_en_30K.json";
        String dosyaAdi = "C:\\Users\\arda\\Desktop\\Proje3\\Proje3\\src\\twitter_data_en_30K.json";


        try {
            String tweetler = null;
            Gson gson = new Gson();
            //Aşağıdaki kodla bütün json dosyasını okuyup bir JsonArray'e attık.
            JsonArray dosyaArray = gson.fromJson(new FileReader(dosyaAdi), JsonArray.class);
            int Arda = 0;
            for(JsonElement eleman : dosyaArray)
            {
                System.out.println(Arda);
                //Tweetler isimli stringi döngünün içine koyuyorum ki her kullanıcı için sıfırlansın.
                JsonObject jsonObject = eleman.getAsJsonObject();

                //Kullanıcı verilerini tek tek alma
                userName = jsonObject.get("username").getAsString();
                name = jsonObject.get("name").getAsString();
                followersCount = jsonObject.get("followers_count").getAsInt();
                followingCount = jsonObject.get("following_count").getAsInt();
                language = jsonObject.get("language").getAsString();
                region = jsonObject.get("region").getAsString();

                //For içinde aldığımız her bilgi için bir kullanıcı oluşturuyoruz .
                //Bu da demektir ki eğer Json Dosyasında 50.000 kullanıcı varsa 50.000 tane user nesnesi üretmeliyiz.
                User kullaniciTemp = new User(userName, name, followersCount, followingCount, language, region);

                JsonArray tweets = jsonObject.getAsJsonArray("tweets");
                for(int i = 0 ; i < tweets.size() ; i++)
                {
                    tweetler = tweets.get(i).getAsString();
                    kullaniciTemp.tweets.add(tweetler);
                }
                System.out.println(Arda);

                JsonArray takipEdilenler = jsonObject.getAsJsonArray("following");
                JsonArray takipciler = jsonObject.getAsJsonArray("followers");

                for(int i = 0 ; i < takipEdilenler.size() ; i++)
                {
                    String takipEdilenKullaniciAdi = takipEdilenler.get(i).getAsString();
                    User kullanici = new User(takipEdilenKullaniciAdi);
                    kullaniciTemp.usersFollowed.add(kullanici);
                }
                System.out.println(Arda);
                for(int i = 0; i < takipciler.size() ; i++)
                {
                    String takipciKullaniciAdi = takipciler.get(i).getAsString();
                    User kullanici = new User(takipciKullaniciAdi);
                    kullaniciTemp.followers.add(kullanici);
                }
                System.out.println(Arda);


                ArrayListim<String> temizlenmisTweetler = new ArrayListim<>();

                for (int i = 0; i < kullaniciTemp.tweets.size(); i++) {
                    // İlgili indeksteki tweet'i aldık
                    String tweet = kullaniciTemp.tweets.get(i);

                    // Noktalama işaretlerini temizleme fonksiyonunu kullanarak temizledik
                    String yeniTemizString = removePunctuation(tweet);

                    // Temizlenmiş tweet'i yeni listeye ekledik
                    temizlenmisTweetler.add(yeniTemizString);
                }

                // Orijinal listeyi temizlenmiş liste ile değiştirdik
                kullaniciTemp.tweets = temizlenmisTweetler;

                Arda++; //1
                System.out.println(Arda);

                for(int i =0;i<kullaniciTemp.tweets.size();i++)
                {
                    //Bir cümle halindeki stringi alıp tempTweet adlı değişkene atıyoruz .
                    //Bu değişkeni kelime kelime ayırıp yeni bir ArrayListe atacağız.
                    String tempTweet = null;
                    tempTweet = kullaniciTemp.tweets.get(i);

                    StringTokenizer ayirici = new StringTokenizer(tempTweet);
                    while(ayirici.hasMoreTokens())
                    {
                        String kelime = ayirici.nextToken();
                        kullaniciTemp.cleanedTweets.add(kelime);
                    }
                }
                System.out.println(Arda);


//                String[] keyKelime = new String[kullaniciTemp.cleanedTweets.size()];
//                int a =0;




                //Bu aşamadan sonra keyKelime isimli array ve hashmap clear edilmeli , sıfırlanmalıdır.
                //Ama keyKelime zaten for döngüsü içinde tanımlanan lokal bir değişken olduğundan for içine tekrar girildiğinde sıfırlanmış olur.



                allUsers.put(userName , kullaniciTemp);
                kullanicilarYeniListem.add(kullaniciTemp);



//                System.out.println("Using entrySet and enhanced for loop:");
//                for (HashMapim.Entry<String, Integer> entry : frequency.entrySet()) {
//                    System.out.println(entry.getKey() + ": " + entry.getValue());
//                }
//
//                // Print using keySet and iterator
//                System.out.println("\nUsing keySet and iterator:");
//                for (String key : frequency.keySet()) {
//                    System.out.println(key + ": " + frequency.get(key));
//                }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }









        //Bütün kullanıcılar arasında graf oluşturan kod
//        Object[] keys = allUsers.keySet().toArray();
//
//        for(int i=0;i< allUsers.size();i++)
//        {
//
//            String key = (String) keys[i];
//
//            User geciciKullanici = allUsers.get(key);
//            for(int j=0;j < geciciKullanici.usersFollowed.size() ;j++)  //Takip ettikleri
//            {
//                graph.addEdge(geciciKullanici, geciciKullanici.usersFollowed.get(j));
//            }
//        }


        //Sadece girilen kullanıcı adına göre graf oluşturan kod
        System.out.println("Hangi kullanıcı için graf ve frekans bulmak istersiniz :");
        Scanner scan = new Scanner(System.in);
        String userKey = scan.nextLine();

        User geciciKullanici = null;
        for(int i=0;i< kullanicilarYeniListem.size();i++)
        {
            if(kullanicilarYeniListem.get(i).username.equals(userKey))
            {
                geciciKullanici = kullanicilarYeniListem.get(i);
                System.out.println(geciciKullanici.getName());
                break;
            }
        }

        //Kullanıcıların hashMap'e doğru atılıp atılmadığına bakmak için allUsers isimli hashMapi bastırdım.
        //for (HashMapim.Entry<String, User> entry : allUsers.entrySet()) {
        //    System.out.println(entry.getKey() + ": " + entry.getValue());
        //}

        String ilgiAlani = geciciKullanici.frekansBul();


        for (HashMapim.Entry<String, Integer> entry : geciciKullanici.frequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        MyGraph<Integer> graph = new MyGraph<>(geciciKullanici.usersFollowed.size());

        for(int i=0;i < geciciKullanici.usersFollowed.size() ;i++)  //Takip ettikleri
        {
            graph.addEdge(geciciKullanici, geciciKullanici.usersFollowed.get(i));
        }
        for (int i = 0; i < geciciKullanici.usersFollowed.size(); i++) {
            graph.addEdge(geciciKullanici, geciciKullanici.usersFollowed.get(i));
            //graph.printGraphToFile("C:\\Users\\alief\\IdeaProjects\\GSON\\src\\graf.txt");
            graph.printGraphToFile("C:\\Users\\arda\\Desktop\\Proje3\\Proje3\\src\\graf.txt");
        }

        User geciciUser = null;
        for(int i=0;i< kullanicilarYeniListem.size();i++)
        {
            if(kullanicilarYeniListem.get(i).getUsername().equals(userKey))
            {
                geciciUser = kullanicilarYeniListem.get(i);
            }
        }


        for(int j=0;j < geciciUser.usersFollowed.size() ;j++)  //Takip ettikleri
        {
            MyGraph.addEdge(geciciUser, geciciUser.usersFollowed.get(j));
            System.out.println(geciciUser.getUsername() + "----->" + geciciUser.usersFollowed.get(j).getUsername());
        }

        System.out.println();
        System.out.println();

        System.out.println("Kullanıcının ilgi alanı : " + ilgiAlani);

        NewJFrame1 AA = new NewJFrame1();

        AA.setVisible(true);




    }
}