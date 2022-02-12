package com.nico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        /*System.out.println( "Video-Scrapper" );
        System.out.println("-----------------------------");
        System.out.println("Link:");
        Scanner scan = new Scanner(System.in);
        String link = scan.nextLine();
        System.out.println(link);

      */

        /*Anicloud ani = new Anicloud("https://anicloud.io/anime/stream/attack-on-titan");

        //System.out.println(ani.getDownloadLink("https://anicloud.io/anime/stream/attack-on-titan/staffel-1/episode-1"));
        
        
        try 
        {
          ArrayList<String> test = ani.getDownloadList(ani.handleLink("https://anicloud.io/anime/stream/attack-on-titan"));
          ani.downloadListe(test);
          
        } 
        catch (Exception e) {
          //TODO: handle exception
        }
       
        System.out.println("");
      
      */
      ChromeOptions options = new ChromeOptions();
      options.setExperimentalOption("detach", true);
      WebDriver driver = new ChromeDriver(options);
      
   
      driver.get("google.at");
      try {
          Thread.sleep(5000);
      } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
            
        
    }
}
