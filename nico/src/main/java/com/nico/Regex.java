package com.nico;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Regex
{
    public static void main(String[] args) 
    {
    //string1234more567string890

    //https://aniworld.to/anime/stream/hunter-x-hunter/staffel-3/episode-5
        Pattern p = Pattern.compile("\\d+");
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










    }

}