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

        Anicloud ani = new Anicloud("https://anicloud.io/anime/stream/attack-on-titan");
        
        ArrayList<String> test = ani.getDownloadList(ani.handleLink("test"));
        System.out.println("");

        //String link = "https://anicloud.io/anime/stream/attack-on-titan/staffel-1/episode-1";
        //link = link.substring(link.lastIndexOf("/"));
        //System.out.println(link);
        
        
        
        //ani.getDownloadLink("https://anicloud.io/anime/stream/attack-on-titan/staffel-1/episode-1");
        //ani.copypasta("https://anicloud.io/redirect/748955");
        //System.out.println("Test");
        
       
      

        //driver.quit();
            
        
    }
}
