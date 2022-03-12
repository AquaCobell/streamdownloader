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
      //TODO
      //Link-Cleaner /Es funktionieren nur Links die direkt auf den Anime zeigen, verbessern
      //GUI fertig machen
      //Advanced Download mit "Episoden Objekten"
      userinterface user = new userinterface();
      user.showMainInterface();
      
    }
}
