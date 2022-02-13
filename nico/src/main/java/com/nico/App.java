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

        Anicloud ani = new Anicloud("https://anicloud.io/anime/stream/my-dress-up-darling");
        
        //System.out.println(ani.getDownloadLink("https://anicloud.io/anime/stream/my-dress-up-darling"));
        
        
        try 
        {
          ArrayList<String> test = ani.getDownloadList(ani.handleLink("https://anicloud.io/anime/stream/my-dress-up-darling"));
          ani.downloadListe(test);
          
        } 
        catch (Exception e) {
          //TODO: handle exception
        }
       
        System.out.println("");
      
      
      
    }
}
