package com.nico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller 
{
    Vidoza vid;
    Controller()
    {
        vid = new Vidoza();
    }

    public void downloadFullAnime(String Link) throws Exception
    {
        ArrayList<String> staffelliste = new ArrayList<>();
        ArrayList<String> episodenliste = new ArrayList<>();

      staffelliste = vid.handleLink(Link);
      episodenliste = vid.getDownloadList(staffelliste);
        
      episodendownloader(episodenliste);
      
    }

    public void downloadab(String link, int staffel, int episode) throws Exception
    {
        ArrayList<String> staffelliste = new ArrayList<>();
        ArrayList<String> episodenliste = new ArrayList<>();

        staffelliste = vid.handleLink(link);

        episodenliste = vid.filter(vid.getDownloadList(staffelliste), 2, 4);
        episodendownloader(episodenliste);
     
    }

    public void downloadStaffel(String link, int vonStaffel, int bisStaffel) throws Exception
    {
        ArrayList<String> staffelliste = new ArrayList<>();
        ArrayList<String> episodenliste = new ArrayList<>();
  
        staffelliste = vid.handleLink(link);
  
        
        episodenliste = vid.getDetailedDownloadList(staffelliste, 3, 3);
        episodendownloader(episodenliste);
    }

    public void episodendownloader(ArrayList<String> episodenliste) throws Exception
    {
        for (String string : episodenliste) 
      {
            String name = string.substring(string.indexOf("stream") + 7 , string.indexOf("staffel")-1 );
            System.out.println("-----name:"+ name);
            int staffelint=0; 
            int episodeint=0;

            Pattern p = Pattern.compile("\\d+");
            Pattern a = Pattern.compile("[^\\d]*[\\d]+[^\\d]+([\\d]+)");
        
            Matcher m;
        
            m = p.matcher(string);
            if(m.find()) 
            {
                staffelint = Integer.parseInt(m.group(0));
            }   

            m = a.matcher(string);
            
            if(m.find())
            {
                episodeint = Integer.parseInt(m.group(1));
            }
 
        vid.downloadEpisode(vid.directlink(vid.getDownloadLink(string)),staffelint,episodeint,name);

      }
    }
}
