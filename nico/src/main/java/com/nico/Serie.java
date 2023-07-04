package com.nico;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Serie implements Serializable
{
    
    Serie(String link, int Episode, int Staffel) throws IOException
    {
        //vid = new Vidoza();
        this.link = link;
        this.Episode = Episode;
        this.Staffel = Staffel;
       

        staffelliste = new ArrayList<>();
        episodenliste = new ArrayList<>();
        
    }

    //Vidoza vid;
    String link;
    int Episode;
    int Staffel;
    int totalEpisodes;

    ArrayList<String> staffelliste;
    ArrayList<String> episodenliste;
    

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

    public int getTotalEpisodesVariable()
    {
        return this.totalEpisodes;
    }

    public ArrayList<String> getStaffelliste() {
        return staffelliste;
    }

    public ArrayList<String> getEpisodenliste() {
        return episodenliste;
    }

    public void setTotalEpisodes(int totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }


    /*  Updates Episoden and Staffelliste und gibt die insgesamte Anzahl an Episoden an die noch übrig sind.
     * 
     * 
     */
    /*public int getTotalEpisodes(int episode, int staffel) throws IOException
    {
        //String s = "https://aniworld.to/anime/stream/beyblade-burst-surge/staffel-1/episode-14";

        //"https://aniworld.to/anime/stream/black-clover/staffel-1/episode-1"
        //ArrayList<String> staffelliste = new ArrayList<>();
        //ArrayList<String> episodenliste = new ArrayList<>();
        
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
                return(counter+1);
            }
            counter++;
     
        }
        return 0;
    }
    */

   /*  public void updateEpisodeandSeasonList() throws IOException
    {
        staffelliste = vid.handleLink(this.link);
        episodenliste = vid.getDownloadList(staffelliste);  
    }
    */


    
}
