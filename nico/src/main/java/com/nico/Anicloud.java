package com.nico;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.regex.Matcher;
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

    @Override
    public String getDownloadLink(String url) 
    {
      
        try
        {
            Document doc = Jsoup.connect(url).get();
            Elements content = doc.getElementsByClass("watchEpisode");
            String s = "https://anicloud.io";
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

    public void copypasta(String url)
    {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("detach", true);
        WebDriver driver = new ChromeDriver(options);
        
     
        driver.get(url);
        //while(!driver.getCurrentUrl().contains("voe"))
        try 
        {
            Thread.sleep(5000);
        } 
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
