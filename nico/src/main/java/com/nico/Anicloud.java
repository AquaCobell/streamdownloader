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


    public void handleLink(String url) 
    {
        
        //System.out.println(this.url.substring(this.url.lastIndexOf("/")+1));
        String serie = this.url.substring(this.url.lastIndexOf("/")+1);
        int staffelnummer = 0;
        int folgennummer = 0;
        ArrayList<String> staffelliste = new ArrayList<>();
        ArrayList<String> folgenliste = new ArrayList<>();
        
        Elements links = doc.getElementsByAttribute("href");
        //https://anicloud.io/anime/stream/attack-on-titan
        for(Element contents : links)
            {
                
                //System.out.println(contents.attr("href"));
                //Filter
                char lastChar = contents.attr("href").charAt(contents.attr("href").length() - 1 );
                if(Character.isDigit(lastChar))
                {
                    //System.out.println(this.url+contents.attr("href"));
                    //System.out.println(this.url);
                    if(contents.attr("href").contains(serie))
                    {
                        if(contents.attr("href").contains("staffel"))
                        {
                           // Matcher matcher = pattern.matcher(contents.attr("href"));
                            //boolean matchfound = matcher.f;
                            
                            //System.out.println( this.url+contents.attr("href"));
                            //System.out.println(this.url+contents.attr("href").substring(this.url.lastIndexOf("/")));
                            System.out.println("gehe zu seite: " + this.url+contents.attr("href") );
                            String defi = this.url+contents.attr("href");
                            defi = defi.substring(defi.lastIndexOf("/"));
                            
                            //System.out.println(defi);
                            if(Pattern.matches("|/staffel-\\d+",defi))
                            {
                                //this.url.substring(this.url.lastIndexOf("/")+1)
                                //System.out.println(contents.attr("href"));
                                //System.out.println(this.url+contents.attr("href"));
                                //while
                                if(!staffelliste.contains(this.url+contents.attr("href"))) //wenn 
                                {
                                    staffelliste.add(this.url+contents.attr("href"));
                                }
                                
                               

                                
                                //Document doc = Jsoup.connect(url).get();
                                //staffelnummer++;
                            }
                            
                            for(String staffel: staffelliste )
                            {
                                try
                                {
                                    Document doc = Jsoup.connect(staffel).get();
                                    Elements site = doc.getElementsByAttribute("href");
                                    for(Element seite : site)
                                    {
                                        System.out.println(site.attr("href")); 
                                    }

                                }
                                catch(Exception e)
                                {

                                }
                                
                            }
                            /*else if(Pattern.matches("|/episode-\\d",defi))
                            {
                                System.out.println(this.url+contents.attr("href"));
                                //folgennummer++;
                            }*/
                            
                        }
                    } 
                }
            }

            //System.out.println(staffelnummer);
            //System.out.println(folgennummer);
    }
    @Override
    public String getDownloadLink(String url) {
        
        try
        {
            Document doc = Jsoup.connect(url).get();
            Elements content = doc.getElementsByClass("watchEpisode");
            //Elements links = doc.select("a[href]");

            for(Element contents : content)
            {
                System.out.println(contents.attr("href"));
            }
            
            
        }
        catch(Exception e)
        {

        }
        return null;

    }

    public void copypasta(String url)
    {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("detach", true);
        WebDriver driver = new ChromeDriver(options);
        
     
        driver.get("https://anicloud.io/redirect/748955");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        System.out.println(driver.getCurrentUrl());
        copyToClipboard(driver.getCurrentUrl());
    }

    void copyToClipboard(String text) {
        java.awt.Toolkit.getDefaultToolkit().getSystemClipboard()
            .setContents(new java.awt.datatransfer.StringSelection(text), null);
    }
    
    
}
