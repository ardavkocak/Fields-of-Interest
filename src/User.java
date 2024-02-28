public class User {

    //HER KULLANICININ BİR İLGİ ALANI DA OLACAK.
    //ÖRNEĞİN KULLANICININ TWEETLERİNDE EN ÇOK KULLANDIĞI 5 KELİME ONUN 5 FARKLI ALANDAKİ İLGİSİNİ GÖSTERECEK.
    String username;
    String name;
    int followingCount;
    int followersCount;
    String language;
    String region;
    ArrayListim<String> tweets;
    ArrayListim<String> cleanedTweets;
    ArrayListim<User> usersFollowed;
    ArrayListim<User> followers;
    HashMapim<String , Integer> frequency;
    String ilgiAlani;

    public User()
    {

    }

    //Json dosyasından takip edilen veya takipçi kullanıcıları okurken onların sadece kullanıcı adını görebiliyoruz .
    //Bu kullanıcılar adına bir nesne oluşturabilmek için bu constructor'ı yazdım çünkü elimizdeki tek veri username.
    public User(String username)
    {
        this.tweets = new ArrayListim<String>();
        this.usersFollowed = new ArrayListim<User>();
        this.followers = new ArrayListim<User>();
        this.username = username;
    }
    public User(String username , String name , int followingCount , int followersCount , String language , String region)
    {
        this.cleanedTweets = new ArrayListim<String>();
        this.tweets = new ArrayListim<String>();
        this.usersFollowed = new ArrayListim<User>();
        this.followers = new ArrayListim<User>();
        this.username = username;
        this.name = name;
        this.followingCount = followingCount;
        this.followersCount = followersCount;
        this.language = language;
        this.region = region;
    }

    public String frekansBul()
    {
        String[] keyKelime = new String[cleanedTweets.size()];
        int a =0;

        frequency = new HashMapim<>();
        //Tweet içinde geçen her kelime ve o kelimenin frekansı ,
        //yani kaç kez geçtiği anahtar-değer ikilisi şeklinde bu HashTable içinde tutulacak
        for (int i = 0; i < cleanedTweets.size(); i++) {
            String kelime = cleanedTweets.get(i);
            boolean isStopWord = false;

            for (int j = 0; j < Main.stopWordsList.size(); j++) {
                if (Main.stopWordsList.get(j).equalsIgnoreCase(kelime)) {
                    isStopWord = true;
                    break;
                }
            }

            if (!isStopWord) {
                if( frequency.containsKey(kelime)) {
                    frequency.put(kelime, frequency.get(kelime) + 1);
                } else {
                    frequency.put(kelime, 1);
                    keyKelime[a] = kelime;
                    a++;
                }
            }
        }

        int encok =0;
        String tekrarEden = null;
        for(int i = 0 ; i < a ; i++)
        {
            String s = keyKelime[i];
            if(frequency.get(s) > encok)
            {
                encok = frequency.get(s);
                tekrarEden = s;
            }
        }

        ilgiAlani = tekrarEden;

        return ilgiAlani;

        }

    //username GetterSetters
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getUsername()
    {
        return username;
    }

    //name GetterSetters
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }

    //followingCount GetterSetters
    public void setFollowingCount(int followingCount)
    {
        this.followingCount = followingCount;
    }
    public int getFollowingCount()
    {
        return followingCount;
    }

    //followersCount GetterSetters
    public void setFollowersCount(int followersCount)
    {
        this.followersCount = followersCount;
    }
    public int getFollowersCount()
    {
        return followersCount;
    }

    //language GetterSetters
    public void setLanguage(String language)
    {
        this.language = language;
    }
    public String getLanguage()
    {
        return language;
    }

    //region GetterSetters
    public void setRegion(String region)
    {
        this.region = region;
    }
    public String getRegion()
    {
        return region;
    }







}
