package com.nico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller 
{
    Vidoza vid;
    Savecontroller savecontroller;
    Controller()
    {
        vid = new Vidoza();
        savecontroller = new Savecontroller();
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

        episodenliste = vid.filter(vid.getDownloadList(staffelliste), staffel, episode);
        episodendownloader(episodenliste);
     
    }

    public void downloadStaffel(String link, int vonStaffel, int bisStaffel) throws Exception
    {
        ArrayList<String> staffelliste = new ArrayList<>();
        ArrayList<String> episodenliste = new ArrayList<>();
  
        staffelliste = vid.handleLink(link);
  
        
        episodenliste = vid.getDetailedDownloadList(staffelliste, vonStaffel, bisStaffel);
        episodendownloader(episodenliste);
    }

    public void episodendownloader(ArrayList<String> episodenliste) throws Exception
    {
        for (String string : episodenliste) 
      {
            //Generiere Name für die Episode
            String name = string.substring(string.indexOf("stream") + 7 , string.indexOf("staffel")-1 ); //Bekomme name aus URL
            System.out.println("-----name:"+ name);
            int staffelint=0; 
            int episodeint=0;

            Pattern p = Pattern.compile("\\d+"); //staffel
            Pattern a = Pattern.compile("[^\\d]*[\\d]+[^\\d]+([\\d]+)"); //episode
        
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
 
        vid.downloadEpisode(vid.directlink(vid.getDownloadLinkSelenium(string)),staffelint,episodeint,name);

      }
    }

    public void RefreshEpisodes() throws Exception
    {
        //loadSeries()
        savecontroller.loadList();
        savecontroller.getSerien().getSerien();
        
       
        for(Serie serie: savecontroller.getSerien().getSerien())
        {
            episodendownloader(savecontroller.checkanddownload(serie));
        }
        //savecontroller.load()
        //for each()
        //....
        //episodendownloader(null);
    }

    public void newSeries(Serie serie)
    {
        try {
            savecontroller.addSerie(serie);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }




}
