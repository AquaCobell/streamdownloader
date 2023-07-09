package com.nico;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Tray 
{
    Controller c;
    Clipboardreader clip;
    GUI gui = new GUI();
    Tray()
    {
        c = new Controller();
        clip = new Clipboardreader();
    }

    public void loadTray() throws AWTException
    {
        TrayIcon trayIcon = null;
        if(SystemTray.isSupported())
        {
            SystemTray tray = SystemTray.getSystemTray();
            //Image image = ImageIO.read(TrayIconService.class.getResourceAsStream("/path/to/your/icon.png"));
            Image image = Toolkit.getDefaultToolkit().getImage("/images/image.png");
            
            
            PopupMenu popup = new PopupMenu();

            

            MenuItem downloadFullAnime= new MenuItem("Download Full Anime");
            MenuItem downloaddetailedAnime= new MenuItem("Download from Season & Episode");
            MenuItem downloadSeasonAnime= new MenuItem("Download from Season to Season");
            MenuItem addSerie = new MenuItem("Neue Serie hinzufügen");
            MenuItem refreshEpisode = new MenuItem("Episoden refreshen");
            MenuItem exit = new MenuItem("Exit");

            exit.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    System.exit(0);
                }
                
            });

            downloadFullAnime.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    try {
                        c.downloadFullAnime(clip.getClipboard());
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    
                }
                
            });

            addSerie.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    ArrayList<Integer> eingaben = gui.enterSeasonEpisode();
                    try 
                    {
                        Serie serie = new Serie(clip.getClipboard(),eingaben.get(1), eingaben.get(0));
                        c.newSeries(serie);
                    } 
                    catch (Exception e1) 
                    {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    
                }
                
            });

            downloaddetailedAnime.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    ArrayList<Integer> eingaben = gui.enterSeasonEpisode();
                    try 
                    {
                        c.downloadab(clip.getClipboard(),eingaben.get(0) , eingaben.get(1));
                    } 
                    catch (Exception e1) 
                    {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    
                }
                
            });

           refreshEpisode.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) 
                {
                   
                    try 
                    {
                        c.RefreshEpisodes();
                    } 
                    catch (Exception e1) 
                    {
                        System.out.println("Es ist ein Fehler aufgetreten" +e1);
                    }
                    
                }
                
            });


            popup.add(downloadFullAnime);
            popup.add(downloaddetailedAnime);
            popup.add(downloadSeasonAnime);
            popup.add(addSerie);
            popup.add(refreshEpisode);
            popup.add(exit);





            trayIcon = new TrayIcon(image, "Test",popup);

            tray.add(trayIcon);
    
            //https://stackoverflow.com/questions/758083/how-do-i-put-a-java-app-in-the-system-tray
            
           

    

        } 
        else
        {
            System.out.println("error");
        }
        
    }
}
