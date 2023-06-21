package com.nico;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Regex
{
    public static void main(String[] args) 
    {
    //string1234more567string890

    //https://aniworld.to/anime/stream/hunter-x-hunter/staffel-3/episode-5
      /*   Pattern p = Pattern.compile("\\d+");
        Pattern a = Pattern.compile("[^\\d]*[\\d]+[^\\d]+([\\d]+)");

        Matcher m;
        
        m = p.matcher("https://aniworld.to/anime/stream/hunter-x-hunter/staffel-355/episode-5");
        if(m.find()) {
            System.out.println(m.group(0));
        }   

        m = a.matcher("https://aniworld.to/anime/stream/hunter-x-hunter/staffel-355/episode-5");
        if(m.find())
        {
            System.out.println(m.group(1));
        }
        */
        

        String s = "https://aniworld.to/anime/stream/black-clover/staffel-1/episode-1";
        Pattern p = Pattern.compile(".+\\/staffel-(\\d+)\\/episode-(\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher m;

        int episode = 5;
         m= p.matcher(String.valueOf(s));
         System.out.println(m.find());
        System.out.println("Gruppe 1: " + m.group(0));
        System.out.println("Gruppe 2: " +m.group(1));
        System.out.println("Gruppe 3: " +m.group(2));
        System.out.println("tst");

        












    }

}