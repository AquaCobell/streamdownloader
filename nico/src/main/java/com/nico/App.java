package com.nico;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        //new picocli.CommandLine(new CLI()).execute(args);
      
        
        //GUI gui = new GUI();
        //gui.enterLink();

        //gui.enterSeasonEpisode();
        
        //Vidoza vid = new Vidoza();
        
        Tray tray = new Tray();
        tray.loadTray();

        

        //Serie test = new Serie("https://aniworld.to/anime/stream/black-clover", 10, 2);
        //test.setEpisode(5);
        //test.setStaffel(2);

        //test.checkfornewEpisode(vid);
        //test.getTotalEpisodes(5, 2, vid);
        //Savecontroller save = new Savecontroller();
        //System.out.println(save.checkfornewEpisode(test));

        //save.checkanddownload(test);

        











        //Thread.sleep(10000);








      /*Vidoza vid = new Vidoza();


      ArrayList<String> staffelliste = new ArrayList<>();
      ArrayList<String> episodenliste = new ArrayList<>();

      staffelliste = vid.handleLink("https://aniworld.to/anime/stream/the-eminence-in-shadow");

      //episodenliste = vid.filter(vid.getDownloadList(staffelliste), 2, 4);
      episodenliste = vid.getDownloadList(staffelliste);
      //episodenliste = vid.getDetailedDownloadList(staffelliste, 3, 3);
      
      for (String string : episodenliste) 
      {
        
        //https://aniworld.to/anime/stream/hunter-x-hunter/staffel-3/episode-3




        

        
        //String staffel = string.substring(string.indexOf("staffel-") + 8 , string.indexOf("staffel-")+9);
        //String episode = string.substring(string.indexOf("episode-") + 8 , string.indexOf("episode-")+9);
        String name = string.substring(string.indexOf("stream") + 7 , string.indexOf("staffel")-1 );
        //System.out.println("-----staffel= "+staffel);
        //System.out.println("-----episode= "+episode);
        System.out.println("-----name:"+ name);
        //int staffelint = Integer.parseInt(staffel);
        //int episodeint = Integer.parseInt(episode);
        
        
        
        
        int staffelint=0; 
        int episodeint=0;

        Pattern p = Pattern.compile("\\d+");
        Pattern a = Pattern.compile("[^\\d]*[\\d]+[^\\d]+([\\d]+)");

        Matcher m;
        
        m = p.matcher(string);
        if(m.find()) {
            staffelint = Integer.parseInt(m.group(0));
        }   

        m = a.matcher(string);
        if(m.find())
        {
            episodeint = Integer.parseInt(m.group(1));
        }

        
         
        vid.downloadEpisode(vid.directlink(vid.getDownloadLink(string)),staffelint,episodeint,name );
      }
      */
    }
}
