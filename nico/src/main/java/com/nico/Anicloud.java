package com.nico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.regex.Pattern;

public class Anicloud extends Site 
{
    
    Document doc;
    String url;
    Pattern pattern = Pattern.compile("|Staffel-\\d+", Pattern.CASE_INSENSITIVE);
    

    public Anicloud()
    {  
    }
    public Anicloud(String url)
    {
        this.url =  url;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    

    /*
    * Extrahiert alle Staffeln aus einem Link und speichert sie in einer Liste
    */
    public ArrayList<String> handleLink(String url) throws IOException 
    {
        
        
        String serie = url.substring(url.lastIndexOf("/")+1); //gibt seriennamen an
        ArrayList<String> staffelliste = new ArrayList<>();
        doc = Jsoup.connect(url).get(); //lade Seite in doc Objekt
        
        Elements links = doc.getElementsByAttribute("href"); //alle Elemente mit href attribut von doc holen
        
        for(Element contents : links) 
            {
                
                
                //Filter
                char lastChar = contents.attr("href").charAt(contents.attr("href").length() - 1 ); 
                if(Character.isDigit(lastChar)) //auf Links prüfen die zum Schluss eine Nummer haben
                {
                    
                    if(contents.attr("href").contains(serie))//Prüfen ob Link ursprüngliche Serie beeinhaltet
                    {
                        if(contents.attr("href").contains("staffel"))//Prüfen ob Link "staffel" beeinhaltet.
                        {

                            String defi = url+contents.attr("href");  
                            defi = defi.substring(defi.lastIndexOf("/")); //letzten Teil vom Link abschneidengit 
                            
                            
                            if(Pattern.matches("|/staffel-[1-9][0-9]*",defi))
                            {

                             
                                if(!staffelliste.contains(url+defi)) //wenn es noch nicht in der Liste ist
                                {
                                    staffelliste.add(url+defi);  
                                }
                            }
                            
                         
                            
                        }
                    } 
                }
            }
        return staffelliste;
    }

    /*
    * Extrahiert alle Episoden aus einer Staffel und speichert sie in einer Liste
    */
    public ArrayList<String> getDownloadList(ArrayList<String> staffelliste)
    {
        ArrayList<String> downloadliste = new ArrayList<>();
        for(String staffelseite: staffelliste)
        {
            try
            {
                
            Document site = Jsoup.connect(staffelseite).get();
            Elements links = site.getElementsByAttribute("href");
            for(Element content: links)
            {
                String defi =  content.attr("href");
                try
                {
                    defi = defi.substring(defi.lastIndexOf("/"));    
                }
                catch(Exception e)
                {
                    System.out.print("Minifehler");
                }
                System.out.println(defi);
                
                if(Pattern.matches("|/episode-[1-9][0-9]*",defi)) 

                {
                    System.out.println("Gefunden: " + defi);
                    if(!downloadliste.contains(staffelseite+defi)) 
                    {
                        downloadliste.add(staffelseite+defi);  
                    }
                } 
            }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            
        }
       
        return downloadliste;
    }


    /*
    * Extrahiert alle Episoden aus bestimmten Staffeln abhängig von Benutzereingabe
    */
    public ArrayList<String> getDetailedDownloadList(ArrayList<String> staffelliste, int vonStaffel, int bisStaffel)
    {
        ArrayList<String> downloadliste = new ArrayList<>();
        int index = 0;
        for(String staffelseite: staffelliste)
        {

            if(index>=(vonStaffel-1) && index<=(bisStaffel-1))
            {
                try
                {
                    
                    Document site = Jsoup.connect(staffelseite).get();
                    Elements links = site.getElementsByAttribute("href");
                    for(Element content: links)
                    {
                        String defi =  content.attr("href");
                        try
                        {
                            defi = defi.substring(defi.lastIndexOf("/"));    
                        }
                        catch(Exception e)
                        {
                            System.out.print("Minifehler");
                        }
                        System.out.println(defi);
                        
                        if(Pattern.matches("|/episode-[1-9][0-9]*",defi)) 

                        {
                            System.out.println("Gefunden: " + defi);
                            if(!downloadliste.contains(staffelseite+defi)) 
                            {
                                downloadliste.add(staffelseite+defi);  
                            }
                        } 
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
            
            index++;
        }
       
        return downloadliste;
    }

    @Override
    /*
    * Bekommt aus einem Link einer Episode den Link zur Weiterleitung voe
    */
    public String getDownloadLink(String url) 
    {
      
        try
        {
            Document doc = Jsoup.connect(url).get();
            Elements content = doc.getElementsByClass("watchEpisode");
            String s = "https:/aniworld.to";
            for(Element contents : content)
            {
                
                return s+ contents.attr("href");
            }
            
            
        }
        catch(Exception e)
        {
            System.out.println("Ein Fehler ist aufgetreten.");
        }
        return null;

    }

    public void downloadListe(ArrayList<String> liste)
    {
        for(String link : liste)
        {
            copypasta(getDownloadLink(link));
        }
    }

    /*
    * Öffnet DownloadLinks mit Browser und versucht dann den Link falls offen in Zwischenablage zu kopieren
    */
    public void copypasta(String url)
    {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Nico\\Desktop\\Various Stuff\\Stream Web Scrapper\\chromedriver.exe");   
        ChromeOptions options = new ChromeOptions();
       options.setExperimentalOption("detach", true);//options
       //options.setExperimentalOption("excludeSwitches","enable-logging");
       options.addArguments("--disable-blink-features=AutomationControlled");
       options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
       options.addArguments("disable-infobars");
       //options.setExperimentalOption("useAutomationExtension", null);
       options.addArguments("--remote-allow-origins=*");

       options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));    

       options.addArguments("window-size=1920,1080");
       options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
       //1options.addExtensions (new File("C:/Users/Nico/Desktop/Stream Web Scrapper/solve.crx"));
        WebDriver driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        
     
        driver.get(url);
        while(driver.getCurrentUrl().contains("aniworld"))
        {
            try 
            {
                Thread.sleep(1000);
            } 
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        
       
        System.out.println(driver.getCurrentUrl());
        copyToClipboard(driver.getCurrentUrl());
        driver.quit();
    }

    void copyToClipboard(String text) {
        java.awt.Toolkit.getDefaultToolkit().getSystemClipboard()
            .setContents(new java.awt.datatransfer.StringSelection(text), null);
    }
  
    
}
