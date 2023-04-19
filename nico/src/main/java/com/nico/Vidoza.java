package com.nico;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator.Id;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


public class Vidoza extends Site 
{
    
    Document doc;
    String url;
    Pattern pattern = Pattern.compile("|Staffel-\\d+", Pattern.CASE_INSENSITIVE);
    

    public Vidoza()
    {  
    }
    public Vidoza(String url)
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

    public ArrayList<String> filter (ArrayList<String> episodenliste, int staffel, int folge)
    {

        ArrayList<String> neueEpisodenliste = new ArrayList<>();
        int aktuellestaffel =0;
        int aktuellefolge =0;
        for(String temp : episodenliste)
        {


            Pattern p = Pattern.compile("\\d+");
            Pattern a = Pattern.compile("[^\\d]*[\\d]+[^\\d]+([\\d]+)");
    
            Matcher m;
            
            m = p.matcher(temp);
            if(m.find()) {
                aktuellestaffel = Integer.parseInt(m.group(0));
            }   
    
            m = a.matcher(temp);
            if(m.find())
            {
                aktuellefolge = Integer.parseInt(m.group(1));
            }

            //aktuellestaffel = Integer.parseInt(temp.substring( temp.indexOf("staffel-") + 8 ,  temp.indexOf("staffel-")+9));
            //aktuellefolge = Integer.parseInt(temp.substring(temp.indexOf("episode-") + 8 , temp.indexOf("episode-")+9));

            //System.out.println(aktuellestaffel);
            //System.out.println(aktuellefolge);
            

            if(aktuellestaffel >= staffel)
            {


                if(aktuellestaffel == staffel)
                {
                    if(aktuellefolge>=folge)
                    {
                        neueEpisodenliste.add(temp);
                    }
                }
                else
                {
                    neueEpisodenliste.add(temp);
                }
            }
        }
        return  neueEpisodenliste;
        
        //String staffel = string.substring(string.indexOf("staffel-") + 8 , string.indexOf("staffel-")+9);
        //String episode = string.substring(string.indexOf("episode-") + 8 , string.indexOf("episode-")+9);

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
            ArrayList<String> liste = new ArrayList<>();
            int counter = 0;
            for(Element contents : content)
            {
                //System.out.println( s + contents.attr("href"));

                liste.add(counter, s + contents.attr("href"));
                counter = counter +1;
                

                //return s+ contents.attr("href");
            }
                                        //0 is VOE
                                        //1 is dodd.y
            return liste.get(2); //2 is VIDOZA
            
            
        }
        catch(Exception e)
        {
            System.out.println("Ein Fehler ist aufgetreten." + e);
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
       options.addArguments("--remote-allow-origins=*");  
       options.addArguments("--disable-blink-features=AutomationControlled");
       options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
       options.addArguments("disable-infobars");
       options.setExperimentalOption("useAutomationExtension", null);
       options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));  
         

       options.addArguments("window-size=1920,1080");
       options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
       options.addExtensions (new File("C:/Users/Nico/Desktop/Stream Web Scrapper/solve.crx"));
        WebDriver driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        
     
        driver.get(url);


        int counter = 0;
        while(driver.getCurrentUrl().contains("aniworld"))
        {

            counter =+1;

            try 

            {
                Thread.sleep(1000);
            } 
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            if(counter == 4)
            {
                
               
            }
        }
        
       
        System.out.println(driver.getCurrentUrl());
        copyToClipboard(driver.getCurrentUrl());
        driver.quit();
    }



    public String directlink(String url)
    {


        Robot robot = null;
        try
        {
            robot = new Robot();
        }
        catch(Exception e)
        {
            System.out.println("Robot error");

        }
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Nico\\Desktop\\Various Stuff\\Stream Web Scrapper\\chromedriver.exe");   
        ChromeOptions options = new ChromeOptions();
       options.setExperimentalOption("detach", true);//options
       //options.setExperimentalOption("excludeSwitches","enable-logging");
       options.addArguments("--disable-blink-features=AutomationControlled");
       options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
       options.addArguments("disable-infobars");
       options.setExperimentalOption("useAutomationExtension", null);
       options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));    

       options.addArguments("window-size=500,800");
       options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
       options.addExtensions (new File("C:/Users/Nico/Desktop/Various Stuff/Stream Web Scrapper/solve.crx"));
        WebDriver driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        
        
        driver.get(url);


        
        int counter = 0;
        while(driver.getCurrentUrl().contains("aniworld"))
        {

            counter = counter +1;

            try 
            {
                Thread.sleep(1000);
            } 
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            if(counter == 4)
            {
              
                /*try
                {

                    WebElement iframe = driver.findElement(By.cssSelector("iframe[title='recaptcha challenge expires in two minutes']"));
                    driver.switchTo().frame(2);
                    WebElement button = driver.findElement(By.id("solver-button"));
                    button.click();
                }
                catch(org.openqa.selenium.NoSuchElementException e)
                {
                    System.out.println("Kein Chaptcha Solver gefunden");
                    System.out.println(e);
                    System.out.println("gg");
                }*/

                try
                {
                    robot.mouseMove(190,755);
                    
                    //WebElement button = driver.findElement(By.id("solver-button"));
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease( InputEvent.BUTTON1_MASK );
                    Thread.sleep(5000);

                }
                catch(NoSuchElementException e)
                {
                    System.out.println("Kein Chaptcha Solver gefunden");
                }
                catch(Exception e)
                {
                    
                }   
                driver.navigate().refresh();
            } 
        }
        System.out.println(driver.getCurrentUrl());
        String originaurl = driver.getCurrentUrl();
       
        
        driver.quit();
        return originaurl;
    }

    void copyToClipboard(String text) 
    {
        java.awt.Toolkit.getDefaultToolkit().getSystemClipboard()
            .setContents(new java.awt.datatransfer.StringSelection(text), null);
    }
  


    void downloadEpisode(String link, int season, int episode, String name) throws Exception
    {
        ProcessBuilder builder = new ProcessBuilder(
          "cmd.exe", "/c", "cd \"C:\\Users\\Nico\\Desktop\\seriendownloader\" && yt-dlp " + link + " -o \"" + name+ " S"+season + " E"+ episode +"\"" + ".mp4");

          
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) { break; }
                System.out.println(line);
            
        }









    }


    
}