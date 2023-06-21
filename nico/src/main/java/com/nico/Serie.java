package com.nico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Serie 
{
    
    Serie(String link, int Episode, int Staffel)
    {
        this.link = link;
        this.Episode = Episode;
        this.Staffel = Staffel;
        
    }

    String link;
    int Episode;
    int Staffel;
    int totalEpisodes;

    public int getEpisode() {
        return Episode;
    }

    public int getStaffel() {
        return Staffel;
    }

    public String getLink() {
        return link;
    }

    public void setEpisode(int episode) {
        Episode = episode;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setStaffel(int staffel) {
        Staffel = staffel;
    }

    public void getTotalEpisodes(int episode, int staffel, Vidoza vid) throws IOException
    {
        //String s = "https://aniworld.to/anime/stream/beyblade-burst-surge/staffel-1/episode-14";

        //"https://aniworld.to/anime/stream/black-clover/staffel-1/episode-1"
        ArrayList<String> staffelliste = new ArrayList<>();
        ArrayList<String> episodenliste = new ArrayList<>();
        staffelliste = vid.handleLink(this.link);
        episodenliste = vid.getDownloadList(staffelliste);
        Pattern p = Pattern.compile(".+\\/staffel-(\\d+)\\/episode-(\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher m;
          
        int counter = 0;
        while(counter < episodenliste.size()) //String.valueOf(episodenliste.get(counter))
        {
            m= p.matcher(episodenliste.get((counter)));
            System.out.println(m.find());
            int erkanntestaffel = Integer.parseInt(m.group(1));
            int erkannteepisode =  Integer.parseInt(m.group(2));

            System.out.println("gefunden:  S" + erkanntestaffel + "E" + erkannteepisode);
            if(erkanntestaffel == staffel && erkannteepisode == episode)
            {
                System.out.println("gefunden");
                System.out.println("ANZAHL EPISODEN: " + (counter+1));
                return;
            }
            counter++;
     
        }



        
         
        
     

       
       
        
        //System.out.println("Gruppe 1: " + m.group(0));
        //System.out.println("Gruppe 2: " +m.group(1));
        //System.out.println("Gruppe 3: " +m.group(2));
        //System.out.println("tst");

        

        



        
            

        //while(true)
        //{
        //    m= p.matcher(String.valueOf(episode));
           
        //}
        
        
        /*for (String episode : episodenliste) 
        {

        }*/
        
        
        
        
        //int counterseason = 0;
        //int counterepisode = 0 ;

       
        /*while(counterseason < season )
        {

            while(counterepisode < episode)
        }*/

    }


    public String checkfornewEpisode(Vidoza vid)
    {
        
        ArrayList<String> staffelliste = new ArrayList<>();
        ArrayList<String> episodenliste = new ArrayList<>();


        
        {

        }
        try
        {
            staffelliste = vid.handleLink(this.link);
            episodenliste = vid.getDownloadList(staffelliste);
            System.out.println("debug");
        }
        catch(Exception e)
        {


        }   
        return "";
    }
}
