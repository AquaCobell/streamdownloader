package com.nico;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Savecontroller
{
    
    Vidoza vid;
    Serienpackage serien;
    Savecontroller()

    {
        serien = new Serienpackage();
        vid = new Vidoza();
    }

    public Serienpackage getSerien() {
        return serien;
    }
        
    
    public int checkfornewEpisode(Serie serie) throws IOException
    {
        String link = serie.getLink();
        int totalepisodes = serie.getTotalEpisodesVariable();


        updateEpisodeandSeasonList(serie);
        //ArrayList<String> staffelliste = new ArrayList<>();
        //ArrayList<String> episodenliste = new ArrayList<>();
        //staffelliste = this.vid.handleLink(link);
        //episodenliste = this.vid.getDownloadList(staffelliste);


        if(serie.getEpisodenliste().size()+1>totalepisodes)
        {
            System.out.println("Found new Episodes:");
            System.out.println(serie.getEpisodenliste().size()- totalepisodes);
            return serie.getEpisodenliste().size()- totalepisodes;
        }


        return 0 ;
    }


    public ArrayList<String> checkanddownload(Serie serie) throws IOException
    {
        int anzahlmissingepisoden= checkfornewEpisode(serie);
        if(anzahlmissingepisoden!=0)
        {

          
            updateEpisodeandSeasonList(serie);
            ArrayList<String> downloadlist = new ArrayList<>();
            ArrayList<String> returndownloadlist = new ArrayList<>();
            downloadlist = serie.getEpisodenliste();
            Collections.reverse(downloadlist);
            for(int i = 0; i<anzahlmissingepisoden; i++)
            {
                System.out.println("Downloade Episode: " + downloadlist.get(i));
                returndownloadlist.add(downloadlist.get(i));
                serie.setTotalEpisodes(serie.getTotalEpisodesVariable() +1);
                
            

                
            }
            return returndownloadlist; 
        }
        return null;
    }

    public void addSerie(Serie serie) throws IOException
    {
        serie = addTotalEpisodestoSeries(serie);
        serien.addSerie(serie);
        safelist();
    }


    
    /**
     *  Speichere Serie in Datei.
     */
    public void safelist() 
    {
        // private static final String filepath="C:\\Users\\nikos7\\Desktop\\obj";
        
        try
        {   
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Nico\\Desktop\\Various Stuff\\Stream Web Scrapper\\nico\\src\\main\\java\\com\\nico\\serien\\obj");  
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serien);
            objectOut.close();  
            System.out.println("Serien gespeichert");

        }
        catch(Exception e)
        {
            System.out.println("Beim Speichern ist ein Fehler aufgetreten." + e);
        }
        
    }

    /**
     *  Lade Serie von Datei und speichere sie in Savecontroller
     */
    public void loadList()
    {
            
        try {
 
            FileInputStream fileIn = new FileInputStream("C:\\Users\\Nico\\Desktop\\Various Stuff\\Stream Web Scrapper\\nico\\src\\main\\java\\com\\nico\\serien\\obj");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
 
            this.serien = (Serienpackage) objectIn.readObject();
 
            System.out.println("Serien geladen");
            objectIn.close();
            
 
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
         
        }
    }

    /**
     * Schreibt die insgesamte Anzahl an gesehenen Serien in die totalEpisodes Variable von 
     * @param serie Serie
     */
    public Serie addTotalEpisodestoSeries(Serie serie) throws IOException
    {
        ArrayList<String> staffelliste = vid.handleLink(serie.getLink());
        ArrayList<String> episodenliste = vid.getDownloadList(staffelliste);
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
            if(erkanntestaffel == serie.getStaffel() && erkannteepisode == serie.getEpisode())
            {
                System.out.println("gefunden");
                System.out.println("ANZAHL EPISODEN: " + (counter+1));
               
                serie.setTotalEpisodes(counter+1);
                return serie;
            }
            counter++;
     
        }
        return serie;
    }

    
    public void updateEpisodeandSeasonList(Serie serie) throws IOException
    {
        serie.staffelliste = vid.handleLink(serie.getLink());
        serie.episodenliste = vid.getDownloadList(serie.staffelliste);  

        
    } 
    
}